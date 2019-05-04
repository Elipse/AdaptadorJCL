package com.mycompany.c04.detalles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.c02.casosdeuso.AlmacenMemberI;
import com.mycompany.c02.casosdeuso.AdaptaMembersCasoDeUso;
import com.mycompany.c02.casosdeuso.ComparaMembersCasoDeUso;
import com.mycompany.c02.casosdeuso.Editor;
import com.mycompany.c03.adaptadores.AdaptaMembersPresentador;
import com.mycompany.c03.adaptadores.ComparaMembersPresentador;
import com.mycompany.c04.detalles.editores.INFOPRO_INFODES_CARD;
import com.mycompany.c04.detalles.editores.INFOPRO_INFODES_DISP;
import com.mycompany.c04.detalles.editores.INFOPRO_INFODES_PROC;
import com.mycompany.c04.detalles.editores.Reemplazo;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import mx.com.eixy.utilities.zos.ftp.FTPClientFactory;
import mx.com.eixy.utilities.zos.ftp.FTPServer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import com.mycompany.c02.casosdeuso.EditorFabrica;
import mx.com.eixy.utilities.zos.ftp.DataSetDefinition;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.c04.detalles", "com.mycompany.c04.detalles.editores"})
@PropertySources({
    /*@PropertySource("file:${user.dir}/app.properties")})  */
    @PropertySource("file:${user.dir}/app.properties")})

public class AdaptadorConfig {

    @Autowired
    private Environment environment;

    private static final String FOLDER = System.getProperty("user.dir");

    public static final String ARCHIVO_PDS = FOLDER + "/_jsons/pds.json";
    public static final String ARCHIVO_TIPOS_FTP = FOLDER + "/_jsons/tipos.json";
    public static final String ARCHIVO_ICON_PROCESO = FOLDER + "/_icons/process_on_32.png";
    public static final String FOLDER_TMP = FOLDER + "/_temp/";

    // This method to resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("#{'${directorio.reemplazos}' + '\\'}")
    private String directorioDeReemplazos;
    @Value("#{'${charset.default}'}")
    private String charsetDefault;

    @Autowired
    List<FTPServer> listaDeServidoresFTP;

    @Bean
    public DataSetDefinition dataSetDefinition() {
        DataSetDefinition dataSetDefinition = DataSetDefinition.newDataDefinition()
                .setDsname("")
                .setDirectorySize("di=900")
                .setRecordFormat("rec=fb")
                .setRecordLength("lr=080")
                .setBlkSize("blksize=32720")
                .setSpaceUnit("cy")
                .setPrimarySpace("pri=10")
                .setSecondarySpace("sec=5");
        return dataSetDefinition;
    }

    @Bean
    public FTPClientFactory ftpClientFactory() {
        listaDeServidoresFTP.stream().forEach(elemento -> {
            System.out.println("eles->listaDeServidoresFTP " + elemento.getServerName());
        });
        return new FTPClientFactory(listaDeServidoresFTP);
    }

    @Autowired
    AlmacenMemberI almacenMember;
    @Autowired
    EditorFabrica fabricaEditor;

    @Bean
    public AdaptaMembersPresentador newAdaptaMembersPresentador() {
        return new AdaptaMembersPresentador();
    }

    @Bean
    public AdaptaMembersCasoDeUso newAdaptaMembersCasoDeUso() {
        return new AdaptaMembersCasoDeUso(almacenMember, fabricaEditor);
    }

    @Bean
    public ComparaMembersPresentador newComparaMembersPresentador() {
        return new ComparaMembersPresentador();
    }

    @Bean
    public ComparaMembersCasoDeUso newComparaMembersCasoDeUso() {
        return new ComparaMembersCasoDeUso(almacenMember);
    }

    @Configuration
    static class ServidoresConfig {

        @Autowired
        private Environment environmentKid;

        @Bean
        public List<FTPServer> listaDeServidoresFTP() {

            Gson gson = new Gson();

            TypeToken<List<FTPServer>> token = new TypeToken<List<FTPServer>>() {
            };

            String archivoServidoresFTP = environmentKid.getProperty("servidores.ftp");

            List<FTPServer> lista;
            try {
                lista = gson.fromJson(new FileReader(archivoServidoresFTP), token.getType());
                return lista;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
            }

            return new ArrayList<>();
        }
    }

   
    @Bean
    public Map<String, List<Reemplazo>> mapaDeReemplazos() {

        Map<String, List<Reemplazo>> reemplazos = new HashMap<>();

        try (Stream<Path> list = Files.list(Paths.get(directorioDeReemplazos))) {
            list.forEach(path -> {

                String json = null;

                File file = path.toFile();
                String fileName = path.getFileName().toString();

                System.out.println("cargando las reglas de " + fileName);

                try {
                    json = FileUtils.readFileToString(path.toFile(), charsetDefault);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                reemplazos.put(StringUtils.substringBefore(fileName, "."), deJsonAReemplazos(json));
            });
        } catch (IOException ex) {
            Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reemplazos;
    }

    private List<Reemplazo> deJsonAReemplazos(String json) {
        Gson gson = new Gson();

        TypeToken<List<Reemplazo>> token = new TypeToken<List<Reemplazo>>() {
        };

        return gson.fromJson(json, token.getType());
    }

    @Autowired
    INFOPRO_INFODES_CARD infopro_infodes_card;
    @Autowired
    INFOPRO_INFODES_PROC infopro_infodes_proc;
    @Autowired
    INFOPRO_INFODES_DISP infopro_infodes_disp;

    @Bean
    public Map<String, Editor> mapaDeEditores() {

        Map<String, Editor> mapaDeEditores = new HashMap<>();

        mapaDeEditores.put(infopro_infodes_card.getClass().getSimpleName(), infopro_infodes_card);
        mapaDeEditores.put(infopro_infodes_proc.getClass().getSimpleName(), infopro_infodes_proc);
        mapaDeEditores.put(infopro_infodes_disp.getClass().getSimpleName(), infopro_infodes_disp);

        return mapaDeEditores;
    }

}

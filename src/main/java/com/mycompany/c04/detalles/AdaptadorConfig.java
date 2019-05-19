package com.mycompany.c04.detalles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.c02.casosdeuso.AlmacenMemberI;
import com.mycompany.c02.casosdeuso.AdaptaMembersCasoDeUso;
import com.mycompany.c02.casosdeuso.ComparaMembersCasoDeUso;
import com.mycompany.c02.casosdeuso.Editor;
import com.mycompany.c03.adaptadores.AdaptaMembersPresentador;
import com.mycompany.c03.adaptadores.ComparaMembersPresentador;
import com.mycompany.c04.detalles.editor.Reemplazo;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import com.mycompany.c03.adaptadores.AdaptaMembersControlador;
import com.mycompany.c04.detalles.editor.EditorEstandar;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import mx.com.eixy.utilities.zos.ftp.DataSetDefinition;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.c04.detalles", "com.mycompany.c04.detalles.editores"})
@PropertySources({
    /*@PropertySource("file:${user.dir}/app.properties")})  */
    /*@PropertySource("file:${user.dir}/app.properties")})*/
    @PropertySource("classpath:app.properties")})

public class AdaptadorConfig {

    @Autowired
    private Environment environment;

    private static final URL TARGET_FOLDER = AdaptadorConfig.class.getClassLoader().getResource(".");

    // This method to resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("#{'${directorio.origen}' + '\\'}")
    private String directorioOrigen;
    @Value("#{'${directorio.destino}' + '\\'}")
    private String directorioDestino;
    @Value("#{'${charset.default}'}")
    private String charsetDefault;

    @Autowired
    List<FTPServer> listaDeServidoresFTP;

    @Bean
    public DataSetDefinition dataSetDefinition() {
        String propertiesFile = environment.getProperty("biblioteca.properties.file");
        DataSetDefinition dataSetDefinition = createBeanFromPropertiesFile(propertiesFile, DataSetDefinition.class);
        return dataSetDefinition;
    }

    private static <T extends Object> T createBeanFromPropertiesFile(String propertiesFile, Class<T> claz) {
        try {
            InputStream inputStream = AdaptadorConfig.class.getClassLoader().getResourceAsStream(propertiesFile);
            Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile);
            Properties properties = new Properties();
            properties.load(inputStream);

            T bean = claz.newInstance();

            BeanWrapper wrapper = new BeanWrapperImpl(bean);
            properties.entrySet().forEach((entry) -> {
                String propertyName = entry.getKey().toString();
                if (wrapper.isWritableProperty(propertyName)) {
                    wrapper.setPropertyValue(propertyName, entry.getValue());
                }
            });

            return bean;
        } catch (IOException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Bean
    public String directorioOrigen() {
        String tmp = TARGET_FOLDER + environment.getProperty("directorio.origen") + "/";
        System.out.println("tmp1 = " + tmp);
        return tmp.replaceAll("file:", "");
    }

    @Bean
    public String directorioDestino() {
        String tmp = TARGET_FOLDER + environment.getProperty("directorio.destino") + "/";
        System.out.println("tmp2 = " + tmp);
        return tmp.replaceAll("file:", "");
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

    @Autowired
    AdaptaMembersPresentador adaptaMembersPresentador;
    @Autowired
    AdaptaMembersCasoDeUso adaptaMembersCasoDeUso;

    @Bean
    public AdaptaMembersControlador newAdaptaMembersControlador() {
        return new AdaptaMembersControlador(adaptaMembersCasoDeUso, adaptaMembersPresentador);
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

            String path = TARGET_FOLDER + environmentKid.getProperty("servidores.ftp");

            Gson gson = new Gson();
            TypeToken<List<FTPServer>> token = new TypeToken<List<FTPServer>>() {
            };

            List<FTPServer> lista;
            try {
                URL urlServidores = new URL(path);
                InputStreamReader inputStreamReader = new InputStreamReader(urlServidores.openStream());
                lista = gson.fromJson(inputStreamReader, token.getType());
                return lista;
            } catch (MalformedURLException ex) {
                Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
            }

            return new ArrayList<>();
        }
    }

    @Bean
    public Map<String, List<Reemplazo>> mapaDeReemplazos() {

        try {
            //reemplazos.put(StringUtils.substringBefore(fileName, "."), deJsonAReemplazos(json));
            Map<String, List<Reemplazo>> reemplazos = new HashMap<>();

            String combinacionesFile = environment.getProperty("combinaciones.properties.file");
            InputStream inputStream = AdaptadorConfig.class.getClassLoader().getResourceAsStream(combinacionesFile);
            Thread.currentThread().getContextClassLoader().getResourceAsStream(combinacionesFile);
            Properties combinacionesProp = new Properties();
            combinacionesProp.load(inputStream);
            Set<Map.Entry<Object, Object>> elements = combinacionesProp.entrySet();

            for (Map.Entry entry : elements) {
                String combinacion = (String) entry.getKey();
                String values = (String) entry.getValue();
                String archivoDeReemplazo = StringUtils.substringBefore(values, ",");
                archivoDeReemplazo = TARGET_FOLDER.getFile() + archivoDeReemplazo;
                String json = FileUtils.readFileToString(new File(archivoDeReemplazo), charsetDefault.trim());
                reemplazos.put(combinacion, deJsonAReemplazos(json));
            }
            return reemplazos;
        } catch (IOException ex) {
            Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<Reemplazo> deJsonAReemplazos(String json) {
        Gson gson = new Gson();

        TypeToken<List<Reemplazo>> token = new TypeToken<List<Reemplazo>>() {
        };

        return gson.fromJson(json, token.getType());
    }

    @Bean
    public Map<String, Editor> mapaDeEditores() throws ClassNotFoundException {

        try {
            Map<String, Editor> mapaDeEditores = new HashMap<>();

            String combinacionesFile = environment.getProperty("combinaciones.properties.file");
            InputStream inputStream = AdaptadorConfig.class.getClassLoader().getResourceAsStream(combinacionesFile);
            Thread.currentThread().getContextClassLoader().getResourceAsStream(combinacionesFile);
            Properties combinacionesProp = new Properties();
            combinacionesProp.load(inputStream);
            Set<Map.Entry<Object, Object>> elements = combinacionesProp.entrySet();

            for (Map.Entry entry : elements) {
                String combinacion = (String) entry.getKey();
                String values = (String) entry.getValue();
                String editorClassName = StringUtils.substringAfter(values, ",");
                Class<EditorEstandar> editorClass = (Class<EditorEstandar>) Class.forName(editorClassName);
                EditorEstandar editor = editorClass.newInstance();
                mapaDeEditores.put(combinacion, editor);
            }

            return mapaDeEditores;
        } catch (InstantiationException | IllegalAccessException | IOException ex) {
            Logger.getLogger(AdaptadorConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}

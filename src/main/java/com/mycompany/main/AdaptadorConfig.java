package com.mycompany.main;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.com.eixy.utilities.zos.ftp.ServerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"com.mycompany.c04.detalles"})
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

    
    @Bean
    public Map<String, ServerConfig> mapaDeServidores() {

        Map<String, ServerConfig> mapa = new HashMap<>();
        Gson gson = new Gson();

        TypeToken<List<ServerConfig>> token = new TypeToken<List<ServerConfig>>() {
        };

        try {
            String archivoServidoresFTP = environment.getProperty("servidores.ftp");
           
            System.out.println("Nomas..." + archivoServidoresFTP);
            
            
            List<ServerConfig> lista = gson.fromJson(new FileReader(archivoServidoresFTP), token.getType());
            lista.stream().forEach(elemento -> {
                System.out.println("eles2 " + elemento.getServerName());
                mapa.put(elemento.getServerName(), elemento);
            });

        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapa;
    }
   

    /*
    @Bean
    public PDSConfig pds() {

        PDSConfig pds = null;

        Gson gson = new Gson();
        try {
            pds = gson.fromJson(new FileReader(ARCHIVO_PDS), PDSConfig.class);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("el pds " + pds);

        return pds;
    }*/
}

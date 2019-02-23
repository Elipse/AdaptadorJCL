/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.mycompany.c04.detalles.AlmacenMember;
import java.net.URL;
import java.net.URLClassLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *
 * @author ELIALVA
 */
public class AdaptadorApp {

    public static void main(String[] args) throws Exception {
       
        AlmacenMember am = new AlmacenMember();
        AlmacenMember am2 = new AlmacenMember();
        
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        
        System.out.println("33* " + am);
        System.out.println("am333255* " + am2);
        System.out.println("Wahta s apppp " + StringUtils.center(" d asd asd ", 10));
        
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AdaptadorConfig.class);
        
       
        
       
        
        /*
        AlmacenMember bean = context.getBean(AlmacenMember.class);
        Peticion peticion = new Transferencia.Peticion();
        peticion.setServidor("INFODES");
        bean.buscaMemberViaID(peticion);
*/
        
    }

}

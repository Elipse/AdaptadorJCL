/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author ELIALVA
 */
public class AdaptadorApp {

    public static void main(String[] args) throws Exception {

        new AnnotationConfigApplicationContext(AdaptadorConfig.class).
                getBean(AdaptadorIUGraficaTest.class).inicia();
    }

}
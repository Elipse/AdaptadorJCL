/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.detalles;

/**
 *
 * @author ELIALVA
 */
public class Borra {

    public static void main(String[] args) {

        String tmp = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        int x = 0;
                int y =0;

        for (int i = 0; i < tmp.length(); i++) {
            x++;
            for (int j = 0; j < tmp.length(); j++) {
                y++;
                System.out.println("###" + tmp.substring(i, i + 1) + tmp.substring(j,j + 1));
            }
        }
        
        System.out.println("x " + x + " y " + y);
    }

}

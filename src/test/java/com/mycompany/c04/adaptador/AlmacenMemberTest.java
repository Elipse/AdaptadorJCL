/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.c04.adaptador;

import com.mycompany.c04.detalles.AlmacenMember;
import com.mycompany.c01.entidades.Member;
import com.mycompany.c04.detalles.AdaptadorConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ELIALVA
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AdaptadorConfig.class})
public class AlmacenMemberTest {
    
    public AlmacenMemberTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of recuperaMember method, of class AlmacenMember.
     */
    
    @Test
    public void testBuscaMedmberViaID() throws Exception {
    }
    
    //@Test
    public void testBuscaMemberViaID() throws Exception {
        System.out.println("buscaMemberViaID");
        Member peticion = null;
        AlmacenMember instance = new AlmacenMember();
        Member expResult = null;
        Member result = instance.recuperaMember(peticion);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    

    
}

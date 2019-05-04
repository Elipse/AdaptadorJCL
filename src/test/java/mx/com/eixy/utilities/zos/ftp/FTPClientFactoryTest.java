/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.eixy.utilities.zos.ftp;

import com.mycompany.c04.detalles.AdaptadorConfig;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ELIALVA
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AdaptadorConfig.class})
public class FTPClientFactoryTest {
    
    public FTPClientFactoryTest() {
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
    
    //@Autowired
    FTPClientFactory ftpClientFactory;

    /**
     * Test of getDataSet method, of class FTPClientFactory.
     */
    //@Test
    public void testGetDataSet() {
        
        System.out.println("getFile");
        Transfer transferencia
                = Transfer.newTransferencia()
                        .setServer("INFODES")
                        .setRemoteFile("TN6EAM.CATALOGO")
                        .setLocalFile("D:\\catalogo.txt");
        
        File result = null;
        try {
            result = ftpClientFactory.getDataSet(transferencia);
            assertNotNull(result);
        } catch (IOException ex) {            
            assertTrue(false);
        }
        
        transferencia.setLocalFile("C:\\catalogo.txt");
        try {
            result = ftpClientFactory.getDataSet(transferencia);
            assertTrue(false);
        } catch (IOException ex) {  
            System.out.println("upis " + ex);
            assertTrue(true);
        }        
    }
    
    //@Test
    public void testPutMember() {

        //java.io.IOException: 550 STOR fails: PNCQP.BTCH.PROCLIB(UPS).  
        //  User not authorized.
        Transfer transferencia
                = Transfer.newTransferencia()
                        .setServer("INFODES")
                        .setRemoteFile("DNCQP.BTCH.PROCLIB(EAM)")
                        .setLocalFile("D:\\catalogo.txt");
        try {
            ftpClientFactory.putMember(transferencia);
            assertTrue(true);
        } catch (IOException e) {
            assertTrue(false);
        }
        
        try {
            transferencia.setLocalFile("D:\\cataSlogo.txt");
            ftpClientFactory.putMember(transferencia);
            assertTrue(false);
        } catch (IOException e) {
            System.out.println("testPutMember: " + e);
            assertTrue(true);
        }
        
        transferencia.setLocalFile("D:\\catalogo.txt");
        
        try {
            transferencia.setRemoteFile("XNCQP.BTCH.PROCLIB(UPS)");
            ftpClientFactory.putMember(transferencia);
            assertTrue(false);
        } catch (IOException e) {
            System.out.println("testPutMember: " + e);
            assertTrue(true);
        }
        
        try {
            transferencia.setRemoteFile("PNCQP.BTCH.PROCLIB(UPS)");
            ftpClientFactory.putMember(transferencia);
            assertTrue(false);
        } catch (IOException e) {
            System.out.println("testPutMember: " + e);
            assertTrue(true);
        }
    }
    
    //@Test
    public void testIsRemoteDataSetAvailable() {
        
        try {
            boolean existe = ftpClientFactory.isRemoteDataSetAvailable("INFODES", "DNCQP.BTCH.PROCLIB");
            assertTrue(existe == true);
            boolean exist2 = ftpClientFactory.isRemoteDataSetAvailable("INFOPRO", "PGA.QAD2200.KTALOGO");
            assertTrue(exist2 == true);
            boolean exist3 = ftpClientFactory.isRemoteDataSetAvailable("INFODES", "DNXCQP.BTCH.PROCLIB");
            assertTrue(exist3 == false);
        } catch (IOException ex) {
            System.out.println("testIsRemoteDataSetAvailable Exception " + ex.getLocalizedMessage());
        }
    }
    
    //@Test
    public void testCreatePartitionedDataSet() {
        
        Exception e = null;
        
        DataSetDefinition dataSetDefinition = DataSetDefinition.newDataDefinition()
                .setDsname("TN6EAM.HOLA.ADIOS2")
                .setDirectorySize("di=900")
                .setRecordFormat("rec=fb")
                .setRecordLength("lr=080")
                .setBlkSize("blksize=32720")
                .setSpaceUnit("cy")
                .setPrimarySpace("pri=10")
                .setSecondarySpace("sec=5");
        
        try {
            //Esto es para generar la terminación OK de la operación atómica de generación
            ftpClientFactory.createPartitionedDataSet("INFODES", dataSetDefinition);
            assertTrue(true);
            //Esto es para generar la excepción
            dataSetDefinition.setDsname("PNCQP.BTCH.CARDS");
            ftpClientFactory.createPartitionedDataSet("INFOPRO", dataSetDefinition);
            assertTrue(false);
        } catch (IOException ex) {
            e = ex;
        }
        
        System.out.println("Genera exception " + e.getMessage());
        assertNotNull(e);
    }
}

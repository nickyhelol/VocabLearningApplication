/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick He
 */
public class VocabAppModelTest {
    

    /**
     * Test of updateUserInfo method, of class VocabAppModel.
     */
    @Test
    public void testUpdateUserInfoByValidInfo() throws SQLException {
        System.out.println("Test updateUserInfo with valid userInfo.");
        String name = "Alex";
        String ps = "123";
        VocabAppModel instance = new VocabAppModel();
        boolean check = false;
        check = instance.updateUserInfo(name, ps);
        assertTrue(check);
        System.out.println("Test succeeds.");
        instance.getConn().close();
    }
    

    /**
     * Test of createUserTable method, of class VocabAppModel.
     */
    @Test
    public void testCreateUserTableByValidTableName() throws SQLException {
        System.out.println("Test createUserTable with valid table name.");
        VocabAppModel instance = new VocabAppModel();
        instance.setUserTableName("WORD_ALEX");
        instance.setUserWordList(new WordDatabase());
        boolean check = false;
        check = instance.createUserTable();
        assertTrue(check);
        System.out.println("Test succeeds.");
        instance.getConn().close();
    }
    
       /**
     * Test of createUserTable method, of class VocabAppModel.
     */
    @Test
    public void testCreateUserTableByInvalidTableName() throws SQLException {
        System.out.println("Test createUserTable with invalid table name.");
        VocabAppModel instance = new VocabAppModel();
        instance.setUserTableName(null);
        instance.setUserWordList(new WordDatabase());
        boolean check = true;
        check = instance.createUserTable();
        assertFalse(check);
        System.out.println("Test succeeds.");
        instance.getConn().close();
    }
    
    /**
     * Test of retriveUserTable method, of class VocabAppModel.
     */
    @Test
    public void testRetriveUserTableByInvalidTable() throws SQLException {
        System.out.println("Test retriveUserTable with invalid table.");
        VocabAppModel instance = new VocabAppModel();
        instance.setUserTableName(null);
        boolean check = true;
        check = instance.retriveUserTable();
        assertFalse(check);
        System.out.println("Test succeeds.");
        instance.getConn().close();
    }

}

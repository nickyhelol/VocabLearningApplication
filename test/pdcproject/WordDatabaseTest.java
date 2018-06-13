/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Nick He
 */
public class WordDatabaseTest {
    
    static VocabAppModel model;
    
    @BeforeClass
    public static void setUpClass() {
        model = new VocabAppModel();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        model.getConn().close();
    }
    
    /**
     * Test of searchByProficiency method, of class WordDatabase.
     */
    @Test
    public void testSearchByProficiencyByAdcanced() {
        System.out.println("Test searchByProficiency by ADVANCED.");
        Proficiency p = Proficiency.ADVANCED;
        WordDatabase instance = model.getWordDatabase();
        WordDatabase result = instance.searchByProficiency(p);
        boolean check = true;
        for(Word w: result.getWords())
        {
            if(w.getProficiency()!= p)
            {
                check = false;
            }
        }
        
        assertTrue(check);
        System.out.println("Test succeeds.");
    }
    
    /**
     * Test of searchByProficiency method, of class WordDatabase.
     */
    @Test
    public void testSearchByProficiencyByIntermediate() {
        System.out.println("Test searchByProficiency by INTERMEDIATE.");
        Proficiency p = Proficiency.INTERMEDIATE;
        WordDatabase instance = model.getWordDatabase();
        WordDatabase result = instance.searchByProficiency(p);
        boolean check = true;
        for(Word w: result.getWords())
        {
            if(w.getProficiency()!= p)
            {
                check = false;
            }
        }
        
        assertTrue(check);
        System.out.println("Test succeeds.");
    }
    
    /**
     * Test of searchByProficiency method, of class WordDatabase.
     */
    @Test
    public void testSearchByProficiencyByBeginner() {
        System.out.println("Test searchByProficiency by BEGINNER.");
        Proficiency p = Proficiency.BEGINNER;
        WordDatabase instance = model.getWordDatabase();
        WordDatabase result = instance.searchByProficiency(p);
        boolean check = true;
        for(Word w: result.getWords())
        {
            if(w.getProficiency()!= p)
            {
                check = false;
            }
        }
        
        assertTrue(check);
        System.out.println("Test succeeds.");
    }

    /**
     * Test of searchByGoal method, of class WordDatabase.
     */
    @Test
    public void testSearchByGoalByCasual() {
        System.out.println("Test searchByGoal by CASUAL");
        Goal g = Goal.CASUAL;
        WordDatabase instance = model.getWordDatabase();
        int expResult = 15;
        int count = 0;
        WordDatabase result = instance.searchByGoal(g);
        for(Word w: result.getWords())
        {
            count++;
        }
        
        assertEquals(expResult, count);
        System.out.println("Test succeeds.");
    }
    
    /**
     * Test of searchByGoal method, of class WordDatabase.
     */
    @Test
    public void testSearchByGoalByRegular() {
        System.out.println("Test searchByGoal by REGULAR");
        Goal g = Goal.REGULAR;
        WordDatabase instance = model.getWordDatabase();
        int expResult = 30;
        int count = 0;
        WordDatabase result = instance.searchByGoal(g);
        for(Word w: result.getWords())
        {
            count++;
        }
        
        assertEquals(expResult, count);
        System.out.println("Test succeeds.");
    }
    
    /**
     * Test of searchByGoal method, of class WordDatabase.
     */
    @Test
    public void testSearchByGoalBySerious() {
        System.out.println("Test searchByGoal by SERIOUS");
        Goal g = Goal.SERIOUS;
        WordDatabase instance = model.getWordDatabase();
        int expResult = 50;
        int count = 0;
        WordDatabase result = instance.searchByGoal(g);
        for(Word w: result.getWords())
        {
            count++;
        }
        
        assertEquals(expResult, count);
        System.out.println("Test succeeds.");
    }
    
    /**
     * Test of searchByGoal method, of class WordDatabase.
     */
    @Test
    public void testSearchByGoalByInsane() {
        System.out.println("Test searchByGoal by INSANE");
        Goal g = Goal.INSANE;
        WordDatabase instance = model.getWordDatabase();
        int expResult = 80;
        int count = 0;
        WordDatabase result = instance.searchByGoal(g);
        for(Word w: result.getWords())
        {
            count++;
        }
        
        assertEquals(expResult, count);
        System.out.println("Test succeeds.");
    }
    
}

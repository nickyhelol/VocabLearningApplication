/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Nick He
 */
public class VocabAppModel{
    
    private Connection conn = null;
    private final String url = "jdbc:derby:VocabAppDB;create=true";
    private final String dbusername = "Nick";  
    private final String dbpassword = "123";   
    
    private Map<String, String> userInfo;
    private WordDatabase wordDatabase;
    private WordDatabase userWordList;
    private String username;
    private String userTableName;
    private int wordIndex;
   
    public VocabAppModel() {
        databaseSetup();
        userInfo = loadUserInfo();
        wordDatabase = new WordDatabase(loadWordDatabase("WORDLIST"));
    }
     
    /**
     * This method is used to setup the database
     * 
     */
    private void databaseSetup()
    {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            System.out.println("Database has connected...");
            Statement statement = getConn().createStatement();

            if (!checkTableExisting("USERINFO")) {
                statement.executeUpdate("CREATE TABLE USERINFO (USERNAME VARCHAR(12), PASSWORD VARCHAR(12))");
                System.out.println("Table USERINFO was created");
            }

            if (!checkTableExisting("WORDLIST")) {
                statement.executeUpdate("CREATE TABLE WORDLIST (CHINESE VARCHAR(8), PINYIN VARCHAR(20), ENGLISH VARCHAR(60), PROFICIENCY VARCHAR(15))");
                System.out.println("Table WORDLIST was created");
                try (BufferedReader br = new BufferedReader(new FileReader("WordList.txt"))) {
                    String string;
                    while ((string = br.readLine()) != null) {
                        String eng = "";
                        String p = "";
                        if (string.length() > 0 && string.length() <= 20) {
                            p = "BEGINNER";
                        } else if (string.length() > 20 && string.length() <= 30) {
                            p = "INTERMEDIATE";
                        } else if (string.length() > 30) {
                            p = "ADVANCED";
                        }
                        String[] array = string.split(" ");
                        for (int i = 2; i < array.length; i++) {
                            eng = eng.concat(array[i] + " ");
                        }
                        statement.executeUpdate("INSERT INTO WORDLIST VALUES ('"+array[0]+ "', '"+array[1]+"', '"+eng+"', '"+p+"')"); 
                    }
                } catch (IOException ex) {
                    System.out.println("Specified file not found!");
                }
            }
            if(statement!=null)
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
            System.out.println("bad");
        }
    }
    
    /**
     * This method is used to load users information
     * from database and store it into a map and return
     * it.
     * 
     * @return 
     */
    private Map<String, String> loadUserInfo()
    {
        Map<String, String> userInfo = new HashMap<>();
        try(Statement statement = getConn().createStatement();
            ResultSet rs = statement.executeQuery("SELECT USERNAME, PASSWORD FROM USERINFO"))
        {
            while(rs.next())
            {
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                userInfo.put(username, password);
            }
        }catch(SQLException ex)
        {
            
        }
        
        return userInfo;
    }
    
    /**
     * This method is used to update USERINFO table 
     * in the database and return a boolean to show its
     * result.
     * 
     * @param name
     * @param ps
     * @return 
     */
    public boolean updateUserInfo(String name, String ps)
    {
        try(Statement statement = getConn().createStatement())
        {
            statement.executeUpdate("INSERT INTO USERINFO VALUES ('"+name+"', '"+ps+"')");
            System.out.println("Update success!");
            return true;
        }catch(SQLException ex)
        {
            System.out.println("Update failed.");
            return false;
        }
    }
    
    /**
     * This method is used to load wordList from database
     * and save it into an ArrayList and return.
     * 
     * @param tablename
     * @return 
     */
    private ArrayList<Word> loadWordDatabase(String tablename)
    {
        ArrayList<Word> words = new ArrayList<>();
        try(Statement statement = getConn().createStatement();
            ResultSet rs = statement.executeQuery("SELECT CHINESE, PINYIN, ENGLISH, PROFICIENCY FROM "+tablename))
        {
            while(rs.next())
            {
                String chin = rs.getString(1);
                String pinyin = rs.getString(2);
                String eng = rs.getString(3);
                Proficiency p = Proficiency.valueOf(rs.getString(4));
                words.add(new Word(chin, pinyin, eng, p));
            }
        }catch(SQLException ex)
        {
            
        }
        return words;
    }
    
    /**
     * This method takes a String as parameter and check
     * if specified table exists. Then return a boolean.
     * 
     * @param newTableName
     * @return 
     */
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            System.out.println("Check existing tables.... ");
            DatabaseMetaData dbmd = getConn().getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(newTableName)) {
                    flag = true;
                    System.out.println("Table "+newTableName+" has already existed.");
                }
            }
            
            if(flag == false)
            {
                System.out.println("No such table found.");
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            
        }
        return flag;
    }
    
    /**
     * This method is used to create a new table 
     * for the current user.
     * 
     * @return 
     */
    public boolean createUserTable()
    {
        try(Statement statement = getConn().createStatement())
        {
            if (checkTableExisting(userTableName)) 
            {
                statement.executeUpdate("DELETE FROM "+userTableName);
                for(Word w: userWordList.getWords())
                {
                    statement.executeUpdate("INSERT INTO "+userTableName+" VALUES ('"+w.getChinese()
                            + "', '"+w.getPinyin()+"', '"+w.getEnglish()+"', '"+w.getProficiency().toString()+"')");
                }
                System.out.println("Table "+username+" updated.");
                return true;
            }else
            {
                statement.executeUpdate("CREATE TABLE "+userTableName
                        +" (CHINESE VARCHAR(8), PINYIN VARCHAR(20), ENGLISH VARCHAR(60), PROFICIENCY VARCHAR(15))");
                System.out.println("Table "+username+" created.");
                for(Word w: userWordList.getWords())
                {
                    statement.executeUpdate("INSERT INTO "+userTableName+" VALUES ('"+w.getChinese()
                            + "', '"+w.getPinyin()+"', '"+w.getEnglish()+"', '"+w.getProficiency().toString()+"')");
                }
                return true;
            }
        }catch(SQLException ex)
        {
            return false;
        }
    }
    
    /**
     * This method is used to retrieve the data 
     * stored in the current user's table.
     * 
     * @return 
     */
    public boolean retriveUserTable()
    {
        if(checkTableExisting(userTableName))
        {
            this.userWordList = new WordDatabase(loadWordDatabase(userTableName.toUpperCase()));
            System.out.println("User found");
            return true;
        }else
        {
            System.out.println("Cannot find "+username);
            return false;
        }
    }

    public void setUserTableName(String userTableName) {
        this.userTableName = userTableName;
    }

    public Connection getConn() {
        return conn;
    }
   
    public int getWordIndex()
    {
        return this.wordIndex;
    }
    
    public void setWordIndex(int index)
    {
        this.wordIndex = index;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public Map<String, String> getUserInfo()
    {
        return this.userInfo;
    }
    
    public WordDatabase getWordDatabase()
    {
        return this.wordDatabase;
    }
    
    public void setUserWordList(WordDatabase words)
    {
        this.userWordList = words;
    }
    
    public WordDatabase getUserWordList()
    {
        return this.userWordList;
    }
    
}
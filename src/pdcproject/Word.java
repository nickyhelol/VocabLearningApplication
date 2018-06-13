/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

/**
 *
 * @author Nick He
 */
public class Word {
    
    private String chinese;
    private String pinyin;
    private String english;
    private Proficiency proficiency;
    
    public Word(String chinese, String pinyin, String english, Proficiency proficiency)
    {
        this.chinese = chinese;
        this.pinyin = pinyin;
        this.english = english;
        this.proficiency = proficiency;
    }
    
    public Word()
    {
        
    }
    
    public String getChinese()
    {
        return this.chinese;
    }
    
    public void setChinese(String chinese)
    {
        this.chinese =chinese;
    }
    
    public String getPinyin()
    {
        return this.pinyin;
    }
    
    public void setPinyin(String pinyin)
    {
        this.pinyin =pinyin;
    }
    
    public String getEnglish()
    {
        return this.english;
    }
    
    public void setEnglish(String english)
    {
        this.english =english;
    }
    
    public Proficiency getProficiency()
    {
        return this.proficiency;
    }
    
    public void setProficiency(Proficiency proficiency)
    {
        this.proficiency = proficiency;
    }
    
    @Override
    public String toString()
    {
        String outcome="";
        outcome+="Chinese: "+this.chinese+"\n";
        outcome+="Pinyin: "+this.pinyin+"\n";
        outcome+="English: "+this.english;
        
        return outcome;
    }
}

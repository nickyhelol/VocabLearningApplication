/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

import java.util.*;

/**
 *
 * @author Nick He
 */
public class WordDatabase {
    
    private ArrayList<Word> words;
    
    
    public WordDatabase(ArrayList<Word> words)
    {
        this.words = words;   
    }
    
    public WordDatabase()
    {
        this(new ArrayList<Word>());
    }
    
    public void setWords(ArrayList<Word> words)
    {
        this.words = words;
    }
    
    public ArrayList<Word> getWords()
    {
        return this.words;
    }
    
    public void add(Word aWord)
    {
        this.words.add(aWord);
    }
    
    public void remove(Word aWord)
    {
        this.words.remove(aWord);
    }
    
    /**
     * This method is used for filtering the word list
     * by Proficiency.
     * 
     * @param p
     * @return 
     */
    public WordDatabase searchByProficiency(Proficiency p)
    {
        WordDatabase result = new WordDatabase();
        for(Word w: words)
        {
            if(w.getProficiency() == p)
            {
                result.add(w);
            }
        }
        
        return result;
    }
    
    /**
     * This method is used for filtering the word list
     * by Goal.
     * 
     * @param g
     * @return 
     */
    public WordDatabase searchByGoal(Goal g)
    {
        int n = 0;
        if(null != g)
        switch (g) {
            case CASUAL:
                n = 15;
                break;
            case REGULAR:
                n = 30;
                break;
            case SERIOUS:
                n = 50;
                break;
            case INSANE:
                n = 80;
                break;
            default:
                break;
        }
        WordDatabase result = new WordDatabase();
        Collections.shuffle(words);
        result.getWords().addAll(words.subList(0, n));
       
        return result;
    }
    
}

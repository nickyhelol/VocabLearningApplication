/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick He
 */
public class VocabAppController {
    
    VocabAppView view;
    
    public VocabAppController()
    {
        view = new VocabAppView();
        
        //User panel controller
        view.getLogin().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                eventHandleLoginButton();
            }
        });
        
        view.getSignin().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                eventHandleSigninButton();
            }
        });
        
        view.getNameInput().addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e)
            {
                eventHandleKeyReleased(e);
            }
        });
        
        view.getPasswordInput().addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e)
            {
                eventHandleKeyReleased(e);
            }
        });
        
        //Learning mode panel controller
        view.getOkButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                eventHandleOkButton();
            }
        });
        
        view.getNewSection().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                view.updateLearningModePanel();
            }
        });
        
        view.getReview().addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                view.updateLearningModePanel();
            }
        });
        
        //Vocab panel controller
        view.getPrevious().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                eventHandlePreviousButtion();
            }
        });
        
        view.getNext().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                eventHandleNextButton();
            }
        });
        
        view.getQuit().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                eventHandleQuitButton();
            }
        });
    }
    
    /**
     * This method is used to handle the event of
     * the login button.
     * 
     */
    private void eventHandleLoginButton()
    {
        String username = view.getNameInput().getText();
        String password = view.getPasswordInput().getText();
        if(this.view.getModel().getUserInfo().containsKey(username))
        {
            if(this.view.getModel().getUserInfo().get(username).equals(password))
            {
                view.getModel().setUsername(username);
                view.getModel().setUserTableName("WORD_"+username);
                view.getContentPane().removeAll();
                view.add(view.getLearningModePanel());
                view.revalidate();
                view.repaint();
            }else
            {
                view.getMessage().setText("Invalid username or password, please enter again.");
                view.getMessage().setVisible(true);
            }
        }else
        {
            view.getMessage().setText("Invalid username or password, please enter again.");
            view.getMessage().setVisible(true);
        }
    }
    
    /**
     * This method is used to handle the event of
     * the signin button.
     */
    private void eventHandleSigninButton()
    {
        String username = view.getNameInput().getText();
        String password = view.getPasswordInput().getText();
        if(view.getModel().getUserInfo().containsKey(username))
        {
            view.getMessage().setText("This username has been occupied.");
            view.getMessage().setVisible(true);
        }else
        {
            view.getModel().setUsername(username);
            view.getModel().setUserTableName("WORD_"+username);
            view.getModel().updateUserInfo(username, password);
            view.getSelectMode().setText("Sign in success, please select your learning mode.");
            view.getContentPane().removeAll();
            view.add(view.getLearningModePanel());
            view.revalidate();
            view.repaint();
        }
    }
    
    /**
     * This method is used to handle the event of
     * a key released.
     * 
     * @param e 
     */
    private void eventHandleKeyReleased(KeyEvent e)
    {
        this.view.updateUserPanel();
    }
    
    /**
     * This method is used to handle the event of
     * the Ok button.
     */
    private void eventHandleOkButton()
    {
        if(view.getNewSection().isSelected())
        {
            Proficiency p = Proficiency.valueOf(view.getProficiency().getSelectedItem().toString().toUpperCase());
            Goal g = Goal.valueOf(view.getGoal().getSelectedItem().toString().toUpperCase());
            WordDatabase result = view.getModel().getWordDatabase().searchByProficiency(p);
            result = result.searchByGoal(g);
            view.getModel().setUserWordList(result);
            view.getModel().createUserTable();
            
            view.getWordsDisplay().setText(view.getModel().getUserWordList().getWords().get(0).toString());
            view.getModel().setWordIndex(0);
            view.updateVocabPanel();
            view.getContentPane().removeAll();
            view.add(view.getVocabPanel());
            view.revalidate();
            view.repaint();
        }else
        {
            if(view.getModel().retriveUserTable())
            {
                view.getWordsDisplay().setText(view.getModel().getUserWordList().getWords().get(0).toString());
                view.getModel().setWordIndex(0);
                view.updateVocabPanel();
                view.getContentPane().removeAll();
                view.add(view.getVocabPanel());
                view.revalidate();
                view.repaint();
                
            }else
            {
                this.view.getWarning().setVisible(true);
            }
        }
    }
    
    /**
     * This method is used to handle the event of
     * the Previous button.
     * 
     */
    private void eventHandlePreviousButtion()
    {
        view.getModel().setWordIndex(view.getModel().getWordIndex()-1);
        view.getWordsDisplay().setText(view.getModel().getUserWordList().getWords().get(view.getModel().getWordIndex()).toString());
        view.updateVocabPanel();
    }
    
    /**
     * This method is used to handle the event of
     * the Next button.
     * 
     */
    private void eventHandleNextButton()
    {
        view.getModel().setWordIndex(view.getModel().getWordIndex()+1);
        view.getWordsDisplay().setText(view.getModel().getUserWordList().getWords().get(view.getModel().getWordIndex()).toString());
        view.updateVocabPanel();
    }
    
    /**
     * This method is used to handle the event of
     * the Quit button.
     * 
     */
    private void eventHandleQuitButton()
    {
        view.getContentPane().removeAll();
        view.add(view.getQuitPanel());
        view.revalidate();
        view.repaint();
        try {
            view.getModel().getConn().close();
            System.out.println("Connection has been safely cloesd.");
        } catch (SQLException ex) {
            Logger.getLogger(VocabAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

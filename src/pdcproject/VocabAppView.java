/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdcproject;

import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Nick He
 */
public class VocabAppView extends JFrame{
    
    private VocabAppModel model;
    
    private JPanel userPanel;
    private JPanel learningModePanel;
    private JPanel vocabPanel;
    private JPanel quitPanel;
    
    //User panel components
    private JLabel username;
    private JLabel password;
    private JLabel message;
    private JButton login;
    private JButton signin;
    private JTextField nameInput;
    private JTextField passwordInput;
    
    //learning mode panel components
    private JCheckBox newSection;
    private JCheckBox review;
    private JLabel label;
    private JComboBox proficiency;
    private JComboBox goal;
    private JLabel selectMode;
    private JButton okButton;
    private JLabel warning;
    
    //vocab panel components
    private JLabel index;
    private JTextArea wordsDisplay;
    private JScrollPane scrollPane;
    private JButton previous;
    private JButton next;
    private JButton quit;
    
    //quit panel components
    private JLabel result;
    
    public VocabAppView()
    {
        this.model = new VocabAppModel();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        
        this.userPanel = new JPanel();
        userPanel.setLayout(null);
        
        this.username = new JLabel("Username:");
        username.setLocation(50, 50);
        username.setSize(70, 20);
        this.userPanel.add(username);
        
        this.nameInput = new JTextField(12);
        nameInput.setLocation(130, 50);
        nameInput.setSize(160, 20);
        this.userPanel.add(nameInput);
        
        this.password = new JLabel("Password:");
        password.setLocation(50, 90);
        password.setSize(70, 20);
        this.userPanel.add(password);
        
        this.passwordInput = new JTextField(12);
        passwordInput.setLocation(130, 90);
        passwordInput.setSize(160, 20);
        this.userPanel.add(passwordInput);
        
        this.login = new JButton("Log in");
        login.setEnabled(false);
        login.setLocation(50, 150);
        login.setSize(110, 30);
        this.userPanel.add(login);
        
        this.signin = new JButton("Sign in");
        signin.setEnabled(false);
        signin.setLocation(180, 150);
        signin.setSize(110, 30);
        this.userPanel.add(signin);
        
        this.message = new JLabel("");
        this.message.setVisible(false);
        message.setLocation(30, 200);
        message.setSize(300, 20);
        this.userPanel.add(message);
        
        this.add(userPanel);
        
        //learningMode panel
        this.learningModePanel = new JPanel();
        this.learningModePanel.setLayout(null);
        
        this.selectMode = new JLabel("Log in success, please select your learning mode.");
        selectMode.setLocation(30, 30);
        selectMode.setSize(300, 20);
        this.learningModePanel.add(selectMode);
        
        newSection = new JCheckBox("New section");
        newSection.setLocation(50, 70);
        newSection.setSize(120, 30);
        this.learningModePanel.add(newSection);
        
        review = new JCheckBox("Review");
        review.setLocation(190, 70);
        review.setSize(200, 30);
        this.learningModePanel.add(review);
        
        label = new JLabel("(Only for old user)");
        label.setLocation(190, 110);
        label.setSize(150, 30);
        this.learningModePanel.add(label);
        
        String[] p = {"Beginner", "Intermediate", "Advanced"};
        proficiency = new JComboBox<>(p);
        proficiency.setLocation(55, 120);
        proficiency.setSize(90, 20);
        this.learningModePanel.add(proficiency);
        
        String[] g = {"Casual", "Regular", "Serious", "Insane"};
        goal = new JComboBox<>(g);
        goal.setLocation(55, 160);
        goal.setSize(90, 20);
        this.learningModePanel.add(goal);
        
        okButton = new JButton("OK");
        okButton.setEnabled(false);
        okButton.setLocation(55, 220);
        okButton.setSize(120, 30);
        this.learningModePanel.add(okButton);
        
        warning = new JLabel("No available word list.");
        warning.setLocation(55, 260);
        warning.setSize(180, 40);
        warning.setVisible(false);
        this.learningModePanel.add(warning);
        
        //vocab panel
        vocabPanel = new JPanel();
        vocabPanel.setLayout(null);
        
        index = new JLabel("");
        index.setLocation(30, 20);
        index.setSize(80, 20);
        this.vocabPanel.add(index);
        
        wordsDisplay = new JTextArea();
        wordsDisplay.setEditable(false);
        scrollPane = new JScrollPane(wordsDisplay,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLocation(30, 40);
        scrollPane.setSize(350, 200);
        this.vocabPanel.add(scrollPane);
        
        previous = new JButton("Previous");
        previous.setLocation(30, 250);
        previous.setSize(90, 30);
        previous.setEnabled(false);
        this.vocabPanel.add(previous);
        
        next = new JButton("Next");
        next.setLocation(190, 250);
        next.setSize(90, 30);
        this.vocabPanel.add(next);
        
        quit = new JButton("Quit");
        quit.setLocation(290, 250);
        quit.setSize(90, 30);
        this.vocabPanel.add(quit);
        
        //quit panel
        quitPanel = new JPanel();
        quitPanel.setLayout(null);
        
        result = new JLabel("You have done an excellent job today, keep going!");
        result.setFont(new Font("myFont", Font.ITALIC, 18));
        result.setLocation(25, 150);
        result.setSize(460, 30);
        quitPanel.add(result);
        
        this.setVisible(true);
        updateUserPanel();
    }
    
    /**
     * This method is used to update the state of 
     * the userPanel.
     * 
     */
    public void updateUserPanel() 
    {
        this.login.setEnabled(this.nameInput.getText().isEmpty()==false && this.passwordInput.getText().isEmpty()==false);
        this.signin.setEnabled(this.nameInput.getText().isEmpty()==false && this.passwordInput.getText().isEmpty()==false);
    }
    
    /**
     * This method is used to update the state of
     * learningModePanel.
     * 
     */
    public void updateLearningModePanel()
    {
        if(this.newSection.isSelected())
        {
            this.review.setEnabled(false);
        }else
        {
            this.review.setEnabled(true);
        }
        
        if(this.review.isSelected())
        {
            this.newSection.setEnabled(false);
        }else
        {
            this.newSection.setEnabled(true);
        }
        
        if(this.newSection.isSelected() || this.review.isSelected())
        {
            this.okButton.setEnabled(true);
        }else
        {
            this.okButton.setEnabled(false);
        }
    }
    
    /**
     * This method is used to update the state of
     * vocabPanel.
     * 
     */
    public void updateVocabPanel()
    {
        if(this.model.getWordIndex()> 0)
        {
            this.previous.setEnabled(true);
        }else
        {
            this.previous.setEnabled(false);
        }
        
        if(this.model.getWordIndex() < (this.model.getUserWordList().getWords().size()-1))
        {
            this.next.setEnabled(true);
        }else
        {
            this.next.setEnabled(false);
        }
        
        this.index.setText("("+(this.model.getWordIndex()+1)+"/"+this.model.getUserWordList().getWords().size()+")");
    }
    
    /**
     * @return the model
     */
    public VocabAppModel getModel() {
        return model;
    }

    /**
     * @return the userPanel
     */
    public JPanel getUserPanel() {
        return userPanel;
    }

    /**
     * @return the learningModePanel
     */
    public JPanel getLearningModePanel() {
        return learningModePanel;
    }

    /**
     * @return the vocabPanel
     */
    public JPanel getVocabPanel() {
        return vocabPanel;
    }

    /**
     * @return the quitPanel
     */
    public JPanel getQuitPanel() {
        return quitPanel;
    }

    /**
     * @return the username
     */
    public JLabel getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public JLabel getPassword() {
        return password;
    }

    /**
     * @return the message
     */
    public JLabel getMessage() {
        return message;
    }

    /**
     * @return the login
     */
    public JButton getLogin() {
        return login;
    }

    /**
     * @return the signin button
     */
    public JButton getSignin() {
        return signin;
    }

    /**
     * @return the nameInput
     */
    public JTextField getNameInput() {
        return nameInput;
    }

    /**
     * @return the passwordInput
     */
    public JTextField getPasswordInput() {
        return passwordInput;
    }

    /**
     * @return the newSection
     */
    public JCheckBox getNewSection() {
        return newSection;
    }

    /**
     * @return the review
     */
    public JCheckBox getReview() {
        return review;
    }

    /**
     * @return the proficiency
     */
    public JComboBox getProficiency() {
        return proficiency;
    }

    /**
     * @return the goal
     */
    public JComboBox getGoal() {
        return goal;
    }

    /**
     * @return the selectMode
     */
    public JLabel getSelectMode() {
        return selectMode;
    }

    /**
     * @return the okButton
     */
    public JButton getOkButton() {
        return okButton;
    }

    /**
     * @return the wordsDisplay
     */
    public JTextArea getWordsDisplay() {
        return wordsDisplay;
    }

    /**
     * @return the previous
     */
    public JButton getPrevious() {
        return previous;
    }

    /**
     * @return the next
     */
    public JButton getNext() {
        return next;
    }

    /**
     * @return the quit
     */
    public JButton getQuit() {
        return quit;
    }

    /**
     * @return the result
     */
    public JLabel getResult() {
        return result;
    }

    /**
     * @return the index
     */
    public JLabel getIndex() {
        return index;
    }

    /**
     * @return the warning
     */
    public JLabel getWarning() {
        return warning;
    }
}

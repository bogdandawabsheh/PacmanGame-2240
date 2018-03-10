	import java.awt.Color;
	import javafx.scene.*;
	 import javafx.application.Application;
	 import javafx.geometry.Rectangle2D;
	 import javafx.scene.Group;
	 import javafx.scene.Scene; 
	 import javafx.scene.image.Image;
	 import javafx.scene.image.ImageView;
	 import javafx.scene.layout.HBox;
	 import javafx.stage.Stage; 
import java.awt.*;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class mainForm extends javax.swing.JFrame {

    // Variables declaration - do not modify                    
    private javax.swing.JButton bttnCredits;
    private javax.swing.JButton bttnExit;
    private javax.swing.JButton bttnHighScore;
    private javax.swing.JButton bttnStart;
    private javax.swing.JLabel lblBackground;
    // End of variables declaration    

    
    /**
     * Creates new form mainMenuForm
     */
    public mainForm() {
       initComponents(); 
    }

    /**
     *Exits form
     */
    //ImageFiew
    
    
    public void exitForm(){
    	this.setVisible(false);
    	this.dispose();
    }
    
    private void initComponents() {
        lblBackground = new javax.swing.JLabel();
        bttnStart = new javax.swing.JButton();
        bttnHighScore = new javax.swing.JButton();
        bttnCredits = new javax.swing.JButton();
        bttnExit = new javax.swing.JButton();
    
    
        
        //lblBackground.add(new JPanel() {
        //    @Override
        //    protected void paintComponent(Graphics g) {
        //    	Rectangle2D temp = new Rectangle2D(0,0,640,195);
        //        Image image = new Image("logo.png");
        //        ImageView iv3 = new ImageView();
        
         //       iv3.setImage(image);
          //      iv3.setViewport(temp);
          //	
           // }
        //}, BorderLayout.CENTER);
       
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(640, 640));

        bttnStart.setText("Start");
        bttnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnStartActionPerformed(evt);
            }
        });
        
        bttnHighScore.setText("Scores");
        bttnHighScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnHighScoreActionPerformed(evt);
            }
        });
        
        bttnCredits.setText("Credits");
        bttnCredits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCreditsActionPerformed(evt);
            }
        });
        
        bttnExit.setText("Exit");
        bttnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnExitActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bttnCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bttnHighScore, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(bttnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(205, 205, 205))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(lblBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(bttnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(bttnHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttnCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        lblBackground.getAccessibleContext().setAccessibleName("lblBackground");
        bttnStart.getAccessibleContext().setAccessibleName("bttnStart");
        bttnHighScore.getAccessibleContext().setAccessibleName("bttnScore");
        bttnCredits.getAccessibleContext().setAccessibleName("bttnCredits");
        bttnExit.getAccessibleContext().setAccessibleName("bttnExit");

        pack();
    }                      
    

    private void bttnStartActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	//START GAME
    	
    	//RETURN HIGHSCORE
    	
    	//PLACE INTO DATABASE 
    	
    	//WHEN RETURNING THE HIGH SCORE INTO THE DATABASE, MAKE SURE TO SET THE FILE BACK TO READONLY()
    
    }
    private void bttnHighScoreActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    	//CREATE NEW FORM
    	//HAVE A TEXT BOX
    	//RETRIEVE ITEMS into highscores array
    }  
    
    
    private void bttnCreditsActionPerformed(java.awt.event.ActionEvent evt) {                                         
        JOptionPane.showMessageDialog(null, "Created by: Bogdan Dawabsheh and Brad Gallant. \n Original Idea by Namco and Bandai Namco, Japan","Credits",JOptionPane.PLAIN_MESSAGE);
        this.setVisible(true);
    }     
    
    private void bttnExitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.exitForm();
        }      
}


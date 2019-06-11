/*
 * |-------------------------------------------------
 * | Copyright © 2009 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.rpncalculator;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.math.BigDecimal;

/**
 * This is listener class for the menubar menuitems. 
 * @author Colin
 *
 */
public class MenubarEventListeners implements ActionListener {

	private GUI view;//View
	private Calculator_Engine calc;//Model
	private ValidOperators v;//part of Model
	private final JFileChooser fc;
	
	/*
	 * The constructor...
	 */
	public MenubarEventListeners(GUI g){
		view = g;//Registering view
		calc = new Calculator_Engine(v);
		fc = new JFileChooser();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev){
		//Check which menu item it is?
		if(ev.getSource() == view.getMenuItems().get(0)){
			initialiseFileChooser();
		}
		else if(ev.getSource() == view.getMenuItems().get(1)){
			System.exit(0);
		}
		else if(ev.getSource() == view.getMenuItems().get(2)){
			showAbout();
		}		
		else if(ev.getSource() == view.getMenuItems().get(3)){
			HelpWindow help = new HelpWindow();
			help.setVisible();
		}
	}
	
	/*
	 * Initialise the file chooser..
	 */
	private void initialiseFileChooser(){
		int returnVal = fc.showOpenDialog(view.getFrame());
        //When getting input from the filechooser..
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            openTheFile(file);
        } 
        else {
            //System.out.println("File Not Opened");
        }
	}
	
	/*
	 * Opens the file
	 * @param f - The file to be opened
	 */
	private void openTheFile(File f){
		//Create filehandler to open the file
		FileHandler of = new FileHandler();
		try{
			//For each word in the file opened.
		    for(String word : of.openFile(f)){
			    //Processing..
			    BigDecimal d = calc.main_Engine(word);
			    if(calc.errorTrue() == true){//If there's an error
				      view.getDisplayArea().setText(calc.getErrorString());
			    }
			    else{
			         view.getDisplayArea().setText("" + d.floatValue());
			    }		
		    }
		}
		catch(Exception e){
			invalidInputFileError(e.getMessage());
		}
	}
	
	/*
	 * Displays an open dialog message box indicating the error it had
	 * occurred. 
	 * @param message
	 */
	private void invalidInputFileError(String message){
		JOptionPane.showMessageDialog(view.getFrame(),
                "The file was not in a recognized file format.",
                "File load Error",
                JOptionPane.ERROR_MESSAGE);
	}
	
	/*
	 * Display the about message. A private
	 * method which is not usable to outside world.
	 */
	private void showAbout(){
	       JOptionPane.showMessageDialog(view, 
	    		   "RPN Calculator\n" + view.getVersion(),
	    		   "About RPN Calculator", 
	    		   JOptionPane.INFORMATION_MESSAGE);
	}
	
}

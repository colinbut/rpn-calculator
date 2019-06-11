/*
 * |-------------------------------------------------
 * | Copyright © 2009 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.rpncalculator;

import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * This class is the GUI class which represents the Help window. It contains the Help function.
 * @author Colin
 *
 */
public class HelpWindow {

	private JFrame frame;//The frame
		
	/*
	 * Constructor..
	 */
	public HelpWindow(){
		frame = new JFrame("Help Contents");
		frame.setPreferredSize(new Dimension(480, 600));
		frame.setMaximumSize(new Dimension(600, 600));
		frame.setResizable(true);//Can be resized
		frame.setLocation(370, 100);
		makeContentPane();//Put together the content pane
		frame.pack();
		frame.setVisible(false);
	}
	
	/*
	 * Returns the frame of this window.
	 * @return frame
	 */
	public JFrame getFrame(){
		return frame;
	}
	
	/*
	 * Puts the content pane together
	 */
	private void makeContentPane(){
		Container contentPane = frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(600, 600));
		contentPane.add(makeHelpWindow());
		JPanel temp = new JPanel();
		temp.setPreferredSize(new Dimension(480, 40));
		contentPane.add(temp, BorderLayout.SOUTH);
		
	}
	
	/*
	 * Makes the Main Help Window.
	 */
	private Container makeHelpWindow(){
		JEditorPane mainHelpWindow = new JEditorPane();
		mainHelpWindow.setDragEnabled(true);
		mainHelpWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    mainHelpWindow.setEditable(false);
	    //Read in the file
	    try{
		   InputStream in = new FileInputStream("src/main/resources/help.rtf");
		   mainHelpWindow.setContentType("text/rtf");
		   mainHelpWindow.read(in, null);
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){//Throw the Exception
			System.out.println(e.getMessage());
		}
		//Make the scroll & put the window inside it    	    
	    JScrollPane scrollPane = new JScrollPane(mainHelpWindow, 
	    		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	    		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setPreferredSize(new Dimension(1000,1000));
	   
	    return scrollPane;
	}
	
	
	/*
	 * Sets the frame visible
	 */
	public void setVisible(){
		frame.setVisible(true);
	}
}

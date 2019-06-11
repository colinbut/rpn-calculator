/*
 * |-------------------------------------------------
 * | Copyright © 2009 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.rpncalculator;

import java.awt.event.*;
import java.math.*;

/**
 * 
 * This class is the event listener class which it should contain
 * all the listeners for events occurring to every single GUI component
 * in the GUI class. 
 * 
 * It communicates which both sides getting information from model/ view and
 * updates the view and if needed the model...
 * 
 * In MVC structure this is: Controller
 * @author Colin
 *
 */
public class EventListener implements ActionListener{
    //Private fields  
	private GUI g;
	private Calculator_Engine calc;
	private ValidOperators v;
		
	/*
	 * Constructor
	 */
	public EventListener(GUI g){
		this.g = g;
		calc = new Calculator_Engine(v);
	}
	
	/*
	 * This method is main method of this class. It listens to
	 * all events for the buttons.
	 * 
	 */
	public void actionPerformed(ActionEvent event){
		String command = event.getActionCommand();
		//Do job of the main thing.. processing
		BigDecimal d = calc.main_Engine(command);//Pass to model method to parse input
		if(calc.errorTrue() == true){//If there's an error
			g.getDisplayArea().setText(calc.getErrorString());//Output back to GUI
		}
		else if(calc.getStatusString() != ""){
			g.getDisplayArea().setText(calc.getStatusString());//Output back to GUI
			calc.resetStatusString();
		}
		else{//After a number has been pressed
		g.getDisplayArea().setText("" + d.floatValue());//Output back to GUI
		}
		
	}
}

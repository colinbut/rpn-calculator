package com.mycompany.rpncalculator;

import java.math.*;
import java.util.*;
/**
 * 
 * This class is the main core of the project of a calculator.
 * It is the engine which provides all the functionality such as
 * all the arithmetic operations...
 * 
 * Such operations include traditional add, sub, mul, div along 
 * with equals. In addition to that many other RPN operations
 * are there in place - neg, dup, swap, recall, print, dot etc
 * 
 * This represents the model and has nothing to do with view.
 * 
 * In MVC structure - Model
 * 
 * @author Colin 
 * 
 * 
 */
public class Calculator_Engine {
	//The stack to hold operands & operators
	private NC_Stack stack; //The Stack
	private NumberContainers fOp;
	private NumberContainers sOp;
	private BigDecimal sum; //Temporary sum storage point..
	private BigDecimal displayValue;//Display Value on GUI
	private ValidOperators vo;//List of valid operators.
	private boolean hasDisplayValue = false;
	private boolean sumFinished;
	private boolean dotPress = false;
	private BigDecimal decimalValue = new BigDecimal(1); 
	//***************************************************
	//Memory Locations...
	private NumberContainers A;
	private NumberContainers B;
	private NumberContainers C;
	private NumberContainers D;
	//***********************************************
	private boolean isError = false;
	private String errString = ""; //Error String
	private HashMap<String, String> errorMap;//Map storing error messages
	private String statusString = "";//Representation of the calculator status.
	
	/*
	 * The Constructor...
	 * initialises everything, all needed variables..
	 */
	public Calculator_Engine(ValidOperators v){
		stack = new NC_Stack();
		//The creation of the stack...
		displayValue = new BigDecimal(0);//resetting the display value to 0
		//Has the same effect as pressing Clear button
		vo = v;
		fOp = new NumberContainers("?", 0);
		sOp = new NumberContainers("?", 0);
		A = new NumberContainers("A", 0);
		B = new NumberContainers("B", 0);
		C = new NumberContainers("C",0);
		D = new NumberContainers("D", 0);
		errorMap = new HashMap<String, String>();
		fillErrorMessages();//Fill error messages
	}
	
	/*
	 * A number pressed method to indicate that a number has
	 * been pressed.
	 * 
	 * @param number - the number being pressed.
	 */
	private void numberPressed(BigDecimal number){
		if(dotPress == false && hasDisplayValue == true){
		    displayValue = displayValue.multiply(new BigDecimal(10)).add(number);
		}
		else if(dotPress == true){//When dot been pressed
			BigDecimal temp = condition().multiply(number);
		    displayValue = displayValue.add(temp);
		}
		else{//Initial stage - nothing there yet
		   displayValue = number;
		   hasDisplayValue = true;
		}
	}
	
	/*
	 * Private method to help the numberPressed method in calculating
	 * the decimal value to be multiplied by in order to create
	 * a decimal display value.
	 * 
	 * @return decimalValue - A BigDecimal object holding the
	 *                        decimal value which needed to be 
	 *                        multiplied by when dot is pressed.
	 */
	private BigDecimal condition(){
		decimalValue = decimalValue.divide(new BigDecimal(10));
		return decimalValue;
	}
	
	/*
	 * A method indicates that dot operator has been pressed.
	 */
	private void dot(){
		dotPress = true;
	}
	
	
	//---------------------Arithmetic Operations!!!---------------//
	
	/*
	 * Addition method!
	 */
	private void add(){
		fOp = stack.sPop();
		sOp = stack.sPop();
		if(isNull() == false){
		    BigDecimal bd = new BigDecimal(fOp.getValue());
		    sum = bd.add(new BigDecimal(sOp.getValue()));
		    stack.sPush(new NumberContainers("?", sum.doubleValue()));
		    statusString = "+";
		}
		else{
			stackUnderFlowError();
		}
		
	}
    
	/*
	 * Subtraction method
	 */
	private void sub(){
		fOp = stack.sPop();
		sOp = stack.sPop();
		if(isNull() == false){
		    BigDecimal bd = new BigDecimal(sOp.getValue());
		    sum = bd.subtract(new BigDecimal(fOp.getValue()));
		    stack.sPush(new NumberContainers("?", sum.doubleValue()));
		    statusString = "-";
		}
		else{
			stackUnderFlowError();
		}
	}
	
	/*
	 * Multiplication method
	 */
	private void mul(){
		fOp = stack.sPop();
		sOp = stack.sPop();
		if(isNull() == false){
		    BigDecimal bd = new BigDecimal(fOp.getValue());
		    sum = bd.multiply(new BigDecimal(sOp.getValue()));
		    stack.sPush(new NumberContainers("?", sum.doubleValue()));
		    statusString = "x";
		}
		else{
			stackUnderFlowError();
		}
	}
	
	/*
	 * Division method
	 */
	private void div(){
		fOp = stack.sPop();
		sOp = stack.sPop();
		if(isNull() == false){
			if(fOp.getValue() == 0){//2nd operand is 0 (another error)
				isError = true;
				errString = errorMap.get("CEDBZ");//Can't divide by 0
			}
			else{
			    BigDecimal bd = new BigDecimal(fOp.getValue());
			    BigDecimal bg = new BigDecimal(sOp.getValue());
			    sum = bg.divide(bd, new MathContext(100));
			    stack.sPush(new NumberContainers("?", sum.doubleValue()));
			    statusString = "/";
			}
		}
		else{//Both operands are null
		   stackUnderFlowError();
		 }
	}
	
	//---------------------------------Function operations--------------------//
	
	/*
	 * Equals method, when equals button is
	 * pressed.
	 */
	private void eq(){
		
		if(sumFinished == true){
			//displayValue = sum;
			statusString = "Answer: " + displayValue.floatValue();
		}
		else{//Sums not finished
		fOp = stack.sPop();
		      if(fOp == null){//NO sum done - state of calculator is at start up..
			     displayValue = new BigDecimal(0);//So it displays 0
		       }
		      else{//Sum done... Finishes
		         BigDecimal bd = new BigDecimal(fOp.getValue());
		         displayValue = bd;
		         statusString = "Answer: " + displayValue.floatValue();
		         hasDisplayValue = false;
		         sumFinished = true;
		         }
		}
	}
	
	/*
	 * The enter button has been pressed. When this button is pressed
	 * it means that a number is to be pushed onto stack..
	 */
	private void enter(){
		stack.sPush(new NumberContainers("?", displayValue.doubleValue()));
		hasDisplayValue = false;
		dotPress = false;
		decimalValue = new BigDecimal(1);
	}
	
	/*
	 * Negates the top of the stack. Changing it 
	 * to a negative value and vice versa. Basically it 
	 * changes the sign of the element.
	 * 
	 * Element popped to negate then pushed back on.
	 */
	private void neg(){
		fOp = stack.sPop();
		stack.sPush(new NumberContainers("?", 0 - (fOp.getValue())));
	}
	
	/*
	 * Swaps the top 2 elements of the stack
	 */
	private void  swap(){
		//Pop 2 elements from stack
		fOp = stack.sPop();
		sOp = stack.sPop();
		//Push them back but in different order so its swapped..
		stack.sPush(fOp);
		stack.sPush(sOp);
	}
	
	/*
	 * Method that duplicates the top of the stack
	 */
	private void dup(){
		fOp = stack.sPop();
		//Push the same element twiced
		stack.sPush(fOp);
		stack.sPush(fOp);
	}
	
	/*
	 * Clears everything. Resets everything back to 
	 * initial condition... including removing all stack
	 * element..
	 */
	private void clear(){
		//reset every variable flag
		displayValue = new BigDecimal(0);
		sumFinished = false;
		dotPress = false;
		isError = false;
		statusString = "";
		decimalValue = new BigDecimal(1);
		//Reseting temporary storages..
		stack.removeAll();
		//Reseting Memory
		A.setValue(0);
		B.setValue(0);
		C.setValue(0);
		D.setValue(0);
		
		sum = new BigDecimal(0);
	}
	
	
	/*
	 * This method only prints out the current expression 
	 * value...
	 */
	private void print(){
		fOp = stack.sPop();//Get the current expression value
		if(fOp == null){//If no current expression value
			isError = true;
			errString = errorMap.get("CEPRI");
			//print error message
		}
		else{
			statusString = "Current Value: " + fOp.getValue();
			stack.sPush(fOp); //push it back onto stack.
		}
	}
	
	/*
	 * Does the assignment of the memory locations of A,B,C and D
	 */
	private void assign(){
		fOp = stack.sPop();//Pop 1st
		sOp = stack.sPop(); //Pop 2nd
		if(fOp != null && sOp != null){
		if(sOp.getMem() == "A"){
		    BigDecimal temp = new BigDecimal(fOp.getValue());
		    A.setValue(temp.doubleValue());
		    stack.sPush(A);//push back to stack with A assigned
		    statusString = "Assign to memory A";
		    }
		else if(sOp.getMem() == "B"){
			BigDecimal temp = new BigDecimal(fOp.getValue());
		    B.setValue(temp.doubleValue());
		    stack.sPush(B);//push back to stack with B assigned
		    statusString = "Assign to memory B";
		    }
		else if(sOp.getMem() == "C"){
			BigDecimal temp = new BigDecimal(fOp.getValue());
		    C.setValue(temp.doubleValue());
		    stack.sPush(C);//push back to stack with C assigned
		    statusString = "Assign to memory C";
		    }
		else if(sOp.getMem() == "D"){
			BigDecimal temp = new BigDecimal(fOp.getValue());
		    D.setValue(temp.doubleValue());
		    stack.sPush(D);//push back to stack with D assigned
		    statusString = "Assign to memory D";
		    }
		}
		else { //Some sort of error has occurred
			isError = true;
			if(fOp == null && sOp == null){
				errString = errorMap.get("CEMAS");
			 }
			else if(fOp.getMem() != "?"){
				errString = errorMap.get("CENTA");
			}
			else if(sOp == null || sOp.getMem() == "?"){
				errString = errorMap.get("CENOM");
			}
		}
		hasDisplayValue = false;
	}
	
	/*
	 * Prints out all the contents of the Memory Locations
	 */
	private void recall(){
		statusString = "A:" + A.getValue() + "\n" +
		       "B:" + B.getValue() + "\n" +
		       "C:" + C.getValue() + "\n" +
		       "D:" + D.getValue() + "\n";
		}
	
	//***************************************************************************
	
	/*
	 * The main part of the calculator engine...
	 * Does all the important stuffs..
	 * 
	 * @param n - A string from button being pressed
	 * @return displayValue - the value to be displayed
	 */
	public BigDecimal main_Engine(String n){
					        
		if(vo.parse(n) != null){ //If its an operator/function
		  switch(vo.parse(n)){
		    case Add: add(); break;
		    case Sub: sub(); break;
		    case Mul: mul(); break;
		    case Div: div(); break;
		    case Eq:  eq(); break;
		    case Neg: neg(); break;
		    case Swap: swap(); break;
		    case Dup: dup(); break;
		    case Clear: clear(); break;
		    case Dot: dot(); break;
		    case Enter: enter(); break;
		    case Print: print(); break;
		    case Recall: recall(); break;
		    case Assign: assign(); break;
		    case MEMA: stack.sPush(A); break;
		    case MEMB: stack.sPush(B); break;
		    case MEMC: stack.sPush(C); break;
		    case MEMD: stack.sPush(D); break;
		    default: 
		         System.out.println(".....");
		         break;
		 }
		}
		else{ //Its a number...
			//Parse the number - convert to a number from string representation
			BigDecimal number = new BigDecimal(Integer.parseInt(n));
			numberPressed(number);//Number pressed
			}
		return displayValue;
	}
	
	//************************************************************************
	
	//-------------------------------Other class operations--------------------//
	
	/*
	 * This method acts as a testing method used for testing
	 * purposes.
	 * It prints out the stack contents but ensure the stack
	 * contents are unmodified.
	 * This method is generally unused. 
	 *
	 */
	public void printStack(){
		stack.print();
	}
	
	/*
	 * Called initially at constructor. Fills all error messages
	 * in a HashMap so it can be retrieved later for use. 
	 */
	private void fillErrorMessages(){
		errorMap.put("CESUF", "Stack Underflow");
		errorMap.put("CESOF", "Stack Overflow");
		errorMap.put("CEMAS", "Memory Assignment Error!");
		errorMap.put("CEDBZ", "Divide by zero");
		errorMap.put("CEPRI", "No current expression value");
		errorMap.put("CESYN", "Syntax Error");
		errorMap.put("CENOM", "No Memory");
		errorMap.put("CENTA", "Nothing to assign");
	}
	
	
	/*
	 * Boolean method determines whether there's an error 
	 * occurred or not.
	 * 
	 * @return isError
	 */
	public boolean errorTrue(){
		return isError;
	}
	
	/*
	 * Returns the error string indicating what kind of 
	 * error it is.
	 * @return errString
	 * 
	 */
	public String getErrorString(){
		return errString;
	}
	
	/*
	 * Retrieve the status string
	 * @return statusString
	 */
	public String getStatusString(){
		return statusString;
	}
	
	/*
	 * Resets the status string
	 */
	public void resetStatusString(){
		statusString = "";
	}
	
	/*
	 * Returns The HashMap containing the error messages.
	 * @return errorMap
	 */
	public HashMap<String, String> getErrorMessages(){
		return errorMap;
	}
	
	/*
	 * Produce stack under flow error message.
	 */
	public void stackUnderFlowError(){
		isError = true;
		errString = errorMap.get("CESUF");
	}
	
	/*
	 * Check if the top 2 expression values is null or not.
	 */
	public boolean isNull(){
		if(sOp == null || fOp == null){
			return true;
		}
		else{
			return false;
		}
	}
}

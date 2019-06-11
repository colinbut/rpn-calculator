/*
 * |-------------------------------------------------
 * | Copyright © 2009 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.rpncalculator;

/**
 * This class is what you called a wrapper class in Object oriented programming terminology
 * and its effect is wrapping an object around an abstract data types & in this occasion its
 * a stack. The stack is containing a list of NumberContainers objects. 
 * 
 * @author Colin
 *
 */
public class NC_Stack {

	private Stack theStack;//The stack
	
	
	/*
	 * Constructor
	 */
	public NC_Stack(){
		theStack = new Stack(20);//Creates a new Stack
	}
	
	/*
	 * Check if stack is empty or not.
	 * 
	 */
	public boolean sEmpty(){
		return theStack.sEmpty();
	}
	
	/*
	 * Check that if the stack of NumberContainers are full or 
	 * not.
	 */
	public boolean sFull(){
		return theStack.sFull();
	}
	
	/*
	 * Right - this pushes an object onto the top of the stack
	 */
	public void sPush(NumberContainers x){
		theStack.sPush(x);
	}
	
	/*
	 * Pops the NumberContainer elements from the stack...
	 */
	public NumberContainers sPop(){
		
		return (NumberContainers)theStack.sPop();
	}
	
	/*
	 * Takes a look at the top item in top of the stack. 
	 */
	public NumberContainers sPeek(){
		return (NumberContainers)theStack.sPeek();
	}
	
	/*
	 * A print method is useful, going through stack using pop
	 * to remove each thing. When it's removed, print each object
	 * and store it in another stack. Then go through other stack
	 * and go through one by one moving back to original stack.
	 */
	public void print(){
		NC_Stack temp = new NC_Stack();//A temporary object of this class
		
		NumberContainers current = sPop();//Get the current element
		while(current != null){ //And while thats not null meaning end of stack not reached yet
			temp.sPush(current); //Add to temporary stack
			current = sPop(); //Get next object..
		}
		
		//Do something to print it out...
		
		current = temp.sPop();
		while(current != null){
			sPush(current); //Put back onto original stack
			current = sPop(); //Get next.
		}
	}
	
	/*
	 * This method will remove all elements in the stack.
	 * It is used when we nead to clear everything from the
	 * calculator..
	 */
	public void removeAll(){
        while(!sEmpty()){ /*While stack is not empty
			             meaning there's still some in it*/
			sPop(); //Pop from stack, remove it...
			//Throwing it away!
		}
	}
	
	
}

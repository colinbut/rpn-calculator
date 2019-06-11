package com.mycompany.rpncalculator;

/************************************************************************
 * This represents the stack. It is totally functioning of a stack. 
 * Stack is constructed using an array where its an example of a 
 * contigiuos method.
 * 
 * see also stack.java from uni
 * 
 * @author Colin
 *
 */

public class Stack implements StackInterface {
	
	public static final int SIZE = 1000;
	private int max;//Maximum size the array can hold...
	private Object data[];
	private int head;
	private int tail;
	
	/*
	 * The constructor
	 */
	public Stack(int s){
		max = s;
		data = new Object[max];
		head = -1;//Start of stack
		tail = -1;//Bottom of stack
	}

    /*
     * (non-Javadoc)
     * @see StackInterface#sEmpty()
     */
	public boolean sEmpty(){
		if(tail < 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see StackInterface#sFull()
	 */
	public boolean sFull(){
		return (head == max);
    }

	/*
	 * (non-Javadoc)
	 * @see StackInterface#sPush(java.lang.Object)
	 */
	public void sPush(Object x){
		if(sEmpty()){ //If empty
			head++; //increment head
			tail++; //increment tail as well because this 1st & only in stack..
			data[head] = x;
		}
		else{//Just add on to next position
			head++;
			data[head] = x;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see StackInterface#sPop()
	 */
	public Object sPop(){
		Object temp = null;
		if(!sEmpty()) {//If it's not empty
			 temp = data[head];
			 head--;
			if(head == -1) {
				 tail--;
				 //head = -1;
			 }
		}
		else{//Stacks empty
			return null;
		}
		return temp;//Return the object that is removed from stack

	}
	
	/*
	 * (non-Javadoc)
	 * @see StackInterface#sPeek()
	 */
	public Object sPeek(){
		return data[head];
	}
	
	/*
	 * Get the array contents from the stack which is made up of an array
	 */
	public Object[] getArrayContents(){
		return data;
	}
}

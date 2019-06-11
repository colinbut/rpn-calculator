package com.mycompany.rpncalculator;

public interface StackInterface {
	
	//This is the interface class for the stack data structure.
	//A stack class must inherit this...
	
	public boolean sEmpty();
	public boolean sFull();
	public void sPush(Object x);
	public Object sPop();
	public Object sPeek();

}

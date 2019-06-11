/*
 * |-------------------------------------------------
 * | Copyright © 2009 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.rpncalculator;

/**
 * This class is about NumberContainers. They are objects which the object is made of 2 
 * properties with a string value of memory. In addition it provides a value to
 * store the value of the object. 
 * 
 * See Calculator_Engine.java
 * 
 * @author Colin
 * 
 */
public class NumberContainers {
     
	private String mem; //What memory is it?
	private double value; //The number associated!
	
	
	/*
	 * Constructor
	 */
	public NumberContainers(String m, double v){
		mem = m;
		value = v;
	}
	
	/*
	 * Returns the mem property.
	 * @return mem
	 */
	public String getMem(){
		return mem;
	}
	
	/*
	 * Returns the value property it's holding
	 * @return value
	 */
	public double getValue(){
		return value;
	}
	
	/*
	 * Sets the value property.
	 * @param v
	 */
	public void setValue(double v){
		value = v;
	}
	
	/*
	 * Set the mem property.
	 * @param m
	 */
	public void setMem(String m){
		mem = m;
	}
}

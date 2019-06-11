package com.mycompany.rpncalculator;

import java.util.*;

/**
 * @author Colin
 * 
 * This is an ENUM
 * 
 * Valid operators allowed. If operators pressed after a number is 
 * not belonging to this then its an error...
 * 
 * Unlikely for error to occur..
 * 
 * Included for completion!
 */
public enum ValidOperators {
	
	Add("+"), Sub("-"), Mul("x"), Div("/"), 
	Eq("="), Neg("Neg"), Swap("Swap"), Dup("Dup"), Numbers("?"),
	Enter("Enter"), Clear("Clear"), Dot("."), Print("Print"),
	Recall("Recall"), Assign("Assign"), MEMA("A"), MEMB("B"), 
	MEMC("C"), MEMD("D");
	
	private String symbol;
	
	
	ValidOperators(String symbol){
		this.symbol = symbol;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	/*
	 * A map storing the values of Enums with their
	 * representative form.
	 */
	private static Map<String, ValidOperators> map 
	= new HashMap<String, ValidOperators>();
    static {
        put(Add);
        put(Sub);
        put(Mul);
        put(Div);
        put(Eq);
        put(Enter);
        put(Neg);
        put(Swap);
        put(Dup);
        put(Numbers);
        put(Clear);
        put(Dot);
        put(Print);
        put(Recall);
        put(Assign);
        put(MEMA);
        put(MEMB);
        put(MEMC);
        put(MEMD);
    }
    
    /*
     * Puts the enums in map
     */
    private static void put(ValidOperators e) {
        map.put(e.getSymbol(), e);
    }
    
    /*
     * Gets the symbol property of ENUM
     */
    public static ValidOperators parse(String symbol) {
        return map.get(symbol);
    }

}

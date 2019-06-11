/*
 * |-------------------------------------------------
 * | Copyright © 2009 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.rpncalculator;

import java.util.*;

/******************************************************************************
 * This class is an example of java programming technique which involves the
 * use of enums (Enumerations). 
 * 
 * @author Colin
 *
 */
public class EnumExample {
    enum E {
        STAR("*"), HASH("#");
 
        private String symbol;
 
        private static Map<String, E> map = new HashMap<String, E>();
        static {
            put(STAR);
            put(HASH);
        }
 
        public String getSymbol() {
            return symbol;
        }
 
        private E(String symbol) {
            this.symbol = symbol;
        }
 
        private static void put(E e) {
            map.put(e.getSymbol(), e);
        }
 
        public static E parse(String symbol) {
            return map.get(symbol);
        }
    }
 
    public static void main(String[] args) {
        System.out.println(E.valueOf("STAR")); //succeeds
        System.out.println(E.parse("*")); //succeeds
 
        System.out.println(E.parse("STAR")); //fails: null
        System.out.println(E.valueOf("*")); //fails: IllegalArgumentException
    }
}



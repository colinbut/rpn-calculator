/*
 *
 */
package com.mycompany.rpncalculator;

import java.io.*;

/**
 * This class function is to provide the ability to handle files such as opening
 * files with the specified file. It reads the file and trims out trailing 
 * white spaces at the end of file and also takes each line and splits the
 * blank spaces between words. It then puts them into the array.
 * 
 * @author Colin
 *
 */
public class FileHandler {
	
	private String[] commandList;//List of commands from input file
	
	/*
	 * Constructor...
	 */
	public FileHandler(){
		commandList = new String[200];//Just a random integer to initialise the array
	}
	
	/*
	 * Returns an array of Strings representing those RPN expressions. It is then 
	 * passed on to another method to process the job of reading those expressions into the
	 * engine. This method opens the file for reading and reads every line.
	 * 
	 * @return commandList
	 * @param f The file
	 */
	public String[] openFile(File f){
		String line = "";
		try{
			//Creates a reader for reading file
			BufferedReader reader = new BufferedReader(new 
					FileReader(f));
			
			line = reader.readLine().trim(); //Reads line and trim out spaces
			while(line != null){ //While line is not nothing...
				//do something with line
				commandList = line.split(" ");//Split the blank spaces...
				line = reader.readLine();//Read next line
			}
			reader.close(); //Close reader
		}
		//Catches the Exceptions
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		return commandList;/*Return the list of commands recieved from file to pass
		                   to model for processing...FileHandler */
	}
	
	/*
	 * A method for printing the list of items in the array of commandList.
	 * It serves the purpose as a testing method making sure the array is working
	 * fine.
	 * 
	 * This method is normally not used but acts as an aid for 
	 * future preferences..
	 */
	public void printCommands(){
				
		int j = 0;
		for(String words : commandList){
			System.out.println(j + ":" + words);
			j++;
		}
	}

}

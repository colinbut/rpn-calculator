package com.mycompany.rpncalculator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class is the GUI class which represents the graphical
 * user interface. In MVC structure this is the View in which it
 * displays information of the program to the users.
 * 
 * This class only represents the view. In other words the UI(User
 * interface) - GUI
 * 
 * Major event listeners are somewhere else.
 * 
 * see also EventListener.java
 * 
 * @author Colin
 * 
 */
public class GUI extends JFrame{
	private JFrame frame;//Frame
	private EventListener eListener;
	private MenubarEventListeners mListener;
	private static final String VERSION = "Version 1.0"; //Version
	private JTextArea dArea;
	private JButton on_off;
	private boolean mode = false;//The mode referring to state of calculator
	private ArrayList<JMenuItem> listOfMenuItems;//List of menu items
	private ArrayList<JButton> listOfButtons;//List of buttons
	
	/*
	 * The constructor...
	 */
	public GUI(){
		frame = new JFrame("RPN Calculator");
		listOfMenuItems = new ArrayList<JMenuItem>();
		listOfButtons = new ArrayList<JButton>();
		//**************************************
		
		on_off = new JButton("On");
		eListener = new EventListener(this);//Register the listeners
		mListener = new MenubarEventListeners(this);
		dArea = new JTextArea();
		makeContentPane(); /*Makes the content pane putting
		                   everything together.*/
		
		//For all buttons - set them disabled at start up
		for(JButton button : listOfButtons){
			button.setEnabled(false);
		}
				
		//Closes the window frame!
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.pack();//Pack everything together.
		frame.setVisible(false);
	}
	
	/*
	 * Returns the frame
	 * @return frame
	 */
	public JFrame getFrame(){
		return frame;
	}
			
	/*
	 * Make the GUI visible to outside.
	 */
	public void makeVisible(){
		frame.setVisible(true);//Make User Interface Visible
	}
	
	/*
	 * Returns the list of buttons
	 * @return listOfButtons
	 */
	public ArrayList<JButton> getButtonList(){
		return listOfButtons;
	}
	
	/*
	 * Makes the content Pane and puts everything together!
	 * 
	 */
	public void makeContentPane(){
		makeMenu();//Make menu
		frame.setLocation(400, 300);
		frame.setResizable(false);//Can't resize
		frame.setMinimumSize(new Dimension(350, 330));
		Container contentPane = frame.getContentPane();
		frame.setPreferredSize(new Dimension(350,330));
		contentPane.setLayout(new GridLayout(0,2));
			
		//Put things together
		contentPane.add(displayArea());
		contentPane.add(FunctionArea());
		contentPane.add(numberButtons());
		contentPane.add(operatorButtons());
	
	}
	
	/*
	 * A JPanel which contains the number buttons
	 * @return innerPanel - the inner panel which represents
	 *                      the number buttons area.
	 */
	public JPanel numberButtons(){
		JButton one,two,three,four,five,six,seven,eight,nine,zero,dot;
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		zero = new JButton("0");
		dot = new JButton(".");
		
		listOfButtons.add(one);
		listOfButtons.add(two);
		listOfButtons.add(three);
		listOfButtons.add(four);
		listOfButtons.add(five);
		listOfButtons.add(six);
		listOfButtons.add(seven);
		listOfButtons.add(eight);
		listOfButtons.add(nine);
		listOfButtons.add(zero);
		listOfButtons.add(dot);
				
		one.addActionListener(eListener);
		two.addActionListener(eListener);
		three.addActionListener(eListener);
		four.addActionListener(eListener);
		five.addActionListener(eListener);
		six.addActionListener(eListener);
		seven.addActionListener(eListener);
		eight.addActionListener(eListener);
		nine.addActionListener(eListener);
		zero.addActionListener(eListener);
		dot.addActionListener(eListener);
		
		on_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(mode == false){
				  for(JButton button : listOfButtons){
					  button.setEnabled(true);
					  on_off.setLabel("Off");
					  mode = true;
				  }
				}
				else{
					for(JButton button : listOfButtons){
						 button.setEnabled(false);
						 on_off.setLabel("On");
						 mode = false;
					  }
				}
				
			}
		});
		
		JPanel onePanel = new JPanel();
		onePanel.add(one);
		JPanel twoPanel = new JPanel();
		twoPanel.add(two);
		JPanel threePanel = new JPanel();
		threePanel.add(three);
		JPanel fourPanel = new JPanel();
		fourPanel.add(four);
		JPanel fivePanel = new JPanel();
		fivePanel.add(five);
		JPanel sixPanel = new JPanel();
		sixPanel.add(six);
		JPanel sevenPanel = new JPanel();
		sevenPanel.add(seven);
		JPanel eightPanel = new JPanel();
		eightPanel.add(eight);
		JPanel ninePanel = new JPanel();
		ninePanel.add(nine);
		JPanel zeroPanel = new JPanel();
		zeroPanel.add(zero);
		JPanel dotPanel = new JPanel();
		dotPanel.add(dot);
		JPanel onOffPanel = new JPanel();
		onOffPanel.add(on_off);
		
		JPanel innerPanel = new JPanel(new GridLayout(4,3));
		innerPanel.add(onePanel);
		innerPanel.add(twoPanel);
		innerPanel.add(threePanel);
		innerPanel.add(fourPanel);
		innerPanel.add(fivePanel);
		innerPanel.add(sixPanel);
		innerPanel.add(sevenPanel);
		innerPanel.add(eightPanel);
		innerPanel.add(ninePanel);
		innerPanel.add(zeroPanel);
		innerPanel.add(dotPanel);
		innerPanel.add(onOffPanel);
				
		return innerPanel;
	}
	
	/*
	 * The operator buttons..
	 * @return innerPanel - the inner panel which stands for
	 *                      the area of operator buttons.
	 */
	public JPanel operatorButtons(){
		JButton add,sub,mul,div,eq,enter;
		
		add = new JButton("+");
		sub = new JButton("-");
		mul = new JButton("x");
		div = new JButton("/");
		eq = new JButton("=");
		enter = new JButton("Enter");
		
		listOfButtons.add(add);
		listOfButtons.add(sub);
		listOfButtons.add(mul);
		listOfButtons.add(div);
		listOfButtons.add(eq);
		listOfButtons.add(enter);
			
		add.addActionListener(eListener);
		sub.addActionListener(eListener);
		mul.addActionListener(eListener);
		div.addActionListener(eListener);
		eq.addActionListener(eListener);
		enter.addActionListener(eListener);
		
		JPanel innerPanel = new JPanel(new GridLayout(3,2));
		
		JPanel addPanel = new JPanel();
		addPanel.add(add);
		JPanel subPanel = new JPanel();
		subPanel.add(sub);
		JPanel mulPanel = new JPanel();
		mulPanel.add(mul);
		JPanel divPanel = new JPanel();
		divPanel.add(div);
		JPanel eqPanel = new JPanel();
		eqPanel.add(eq);
		JPanel enterPanel = new JPanel();
		enterPanel.add(enter);
				
		innerPanel.add(addPanel);
		innerPanel.add(subPanel);
		innerPanel.add(mulPanel);
		innerPanel.add(divPanel);
		innerPanel.add(eqPanel);
		innerPanel.add(enterPanel);
		
		return innerPanel;
	}
	
	/*
	 * The display area in which it signals.
	 * @return displayPanel - the panel which shows both buttons 
	 *                        with the display area.
	 */
	private JPanel displayArea(){
		JPanel displayPanel = new JPanel(new GridLayout(2,1));
		//Display area settings..		
		dArea.setBorder(BorderFactory.createBevelBorder(1));
		dArea.setEditable(false);
		dArea.setPreferredSize(new Dimension(200,75));
		
		//----------------------------------------------------------//
		
		JPanel innerPanel = new JPanel(new GridLayout(2,2));
		//Creates those buttons		
		JButton clear = new JButton("Clear");
		JButton print = new JButton("Print");
		JButton recall = new JButton("Recall");
		
		listOfButtons.add(clear);
		listOfButtons.add(print);
		listOfButtons.add(recall);
		
		//Assigns them listeners
		clear.addActionListener(eListener);
		print.addActionListener(eListener);
		recall.addActionListener(eListener);
		//Provide buttons the panel to be put into
		JPanel clearPanel = new JPanel();
		clearPanel.add(clear);
		JPanel printPanel = new JPanel();
		printPanel.add(print);
		JPanel recallPanel = new JPanel();
		recallPanel.add(recall);
		//Combine together		
		innerPanel.add(clearPanel);
		innerPanel.add(printPanel);
		innerPanel.add(recallPanel);
		//Join the display area with the buttons
		displayPanel.add(dArea);
		displayPanel.add(innerPanel);
		return displayPanel;
	}
	
	/*
	 * The area which has the functions plus extra functions
	 * and also the memory location goes here...
	 * @return FArea - the panel which has the memory buttons plus
	 *                 extra functions.
	 */
	private JPanel FunctionArea(){
		JPanel FArea = new JPanel(new GridLayout(4,3));
		//Create the buttons
		JButton memA = new JButton("A");
		JButton memB = new JButton("B");
		JButton memC = new JButton("C");
		JButton memD = new JButton("D");
		JButton neg = new JButton("Neg");
		JButton swap = new JButton("Swap");
		JButton dup = new JButton("Dup");
		JButton assign = new JButton("Assign");
		
		listOfButtons.add(memA);
		listOfButtons.add(memB);
		listOfButtons.add(memC);
		listOfButtons.add(memD);
		listOfButtons.add(neg);
		listOfButtons.add(swap);
		listOfButtons.add(dup);
		listOfButtons.add(assign);
		
		//Provide action listeners for the buttons on events
		memA.addActionListener(eListener);
		memB.addActionListener(eListener);
		memC.addActionListener(eListener);
		memD.addActionListener(eListener);
		neg.addActionListener(eListener);
		swap.addActionListener(eListener);
		dup.addActionListener(eListener);
		assign.addActionListener(eListener);
		
		//Creates panels for the buttons & adds them to it
		JPanel APanel = new JPanel();
		APanel.add(memA);
		JPanel BPanel = new JPanel();
		BPanel.add(memB);
		JPanel CPanel = new JPanel();
		CPanel.add(memC);
		JPanel DPanel = new JPanel();
		DPanel.add(memD);
		JPanel negPanel = new JPanel();
		negPanel.add(neg);
		JPanel swapPanel = new JPanel();
		swapPanel.add(swap);
		JPanel dupPanel = new JPanel();
		dupPanel.add(dup);
		JPanel assPanel = new JPanel();
		assPanel.add(assign);
		
		//Adds the button panels to one big panel
		FArea.add(APanel);
		FArea.add(BPanel);
		FArea.add(CPanel);
		FArea.add(DPanel);
		FArea.add(negPanel);
		FArea.add(swapPanel);
		FArea.add(dupPanel);
		FArea.add(assPanel);
		return FArea;
	}
	
	/*
	 * Makes the Menu bar!
	 */
	private void makeMenu(){
		//Initialise the menu bar
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar); //Sets the menu bar to the frame
		
		//Creates the menus on the menu bar.
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		JMenu HelpMenu = new JMenu("Help");
		menubar.add(HelpMenu);
		
		/* Creates menu items for File menu and assigns them their
		   listeners.*/
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		openItem.addActionListener(mListener);
		
		JMenuItem quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
		quitItem.addActionListener(mListener);
		
		/* Creates menu items for File menu and assigns them their
		 * listeners.
		 */
		JMenuItem aboutItem = new JMenuItem("About");
		HelpMenu.add(aboutItem);
		aboutItem.addActionListener(mListener);
		
		JMenuItem helpTopics = new JMenuItem("Help Topics");
		HelpMenu.add(helpTopics);
		helpTopics.addActionListener(mListener);
		
		/*Add it all to an array list so it can be used later for other
		classes*/
		listOfMenuItems.add(openItem);
		listOfMenuItems.add(quitItem);
		listOfMenuItems.add(aboutItem);
		listOfMenuItems.add(helpTopics);
	}
	
	/*
	 * Returns the display area.
	 * @return dArea - the display area.
	 */
	public JTextArea getDisplayArea(){
		return dArea;
	}
	
	/*
	 * Returns version of Program
	 * @return VERSION
	 */
	public String getVersion(){
		return VERSION;
	}
	
	/*
	 * Returns array list of JMenuItems
	 * @return listOfMenuItems
	 */
	public ArrayList<JMenuItem> getMenuItems(){
		return listOfMenuItems;
	}
	
	

}

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

/*  Project Name: StampPad
	Description:
	StampPad is a tool designed to assist me in accessing 
	password locked tools on my desktop. 

	Completed:
	Primary Functions
	-Frame 
	-Text
	-Copy Button
	-CSV File with data reading
	ToDo:
	Secondary Functions
	-Total of 5 buttons and text fields
	-Pincode Lock to access passwords
	-encrypted or otherwise secured CSV
	Tertiary Functions
	-Make it look good
	-Update Password function
	-customizable number of fields and buttons
*/
public class StampPad implements ActionListener{

	//Key varibles defining the buttons, textfeilds, the panel orginizing the buttons and feilds, and the frame its self that will be displayed to the user
	JFrame frame;
	JTextField textField;
	JButton copyButtons;
	JPanel panel;

	//The StampPad class and all of the definitions for the various entities
	StampPad(){

		//Setting size and behavior of the key entities
		frame = new JFrame("StampPad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		String line = "";

		//reading the CSV
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("secret.csv"));
			while ((line = br.readLine()) != null) {
				
				//assigning the testfield with the password
				String[] values = line.split(",");

				textField = new JTextField(values[0]);
			
			}
			//File read error
		} catch (Exception e) {
			System.out.println("Error in file read");
		}
		
		//copy buttons,orginizing panel, and frame 
		copyButtons = new JButton();
		//copybutton actionListener 
		copyButtons.addActionListener(this);
		copyButtons.setText("Copy");

		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4,2,10,10));
		panel.add(textField);
		panel.add(copyButtons);

		frame.add(panel);
		frame.setVisible(true);

	}
	//StampPad Main constructs StampPad
	public static void main(String[] args) {
		StampPad stamp = new StampPad();
	}

	//action for actionlistener 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == copyButtons){
			StringSelection stringSelection = new StringSelection (textField.getText());
			Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			clpbrd.setContents (stringSelection, null);
		}
		
	}

}

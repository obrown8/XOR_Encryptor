/**
 * @author Owen Brown and Mathew Boivin
 * Uploaded: November 29, 2018
 *
 *	XOR Encryptor - This program encrypts messages by XORing the ascii value of the string
 *		with a specific key to get a message in binary. It can also accept binary and 
 *		decrypt a message from that the opposite way.
 */
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class XORTester extends JFrame implements ActionListener{
	
	JButton encB, decB;
	JTextField input; 
    	JTextArea output;
    	JLabel msgLabel;
    
    
   	String asciikey = "", ascii = "", message;
    	char key = 0;
    	int yourInt = 0;
   	StringBuilder asciimsg = new StringBuilder("");

    	public XORTester(){
		//frame
		setTitle("Encryption/Decryption");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//panels
		JPanel p1, p2;
		p1 = new JPanel();
		p2 = new JPanel();

		//elements
		msgLabel = new JLabel("Message to encrypt/decrypt: ");
		input = new JTextField(50);

		encB = new JButton("Encrypt!");
		encB.addActionListener(this);

		decB = new JButton("Decrypt!");
		decB.addActionListener(this);

		output = new JTextArea(20,15);

		p1.add(msgLabel);
		p1.add(input);
		p1.add(encB);
		p1.add(decB);
		p2.add(output);

		Box main = new Box(BoxLayout.Y_AXIS);
		main.add(p1);
		main.add(p2);

		add(main);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		message = input.getText();
		key = 'U';

		asciikey = Integer.toBinaryString((int) key);
		while(asciikey.length() < 8) {
		   asciikey = "0" + asciikey;
		}
		if(e.getSource() == encB){
		    //encrypt

			for(int i = 0; i < message.length(); i++) {
				yourInt = (int) message.charAt(i);
				ascii = Integer.toBinaryString(yourInt);
				while(ascii.length() < 8) {
					ascii = "0" + ascii;
				}
				output.append(XOR(ascii).toString());
			}
			output.append("\n");
			asciimsg = new StringBuilder("");
		}

		else if (e.getSource() == decB) {
		    //decrypt
			String asciiTemp = "";
			int count = 0;
			for(int i = 0; i < (message.length() / 8); i++) {
				for(int j = 0; j < 8; j++){
					ascii = Character.toString(message.charAt(count));
					count++;
					asciiTemp += (ascii);
				}
				output.append(BackToString(XOR(asciiTemp).toString()));
				asciiTemp = "";
			}
			output.append("\n");
			asciimsg = new StringBuilder("");
		}
	 }

	public StringBuilder XOR(String a) {
		asciimsg = new StringBuilder("");
		for(int j = 0; j < a.length(); j++){
			if(a.charAt(j) == asciikey.charAt(j))
				asciimsg.append("0");
			else
				asciimsg.append("1");
		}
		return asciimsg;
	}

	public String BackToString(String s) {
		//make sure binary is all 1s and 0s
		 for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != '1' && s.charAt(i) != '0') {
			    return null;
			}
		    }

		 //make sure s.length is a multiple of 8
		 if(s.length() % 8 != 0) {
			 return null;
		 }

		 //split the string into 8 bit segments
		 String retMsg = "";
		 String decMsg = "";
		 for(int i = 0; i < s.length() - 7; i += 8) {
			 decMsg += s.substring(i, i + 8) + " ";
		 }

		 String[] bytes = decMsg.split(" ");
		 for(int i = 0; i < bytes.length; i++) {
			 retMsg += (char)Integer.parseInt(bytes[i], 2);
		 }

		 return retMsg;
	    }

	public static void main(String[] args){
		Tester t = new Tester();
	}
}

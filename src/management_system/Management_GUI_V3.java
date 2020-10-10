package management_system;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;

import javax.swing.*;

public class Management_GUI_V3 implements ActionListener {

	/*UNFINISHED MANAGEMENT SYSTEM -> NEED TO IMPLEMENT NeXT VERSION*/
	/*IF YOU READ THIS, PRANAV, WORK ON IT!!*/
	
	private JFrame frame;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3;
	private JTextField textField;
	private JLabel lab1;
	Instant start, end;
	static ArrayList<Student> students = new ArrayList<Student>();
	private boolean entered = false;
	
	public Management_GUI_V3() {
		frame = new JFrame();
		frame.setTitle("Secres Industries Attendance");
		frame.setBounds(0, 0, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 20, 600, 500);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 40, 400, 300);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 600, 20);
		frame.getContentPane().add(menuBar);
		
		menu1 = new JMenu("File");
		menu2 = new JMenu("Help");
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		item1 = new JMenuItem("New...");
		
		menu1.add(item1);
		item1.addActionListener(this);
		
		item2 = new JMenuItem("Search");
		item3 = new JMenuItem("About");
		
		menu2.add(item2);
		menu2.add(item3);
		
		textField = new JTextField();
		textField.setBounds(20, 370, 300, 25);
		textField.addActionListener(this);
		panel.add(textField);
		
		lab1 = new JLabel("Type id:");
		lab1.setBounds(20, 350, 100, 20);
		panel.add(lab1);
		
		frame.setVisible(true);
		textField.requestFocus();
		
		students.add(new Student("2231044", "Pranav Amarnath", false));
		students.add(new Student("2191341", "Tarun Amarnath", false));
		students.add(new Student("2231764", "Steve Mathew", false));
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==textField){
			String content = textField.getText();
			int i;
			for(i=0; i<students.size(); i++) {
				if(students.get(i).id.equals(content)) {
					if(students.get(i).signedIn == false) {
						textArea.append("Signed in: " + students.get(i).name + "\n");
						start = Instant.now();
						students.get(i).signedIn = true;
						entered = true;
						break;
					}
					else if(students.get(i).signedIn == true) {
						//textArea.append(content + "\n");
						textArea.append("Signed out: " + students.get(i).name);
						end = Instant.now();
						Duration timeElapsed = Duration.between(start, end);
						textArea.append(":  " + timeElapsed + "\n");
						students.get(i).signedIn = false;
						entered = true;
						break;
					}
				}
			}
			if(entered != true) {
				textArea.append("INVALID ID: Please try again." + "\n");
			}
			entered = false;
			textField.setText("");
		}
		else if(e.getSource()==item1) {
			new NewStudent();
		}
	}
	
	public static void main(String[] args) {
		new Management_GUI_V3();
	}

}

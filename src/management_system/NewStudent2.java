package management_system;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("unused")
public class NewStudent2 implements ActionListener {

	private static JFrame frame;
	private JPanel panel;
	private JTextField textField1, textField2;
	private JLabel lab1, lab2;
	
	public NewStudent2() {
		frame = new JFrame();
		frame.setTitle("Create New User");
		frame.setSize(400, 300);
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(UIManager.getLookAndFeel()!=Management_GUI_V4.dark && UIManager.getLookAndFeel()!=Management_GUI_V4.dark2) {
			frame.getContentPane().setBackground(Color.WHITE);
		}
		
		frame.setLayout(null);
		
		/*try {
			frame.setIconImage(ImageIO.read(new File("src/management_system/Icon2.png")));
		} catch (IOException e) { }*/
		
		panel = new JPanel();
		panel.setSize(400, 300);
		
		if(UIManager.getLookAndFeel()!=Management_GUI_V4.dark && UIManager.getLookAndFeel()!=Management_GUI_V4.dark2) {
			panel.setBackground(Color.WHITE);
		}
		
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		textField1 = new JTextField();
		textField1.setBounds(100, 60, 180, 25);
		textField1.addActionListener(this);
		textField1.requestFocus();
		panel.add(textField1);
		
		textField2 = new JTextField();
		textField2.setBounds(100, 160, 180, 25);
		textField2.addActionListener(this);
		panel.add(textField2);
		
		lab1 = new JLabel("Enter new id:");
		lab1.setBounds(100, 30, 200, 20);
		panel.add(lab1);
		
		lab2 = new JLabel("Enter name:");
		lab2.setBounds(100, 130, 100, 20);
		panel.add(lab2);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String content = "0";
		String name = "0";
		if(e.getSource() == textField1) {
			textField2.requestFocus();
		}
		if(e.getSource() == textField2) {
			content = textField1.getText();
			if(!(textField2.getText().equals("") && textField2.getText().equals(" ") && textField2.getText().equals("0"))) {
				name = textField2.getText();
			}
		}
		if(content!="0" || name!="0") {
			boolean retrievedId = false;
			/*for (int i = 0; i<Management_GUI_V4.students.size(); i++) {
				System.out.print(Management_GUI_V4.students.get(i).id + " ");
				System.out.print(Management_GUI_V4.students.get(i).name + " ");
				System.out.println(Management_GUI_V4.students.get(i).signedIn);
		    }*/
			/*
			Management_GUI_V4.foundId = false;
			Management_GUI_V4.readTxtFile(content);
			if(Management_GUI_V4.foundId) {
				retrievedId = true;
			}
			if(retrievedId) {
				JOptionPane.showMessageDialog(frame, "This id is already taken. Please contact your system administrator if there was an error.", "Id taken", 0);
			}
			*/
			//else {
				Student2 student = new Student2(content, name, false, "0");
				Management_GUI_V4.students.add(student);
				Management_GUI_V4.writeTxtFile("resources/idData.txt");
				Management_GUI_V4.writeCsvFile("resources/data.csv");
			//}
			textField1.setText("");
			textField2.setText("");
			textField1.requestFocus();
		}
	}

	static JFrame getFrame() {
		return frame;
	}
	
}

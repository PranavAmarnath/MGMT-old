package management_system;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class NewStudent implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JTextField textField1, textField2;
	private JLabel lab1, lab2;
	
	public NewStudent() {
		frame = new JFrame();
		frame.setTitle("Create New Person");
		frame.setBounds(0, 0, 500, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 20, 500, 400);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		textField1 = new JTextField();
		textField1.setBounds(20, 100, 200, 25);
		textField1.addActionListener(this);
		textField1.requestFocus();
		panel.add(textField1);
		
		textField2 = new JTextField();
		textField2.setBounds(20, 200, 200, 25);
		textField2.addActionListener(this);
		panel.add(textField2);
		
		lab1 = new JLabel("Enter new id:");
		lab1.setBounds(20, 70, 100, 20);
		panel.add(lab1);
		
		lab2 = new JLabel("Enter name:");
		lab2.setBounds(20, 170, 100, 20);
		panel.add(lab2);
		
		frame.setVisible(true);		
	}

	public void actionPerformed(ActionEvent e) {
		String content = "0";
		String name = "0";
		//if(e.getSource() == textField1) {
			content = textField1.getText();
		//}
		if(e.getSource() == textField2) {
			name = textField2.getText();
		}
		if(content!="0" || name!="0") {
			Management_GUI_V3.students.add(new Student(content, name, false));
			for (int i = 0; i<Management_GUI_V3.students.size(); i++) {
				System.out.print(Management_GUI_V3.students.get(i).id + " ");
				System.out.print(Management_GUI_V3.students.get(i).name + " ");
				System.out.println(Management_GUI_V3.students.get(i).signedIn);
		    }
			textField1.setText("");
			textField2.setText("");
			textField1.requestFocus();
		}
	}
	
}

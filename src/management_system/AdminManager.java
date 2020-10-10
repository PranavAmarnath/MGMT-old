package management_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

@SuppressWarnings("unused")
public class AdminManager implements ActionListener {

	private static JFrame frame;
	private JPanel panel;
	private JTextField textField;
	static JPasswordField passField;
	private JLabel lab1, lab2;
	static boolean entered;
	
	public AdminManager() {
		frame = new JFrame();
		frame.setTitle("Admin Privileges Required");
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(UIManager.getLookAndFeel()!=Management_GUI_V4.dark && UIManager.getLookAndFeel()!=Management_GUI_V4.dark2) {
			frame.getContentPane().setBackground(Color.WHITE);
		}
		
		frame.setLayout(null);
		
		/*try {
			frame.setIconImage(ImageIO.read(new File("img/Icon.png")));
		} catch (IOException e) { }*/
		
		panel = new JPanel();
		panel.setBounds(0, 0, 400, 300);
		
		if(UIManager.getLookAndFeel()!=Management_GUI_V4.dark && UIManager.getLookAndFeel()!=Management_GUI_V4.dark2) {
			panel.setBackground(Color.WHITE);
		}
			
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setBounds(100, 60, 180, 25);
		textField.addActionListener(this);
		textField.requestFocus();
		panel.add(textField);
		
		passField = new JPasswordField();
		passField.setBounds(100, 160, 180, 25);
		passField.addActionListener(this);
		panel.add(passField);
		passField.setVisible(false);
		
		lab1 = new JLabel("Enter Administrator Username:");
		lab1.setBounds(100, 30, 200, 20);
		panel.add(lab1);
		
		lab2 = new JLabel("Enter Administrator Password:");
		lab2.setBounds(100, 130, 200, 20);
		panel.add(lab2);
		lab2.setVisible(false);
		
		frame.setVisible(true);
	}
	
	static JFrame getFrame() {
		return frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==textField) {
			if(textField.getText().equals("admin")) {
				lab2.setVisible(true);
				passField.setVisible(true);
				passField.requestFocus();
			}
		}
		else if(e.getSource()==passField) {
			if(String.valueOf(passField.getPassword()).equals("lancerrobotics")) {
				entered = true;
				frame.setVisible(false);
				frame.dispose();
				new NewStudent2();
				NewStudent2.getFrame().setIconImage(Management_GUI_V4.icon.getImage());
				System.setProperty("apple.awt.application.name", "MGMT"); // mac header on mac menubar
			}
			else {
				passField.setText("");
			}
		}
	}
	
}

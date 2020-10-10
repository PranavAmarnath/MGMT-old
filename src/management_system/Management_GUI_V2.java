package management_system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Management_GUI_V2 implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3;
	private JTextField textField;
	private JLabel lab1;
	boolean signedIn = false;
	Instant start, end;
	Map<Long,String> map=new HashMap<Long,String>();
	
	public Management_GUI_V2() {
		frame = new JFrame();
		frame.setTitle("Secres Industries");
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
		
		item1 = new JMenuItem("Add in .txt");
		
		menu1.add(item1);
		
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
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==textField){
			long content = Long.parseLong(textField.getText());
			//long id = 2231044;
			map.put((long) 2231044,"Pranav Amarnath");
			map.put((long) 2191345,"Steven Bobs");
			map.put((long) 2221222,"Bill Setag");
			map.put((long) 2231498,"Mark Allen");
			map.put((long) 2241567,"Steve Kainzow");
			map.put((long) 2191434,"Mara Haggs");
			//System.out.println(content);
			String val = map.get(content);
			if(map.containsKey(content) && signedIn == false) {
				textArea.append("Signed in: " + val + "\n");
				start = Instant.now();
				signedIn = true;
			}
			else if(map.containsKey(content) && signedIn == true) {
				//textArea.append(content + "\n");
				textArea.append("Signed out: " + val);
				end = Instant.now();
				Duration timeElapsed = Duration.between(start, end);
				textArea.append(":  " + timeElapsed + "\n");
				signedIn = false;
			}
			else {
				textArea.append("INVALID ID: Please try again." + "\n");
			}
		}
	}
	
	public static void main(String[] args) {
		new Management_GUI_V2();
	}

}

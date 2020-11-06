package management_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Management_GUI_V1 implements ActionListener {

	private JFrame frame;
	private JPanel panel, panel2;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3;
	private JTextField textField;
	private JLabel lab1;
	boolean signedIn = false;
	Instant start, end;
	
	public Management_GUI_V1() {
		frame = new JFrame();
		frame.setTitle("Secres Industries");
		//frame.setBounds(0, 0, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setMinimumSize(new Dimension(600, 500));
		//frame.setLayout(null);
		
		panel = new JPanel();
		//panel.setBounds(0, 20, 600, 500);
		panel.setBackground(Color.WHITE);
		//panel.setLayout(null);
		panel.setLayout(new BorderLayout(0, 20));
		frame.getContentPane().add(panel);
		panel.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		scrollPane = new JScrollPane();
		//scrollPane.setBounds(20, 40, 400, 300);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		menuBar = new JMenuBar();
		//menuBar.setBounds(0, 0, 600, 20);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
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
		
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(0, 2));
		panel2.setBackground(Color.WHITE);
		panel.add(panel2, BorderLayout.SOUTH);
		panel2.setBorder(new EmptyBorder(5, 50, 5, 50));
		
		lab1 = new JLabel("Type id:");
		//lab1.setBounds(20, 350, 100, 20);
		panel2.add(lab1, BorderLayout.NORTH);
		
		textField = new JTextField();
		//textField.setBounds(20, 370, 300, 25);
		textField.addActionListener(this);
		panel2.add(textField, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		textField.requestFocus();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==textField){
			long content = Long.parseLong(textField.getText());
			long id = 2231044;
			//System.out.println(content);
			if(content == id && signedIn == false) {
				textArea.append("Signed in: Pranav Amarnath" + "\n");
				start = Instant.now();
				signedIn = true;
			}
			else if(content == id && signedIn == true) {
				//textArea.append(content + "\n");
				textArea.append("Signed out: Pranav Amarnath");
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Management_GUI_V1();
			}
		});
	}

}

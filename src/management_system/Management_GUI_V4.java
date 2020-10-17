package management_system;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.time.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

import de.javasoft.synthetica.bluesteel.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;
import de.javasoft.synthetica.plain.SyntheticaPlainLookAndFeel;
import de.javasoft.synthetica.standard.SyntheticaStandardLookAndFeel;

import com.formdev.flatlaf.*;
import com.sun.tools.javac.Main;

@SuppressWarnings("unused")
public class Management_GUI_V4 implements ActionListener {

	/*UNFINISHED MANAGEMENT SYSTEM -> NEED TO IMPLEMENT NeXT VERSION*/
	/*IF YOU READ THIS, PRANAV, WORK ON IT!!*/

	/*
	Implement tasks on README
	*/

	private JFrame frame;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu menu1, menu2, menu3;
	private JMenuItem itemN, itemC, item1, item2, item3, item4, item5, item6, itemA;
	private JTextField textField;
	private JLabel lab1;
	static ArrayList<Student2> students = new ArrayList<Student2>();
	private Instant[] start;
	private Instant[] end;
	private Duration timeElapsed;
	private boolean entered = false;
	static SyntheticaDarkLookAndFeel dark;
	static FlatDarkLaf dark2;
	static FlatIntelliJLaf intellij;
	static MetalLookAndFeel metal;
	static URL iconURL = Management_GUI_V4.class.getResource("Icon2.png");
	// iconURL is null when not found
	static ImageIcon icon = new ImageIcon(iconURL);
	static Taskbar taskbar = Taskbar.getTaskbar();
	// Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    // CSV file header
    private static final String FILE_HEADER = "id,firstName,lastName,gender,age";
	static PrintWriter out, txtOut;
	static boolean foundId;
	//static URL url = Management_GUI_V4.class.getClassLoader().getResource("resources/idData.txt");
	//InputStreamReader file = new InputStreamReader(url.openStream());
	static InputStream in = Management_GUI_V4.class.getResourceAsStream("resources/idData.txt");

	public Management_GUI_V4() {
		try {
			dark = new SyntheticaDarkLookAndFeel();
			dark2 = new FlatDarkLaf();
			intellij = new FlatIntelliJLaf();
			metal = new MetalLookAndFeel();
			UIManager.setLookAndFeel(metal);
		} catch (Exception e) { }

		frame = new JFrame();

		if(UIManager.getLookAndFeel()==dark || UIManager.getLookAndFeel()==dark2) {
			frame.setTitle("Secres Management System (Dark)");
		}
		else {
			frame.setTitle("Secres Management System (Metal)");
		}

		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if(UIManager.getLookAndFeel()==metal) {
			frame.getContentPane().setBackground(Color.WHITE);
		}

		frame.setLayout(null);
		
		/*
		if (SwingUtilities.isEventDispatchThread()) {
		    System.err.println("Is running on EDT");
		} else {
		    System.err.println("Is not running on EDT");
		}
		*/

		UIManager.put("swing.boldMetal", Boolean.FALSE);

		/*try {
			frame.setIconImage(ImageIO.read(new File("src/management_system/Icon2.png")));
		} catch (IOException e) { }*/

		//frame.setIconImage(new ImageIcon(getClass().getResource("Icon2.png")).getImage());

		try {
			taskbar.setIconImage(icon.getImage());
		} catch(Exception e) { }
		frame.setIconImage(icon.getImage());

		panel = new JPanel();
		panel.setSize(600, 500);

		if(UIManager.getLookAndFeel()==metal) {
			panel.setBackground(Color.WHITE);
		}

		panel.setLayout(null);
		frame.getContentPane().add(panel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 40, 400, 300);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		menuBar = new JMenuBar();
		menuBar.setSize(600, 20);

		menu1 = new JMenu("File");
		menu2 = new JMenu("View");
		menu3 = new JMenu("Help");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		panel.add(menuBar);

		if(UIManager.getLookAndFeel()==dark) {
			menu2.setEnabled(false);
		}

		itemN = new JMenuItem("New");
		menu1.add(itemN);
		itemN.addActionListener(this);
		itemN.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		itemN.setToolTipText("Create new user");

		itemC = new JMenuItem("Close");
		menu1.add(itemC);
		itemC.addActionListener(this);
		itemC.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

		item1 = new JMenuItem("Metal");
		menu2.add(item1);
		item1.addActionListener(this);

		item2 = new JMenuItem("Nimbus");
		menu2.add(item2);
		item2.addActionListener(this);

		item3 = new JMenuItem("System");
		menu2.add(item3);
		item3.addActionListener(this);

		menu2.addSeparator();

		item4 = new JMenuItem("Light");
		menu2.add(item4);
		item4.addActionListener(this);

		item5 = new JMenuItem("Dark");
		menu2.add(item5);
		item5.addActionListener(this);

		menu2.addSeparator();

		item6 = new JMenuItem("Synth");
		menu2.add(item6);
		item6.addActionListener(this);
		item6.setEnabled(false);

		itemA = new JMenuItem("About");
		menu3.add(itemA);
		itemA.addActionListener(this);

		textField = new JTextField();
		textField.setBounds(20, 370, 300, 25);
		textField.addActionListener(this);
		panel.add(textField);

		lab1 = new JLabel("Type id:");
		lab1.setBounds(20, 350, 100, 20);
		panel.add(lab1);

		frame.setJMenuBar(menuBar); // for picky mac users
		frame.setVisible(true);
		textField.requestFocus();
		
		/*
		students.add(new Student2("2231044", "Pranav Amarnath", false, "0"));
		students.add(new Student2("2191341", "Tarun Amarnath", false, "0"));
		students.add(new Student2("2231764", "Steve Mathew", false, "0"));
		*/
		
		start = new Instant[10000];
		end = new Instant[10000];

	}
	
	public static void writeCsvFile(String filename) {
		out = null;
		
		try {
			File file = new File(filename);
			//System.out.println(file.getAbsolutePath());
			FileWriter fileWriter = new FileWriter(file, true);
			out = new PrintWriter(fileWriter);
	
			// Write the CSV file header
			//out.println(FILE_HEADER.toString());
			
			// Add a new line separator after the header
			out.append(NEW_LINE_SEPARATOR);
	
			// Write a new student object list to the CSV file
			
			//for(Student2 student : students) {
				out.append(String.valueOf(students.get(students.size()-1).id));
				out.append(COMMA_DELIMITER);
				out.append(students.get(students.size()-1).name);
				out.append(COMMA_DELIMITER);
				out.append(String.valueOf(students.get(students.size()-1).signedIn));
				out.append(COMMA_DELIMITER);
				out.append(String.valueOf(students.get(students.size()-1).time));
	
				out.flush();
			//}
			
			out.flush();
		} catch (Exception e) {
			System.out.println("Exception occurred in writeCsvFile.");
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	public static void writeTxtFile(String filename) {
		txtOut = null;
		
		try {
			File file = new File(filename);
			//System.out.println(file.getAbsolutePath());
			FileWriter fileWriter = new FileWriter(file, true);
			txtOut = new PrintWriter(fileWriter);
	
			// Write the CSV file header
			//txtOut.println(FILE_HEADER.toString());
			
			// Add a new line separator after the header
			txtOut.append(NEW_LINE_SEPARATOR);
	
			// Write a new student object list to the CSV file
			
			//for(Student2 student : students) {
				txtOut.append(String.valueOf(students.get(students.size()-1).id));
				//txtOut.append(COMMA_DELIMITER);
				//txtOut.append(students.get(students.size()-1).name);
				//txtOut.append(NEW_LINE_SEPARATOR);
	
				txtOut.flush();
			//}
			
			txtOut.flush();
		} catch (Exception e) {
			System.out.println("Exception occurred in writeTxtFile.");
		} finally {
			if(txtOut != null) {
				txtOut.close();
			}
		}
	}
	
	public static void readTxtFile(String content) {
		BufferedReader br;
		try {
			//FileReader file = new FileReader(filename);
			//InputStreamReader file = new InputStreamReader(url.openStream());
			//System.out.println(file.getAbsolutePath());
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("idData.txt");
			br = new BufferedReader(new InputStreamReader(in));
			
			String line = br.readLine();
			int i = 0;
			while(line != null) {
				if(line.equals(content)) {
					foundId = true;
					//System.out.println(foundId + ", i: " + i + " content: " + content);
					students.add(new Student2(content, content, false, "0"));
					break;
				}
				line = br.readLine();
				i++;
			}
			
			br.close();
		} catch (Exception e) {
			//System.out.println("Exception occurred in readTxtFile.");
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		int trackEvent = 0;
		if(e.getSource()==textField){
			String content = textField.getText();
			int i;
			readTxtFile(content);
			for(i=0; i<students.size(); i++) {
				if(students.get(i).id.equals(content) && foundId) {
					if(students.get(i).signedIn == false) {
						textArea.append("Signed in: " + students.get(i).name + "\n");
						start[i] = Instant.now();
						trackEvent = i;
						students.get(i).signedIn = true;
						entered = true;
						break;
					}
					else if(students.get(i).signedIn == true) {
						//textArea.append(content + "\n");
						textArea.append("Signed out: " + students.get(i).name);
						end[trackEvent] = Instant.now();
						timeElapsed = Duration.between(start[i], end[trackEvent]);
						students.get(i).time = students.get(i).time + Duration.between(start[i], end[trackEvent]);
						textArea.append(" || Duration: " + timeElapsed + "\n");
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
			textField.requestFocus();
		}

		else if(e.getSource()==itemN) {
			/*
			if (SwingUtilities.isEventDispatchThread()) {
			    System.err.println("Is running on EDT");
			} else {
			    System.err.println("Is not running on EDT");
			}
			*/
			new AdminManager();
			AdminManager.getFrame().setIconImage(icon.getImage());
		}

		else if(e.getSource()==itemC) {
			System.exit(0);
		}

		else if(e.getSource()==item1) {
			try {
				UIManager.setLookAndFeel(metal);
			}
			catch (UnsupportedLookAndFeelException f) { }
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setTitle("Secres Management System (Metal)");
			frame.getContentPane().setBackground(Color.WHITE);
			panel.setBackground(Color.WHITE);
			textArea.setBackground(Color.WHITE);
			textField.requestFocus();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		else if(e.getSource()==item2) {
			try {
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
			}
			catch (UnsupportedLookAndFeelException f1) { }
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setTitle("Secres Management System (Nimbus)");
			frame.getContentPane().setBackground(Color.WHITE);
			panel.setBackground(Color.WHITE);
			textArea.setBackground(Color.WHITE);
			textField.requestFocus();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		else if(e.getSource()==item3) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception useDefault) {}
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setTitle("Secres Management System (System)");
			frame.getContentPane().setBackground(Color.WHITE);
			panel.setBackground(Color.WHITE);
			textArea.setBackground(Color.WHITE);
			textField.requestFocus();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		else if(e.getSource()==item4) {
			try {
				UIManager.setLookAndFeel(intellij);
			} catch (Exception useDefault) {}
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setTitle("Secres Management System (Intellij)");
			frame.getContentPane().setBackground(Color.WHITE);
			panel.setBackground(Color.WHITE);
			textArea.setBackground(Color.WHITE);
			textField.requestFocus();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		else if(e.getSource()==item5) {
			try {
				UIManager.setLookAndFeel(dark2);
			} catch (Exception useDefault) {}
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setTitle("Secres Management System (Dark)");
			frame.getContentPane().setBackground(new Color(60, 63, 65));
			panel.setBackground(new Color(60, 63, 65)); // for FlatDarkLaF
			textArea.setBackground(new Color(60, 63, 65));
			textField.requestFocus();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		else if(e.getSource()==item6) {
			try {
				UIManager.setLookAndFeel(new SynthLookAndFeel());
			}
			catch (Exception useDefault) { }
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setTitle("Secres Management System (Synth)");
			textField.requestFocus();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}

		else if(e.getSource()==itemA) {
			JOptionPane.showMessageDialog(frame, "Hello there! Welcome to the Secres Management System.\nMade with Java Swing :P.", "About", 1);
		}
	}
	/*
	public static void disableWarning() {
	    System.err.close();
	    System.setErr(System.out);
	}
	*/
}

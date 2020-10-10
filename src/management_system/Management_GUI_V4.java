package management_system;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
	 1. Fix Exceptions (NumberFormatException, NullPointerException etc.)
	 2. Implement text read/write
	 3. Fix time signed in per person
	 4. About Section
	 5. Administrator privileges
	 6. Add JMenu for Look & Feel
	 7. Fix scrolling so that when new data entered scrolls to bottom
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
	Instant start, end;
	static ArrayList<Student> students = new ArrayList<Student>();
	private boolean entered = false;
	static SyntheticaDarkLookAndFeel dark;
	static FlatDarkLaf dark2;
	static FlatIntelliJLaf intellij;
	static MetalLookAndFeel metal;
	static URL iconURL = Management_GUI_V4.class.getResource("Icon2.png");
	// iconURL is null when not found
	static ImageIcon icon = new ImageIcon(iconURL);
	static Taskbar taskbar = Taskbar.getTaskbar();

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

		frame.setBounds(0, 0, 600, 500);
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
		panel.setBounds(0, 0, 600, 500);

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
		menuBar.setBounds(0, 0, 600, 20);

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
			JOptionPane.showMessageDialog(frame, "Hello there! Welcome to the Secres Management System.\nMade with Java Swing :P.");
		}
	}

	public static void disableWarning() {
	    System.err.close();
	    System.setErr(System.out);
	}

}

package management_system;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true"); // for picky mac users
		System.setProperty("apple.awt.application.name", "MGMT"); // mac header on mac menubar
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Management_GUI_V4();
				//Management_GUI_V4.writeCsvFile("data.csv");
				//Management_GUI_V4.disableWarning();
			}
		});
	}

}

package Rep_GUI_Pkg;

import javax.swing.SwingUtilities;

public class GUI_Driver {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Rep_GUI();
			}
		});
	}

}
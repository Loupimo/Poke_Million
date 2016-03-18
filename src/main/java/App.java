import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
	protected static String[] suffixes = { "csv" };
	protected static String[] descriptions = { "Fichiers CSV (.csv)" };
	public static CSVFilter filtre = new CSVFilter(suffixes, descriptions);

	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setSize(new Dimension(500, 500));
		JButton but = new JButton(new LoadCSV());
		f.add(but);
		f.setVisible(true);

	}

	private static class LoadCSV extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private LoadCSV() {
			super("Ouvrir");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				//Définit le style de la fenêtre de sélection avec celui du system
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CSVLoader load = new CSVLoader ();
			load.loadFile();
		}
	}
}

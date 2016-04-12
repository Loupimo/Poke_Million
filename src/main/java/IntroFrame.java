import javax.swing.JFrame;

//Fenêtre d'introduction du jeu

public class IntroFrame extends JFrame{
	
	public IntroFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	//On ajoute et active le jpanel d'intro à la fenêtre
	public void animationIntro(){
		System.out.println("animation intro");
		StartingWindow start = new StartingWindow();
		start.ignition(); //lancement de la musique
		this.add(start);
		this.setVisible(true);
		
	}
	
	//Fermeture de la fenêtre d'intro et création de la fenêtre principale + ajout du menu
	public void closeFrame(){
		this.dispose();
		MainFrame main = new MainFrame();
		main.addMenu();
	}
	
	
}

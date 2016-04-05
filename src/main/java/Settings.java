import java.awt.*;


//Cette classe permet d'obtenir des infos relatives à l'écran de l'utilisateur

public class Settings {
	private int hauteur, largeur;
	private Toolkit tools = Toolkit.getDefaultToolkit();
	
	public Settings(){
		hauteur = tools.getScreenSize().height;
		largeur = tools.getScreenSize().width;
	}
	
	public int getHeight(){
		return hauteur;
	}
	
	public int getWidth(){
		return largeur;
	}

}

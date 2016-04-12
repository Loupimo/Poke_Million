import java.io.File;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioEngine extends Thread{
	private String name; //Nom de la musique voulue
	private AudioInputStream input; //Fichier d'entrée
	private String path; //Chemin du fichier
	public Clip clip;
	private long length; //Durée de la musique
	
	public AudioEngine(String n){
		this.name = n;
		this.path = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/Music/" + name; //chemin absolu du fichier .wav
		path = path.replace("\\", "/"); //on remplace les \ par des / pour
	}
	
	public void run(){
		try{
			input = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()); //On récupère le fichier
			clip = AudioSystem.getClip();
			clip.open(input); //On ouvre le fichier
			length = clip.getMicrosecondLength(); //On lit la durée de lecture du fichier
			clip.start(); //On lance le .wav
			Thread.sleep(length/1000); //On arrête ce thread le temps que le .wav soit fini
			clip.stop();
			return;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void stopMusic(){
		this.clip.stop(); //Arrêt de la musique
		this.stop(); //Arrêt de ce thread
	}
}

/** La gestion de la musique de l'introduction nécessite une classe particulière car il s'agit d'une séquence de 2 musiques **/


public class MusicIntroControler extends Thread{
	private AudioEngine opening; //Musique
	private boolean flag = true; //Indicateur permettant d'arrêter ce thread s'il change de valeur
	public MusicIntroControler(){}
	
	public void run(){
		
		//Ouverture et lancement de la première musique
		this.opening = new AudioEngine("opening1.wav");
		opening.start();
		
		while(opening.isAlive()){ //Bloque ce thread tant que la musique est active
			if(flag == false){
				stopMusic(); //Arrête la musique si l'indicateur change de valeur
			}
			
		}
		
		
		//Lancement de la seconde musique
		opening = new AudioEngine("opening2.wav");
		opening.start();
		
		while(opening.isAlive()){ //Bloque ce thread tant que la musique est active
			if (flag == false){
				stopMusic();
			}
		}
	}
	
	
	//Arrêt de la musique
	public void stopMusic(){
		this.opening.clip.stop();
		this.opening.stop();
	}
	
	
	//Changement de l'indicateur
	public void changeFlag(){
		this.flag = false;
	}

}

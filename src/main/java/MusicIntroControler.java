
public class MusicIntroControler extends Thread{
	private AudioEngine opening;
	private boolean flag = true;
	public MusicIntroControler(){
		
	}
	
	public void run(){
		this.opening = new AudioEngine("opening1.wav");
		opening.start();
		
		while(opening.isAlive()){
			if(flag == false){
				stopMusic();
			}
			
		}
		
		opening = new AudioEngine("opening2.wav");
		opening.start();
		
		while(opening.isAlive()){
			if (flag == false){
				stopMusic();
			}
		}
	}
	
	public void stopMusic(){
		this.opening.clip.stop();
		this.opening.stop();
	}
	
	public void changeFlag(){
		this.flag = false;
	}

}

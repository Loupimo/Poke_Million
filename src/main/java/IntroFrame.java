import javax.swing.JFrame;

public class IntroFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntroFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	public void animationIntro(){
		System.out.println("animation intro");
		StartingWindow start = new StartingWindow();
		start.ignition();
		this.add(start);
		this.setVisible(true);
		
	}
	
	public void closeFrame(){
		this.dispose();
		MainFrame main = new MainFrame();
		main.addMenu();
	}
	
	
}

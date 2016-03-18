import javax.swing.ImageIcon;

public class Pokemon {
	
	private int Id;
	private String name;
	private ImageIcon sprite;

	public Pokemon(int p_Id, String p_name, String spritePath) {
		this.Id = p_Id;
		this.name = p_name;
		this.sprite = new ImageIcon(spritePath);
	}

	public Pokemon(int p_Id, String p_name, ImageIcon p_sprite) {
		this.Id = p_Id;
		this.name = p_name;
		this.sprite = p_sprite;
	}

	public int getId() {
		return this.Id;
	}

	public String getName() {
		return this.name;
	}

	public ImageIcon getSprite() {
		return this.sprite;
	}
}

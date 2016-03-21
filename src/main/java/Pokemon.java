import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Pokemon {
	
	private int Id;
	private String name;
	private ImageIcon sprite;
	private LinkedList<String> typeList;

	public Pokemon(int p_Id, String p_name, String spritePath, LinkedList<String> p_type) {
		this.Id = p_Id;
		this.name = p_name;
		this.sprite = new ImageIcon(spritePath);
		this.typeList = p_type;
	}

	public Pokemon(int p_Id, String p_name, ImageIcon p_sprite, String p_type) {
		this.Id = p_Id;
		this.name = p_name;
		this.sprite = p_sprite;
		this.typeList = new LinkedList<String> ();
		this.typeList.add(p_type);
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
	
	public LinkedList<String> getTypeList ()
	{
		return this.typeList;
	}
}

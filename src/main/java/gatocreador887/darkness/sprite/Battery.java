package gatocreador887.darkness.sprite;

import java.awt.Image;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class Battery extends Sprite {
	public static final Image REGULAR_IMAGE = new ImageIcon(Main.class.getResource("/sprites/battery/battery_regular.png")).getImage();
	private Type type;
	
	public Battery(double x, double y, Level level, Type type) {
		super(x, y, level);
		
		this.type = type;
		this.width = 4;
		this.height = 10;
		
		if (this.type == Type.REGULAR) {
			this.image = REGULAR_IMAGE;
		}
	}
	
	public String getName() {
		return "Battery (" + this.type.toString().toLowerCase() + ")";
	}
	
	public Type getType() {
		return this.type;
	}
	
	public enum Type {
		REGULAR;
	}
}

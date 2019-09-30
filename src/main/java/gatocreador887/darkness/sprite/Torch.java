package gatocreador887.darkness.sprite;

import java.awt.Image;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class Torch extends Sprite {
	public static final Image IMAGE = new ImageIcon(Main.class.getResource("/sprites/torch.png")).getImage();
	
	public Torch(double x, double y, Level level) {
		super(x, y, level);
		
		this.width = 4;
		this.height = 10;
	}
	
	public String getName() {
		return "Torch";
	}
}

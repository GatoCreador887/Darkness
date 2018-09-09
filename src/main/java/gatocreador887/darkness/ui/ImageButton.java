package gatocreador887.darkness.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import gatocreador887.darkness.StaticFields;

public class ImageButton extends Button {
	private Image image;
	
	public ImageButton(UI owner, String id, Color color, int x, int y, int width, int height, Image image) {
		super(owner, id, color, x, y, width, height);
		
		this.image = image;
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(this.image, this.x, this.y, this.width, this.height, StaticFields.board);
	}
}

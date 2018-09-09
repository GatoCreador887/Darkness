package gatocreador887.darkness.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import gatocreador887.darkness.util.sound.AudioUtils;

public class Button {
	protected UI owner;
	protected String id;
	protected Color color;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Button(UI owner, String id, Color color, int x, int y, int width, int height) {
		this.owner = owner;
		this.id = id;
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void doClick(Point p) {
		if (new Rectangle(this.x, this.y, this.width, this.height).contains(p)) {
			this.owner.buttonClicked(this);
			AudioUtils.playSound(this.getClass().getResource("/sounds/click.wav"), 0.0f);
		}
	}
	
	public String getId() {
		return this.id;
	}
	
	public void render(Graphics2D g2d) {
		Color prevColor = g2d.getColor();
		g2d.setColor(this.color);
		g2d.fillRect(this.x, this.y, this.width, this.height);
		g2d.setColor(prevColor);
	}
}

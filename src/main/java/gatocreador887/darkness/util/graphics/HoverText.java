package gatocreador887.darkness.util.graphics;

import java.awt.Color;

public class HoverText {
	protected final String text;
	protected final Color color;
	protected int x;
	protected int y;
	protected int deleteTimestamp;
	protected boolean remove;
	
	public HoverText(String text, Color color, int x, int y, int deleteTimestamp) {
		this.text = text;
		this.color = color;
		this.x = x;
		this.y = y;
		this.deleteTimestamp = deleteTimestamp;
	}
	
	public void update(int step) {
		if (step > this.deleteTimestamp) {
			this.remove = true;
		}
	}
	
	public String getText() {
		return this.text;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean shouldRemove() {
		return this.remove;
	}
}

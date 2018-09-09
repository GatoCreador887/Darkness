package gatocreador887.darkness.ui;

import java.awt.Color;
import java.awt.Graphics2D;

public class TextButton extends Button {
	private String text;
	private Color textColor;
	
	public TextButton(UI owner, String id, Color color, int x, int y, int width, int height, String text, Color textColor) {
		super(owner, id, color, x, y, width, height);
		
		this.text = text;
		this.textColor = textColor;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void render(Graphics2D g2d) {
		super.render(g2d);
		
		Color prevColor = g2d.getColor();
		g2d.setColor(this.textColor);
		g2d.drawString(this.text, this.x + this.width / 2 - (int) g2d.getFont().getStringBounds(this.text, g2d.getFontRenderContext()).getWidth() / 2, this.y + this.height / 2 + 3);
		g2d.setColor(prevColor);
	}
}

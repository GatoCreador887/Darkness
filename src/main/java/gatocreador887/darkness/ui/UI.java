package gatocreador887.darkness.ui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gatocreador887.darkness.StaticFields;

public abstract class UI {
	protected UI parent;
	protected ArrayList<Button> buttons = new ArrayList<Button>();
	
	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
	
	public void keyEvent(KeyEvent e, boolean pressed) {
		if (pressed) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					if (this.parent != null) {
						StaticFields.board.setActiveUI(this.parent);
					}
					
					break;
			}
		}
	}
	
	public void mouseClicked(MouseEvent e, Point p) {
		for (Button b : this.getButtons()) {
			b.doClick(p);
		}
	}
	
	protected void buttonClicked(Button b) {
	}
	
	public void render(Graphics2D g2d) {
		for (Button b : this.buttons) {
			b.render(g2d);
		}
	}
}

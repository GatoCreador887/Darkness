package gatocreador887.darkness.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.Settings;

public class UISettings extends UI {
	
	public UISettings() {
		this.buttons.add(new TextButton(this, "alternateCustomLevelStartSound", Color.DARK_GRAY, Board.WIDTH / 2 - 105, Board.HEIGHT / 2, 210, 20, "Alternate Custom Level Start Sound", Color.WHITE));
	}
	
	protected void buttonClicked(Button b) {
		switch (b.getId()) {
			case "alternateCustomLevelStartSound":
				Settings.alternateCustomLevelStartSound = !Settings.alternateCustomLevelStartSound;
				Settings.write();
				
				break;
		}
	}
	
	public void render(Graphics2D g2d) {
		super.render(g2d);
		
		if (Settings.alternateCustomLevelStartSound) {
			g2d.drawString("Alternate custom level start sound is on", Board.WIDTH / 2 - 95, 290);
		} else {
			g2d.drawString("Alternate custom level start sound is off", Board.WIDTH / 2 - 95, 290);
		}
	}
}

package gatocreador887.darkness.ui;

import java.awt.Color;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.StaticFields;
import gatocreador887.darkness.sprite.Brain;
import gatocreador887.darkness.sprite.Ghost;
import gatocreador887.darkness.sprite.GooBlob;
import gatocreador887.darkness.sprite.ObliterationVortex;
import gatocreador887.darkness.sprite.Zombie;

public class UIMonsterList extends UI {
	public UIMonsterList() {
		this.buttons.add(new ImageButton(this, "ghost", Color.DARK_GRAY, Board.WIDTH / 2 - 55, Board.HEIGHT / 2 - 10, 20, 20, Ghost.NORMAL_IMAGE));
		this.buttons.add(new ImageButton(this, "zombie", Color.DARK_GRAY, Board.WIDTH / 2 - 35, Board.HEIGHT / 2 - 10, 20, 20, Zombie.IMAGE));
		this.buttons.add(new ImageButton(this, "brain", Color.DARK_GRAY, Board.WIDTH / 2 - 15, Board.HEIGHT / 2 - 10, 20, 20, Brain.IMAGE_0));
		this.buttons.add(new ImageButton(this, "goo_blob", Color.DARK_GRAY, Board.WIDTH / 2 + 5, Board.HEIGHT / 2 - 6, 20, 16, GooBlob.IMAGE));
		this.buttons.add(new ImageButton(this, "infernal_ghost", Color.DARK_GRAY, Board.WIDTH / 2 + 25, Board.HEIGHT / 2 - 10, 20, 20, Ghost.INFERNAL_IMAGE));
		this.buttons.add(new ImageButton(this, "obliteration_vortex", Color.DARK_GRAY, Board.WIDTH / 2 + 45, Board.HEIGHT / 2 - 10, 20, 20, ObliterationVortex.IMAGE_0));
	}
	
	public void buttonClicked(Button b) {
		UIMonsterDescription ui = null;
		
		switch (b.getId()) {
			case "ghost":
				ui = new UIMonsterDescription("ghost");
				break;
			case "zombie":
				ui = new UIMonsterDescription("zombie");
				break;
			case "brain":
				ui = new UIMonsterDescription("brain");
				break;
			case "goo_blob":
				ui = new UIMonsterDescription("goo_blob");
				break;
			case "infernal_ghost":
				ui = new UIMonsterDescription("infernal_ghost");
				break;
			case "obliteration_vortex":
				ui = new UIMonsterDescription("obliteration_vortex");
				break;
		}
		
		ui.parent = this;
		StaticFields.board.setActiveUI(ui);
	}
}

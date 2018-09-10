package gatocreador887.darkness.ui;

import java.awt.Color;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.Level;
import gatocreador887.darkness.StaticFields;

public class UISelectMode extends UI {
	public UISelectMode() {
		this.buttons.add(new TextButton(this, "classic", Color.DARK_GRAY, Board.WIDTH / 2 - 40, Board.HEIGHT / 2 - 10, 80, 20, "Classic", Color.WHITE));
	}
	
	protected void buttonClicked(Button b) {
		switch (b.id) {
			case "classic":
				StaticFields.board.setLevel(new Level());
				StaticFields.board.setActiveUI(null);
				break;
		}
	}
}

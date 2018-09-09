package gatocreador887.darkness.ui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.StaticFields;
import gatocreador887.darkness.sprite.Brain;
import gatocreador887.darkness.sprite.Ghost;
import gatocreador887.darkness.sprite.GooBlob;
import gatocreador887.darkness.sprite.ObliterationVortex;
import gatocreador887.darkness.sprite.Zombie;

public class UIMonsterDescription extends UI {
	private String monster;
	
	public UIMonsterDescription(String monster) {
		this.monster = monster;
	}
	
	public void render(Graphics2D g2d) {
		super.render(g2d);
		
		int step = StaticFields.board.getStep();
		
		String monsterDescription = "";
		String monsterName = "";
		Image monsterImage = null;
		
		switch (this.monster) {
			case "ghost":
				monsterDescription = 
					"A pale monster that is afraid of bright light, the Ghost is most dangerous\n" +
					"when your battery is low. It is neither slow nor fast, and it is the most common Monster\n" +
					"in the Darkness.";
				
				monsterName = "Ghost";
				monsterImage = Ghost.NORMAL_IMAGE;
				break;
			case "zombie":
				monsterDescription = 
					"A poor soul who stumbled into the depths just like you and ran into some other Zombies,\n" +
					"this monster shambles unsteadily towards you, ignoring the light. It is the second most common Monster\n" +
					"in the Darkness.";
				
				monsterName = "Zombie";
				monsterImage = Zombie.IMAGE;
				break;
			case "brain":
				monsterDescription = 
					"A disembodied brain crawling around on tentacles, this is possibly the creepiest Monster in the Darkness.\n" +
					"It lunges towards you after a short interval, and like the Zombie, it ignores light. It is the third most common\n" +
					"Monster in the Darkness.";
				
				monsterName = "Brain";
				
				if (step % 50 <= 25) {
					monsterImage = Brain.IMAGE_0;
				} else {
					monsterImage = Brain.IMAGE_1;
				}
				
				break;
			case "goo_blob":
				monsterDescription = 
					"A blob of green goo that hops around, and, when close enough, shoots small orbs of goo at you.\n" +
					"It is the third least common Monster in the Darkness.";
				
				monsterName = "Goo Blob";
				monsterImage = GooBlob.IMAGE;
				break;
			case "infernal_ghost":
				monsterDescription = 
					"An ash-colored monster, the Infernal Ghost is not afraid of light like its cousin, the regular Ghost.\n" +
					"It is also faster than a regular Ghost, and it is the second least common Monster\n" +
					"in the Darkness.";
				
				monsterName = "Infernal Ghost";
				monsterImage = Ghost.INFERNAL_IMAGE;
				break;
			case "obliteration_vortex":
				monsterDescription = 
					"A vortex that destroys anything it touches, except for walls and other vortices.\n" +
					"It is the least common Monster in the Darkness.";
				
				monsterName = "Obliteration Vortex";
				
				if (step % 15 <= 3) {
					monsterImage = ObliterationVortex.IMAGE_0;
				} else if (step % 15 <= 6) {
					monsterImage = ObliterationVortex.IMAGE_1;
				} else if (step % 15 <= 9) {
					monsterImage = ObliterationVortex.IMAGE_2;
				} else {
					monsterImage = ObliterationVortex.IMAGE_3;
				}
				
				break;
		}
		
		g2d.drawImage(monsterImage, Board.WIDTH / 2, Board.HEIGHT / 2 - 70, 32, 32, StaticFields.board);
		Font prevFont = g2d.getFont();
		g2d.setFont(new Font(StaticFields.FONT_NAME, Font.BOLD, 20));
		g2d.drawString(monsterName, Board.WIDTH / 2, Board.HEIGHT / 2);
		g2d.setFont(prevFont);
		
		String[] lines = monsterDescription.split("\n");
		
		for (int y = 0; y < lines.length; y++) {
			String line = lines[y];
			
			g2d.drawString(line, 10, Board.HEIGHT / 2 + y * 12 + 30);
		}
	}
}

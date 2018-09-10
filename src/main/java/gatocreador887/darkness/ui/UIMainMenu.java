package gatocreador887.darkness.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.Level;
import gatocreador887.darkness.StaticFields;
import gatocreador887.darkness.sprite.Brain;
import gatocreador887.darkness.sprite.Directioned;
import gatocreador887.darkness.sprite.Ghost;
import gatocreador887.darkness.sprite.GooBlob;
import gatocreador887.darkness.sprite.ObliterationVortex;
import gatocreador887.darkness.sprite.Sprite;
import gatocreador887.darkness.sprite.Zombie;
import gatocreador887.darkness.util.graphics.TransparentGradientInImage;

public class UIMainMenu extends UI {
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int prevStep;
	
	public UIMainMenu() {
		this.buttons.add(new TextButton(this, "play", Color.DARK_GRAY, Board.WIDTH / 2 - 140, Board.HEIGHT / 2 - 10, 125, 20, "Play", Color.WHITE));
		this.buttons.add(new TextButton(this, "monsterList", Color.DARK_GRAY, Board.WIDTH / 2 + 20, Board.HEIGHT / 2 - 10, 120, 20, "Monsters", Color.WHITE));
		this.buttons.add(new TextButton(this, "settings", Color.DARK_GRAY, Board.WIDTH / 2 - 30, Board.HEIGHT / 2 + 30, 120, 20, "Settings", Color.WHITE));
		this.buttons.add(new TextButton(this, "customLevel", Color.DARK_GRAY, Board.WIDTH / 2 - 140, Board.HEIGHT / 2 + 30, 100, 20, "Play Custom Level", Color.WHITE));
	}
	
	protected void buttonClicked(Button b) {
		UI ui = null;
		
		switch (b.getId()) {
			case "play":
				ui = new UISelectMode();
				break;
			case "monsterList":
				ui = new UIMonsterList();
				break;
			case "settings":
				ui = new UISettings();
				break;
			case "customLevel":
				ui = new UICustomLevel();
				break;
		}
		
		if (ui != null) {
			ui.parent = this;
			StaticFields.board.setActiveUI(ui);
		}
	}
	
	public void render(Graphics2D g2d) {
		if (StaticFields.board.getStep() != this.prevStep) {
			this.prevStep = StaticFields.board.getStep();
			
			ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
			spriteList.addAll(this.sprites);
			
			for (Sprite s : spriteList) {
				if (!s.remove()) {
					s.update(StaticFields.board.getStep());
				} else {
					this.sprites.remove(s);
				}
				
				if (s.getX() > Board.WIDTH || s.getX() < -s.getHitbox().width) {
					s.setRemove();
				}
				
				if (s.getY() > Board.HEIGHT || s.getY() < -s.getHitbox().height) {
					s.setRemove();
				}
				
				if (s instanceof Directioned) {
					Directioned d = (Directioned) s;
					
					if (d.getDirection() > 0.625f) {
						d.setDirection(0.625f);
					} else if (d.getDirection() < 0.375f) {
						d.setDirection(0.375f);
					}
				}
			}
			
			if (ThreadLocalRandom.current().nextFloat() < 0.01f) {
				Directioned directioned = null;
				
				if (ThreadLocalRandom.current().nextFloat() < 0.025f) {
					directioned = new ObliterationVortex(Board.WIDTH - 1, Board.HEIGHT / 2 - 8, null);
				} else if (ThreadLocalRandom.current().nextFloat() < 0.075f) {
					directioned = new Ghost(Board.WIDTH - 1, Board.HEIGHT / 2 - 8, null, Ghost.Type.INFERNAL);
				} else if (ThreadLocalRandom.current().nextFloat() < 0.125f) {
					directioned = new GooBlob(Board.WIDTH - 1, Board.HEIGHT / 2 - 8, null);
				} else if (ThreadLocalRandom.current().nextFloat() < 0.15f) {
					directioned = new Brain(Board.WIDTH - 1, Board.HEIGHT / 2 - 8, null);
				} else if (ThreadLocalRandom.current().nextFloat() < 0.3f) {
					directioned = new Zombie(Board.WIDTH - 1, Board.HEIGHT / 2 - 8, null);
				} else {
					directioned = new Ghost(Board.WIDTH - 1, Board.HEIGHT / 2 - 8, null, Ghost.Type.NORMAL);
				}
				
				directioned.setDirection(0.5f);
				this.sprites.add(directioned);
			}
		}
		
		Color prevColor = g2d.getColor();
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, Board.WIDTH, Board.HEIGHT);
		
		for (Sprite s : this.sprites) {
			if (s.getImage() != null) {
				g2d.drawImage(s.getImage(), (int) s.getX(), (int) s.getY(), StaticFields.board);
			}
		}
		
		TransparentGradientInImage.updateGradientAt(Level.SHADOW_IMAGE, Level.SHADOW, new Point(Board.WIDTH / 2, Board.HEIGHT / 2), (Board.WIDTH + Board.HEIGHT) / 4, 255);
		g2d.drawImage(Level.SHADOW, 0, 0, StaticFields.board);
		
		super.render(g2d);
		
		Font prevFont = g2d.getFont();
		g2d.setFont(new Font(StaticFields.FONT_NAME, Font.BOLD, 35));
		g2d.setColor(Color.BLACK);
		g2d.drawString("Darkness", Board.WIDTH / 2 - 70, Board.HEIGHT / 2 - 100);
		g2d.setFont(prevFont);
		//g2d.drawImage(Player.NORMAL_IMAGE, Board.WIDTH / 2 - 8, Board.HEIGHT / 2 - 90, StaticFields.board);
		g2d.setColor(prevColor);
	}
}

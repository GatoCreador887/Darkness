package gatocreador887.darkness;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.sprite.Battery;
import gatocreador887.darkness.sprite.Brain;
import gatocreador887.darkness.sprite.Ghost;
import gatocreador887.darkness.sprite.GooBlob;
import gatocreador887.darkness.sprite.ObliterationVortex;
import gatocreador887.darkness.sprite.Player;
import gatocreador887.darkness.sprite.Spikeball;
import gatocreador887.darkness.sprite.Sprite;
import gatocreador887.darkness.sprite.Torch;
import gatocreador887.darkness.sprite.Zombie;
import gatocreador887.darkness.ui.UIMainMenu;
import gatocreador887.darkness.util.graphics.HoverText;
import gatocreador887.darkness.util.graphics.TransparentGradientInImage;
import gatocreador887.darkness.util.sound.AudioUtils;

public class Level {
	public static final int WALL_SIZE = 50;
	public static final String[] LEVELS = {
					//Level 1
					"+------+::::::\n" +
					"|      |::::::\n" +
					"+-- +- +-+::::\n" +
					"|   |    +--+:\n" +
					"+---++--    |:\n" +
					"|F   |b  +--+:\n" +
					"+--+ +-- |  ++\n" +
					"|S |        b|\n" +
					"|  +- --++ -++\n" +
					"|  |   b||   |\n" +
					"|       ||   |\n" +
					"+-------++---+",
					//Level 2
					"+--+---+---+---+::::\n" +
					"|  |   |   |   +---+\n" +
					"|        S         |\n" +
					"|  |   |   |   |   |\n" +
					"|  +---+---+---+   |\n" +
					"|      |:::|  b|   |\n" +
					"+--+   |:::|       |\n" +
					":::+ --+---+-------+\n" +
					":::|  b    |::::::::\n" +
					":::+---+-- +--+:::::\n" +
					":::::::|F     |:::::\n" +
					":::::::+------+:::::",
					//Level 3
					"::+-------+\n" +
					"::|b      |\n" +
					"::|    +--+\n" +
					"+-+    +--+\n" +
					"|        F|\n" +
					"|b|    +--+\n" +
					"+-+    |:::\n" +
					"::| +--+:::\n" +
					"::| | S|:::\n" +
					"+-+ | -+--+\n" +
					"|b|       |\n" +
					"| | +- -+-+\n" +
					"|   |   |::\n" +
					"+---+---+::",
					//Level 4
					"+---------+\n" +
					"|         |\n" +
					"| b| +--+ |\n" +
					"+--+ | F| |\n" +
					"|    |  | |\n" +
					"| b| |  | |\n" +
					"++-+ |  | |\n" +
					":|S  |    |\n" +
					":+-+ +----+\n" +
					":::|     b|\n" +
					":::+------+",
					//Level 5
					"+-------+::\n" +
					"|       |::\n" +
					"|     | |::\n" +
					"|  F  | |::\n" +
					"|     | |::\n" +
					"+-----+ |::\n" +
					"|    b| |::\n" +
					"| +---+ |::\n" +
					"| | S   |::\n" +
					"| + +-+ +-+\n" +
					"|   |:|  b|\n" +
					"+---+:| +-+\n" +
					"::::::| |::\n" +
					":::+--+ |::\n" +
					":::|b   |::\n" +
					":::|    |::\n" +
					":::+----+::",
					//Level 6
					":+---+:::::::\n" +
					":|   ++::::::\n" +
					":| | b+-----+\n" +
					"++ ++       |\n" +
					"| S +-+-+   |\n" +
					"++ ++:|b|   |\n" +
					":| |::| |   |\n" +
					"++ ++-+ |   |\n" +
					"|       |   |\n" +
					"|   +---+   |\n" +
					"++ ++       |\n" +
					":|b|F +-----+\n" +
					":+-+--+::::::",
					//Level 7
					":::::::::+-+::::::\n" +
					":::::::::|b|::::::\n" +
					":::::::::| |::::::\n" +
					"::::::+--+ +--+:::\n" +
					"+-----+       |:::\n" +
					"|     |  +-+  +--+\n" +
					"|  S     |:|     |\n" +
					"|     |  +-+  +  |\n" +
					"+-+--++       |  |\n" +
					"::|F ++--+ +--+  |\n" +
					"::|  |:::| |::|  |\n" +
					"::|  |:::|b|::|  |\n" +
					"::|  +---+-+--+  |\n" +
					"::|              |\n" +
					"::|             b|\n" +
					"::+--------------+",
					//Level 8
					":::+-------+:::::::::\n" +
					"::++b      ++::::::::\n" +
					":++         ++:::::::\n" +
					"++           ++::+--+\n" +
					"|             |::|b |\n" +
					"|             |::|  |\n" +
					"|     +-+     +--+  |\n" +
					"|     |F|           |\n" +
					"|     +g+     +--+  |\n" +
					"|             |::|  |\n" +
					"|             |::| k|\n" +
					"++           ++::+--+\n" +
					":++         ++:::::::\n" +
					"::++   S  b++::::::::\n" +
					":::+-------+:::::::::\n",
					//Level 9
					":::::::+---+:::::::::::\n" +
					":::::::| b +---+:::::::\n" +
					":::::::|       +---+:::\n" +
					":::::::|   +       |:::\n" +
					"::::::+++ ++++     |:::\n" +
					"+-+---+     +--+   +--+\n" +
					"|F|S           |  b|  |\n" +
					"| ++--+     +  +---+  |\n" +
					"| b+--+-----+         |\n" +
					"|              +------+\n" +
					"|     c        |:::::::\n" +
					"+--------------+:::::::",
					//Level 10
					":::::+-+:::::::::::\n" +
					":::::|F|:::+-+:::::\n" +
					":::::|g|:::|k|:::::\n" +
					":::::| |:::| +----+\n" +
					":::+-+b+-+:|      |\n" +
					"+--+     +-+ +--+ |\n" +
					"|S          b|::| |\n" +
					"+--+     +---+::|b|\n" +
					":::+-----+::::::+-+"
	};
	public static final Image SHADOW_IMAGE = new ImageIcon(Level.class.getResource("/sprites/shadow.png")).getImage();
	public static final BufferedImage SHADOW = TransparentGradientInImage.convertToARGB(SHADOW_IMAGE);;
	public Player player;
	public Image finishImage = new ImageIcon(this.getClass().getResource("/sprites/finish.png")).getImage();
	private String level;
	public int levelNumber;
	public Point finishPoint;
	private int levelupTimestamp;
	public float difficulty = 1.0f;
	private boolean gameOver;
	private int score;
	private int levelTime;
	private CustomLevel customLevel;
	public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
	public ArrayList<HoverText> hoverTexts = new ArrayList<HoverText>();
	
	public Level(String level) {
		this.level = level;
		
		this.init();
	}
	
	public Level(CustomLevel level) {
		this.customLevel = level;
		this.level = level.data;
		this.difficulty = level.difficulty;
		
		this.init();
	}
	
	public Level() {
		this(LEVELS[0]);
	}
	
	private void init() {
		this.parseLevel();
		if (this.customLevel == null) {
			AudioUtils.playSound(this.getClass().getResource("/sounds/start.wav"), -2.5f);
		} else if (Settings.getAlternateCustomLevelStartSound()) {
			AudioUtils.playSound(this.getClass().getResource("/sounds/custom_level_start.wav"), 0.0f);
		} else {
			AudioUtils.playSound(this.getClass().getResource("/sounds/start.wav"), -2.5f);
		}
	}
	
	private void parseLevel() {
		if (this.player != null) {
			this.player = new Player(this.player);
		} else {
			this.player = new Player(0.0d, 0.0d, this);
			this.player.setBattery(1.0f);
			this.player.setLight(0.2f + 0.02f * ((this.difficulty - 1.0f) / 0.25f));
		}
		
		int startX = 0;
		int startY = 0;
		
		String[] lines = this.level.split("\n");
		
		for (int y = 0; y < lines.length; y++) {
			String line = lines[y];
			int yPos = y * WALL_SIZE;
			
			for (int x = 0; x < line.length(); x++) {
				int xPos = x * WALL_SIZE;
				
				if (line.charAt(x) == ':') {
					//Does nothing
				} else if (line.charAt(x) == '-') {
					this.walls.add(new Rectangle(xPos - (int) (WALL_SIZE / 2.5), yPos, WALL_SIZE, WALL_SIZE / 4));
				} else if (line.charAt(x) == '|') {
					this.walls.add(new Rectangle(xPos, yPos - (int) (WALL_SIZE / 2.5), WALL_SIZE / 4, WALL_SIZE));
				} else if (line.charAt(x) == '+') {
					this.walls.add(new Rectangle(xPos - (int) (WALL_SIZE / 2.5), yPos, WALL_SIZE, WALL_SIZE / 4));
					this.walls.add(new Rectangle(xPos, yPos - (int) (WALL_SIZE / 2.5), WALL_SIZE / 4, WALL_SIZE));
				} else if (line.charAt(x) == 'b') {
					this.sprites.add(new Battery(xPos, yPos, this, Battery.Type.REGULAR));
				} else if (line.charAt(x) == 't') {
					this.sprites.add(new Torch(xPos, yPos, this));
				} else if (line.charAt(x) == 'S') {
					startX = xPos;
					startY = yPos;
				} else if (line.charAt(x) == 'F') {
						this.finishPoint = new Point(xPos, yPos);
				} else {
					if (ThreadLocalRandom.current().nextFloat() < 0.005f / (this.difficulty / 4.0f)) {
						this.sprites.add(new Battery(xPos, yPos, this, Battery.Type.REGULAR));
					} else if (ThreadLocalRandom.current().nextFloat() < 0.0025f * (1.0f + this.difficulty / 4.0f)) {
						this.sprites.add(new Torch(xPos, yPos, this));
					}
					
					if (ThreadLocalRandom.current().nextFloat() < this.difficulty / 15.0f) {
						this.sprites.add(new Ghost(xPos, yPos, this, Ghost.Type.NORMAL));
					}
					
					if (ThreadLocalRandom.current().nextFloat() < this.difficulty / 35.0f) {
						this.sprites.add(new Zombie(xPos, yPos, this));
					}
					
					if (this.difficulty >= 1.75f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 40.0f) {
						this.sprites.add(new Brain(xPos, yPos, this));
					}
					
					if (this.difficulty >= 2.0f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 50.0f) {
						this.sprites.add(new GooBlob(xPos, yPos, this));
					}
					
					if (this.difficulty >= 1.75f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 45.0f) {
						this.sprites.add(new Spikeball(xPos, yPos, this));
					}
					
					if (this.difficulty >= 2.5f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 55.0f) {
						this.sprites.add(new Ghost(xPos, yPos, this, Ghost.Type.INFERNAL));
					}
					
					if (this.difficulty >= 3.25f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 60.0f) {
						this.sprites.add(new ObliterationVortex(xPos, yPos, this));
					}
				}
			}
		}
		
		this.player.setPos(startX, startY);
	}
	
	public void beginLevelup(int levelNumber) {
		int multiplier = levelNumber - this.levelNumber;
		this.levelupTimestamp = StaticFields.board.getStep();
		this.difficulty += 0.25f * multiplier;
		this.levelNumber = levelNumber;
		this.player.setLight(this.player.getLight() + 0.02f * multiplier);
		this.sprites.forEach(s -> {
			s.setRemove();
		});
		this.walls.clear();
		AudioUtils.playSound(this.getClass().getResource("/sounds/levelup.wav"), -5.0f);
	}
	
	private void levelup() {
		if (this.customLevel == null) {
			if (this.levelNumber < LEVELS.length) {
				this.level = LEVELS[this.levelNumber];
			}
			
			this.levelTime = 0;
			this.parseLevel();
		} else {
			StaticFields.board.setActiveUI(new UIMainMenu());
			StaticFields.board.setLevel(null);
		}
	}
	
	public void keyEvent(KeyEvent e, boolean pressed) {
		this.player.keyEvent(e, pressed);
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && pressed && this.gameOver) {
			StaticFields.board.setActiveUI(new UIMainMenu());
			StaticFields.board.setLevel(null);
		}
	}
	
	public void update(int step) {
		if (this.levelupTimestamp > 0 && step == this.levelupTimestamp + 100) {
			this.levelup();
		}
		
		if (!this.player.remove()) {
			this.player.update(step);
		} else if (!this.gameOver) {
			this.gameOver = true;
			this.sprites.forEach(s -> {
				s.setRemove();
			});
			this.walls.clear();
			AudioUtils.playSound(this.getClass().getResource("/sounds/lose.wav"), -5.0f);
		}
		
		ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
		spriteList.addAll(this.sprites);
		
		for (Sprite s : spriteList) {
			if (!s.remove()) {
				s.update(step);
			} else {
				this.sprites.remove(s);
			}
		}
		
		ArrayList<HoverText> hoverTextList = new ArrayList<HoverText>();
		hoverTextList.addAll(this.hoverTexts);
		
		for (HoverText ht : hoverTextList) {
			if (!ht.shouldRemove()) {
				ht.update(step);
			} else {
				this.hoverTexts.remove(ht);
			}
		}
		
		this.spawnExtraMonsters();
		
		this.levelTime++;
	}
	
	private void spawnExtraMonsters() {
		if ((this.levelupTimestamp == 0 || StaticFields.board.getStep() >= this.levelupTimestamp + 100) && ThreadLocalRandom.current().nextFloat() < this.difficulty * (this.levelTime / 1000000.0f)) {
			String[] lines = this.level.split("\n");
			
			for (int y = 0; y < lines.length; y++) {
				String line = lines[y];
				int yPos = y * WALL_SIZE;
				
				for (int x = 0; x < line.length(); x++) {
					int xPos = x * WALL_SIZE;
					
					if (this.player.distanceTo(xPos, yPos) > 150.0d) {
						if (line.charAt(x) != ':' && line.charAt(x) != '-' && line.charAt(x) != '|' && line.charAt(x) != '+' && line.charAt(x) != 'b' && line.charAt(x) != 'S' && line.charAt(x) != 'F') {
							if (ThreadLocalRandom.current().nextFloat() < this.difficulty / 15.0f) {
								this.sprites.add(new Ghost(xPos, yPos, this, Ghost.Type.NORMAL));
							}
							
							if (ThreadLocalRandom.current().nextFloat() < this.difficulty / 35.0f) {
								this.sprites.add(new Zombie(xPos, yPos, this));
							}
							
							if (this.difficulty >= 1.5f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 40.0f) {
								this.sprites.add(new Brain(xPos, yPos, this));
							}
							
							if (this.difficulty >= 1.5f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 42.5f) {
								this.sprites.add(new GooBlob(xPos, yPos, this));
							}
							
							if (this.difficulty >= 1.75f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 42.5f) {
								this.sprites.add(new Spikeball(xPos, yPos, this));
							}
							
							if (this.difficulty >= 2.0f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 45.0f) {
								this.sprites.add(new Ghost(xPos, yPos, this, Ghost.Type.INFERNAL));
							}
							
							if (this.difficulty >= 3.0f && ThreadLocalRandom.current().nextFloat() < this.difficulty / 60.0f) {
								this.sprites.add(new ObliterationVortex(xPos, yPos, this));
							}
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics2D g2d, int step) {
		Font prevFont = g2d.getFont();
		g2d.setFont(new Font(StaticFields.FONT_NAME, Font.BOLD, 15));
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, Board.WIDTH, Board.HEIGHT);
		g2d.drawImage(this.player.getImage(), Board.WIDTH / 2 - this.player.getHitbox().width / 2, Board.HEIGHT / 2 - this.player.getHitbox().height / 2, StaticFields.board);
		
		if (this.player.hasTorch()) {
			g2d.drawImage(Torch.IMAGE, Board.WIDTH / 2 - this.player.getHitbox().width / 2 - 1, Board.HEIGHT / 2 - this.player.getHitbox().height / 2 - 2, StaticFields.board);
		}
		
		g2d.setColor(Color.BLACK);
		
		int transX = (int) -this.player.getX() + Board.WIDTH / 2 - this.player.getHitbox().width / 2;
		int transY = (int) -this.player.getY() + Board.HEIGHT / 2 - this.player.getHitbox().height / 2;
		
		g2d.translate(transX, transY);
		
		if (this.isWithinScreen(new Rectangle(this.finishPoint.x, this.finishPoint.y, this.finishImage.getWidth(StaticFields.board), this.finishImage.getHeight(StaticFields.board)))) {
			g2d.drawImage(this.finishImage, this.finishPoint.x, this.finishPoint.y, StaticFields.board);
		}
		
		for (Sprite s : this.sprites) {
			if (s.getImage() != null) {
				if (this.isWithinScreen(s.getHitbox())) {
					g2d.drawImage(s.getImage(), (int) s.getX(), (int) s.getY(), StaticFields.board);
				}
			}
		}
		
		for (Rectangle wall : this.walls) {
			if (this.isWithinScreen(wall)) {
				g2d.fillRect(wall.x, wall.y, wall.width, wall.height);
			}
		}
		
		for (HoverText ht : this.hoverTexts) {
			g2d.setColor(ht.getColor());
			g2d.drawString(ht.getText(), ht.getX(), ht.getY());
		}
		
		g2d.translate(-transX, -transY);
		
		int radius = (int) ((this.player.getLight() + ThreadLocalRandom.current().nextFloat() * 0.004f) * (Board.WIDTH + Board.HEIGHT) / 4);
		
		if (radius < 1) {
			radius = 1;
		}
		
		TransparentGradientInImage.updateGradientAt(SHADOW_IMAGE, SHADOW, new Point(Board.WIDTH / 2, Board.HEIGHT / 2), radius, (int) (this.player.getBattery() * 255));
		g2d.drawImage(SHADOW, 0, 0, StaticFields.board);
		
		if (this.player.getBattery() > 0.75f) {
			g2d.setColor(Color.YELLOW);
		} else if (this.player.getBattery() > 0.5f) {
			g2d.setColor(Color.ORANGE);
		} else if (this.player.getBattery() > 0.25f) {
			g2d.setColor(Color.GREEN);
		} else if (this.player.getBattery() > 0.0f) {
			g2d.setColor(Color.RED);
		}
		
		g2d.drawString(Integer.toString((int) Math.floor(1 + this.player.getBattery() * 5)), (int) ((1.0f - this.player.getLight()) * Board.WIDTH / 2) - 30, Board.HEIGHT / 2 - 35);
		g2d.fillRect((int) ((1.0f - this.player.getLight()) * Board.WIDTH / 2) - 30, Board.HEIGHT / 2 - 24 + (int) (50 * (1.0f - this.player.getBattery())), 10, (int) (50 * this.player.getBattery()));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawRect((int) ((1.0f - this.player.getLight()) * Board.WIDTH / 2) - 30, Board.HEIGHT / 2 - 25, 10, 50);
		g2d.drawString(Integer.toString(this.score), Board.WIDTH / 2 - 5, (int) (this.player.getLight() * Board.HEIGHT / 2 + Board.HEIGHT / 2) + 10);
		g2d.setColor(Color.RED);
		
		if (this.player.getNearbyMonster() != null && step % 30 <= 15) {
			g2d.drawImage(this.player.getNearbyMonster().getImage(), Board.WIDTH / 2 - this.player.getNearbyMonster().getImage().getWidth(StaticFields.board) / 2, (int) ((1.0f - this.player.getLight()) * Board.HEIGHT / 2) - this.player.getNearbyMonster().getImage().getHeight(StaticFields.board) - 20, StaticFields.board);
			g2d.drawString(this.player.getNearbyMonster() + " Nearby!", Board.WIDTH / 2 - 60, (int) ((1.0f - this.player.getLight()) * Board.HEIGHT / 2));
		}
		
		g2d.setColor(Color.WHITE);
		
		int randomness = (int) (ThreadLocalRandom.current().nextFloat() * 2.0f);
		
		if (this.levelupTimestamp > 0 && step < this.levelupTimestamp + 100) {
			g2d.drawImage(SHADOW_IMAGE, 0, 0, StaticFields.board);
			
			if (this.customLevel == null) {
				String difficulty = "Undefined";
				
				if (this.difficulty < 1.5f) {
					difficulty = "Easy";
				} else if (this.difficulty >= 1.5f && this.difficulty < 2.0f) {
					difficulty = "Normal";
				} else if (this.difficulty >= 2.0f && this.difficulty < 2.5f) {
					difficulty = "Hard";
				} else if (this.difficulty >= 2.5f && this.difficulty < 3.0f) {
					difficulty = "Harder";
				} else if (this.difficulty >= 3.0f && this.difficulty < 3.5f) {
					difficulty = "Insane";
				} else if (this.difficulty >= 3.5f && this.difficulty < 4.0f) {
					difficulty = "Demonic";
				}
				
				g2d.drawString("Level Up!", Board.WIDTH / 2 - 30, Board.HEIGHT / 2 + randomness);
				g2d.drawString("Level: " + (this.levelNumber + 1), Board.WIDTH / 2 - 25, Board.HEIGHT / 2 + 30 + randomness);
				g2d.drawString("Difficulty: " + difficulty, Board.WIDTH / 2 - 50, Board.HEIGHT / 2 + 60 + randomness);
			} else {
				g2d.drawString("Level Completed!", Board.WIDTH / 2 - 50, Board.HEIGHT / 2 + randomness);
				g2d.drawString("Level: " + this.customLevel.name, Board.WIDTH / 2 - 25, Board.HEIGHT / 2 + 30 + randomness);
			}
		}
		
		if (this.gameOver) {
			g2d.drawImage(SHADOW_IMAGE, 0, 0, StaticFields.board);
			g2d.drawString("Game Over", Board.WIDTH / 2 - 30, Board.HEIGHT / 2 + randomness);
			
			String monsterName = this.player.killer.toString();
			String a = "a";
			
			if (monsterName.charAt(0) == 'A' || monsterName.charAt(0) == 'E' || monsterName.charAt(0) == 'I' || monsterName.charAt(0) == 'O' || monsterName.charAt(0) == 'U') {
				a = "an";
			}
			
			g2d.drawString("You were killed by " + a + " " + monsterName, Board.WIDTH / 2 - 80, Board.HEIGHT / 2 + 30 + randomness);
			
			if (this.customLevel == null) {
				g2d.drawString("Your level: " + (this.levelNumber + 1), Board.WIDTH / 2 - 40, Board.HEIGHT / 2 + 60 + randomness);
			} else {
				g2d.drawString("Your level: " + this.customLevel.name, Board.WIDTH / 2 - 40, Board.HEIGHT / 2 + 60 + randomness);
			}
			
			g2d.drawString("Your score: " + this.score, Board.WIDTH / 2 - 40, Board.HEIGHT / 2 + 90 + randomness);
		}
		
		g2d.setFont(prevFont);
	}
	
	private boolean isWithinScreen(Rectangle r) {
		return new Rectangle((int) this.player.getX() - Board.WIDTH / 2, (int) this.player.getY() - Board.HEIGHT / 2, Board.WIDTH, Board.HEIGHT).intersects(r);
	}
	
	public void incrementScore(int amount) {
		this.score += amount;
	}
}

package gatocreador887.darkness.sprite;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;
import gatocreador887.darkness.StaticFields;
import gatocreador887.darkness.util.graphics.HoverText;
import gatocreador887.darkness.util.sound.AudioUtils;

public class Player extends Sprite {
	public static final Image NORMAL_IMAGE = new ImageIcon(Main.class.getResource("/sprites/player/player_normal.png")).getImage();
	public static final Image FRIGHTENED_IMAGE = new ImageIcon(Main.class.getResource("/sprites/player/player_frightened.png")).getImage();
	private float light;
	private float battery;
	private double speed;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	private boolean frightened;
	private boolean levelup;
	private Sprite nearbyMonster;
	private boolean invulnerable;
	private boolean switchingLevel;
	private String levelSwitchText = "";
	private float torchBurn;
	public Sprite killer;
	
	public Player(double x, double y, Level level) {
		super(x, y, level);
		
		this.speed = 1.6d;
		this.image = NORMAL_IMAGE;
		this.width = 16;
		this.height = 16;
	}
	
	public Player(Player oldPlayer) {
		this(oldPlayer.x, oldPlayer.y, oldPlayer.level);
		
		this.light = oldPlayer.light;
		this.battery = oldPlayer.battery;
		this.speed = oldPlayer.speed;
		this.frightened = oldPlayer.frightened;
		this.invulnerable = oldPlayer.invulnerable;
		this.torchBurn = oldPlayer.torchBurn;
	}
	
	public String getName() {
		return "Player";
	}
	
	public void update(int step) {
		super.update(step);
		
		if (!this.levelup && this.getHitbox().intersects(new Rectangle(this.level.finishPoint.x, this.level.finishPoint.y, this.level.finishImage.getWidth(StaticFields.board), this.level.finishImage.getHeight(StaticFields.board)))) {
			this.level.beginLevelup(this.level.levelNumber + 1);
			this.levelup = true;
		}
		
		if (!this.levelup) {
			int divisor = (int) (40 / this.level.difficulty);
			
			if (divisor < 1) {
				divisor = 1;
			}
			
			if (step % divisor == 0) {
				this.level.incrementScore(1);
			}
			
			if (!this.hasTorch()) {
				this.battery -= 0.00015f;
			}
		}
		
		if (this.battery < 0.0f) {
			this.battery = 0.0f;
		}
		
		if (this.hasTorch()) {
			this.torchBurn -= 0.001f;
		}
		
		double speed = this.speed;
		
		if (this.isFrightened()) {
			speed *= 1.3d;
		}
		
		if (this.moveLeft && !this.moveUp && !this.moveDown) {
			this.x -= speed;
		} else if (this.moveLeft) {
			this.x -= speed * 0.75d;
		} else if (this.moveRight && !this.moveUp && !this.moveDown) {
			this.x += speed;
		} else if (this.moveRight) {
			this.x += speed * 0.75d;
		}
		
		if (this.moveUp && !this.moveLeft && !this.moveRight) {
			this.y -= speed;
		} else if (this.moveUp) {
			this.y -= speed / 2;
		} else if (this.moveDown && !this.moveLeft && !this.moveRight) {
			this.y += speed;
		} else if (this.moveDown) {
			this.y += speed / 2;
		}
		
		Sprite nearestMonster = null;
		double dist = Double.MAX_VALUE;
		
		for (Sprite s : this.level.sprites) {
			if (s instanceof Monster) {
				double newMonsterDist = this.distanceTo(s);
				
				if (newMonsterDist < dist) {
					nearestMonster = s;
					dist = newMonsterDist;
				}
			}
			
			if (this.getHitbox().intersects(s.getHitbox())) {
				if (s instanceof Battery) {
					Battery b = (Battery) s;
					
					if (b.getType() == Battery.Type.REGULAR) {
						this.battery += 0.2f;
						this.level.incrementScore(15);
						AudioUtils.playSound(this.getClass().getResource("/sounds/battery_regular_collect.wav"), -5.0f);
						this.level.hoverTexts.add(new HoverText("+1B", Color.LIGHT_GRAY, (int) b.x, (int) b.y, step + 50));
						this.level.hoverTexts.add(new HoverText("+15", Color.LIGHT_GRAY, (int) b.x, (int) b.y + 10, step + 50));
					}
					
					if (this.battery > 1.0f) {
						this.battery = 1.0f;
					}
					
					b.setRemove();
				} else if (s instanceof Torch) {
					this.torchBurn = 1.0f;
					this.level.incrementScore(30);
					AudioUtils.playSound(this.getClass().getResource("/sounds/torch_collect.wav"), -5.0f);
					this.level.hoverTexts.add(new HoverText("+30", Color.LIGHT_GRAY, (int) s.x, (int) s.y, step + 50));
					s.setRemove();
				} else if (s instanceof MeleeMonster || s instanceof MonsterProjectile) {
					if (this.hasTorch()) {
						s.setRemove();
						this.level.incrementScore(75);
						this.level.hoverTexts.add(new HoverText("+75", Color.LIGHT_GRAY, (int) s.x, (int) s.y, step + 50));
					} else if (!this.invulnerable) {
						this.killer = s;
						this.setRemove();
					}
				}
			}
		}
		
		if (dist < 150.0d) {
			double freq = dist / 150.0d;
			
			if (freq < 0.05d) {
				freq = 0.05d;
			}
			
			if (step % (int) (freq * 150) == 0) {
				AudioUtils.playSound(this.getClass().getResource("/sounds/alarm.wav"), -10.0f);
			}
			
			if (dist < 100.0d) {
				this.nearbyMonster = nearestMonster;
				this.setFrightened(true);
			} else {
				this.nearbyMonster = null;
				this.setFrightened(false);
			}
		} else {
			this.setFrightened(false);
		}
	}
	
	public void keyEvent(KeyEvent e, boolean pressed) {
		if (pressed) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					this.moveLeft = true;
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					this.moveRight = true;
					break;
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					this.moveUp = true;
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					this.moveDown = true;
					break;
				case KeyEvent.VK_T:
					this.light = 0.85f;
					this.invulnerable = true;
					break;
				case KeyEvent.VK_L:
					this.switchingLevel = true;
					break;
			}
			
			if (this.switchingLevel) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_0:
					case KeyEvent.VK_1:
					case KeyEvent.VK_2:
					case KeyEvent.VK_3:
					case KeyEvent.VK_4:
					case KeyEvent.VK_5:
					case KeyEvent.VK_6:
					case KeyEvent.VK_7:
					case KeyEvent.VK_8:
					case KeyEvent.VK_9:
						this.levelSwitchText += e.getKeyChar();
						break;
					case KeyEvent.VK_ENTER:
						this.switchingLevel = false;
						
						if (!this.levelSwitchText.isEmpty()) {
							this.level.beginLevelup(Integer.parseInt(this.levelSwitchText));
							this.levelSwitchText = "";
						} else {
							this.level.beginLevelup(this.level.levelNumber + 1);
						}
						
						break;
				}
			}
		} else {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					this.moveLeft = false;
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					this.moveRight = false;
					break;
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					this.moveUp = false;
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					this.moveDown = false;
					break;
			}
		}
	}
	
	public float getLight() {
		return this.torchBurn > 0.1f ? 0.3f : (this.hasTorch() ? 0.25f : this.light);
	}
	
	public void setLight(float light) {
		this.light = light;
	}
	
	public float getBattery() {
		return this.battery;
	}
	
	public void setBattery(float battery) {
		this.battery = battery;
	}
	
	public boolean isFrightened() {
		return this.frightened;
	}
	
	public void setFrightened(boolean frightened) {
		this.frightened = frightened;
		
		if (this.frightened) {
			if (!this.image.equals(FRIGHTENED_IMAGE)) {
				this.image = FRIGHTENED_IMAGE;
			}
		} else {
			if (!this.image.equals(NORMAL_IMAGE)) {
				this.image = NORMAL_IMAGE;
			}
		}
	}
	
	public Sprite getNearbyMonster() {
		return this.nearbyMonster;
	}
	
	public boolean hasTorch() {
		return this.torchBurn > 0.0f;
	}
}

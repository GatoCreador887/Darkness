package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class Spikeball extends Directioned implements MeleeMonster, ProjectileMonster {
	public static final Image IMAGE_0 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_0.png")).getImage();
	public static final Image IMAGE_1 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_1.png")).getImage();
	public static final Image IMAGE_2 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_2.png")).getImage();
	public static final Image IMAGE_3 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_3.png")).getImage();
	public static final Image IMAGE_4 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_4.png")).getImage();
	public static final Image IMAGE_5 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_5.png")).getImage();
	public static final Image IMAGE_6 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_6.png")).getImage();
	public static final Image IMAGE_7 = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_7.png")).getImage();
	public static final Image PROJECTILE_IMAGE = new ImageIcon(Main.class.getResource("/sprites/monster/spikeball/spikeball_projectile.png")).getImage();
	private int animationVariation = ThreadLocalRandom.current().nextInt(4);
	private int spikeAmount;
	private int randomShootOffset = ThreadLocalRandom.current().nextInt(15);
	
	public Spikeball(double x, double y, Level level) {
		super(x, y, level);
		
		this.width = 16;
		this.height = 16;
		this.image = IMAGE_0;
		this.spikeAmount = 4;
		this.speed = 0.8d;
	}
	
	public String getName() {
		return "Spikeball";
	}
	
	public void update(int step) {
		super.update(step);
		
		this.direction += (ThreadLocalRandom.current().nextFloat() - ThreadLocalRandom.current().nextFloat()) * 0.1f;
		
		if (this.level != null && this.distanceTo(this.level.player) < 175.0d) {
			double directionDegrees = Math.toDegrees(Math.atan2(this.level.player.x - this.x, this.level.player.y - this.y));
			this.direction = (270.0f - (float) directionDegrees + 180.0f) / 360.0f;
		}
		
		if ((step + this.randomShootOffset + ThreadLocalRandom.current().nextInt(4)) % 30 == 0 && this.level != null && this.distanceTo(this.level.player) <= 75.0d) {
			this.level.sprites.add(new SpikeProjectile(this.x, this.y, this, this.direction, 5.0d));
			this.spikeAmount--;
			
			if (this.spikeAmount == 3) {
				this.image = IMAGE_2;
			} else if (this.spikeAmount == 2) {
				this.image = IMAGE_4;
			} else if (this.spikeAmount == 1) {
				this.image = IMAGE_6;
			} else if (this.spikeAmount == 0) {
				this.remove = true;
			}
		}
		
		double phi = 2 * Math.PI * this.direction;
		double addedX = Math.cos(phi) * this.speed;
		double addedY = Math.sin(phi) * this.speed;
		
		if (this.level != null ? this.distanceTo(this.level.player) > 75.0d : true) {
			this.x += addedX;
			this.y += addedY;
		}
		
		if ((step + this.animationVariation) % 3 == 0) {
			if (this.image == IMAGE_0) {
				this.image = IMAGE_1;
			} else if (this.image == IMAGE_1) {
				this.image = IMAGE_0;
			}
			if (this.image == IMAGE_2) {
				this.image = IMAGE_3;
			} else if (this.image == IMAGE_3) {
				this.image = IMAGE_2;
			}
			if (this.image == IMAGE_4) {
				this.image = IMAGE_5;
			} else if (this.image == IMAGE_5) {
				this.image = IMAGE_4;
			}
			if (this.image == IMAGE_6) {
				this.image = IMAGE_7;
			} else if (this.image == IMAGE_7) {
				this.image = IMAGE_6;
			}
		}
	}
	
	public class SpikeProjectile extends Sprite implements MonsterProjectile {
		private Spikeball owner;
		private float direction;
		private double speed;
		
		public SpikeProjectile(double x, double y, Spikeball owner, float direction, double speed) {
			super(x, y, owner.level);
			
			this.owner = owner;
			this.direction = direction;
			this.speed = speed;
			this.width = 6;
			this.height = 6;
			this.image = PROJECTILE_IMAGE;
		}
		
		public String getName() {
			return this.owner.getName();
		}
		
		public void update(int step) {
			super.update(step);
			
			double phi = 2 * Math.PI * this.direction;
			double addedX = Math.cos(phi) * this.speed;
			double addedY = Math.sin(phi) * this.speed;
			
			this.x += addedX;
			this.y += addedY;
		}
		
		public void onImpact() {
			this.remove = true;
		}
	}
}

package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class GooBlob extends Directioned implements MeleeMonster, ProjectileMonster {
	public static final Image IMAGE = new ImageIcon(Main.class.getResource("/sprites/monster/goo_blob/goo_blob.png")).getImage();
	public static final Image PROJECTILE_IMAGE = new ImageIcon(Main.class.getResource("/sprites/monster/goo_blob/goo_blob_projectile.png")).getImage();
	private int randomMoveOffset = ThreadLocalRandom.current().nextInt(30);
	
	public GooBlob(double x, double y, Level level) {
		super(x, y, level);
		
		this.width = 14;
		this.height = 9;
		this.image = IMAGE;
	}
	
	public String getName() {
		return "Goo Blob";
	}
	
	public void update(int step) {
		super.update(step);
		
		this.direction += (ThreadLocalRandom.current().nextFloat() - ThreadLocalRandom.current().nextFloat()) * 0.025f;
		
		if (this.level != null && this.distanceTo(this.level.player) < 200.0d) {
			double directionDegrees = Math.toDegrees(Math.atan2(this.level.player.x - this.x, this.level.player.y - this.y));
			this.direction = (270.0f - (float) directionDegrees + 180.0f) / 360.0f;
			
			if (this.level.player.hasTorch()) {
				this.direction += 0.5f;
			}
		}
		
		if ((step + this.randomMoveOffset + ThreadLocalRandom.current().nextInt(4)) % 30 == 0 && this.level != null && this.distanceTo(this.level.player) <= 125.0d && !this.level.player.hasTorch()) {
			this.level.sprites.add(new GooProjectile(this.x, this.y, this, this.direction, 2.5d));
		}
		
		if ((step + this.randomMoveOffset + ThreadLocalRandom.current().nextInt(2)) % 30 <= 8) {
			double phi = 2 * Math.PI * this.direction;
			double addedX = Math.cos(phi) * 0.5d;
			double addedY = Math.sin(phi) * 0.5d;
			
			if (this.level != null ? this.distanceTo(this.level.player) > 125.0d || this.level.player.hasTorch() : true) {
				this.x += addedX;
				this.y += addedY;
			}
		}
	}
	
	public class GooProjectile extends Sprite implements MonsterProjectile {
		private GooBlob owner;
		private float direction;
		private double speed;
		
		public GooProjectile(double x, double y, GooBlob owner, float direction, double speed) {
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

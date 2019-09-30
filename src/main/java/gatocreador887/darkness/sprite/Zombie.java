package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class Zombie extends Directioned implements MeleeMonster {
	public static final Image IMAGE = new ImageIcon(Main.class.getResource("/sprites/monster/zombie.png")).getImage();
	
	public Zombie(double x, double y, Level level) {
		super(x, y, level);
		
		this.image = IMAGE;
		this.width = 16;
		this.height = 16;
		this.speed = 0.6d + ThreadLocalRandom.current().nextDouble() * 0.1d;
	}
	
	public String getName() {
		return "Zombie";
	}
	
	public void update(int step) {
		super.update(step);
		
		this.direction += (ThreadLocalRandom.current().nextFloat() - ThreadLocalRandom.current().nextFloat()) * 0.05f;
		
		if (this.level != null && this.distanceTo(this.level.player) < 300.0d) {
			double directionDegrees = Math.toDegrees(Math.atan2(this.level.player.x - this.x, this.level.player.y - this.y));
			this.direction = (270.0f - (float) directionDegrees + 180.0f) / 360.0f;
			
			if (this.level.player.hasTorch()) {
				this.direction += 0.5f;
			}
		}
		
		double phi = 2 * Math.PI * this.direction;
		double addedX = Math.cos(phi) * this.speed;
		double addedY = Math.sin(phi) * this.speed;
		
		this.x += addedX;
		this.y += addedY;
	}
}

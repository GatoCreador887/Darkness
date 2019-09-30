package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class Brain extends Directioned implements MeleeMonster {
	public static final Image IMAGE_0 = new ImageIcon(Main.class.getResource("/sprites/monster/brain/brain_0.png")).getImage();
	public static final Image IMAGE_1 = new ImageIcon(Main.class.getResource("/sprites/monster/brain/brain_1.png")).getImage();
	private int randomMoveOffset = ThreadLocalRandom.current().nextInt(20);
	
	public Brain(double x, double y, Level level) {
		super(x, y, level);
		
		this.image = IMAGE_0;
		this.width = 16;
		this.height = 16;
	}
	
	public String getName() {
		return "Brain";
	}
	
	public void update(int step) {
		super.update(step);
		
		this.direction += (ThreadLocalRandom.current().nextFloat() - ThreadLocalRandom.current().nextFloat()) * 0.05f;
		
		if (this.level != null && this.distanceTo(this.level.player) < 200.0d) {
			double directionDegrees = Math.toDegrees(Math.atan2(this.level.player.x - this.x, this.level.player.y - this.y));
			this.direction = (270.0f - (float) directionDegrees + 180.0f) / 360.0f;
			
			if (this.level.player.hasTorch()) {
				this.direction += 0.5f;
			}
		}
		
		if ((step + this.randomMoveOffset + ThreadLocalRandom.current().nextInt(2)) % 20 <= 4) {
			double phi = 2 * Math.PI * this.direction;
			double addedX = Math.cos(phi) * 3.1d;
			double addedY = Math.sin(phi) * 3.1d;
			
			this.x += addedX;
			this.y += addedY;
			
			if (this.image == IMAGE_0) {
				this.image = IMAGE_1;
			} else if (this.image == IMAGE_1) {
				this.image = IMAGE_0;
			}
		}
	}
}

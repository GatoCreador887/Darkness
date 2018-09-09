package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;
import gatocreador887.darkness.util.sound.AudioUtils;

public class ObliterationVortex extends Directioned implements MeleeMonster {
	public static final Image IMAGE_0 = new ImageIcon(Main.class.getResource("/sprites/monster/obliteration_vortex/obliteration_vortex_0.png")).getImage();
	public static final Image IMAGE_1 = new ImageIcon(Main.class.getResource("/sprites/monster/obliteration_vortex/obliteration_vortex_1.png")).getImage();
	public static final Image IMAGE_2 = new ImageIcon(Main.class.getResource("/sprites/monster/obliteration_vortex/obliteration_vortex_2.png")).getImage();
	public static final Image IMAGE_3 = new ImageIcon(Main.class.getResource("/sprites/monster/obliteration_vortex/obliteration_vortex_3.png")).getImage();
	private int animationVariation = ThreadLocalRandom.current().nextInt(2);
	
	public ObliterationVortex(double x, double y, Level level) {
		super(x, y, level);
		
		this.image = IMAGE_0;
		this.width = 16;
		this.height = 16;
	}
	
	public String getName() {
		return "Obliteration Vortex";
	}
	
	public void update(int step) {
		super.update(step);
		
		this.direction += (ThreadLocalRandom.current().nextFloat() - ThreadLocalRandom.current().nextFloat()) * 0.1f;
		
		if (this.level != null && this.distanceTo(this.level.player) < 375.0d) {
			double directionDegrees = Math.toDegrees(Math.atan2(this.level.player.x - this.x, this.level.player.y - this.y));
			this.direction = (270.0f - (float) directionDegrees + 180.0f) / 360.0f;
		}
		
		double phi = 2 * Math.PI * this.direction;
		double addedX = Math.cos(phi) * 1.15d;
		double addedY = Math.sin(phi) * 1.15d;
		
		this.x += addedX;
		this.y += addedY;
		
		if ((step + this.animationVariation) % 3 == 0) {
			if (this.image == IMAGE_0) {
				this.image = IMAGE_1;
			} else if (this.image == IMAGE_1) {
				this.image = IMAGE_2;
			} else if (this.image == IMAGE_2) {
				this.image = IMAGE_3;
			} else if (this.image == IMAGE_3) {
				this.image = IMAGE_0;
			}
		}
		
		if (this.level != null) {
			for (Sprite s : this.level.sprites) {
				if (this.getHitbox().intersects(s.getHitbox())) {
					if (!(s instanceof ObliterationVortex)) {
						s.setRemove();
						AudioUtils.playSound(this.getClass().getResource("/sounds/obliterate.wav"), -5.0f);
					}
				}
			}
		}
	}
}

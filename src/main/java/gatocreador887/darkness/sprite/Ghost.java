package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.Level;
import gatocreador887.darkness.Main;

public class Ghost extends Directioned implements MeleeMonster {
	public static final Image NORMAL_IMAGE = new ImageIcon(Main.class.getResource("/sprites/monster/ghost.png")).getImage();
	public static final Image INFERNAL_IMAGE = new ImageIcon(Main.class.getResource("/sprites/monster/infernal_ghost.png")).getImage();
	private Type type;
	
	public Ghost(double x, double y, Level level, Type type) {
		super(x, y, level);
		
		this.type = type;
		
		if (this.type == Type.NORMAL) {
			this.image = NORMAL_IMAGE;
			this.speed = 0.9d;
		} else if (this.type == Type.INFERNAL) {
			this.image = INFERNAL_IMAGE;
			this.speed = 1.1d;
		}
		
		this.speed += ThreadLocalRandom.current().nextDouble() * 0.05d;
		
		this.width = 16;
		this.height = 16;
	}
	
	public String getName() {
		return this.type.name;
	}
	
	public void update(int step) {
		super.update(step);
		
		this.direction += (ThreadLocalRandom.current().nextFloat() - ThreadLocalRandom.current().nextFloat()) * 0.1f;
		
		if (this.level != null && this.distanceTo(this.level.player) < 150.0d) {
			double directionDegrees = Math.toDegrees(Math.atan2(this.level.player.x - this.x, this.level.player.y - this.y));
			this.direction = (270.0f - (float) directionDegrees + 180.0f) / 360.0f;
			
			if (this.type.afraidOfLight && this.level.player.getBattery() > this.type.afraidOfLightBrightness && this.distanceTo(this.level.player) < this.level.player.getLight() * ((Board.WIDTH + Board.HEIGHT) / 2) / 2) {
				this.direction = 1.0f - this.direction;
			}
		}
		
		double phi = 2 * Math.PI * this.direction;
		double addedX = Math.cos(phi) * this.speed;
		double addedY = Math.sin(phi) * this.speed;
		
		this.x += addedX;
		this.y += addedY;
	}
	
	public enum Type {
		NORMAL(true, 0.8f, "Ghost"),
		INFERNAL(false, 0.0f, "Infernal Ghost");
		
		public final boolean afraidOfLight;
		public final float afraidOfLightBrightness;
		public final String name;
		
		private Type(boolean afraidOfLight, float afraidOfLightBrightness, String name) {
			this.afraidOfLight = afraidOfLight;
			this.afraidOfLightBrightness = afraidOfLightBrightness;
			this.name = name;
		}
	}
}

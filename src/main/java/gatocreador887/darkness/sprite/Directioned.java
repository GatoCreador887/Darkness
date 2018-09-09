package gatocreador887.darkness.sprite;

import java.util.concurrent.ThreadLocalRandom;

import gatocreador887.darkness.Level;

public abstract class Directioned extends Sprite {
	protected float direction = ThreadLocalRandom.current().nextFloat();
	protected double speed;
	
	public Directioned(double x, double y, Level level) {
		super(x, y, level);
	}
	
	public void update(int step) {
		super.update(step);
		
		if (this.direction < 0.0f) {
			this.direction = 1.0f + this.direction;
		} else if (this.direction > 1.0f) {
			this.direction = 1.0f - this.direction;
		}
	}
	
	public float getDirection() {
		return this.direction;
	}
	
	public void setDirection(float direction) {
		this.direction = direction;
	}
}

package gatocreador887.darkness.sprite;

import java.awt.Image;
import java.awt.Rectangle;

import gatocreador887.darkness.Level;

public abstract class Sprite {
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected Image image;
	protected boolean remove;
	protected Level level;
	
	public Sprite(double x, double y, Level level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}
	
	public abstract String getName();
	
	public void update(int step) {
		if (this.doesWallCheck() && this.level != null) {
			for (Rectangle wall : this.level.walls) {
				if (this.getHitbox().intersects(wall)) {
					this.onImpact();
					
					if (this.x < wall.x + wall.width / 2) {
						this.x += wall.x - (this.x + this.width);
					} else if (this.x > wall.x + wall.width / 2) {
						this.x += (wall.x + wall.width) - this.x;
					}
					
					if (this.y < wall.y + wall.height / 2) {
						this.y += wall.y - (this.y + this.height);
					} else if (this.y > wall.y + wall.height / 2) {
						this.y += (wall.y + wall.height) - this.y;
					}
				}
			}
		}
	}
	
	protected void onImpact() {
	}
	
	public boolean doesWallCheck() {
		return true;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int) this.x, (int) this.y, this.width, this.height);
	}
	
	public double distanceTo(double x, double y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}
	
	public double distanceTo(Sprite another) {
		return this.distanceTo(another.x, another.y);
	}
	
	public boolean remove() {
		return this.remove;
	}
	
	public void setRemove() {
		this.remove = true;
	}
	
	public String toString() {
		return this.getName();
	}
}

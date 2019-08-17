
public class Postion {
	
	int x, y;
	Double speed = 0.0, jumpSpeed = 0.0, y_speed = 0.0;
	boolean moving = false;
	
	public Postion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
		moving = true;
	}
	
	public void setJumpSpeed(double jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
		moving = true;
	}
}

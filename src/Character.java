
public class Character {
	
	int lives = 3;
	int points = 0;
	Postion shape;
	boolean onGround = false, right = false, left = false, jump = false;
	
	public Character(Postion shape, double speed) {
		this.shape = shape;
		shape.setSpeed(10);
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setJump(boolean jump) {
		this.jump = jump;
	}
}
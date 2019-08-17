
public class Engine {
	double Gravity = .3;
	Obstacle[] obstacles;
	Character[] characters;
	//Enemey[] enemies;
	
	public Engine(Obstacle[] obstacles) {
		this.obstacles = obstacles;
	}
	
	public void update_postion(Character var) {
		
		if(var.right) {
			if(goRight((Rectangle)var.shape)) {
				var.shape.x += var.shape.speed;
			} else {
				var.shape.x += distanceToRight((Rectangle) var.shape);
			}
			
		} else if(var.left) {
			if(goLeft((Rectangle)var.shape)) {
				var.shape.x -= var.shape.speed;
			} else {
				var.shape.x -= distanceToLeft((Rectangle) var.shape);
			}
		}
		
		if(!var.onGround) {
			var.shape.y_speed -= Gravity;
			if(var.shape.y_speed > 0) {
				
				if(hitTheCeiling((Rectangle) var.shape)) {
					var.shape.y -= distanceToCeiling((Rectangle) var.shape);
					var.shape.y_speed = 0.0;
					System.out.println("hitTheCeiling");
				} else {
					var.shape.y -= var.shape.y_speed;
					System.out.println("raising");
				}
				
			} else {
				
				if(onTheGround((Rectangle)var.shape)) {
					var.shape.y += distanceToGround((Rectangle) var.shape);
					var.onGround= true;
					var.shape.y_speed = -0.000001;
					System.out.println("onTheGround");
				} else {
					var.shape.y -= var.shape.y_speed;
					System.out.println("falling");
				}
			}
			
		}else if(var.jump) {
			var.onGround = false;
			var.jump = false;
			var.shape.y_speed = var.shape.jumpSpeed;
			System.out.println("Jumped");
			// 
		}  else {
			var.onGround = onTheGround((Rectangle) var.shape);
			System.out.println("Check the ground");
		}
	}
	
	public boolean goLeft(Rectangle postion) {
		// Going through all hit box in the game
		for (int i = 0; i < obstacles.length; i++) {
			Rectangle obstacle = (Rectangle)obstacles[i].shape;
			
			if(postion.y <= obstacle.y + obstacle.height && postion.y + postion.height >= obstacle.y && obstacle.x + 
					obstacle.width <= postion.x && postion.x < obstacle.x + obstacle.width + postion.speed) 
				return false;
			
		}
		return true;
	}

	public double distanceToLeft(Rectangle postion) {
		// Going through all hit box in the game
		double speed = postion.speed;
		for (int i = 0; i < obstacles.length; i++) {
			Rectangle obstacle = (Rectangle)obstacles[i].shape;
			
			if(postion.y <= obstacle.y + obstacle.height && postion.y + postion.height >= obstacle.y && obstacle.x + 
					obstacle.width <= postion.x && postion.x < obstacle.x + obstacle.width + speed) 
				speed = postion.x - obstacle.x - obstacle.width;
			
		}
		return speed;
	}
	
	public boolean goRight(Rectangle postion) {

		// Going through all hit box in the game
		for (int i = 0; i < obstacles.length; i++) {
			Rectangle obstacle = (Rectangle)obstacles[i].shape;
			if (postion.y <= obstacle.x + obstacle.width && postion.y + postion.height >= obstacle.y && obstacle.x + obstacle.width >= 
					postion.x + postion.width && postion.x + postion.width > obstacle.x - obstacle.speed)
				return false;
			
		}
		return true;
	}
	
	public double distanceToRight(Rectangle postion) {
		double speed = postion.speed;
		// Going through all hit box in the game
		for (int i = 0; i < obstacles.length; i++) {
			Rectangle obstacle = (Rectangle)obstacles[i].shape;
			
			if (postion.y <= obstacle.x + obstacle.width && postion.y + postion.height > obstacle.y && obstacle.x + obstacle.width >= 
					postion.x + postion.width && postion.x + postion.width > obstacle.x  - speed) {
				speed = obstacle.x - postion.x - postion.width ;
			}
			
		}
		return speed;
	}
	
	public boolean hitTheCeiling(Rectangle postion) {
		return distanceToCeiling(postion) != postion.y_speed;
	}
	
	public double distanceToCeiling(Rectangle postion) {
		double y_speed = postion.y_speed;
		
		// Going through all hit box in the game
		for (int i = 0; i < obstacles.length; i++) {
			Rectangle obstacle = (Rectangle)obstacles[i].shape;
			
			if(postion.y < obstacle.y + obstacle.height && y_speed > postion.y - obstacle.y + obstacle.height 
					&& postion.x <= obstacle.x + obstacle.width && postion.x + postion.width >= obstacle.y) {
				y_speed = postion.y - obstacle.y + obstacle.height;
			}
		}
		return y_speed;
	}
	
	public boolean onTheGround(Rectangle postion) {
		return distanceToGround(postion) != postion.y_speed;
	}
	
	public double distanceToGround(Rectangle postion) {
		double y_speed = postion.y_speed;
		
		// Going through all hit box in the game
		for (int i = 0; i < obstacles.length; i++) {
			Rectangle obstacle = (Rectangle)obstacles[i].shape;
			if(postion.y + postion.height <= obstacle.y && postion.y + postion.height - y_speed > obstacle.y
					&& postion.x < obstacle.x + obstacle.width && postion.x + postion.width > obstacle.x) {
				y_speed = obstacle.y - (postion.y + postion.height);
			}
		}
		return y_speed;
	}
	
}

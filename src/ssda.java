

public class ssda {
	
	public static void main(String[] args) {
		Obstacle[] o = {new Obstacle(new Rectangle(-100,8,200,4)), new Obstacle(new Rectangle(20,0,100,100)), new Obstacle(new Rectangle(-20,0,10,10))};
		Engine e = new Engine(o);
		Character c = new Character(new Rectangle(1,0,2,2), 10);
		
		c.shape.jumpSpeed = 10.0;
		for(int i=0;i<10;i++) {
			e.update_postion(c);
			System.out.println("("+ c.shape.x +"," + c.shape.y + ")" + " y_speed =" + c.shape.y_speed);
		}
		c.setRight(true);
		for(int i=0;i<10;i++) {
			e.update_postion(c);
			System.out.println("("+ c.shape.x +"," + c.shape.y + ")" + " y_speed =" + c.shape.y_speed);
		}
		c.setRight(false);
		c.setLeft(true);
		for(int i=0;i<10;i++) {
			e.update_postion(c);
			System.out.println("("+ c.shape.x +"," + c.shape.y + ")" + " y_speed =" + c.shape.y_speed);
		}
		
	}
	
}

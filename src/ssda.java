
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ssda extends JFrame {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double proX = screenSize.getWidth() / 1920, proY = screenSize.getHeight() / 1080;
	DrawingPanel z1 = new DrawingPanel();
	Character Character = new Character(901,proX,proY);
	Enemy Enemy[] ;
	int NE=2;
	Timer Timer;
	movment z5 = new movment();
	Jump z6 = new Jump();
	Color ScoreAndLives = new Color(20, 219, 235);
	int redness = 191;
	int greenes = 213;
	int bluness = 43;
	Color Timercolor = new Color(redness, greenes, bluness);
	static int up = KeyEvent.VK_W;
	static int right = KeyEvent.VK_D;
	static int down = KeyEvent.VK_S;
	static int left = KeyEvent.VK_A;
	static int up1 = KeyEvent.VK_UP;
	static int right1 = KeyEvent.VK_RIGHT;
	static int down1 = KeyEvent.VK_DOWN;
	static int left1 = KeyEvent.VK_LEFT;
	static int shift = KeyEvent.VK_SHIFT;
	static int enter = KeyEvent.VK_ENTER;
	static int esc = KeyEvent.VK_ESCAPE;
	static int space = KeyEvent.VK_SPACE;
	boolean Up = false;
	double d = 0;
	double con = 8.0 * proY + 2;
	boolean fall = true;
	boolean Speed;
	boolean prompt = false;// to see if a prompt is being shown
	boolean Running = true;// to see if the game is running
	int postion = 0;
	// here
	int N = 14, last = 0, score = 0, lives = 3, speed = (int) (6 * proX), ThreadSpeed = 8; // Right=1,Left=-1;
	boolean Right, Left, Finish;
	obj a[];
	Object lock = new Object();
	Object lock2 = new Object();
	Object lock3 = new Object();
	// meanu array
	String[] menue = { "MENUE", "Resume", "Restart", "Save", "Exit" };
	EscPrompt men = new EscPrompt(menue, proX, proY);
	// Yes NO prompt
	Prompt YesNo = new Prompt("GAME OVER", proX, proY);
	painter Painter=new painter();
	// Hide cursor
	BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	SimpleAudioPlayer BGM1;
	public ssda() {
		// Hide cursor
		this.getContentPane().setCursor(blankCursor);
		//print screen size
		System.out.println(screenSize.getWidth() + " " + screenSize.getHeight());
		//audio
		try {
			BGM1 = new SimpleAudioPlayer("BGM1.wav");
			BGM1.play();
			BGM1.clip.setMicrosecondPosition(0);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//draw the map
		a = new obj[N];
		a[0] = new obj(0, 1000, 1800, 85);
		a[1] = new obj(200, 950, 50, 50);
		a[2] = new obj(300, 850, 50, 50);
		a[3] = new obj(400, 750, 50, 50);
		a[4] = new obj(500, 650, 50, 50);
		a[5] = new obj(600, 550, 50, 50);
		a[6] = new obj(500, 950, 50, 50);
		a[7] = new obj(2000, 950, 50, 50);
		a[8] = new obj(2200, 850, 50, 50);
		a[9] = new obj(2400, 650, 100, 50);
		a[10] = new obj(2100, 750, 10, 10);
		a[11] = new obj(2600, 1000, 1800, 85);
		a[12] = new obj(4300, 950, 100, 50);	
		a[13] = new obj(6000, 1000, 10, 10);
		for (int i = 0; i < N; i++) {
			a[i].setX((int) (a[i].getX() * proX));
			;
			a[i].setLH((int) (a[i].getLH() * proX));
			;
			a[i].setY((int) (a[i].getY() * proY));
			;
			a[i].setLV((int) (a[i].getLV() * proY));
			;
		}
		// here
		// enemy thread

		Enemy = new Enemy[2];
		Enemy[0]=new Enemy(1200,901,true,true,Color.pink);
		Enemy[1]=new Enemy(4400,901,false,false,Color.GREEN);
		Enemy[0].start();
		Enemy[1].start();
		// end

		this.setTitle("Die or be Dead");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		if (gd.isFullScreenSupported()) {
			this.setUndecorated(true);
			gd.setFullScreenWindow(this);
		}
		this.add(z1);
    	// start timer thread and painter 
		Timer = new Timer(0,true);
		Timer.start();
		Painter.start();
		this.addKeyListener(new MyKeyListener());
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {

				/*
				 * int temp2 = JOptionPane.showConfirmDialog(null,
				 * "Are you Sure (Data Will be Lost) ?", "Exit Game",
				 * JOptionPane.YES_NO_OPTION); if (temp2 == 0) { System.exit(0); }
				 * 
				 * else
				 */
				System.exit(0);
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}
		});
		this.setVisible(true);
	}

	class MyKeyListener extends KeyAdapter {
		public MyKeyListener() {
		}

		@SuppressWarnings("deprecation")
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int action = e.getExtendedKeyCode();

			if (action == esc) {
				if (Running) {
					for(int E=0;E<NE;E++)
					Enemy[E].suspend();
					Timer.suspend();
					z5.suspend();
					z6.suspend();
					Running = false;
					repaint();
				} else {
					for(int E=0;E<NE;E++)
						Enemy[E].resume();
					Timer.resume();
					z5.resume();
					z6.resume();
					Running = true;
				}

			}
			if (!prompt && Running) {

				if ((action == up || action == up1) && Character.air) {
					if (!Up) {
						Up = true;
						z6 = new Jump();
						z6.start();
					}

				}
				if ((action == right || action == right1) && !Right) {
					last = 1;
					Character.setRight();
					if (goRight(Character.posx, Character.posy, Character.width, Character.hight)) {
						Right = true;
						z5 = new movment();
						z5.start();
					}
				}

				if (action == shift) {
					Speed = true;
					ThreadSpeed = 4;
				}

				if ((action == left || action == left1) && !Left) {
					last = -1;
					Character.setLeft();
					if (goLeft(Character.posx, Character.posy, Character.width, Character.hight)) {
						Left = true;
						z5 = new movment();
						z5.start();
					}
				}
			} else if (!Running) {
				if (action == enter) {
					if (men.selected == 1) {
						for(int E=0;E<NE;E++)
							Enemy[E].resume();
						Timer.resume();
						z5.resume();
						z6.resume();
						Running = true;
					} else if (men.selected == 2) {
						YesNo.Stop=true;
						lives = 3;
						score = 0;
						Timer.setTime(0);
						Reset();
						for(int E=0;E<NE;E++)
							Enemy[E].resume();
						Timer.resume();
						z5.resume();
						z6.resume();
						Running = true;
					} else if (men.selected == 3) {

					} else if (men.selected == 4) {
						System.exit(0);
					}
				}
				if (action == up || action == up1) {
					men.up();
				}
				if (action == down || action == down1) {
					men.Down();
				}
				// Important don't delete
				repaint();
			}
			else if (prompt) {
				if (action == space) {
					prompt = false;
					YesNo.Stop=true;
				}
			} 
		}

		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
			int action = e.getExtendedKeyCode();

			if (action == right || action == right1) {
				Right = false;
				last = -1;
			}
			if (action == left || action == left1) {
				Left = false;
				last = 1;
			}
			if (action == shift) {
				Speed = false;
				ThreadSpeed = 8;
			}
			if ((action == up || action == up1) && Character.air) {
				Up = false;
			}
		}
	}

	class DrawingPanel extends JPanel {
		public void DrawingPanel() {
			setBackground(Color.LIGHT_GRAY);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(a[0].getX(), a[0].getY(), a[0].getLH(), a[0].getLV());
			g.setColor(new Color(67, 67, 224));
			for (int i = 1; i < N; i++) {
				if (i == 11)
					g.setColor(Color.black);
				g.fillRect(a[i].getX(), a[i].getY(), a[i].getLH(), a[i].getLV());

			}
			// here
			for(int E=0;E<NE;E++)
				if(!Enemy[E].Dead)
					Enemy[E].draw(g);
			g.setColor(Color.LIGHT_GRAY);
			Character.draw(g);
			g.setColor(ScoreAndLives);
			g.setFont(new Font("Showcard Gothic", 1, (int) (70 * proX)));
			g.drawString("Score:" + score, (int) (70 * proX), (int) (100 * proY));
			// here
			g.drawString(lives + "x", (int) (1700 * proX), (int) (100 * proY));
			g.setColor(Timercolor);
			Timercolor = new Color(redness, greenes, bluness);
			g.drawString("" + Timer.getTime() / 100, (int) (900 * proX), (int) (100 * proY));

			// prompt
			if (prompt)
				YesNo.draw(g);
			if (!Running)
				men.draw(g);
		}
	}

	// Character


	// here
	// enemy z3 class Starts
	class Enemy extends Thread {
		int posx , posy, ConstantPosx = (int) (750 * proX);
		int width, hight;
		boolean Dead = false;
		Random x = new Random();
		int tempRand;
		boolean fall,killable;//can fall form ground-----can be killed
		Color C;

		public Enemy(int posx,int posy,boolean fall,boolean killable,Color color) {
			this.hight = (int) (100 * proY);
			this.width = (int) (50 * proX);
			this.posx =(int) (posx * proX);
			this.posy = (int) (posy * proY);
			this.fall=fall;
			this.killable=killable;
			C=color;
		}

		public void ReSeteE(int posx,int posy){
			this.posx=posx;
			this.posy=posy;
			this.resume();
			this.Dead=false;
			
		}

		public void draw(Graphics g) {
			g.setColor(C);
			g.fillRect(posx, posy, width, hight / 2);
			g.fillOval(posx, posy + (int) (49 * proY), width, hight / 2);

		}

		@Override
		public void run() {
			super.run();
			while (!Dead) {

				tempRand = x.nextInt(101) - 50;
				if (tempRand < 0)
					tempRand -= 25;
				else
					tempRand += 25;
				int i = 0;
				while (!Dead && (i < Math.abs(tempRand))) {
					i++;
					if (tempRand > 0) {
						if (goRight(this.posx + 5, this.posy, this.width, this.hight)) {
							this.posx += 5;
							repaint();
						} else
							break;
					} else {
						if (goLeft(this.posx - 5, this.posy, this.width, this.hight)) {
							this.posx -= 5;
							repaint();
						} else
							break;
					}
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!(this.posy>Character.getPosy()+Character.getHight()||this.posy+this.width<Character.getPosy())&&!(this.posx+this.width<Character.getPosx()||this.posx>Character.getPosx()+Character.getWidth())) {
						die();
				}
					
					fallEnemy(this);

				}

			}

		}
	}

	// the end of enemy class

	class Jump extends Thread {

		public void run() {
			super.run();
			synchronized (lock2) {
				Character.air = false;
				fall = false;
				
				while (checkJump(Character.posx, Character.posy, Character.width, Character.hight)) {
					if (!checkJump(Character.posx, Character.posy - (int) (con - d), Character.width, Character.hight)) {
						Character.posy = Maxjump(Character.posx, Character.posy, Character.width, Character.hight);
						repaint();
						d = 0;
						Character.air = true;
						fall = true;
						break;
					} else if (d < con && -1 != goUp(Character.posx, Character.posy - (int) (con - d), Character.width, Character.hight)) {
						Character.posy = goUp(Character.posx, Character.posy - (int) (con - d), Character.width, Character.hight) - 1;
						d = con;
					} else {
						if(!Finish)Character.posy -= (int) (con - d);
					}
					if(!Finish)d += 0.3 * proY;
					// here
					// Lost statment // here is the reset option
					for(int E=0;E<NE;E++)
						if (!Enemy[E].Dead&&Character.posy+Character.hight<Enemy[E].posy&&Character.posy+Character.hight+d/2>Enemy[E].posy&&!(Character.posx>Enemy[E].posx+Enemy[E].width||Character.posx+Character.width<Enemy[E].posx)){
							d=0;
							if(Enemy[E].killable){
								Enemy[E].suspend();
								Enemy[E].Dead=true;
								score+=100;
							}
							
						}
					if (Character.posy -100> screenSize.getHeight() && !Finish) {
						die();
					}
					// The end of reset statment
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Up = false;
			}
		}
	}
	class painter extends Thread {

		public void run() {
			super.run();
			do {
				
				repaint();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (true);
		}

	}


	class movment extends Thread {
		public void run() {
			super.run();
			synchronized (lock) {
				do {
					if (last == 1 && Right && !Finish) {
						if (goRight(Character.posx + speed, Character.posy, Character.width, Character.hight)) {
							for (int i = 0; i < N; i++)
								a[i].setX(a[i].getX() - speed);
							for(int E=0;E<NE;E++)
								if(!Enemy[E].Dead) {
									Enemy[E].ConstantPosx -= speed;
									if (goRight(Enemy[E].posx - speed, Enemy[E].posy, Enemy[E].width, Enemy[E].hight))
										Enemy[E].posx -= speed;
									}
							postion += speed;
						} else if (goRight(Character.posx + 1, Character.posy, Character.width, Character.hight)) {
							for (int i = 0; i < N; i++)
								a[i].setX(a[i].getX() - 1);
							for(int E=0;E<NE;E++)
								if(!Enemy[E].Dead) {
									Enemy[E].ConstantPosx -= 1;
									if (goRight(Enemy[E].posx - 1, Enemy[E].posy, Enemy[E].width, Enemy[E].hight))
										Enemy[E].posx -= 1;
									}
							postion += 1;
						}

					}
					if (last == -1 && !Finish && Left) {
						if (goLeft(Character.posx - speed, Character.posy, Character.width, Character.hight)) {
							for (int i = 0; i < N; i++)
								a[i].setX(a[i].getX() + speed);
							for(int E=0;E<NE;E++)
								if(!Enemy[E].Dead) {
									Enemy[E].ConstantPosx += speed;
									if (goLeft(Enemy[E].posx + speed, Enemy[E].posy, Enemy[E].width, Enemy[E].hight))
										Enemy[E].posx += speed;
									}
							postion -= speed;
						} else if (goLeft(Character.posx - 1, Character.posy, Character.width, Character.hight)) {
							for (int i = 0; i < N; i++)
								a[i].setX(a[i].getX() + 1);
							for(int E=0;E<NE;E++)
								if(!Enemy[E].Dead) {
									Enemy[E].ConstantPosx += 1;
									if (goLeft(Enemy[E].posx + 1, Enemy[E].posy, Enemy[E].width, Enemy[E].hight))
										Enemy[E].posx += 1;
									}
							postion -= 1;
						}

					}
					repaint();
					try {
						Thread.sleep(ThreadSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (checkJump(Character.posx, Character.posy + 1, Character.width, Character.hight) && fall && d < con) {
						d = con;
						z6 = new Jump();
						z6.start();
					}
				} while (Right || Left);

			}
			Left = false;
			Right = false;
		}
	}


	public static void main(String[] args) {
		new Frame();
	}

	public boolean goLeft(int x, int y, int w, int h) {
		for (int i = 0; i < N; i++) {
			if (!(x + 1 > a[i].getX() + a[i].getLH() || x + w - 1 < a[i].getX() + 1)
					&& !(y + 1 > a[i].getY() + a[i].getLV() || y + h - 2 < a[i].getY()))
				return false;
		}
		return true;
	}

	public boolean goRight(int x, int y, int w, int h) {
		for (int i = 0; i < N; i++) {
			if (!(x + 1 > a[i].getX() + a[i].getLH() || x + w - 1 < a[i].getX())
					&& !(y + 1 > a[i].getY() + a[i].getLV() || y + h - 2 < a[i].getY()))
				return false;
		}
		return true;
	}

	public boolean checkJump(int x, int y, int w, int h) {
		for (int i = 0; i < N && !Finish; i++) {
			if (y + h - 1 > a[i].getY() && y + h - 1 < a[i].getY() + a[i].getLV()
					&& !(x + 1 > a[i].getX() + a[i].getLH() || x + w < a[i].getX() + 1)) {
				return false;
			}
		}
		return true;
	}

	public int Maxjump(int x, int y, int w, int h) {
		int temp = 0;
		for (int i = 1; i < N; i++) {
			if (!(y + 1 > a[i].getY() + a[i].getLV())
					&& !(x + 1 > a[i].getX() + a[i].getLH() || x + w < a[i].getX() + 1)) {
				if (a[temp].getY() > a[i].getY())
					temp = i;
			}
		}
		return a[temp].getY() - h + 1;
	}

	public int goUp(int x, int y, int w, int h) {
		for (int i = 0; i < N; i++) {
			if (!(y > a[i].getY() + a[i].getLV() || y + h < a[i].getY())
					&& !(x + 1 > a[i].getX() + a[i].getLH() || x + w < a[i].getX() + 1))
				return a[i].getY() + a[i].getLV() + 1;
		}
		return -1;
	}

	// here a new method
	// reset method starts
	public void Reset() {
		// z1 = new DrawingPanel();
		Character.setPosy(901);
		Up = false;

		Finish = false;
		last = 0;
		Right = false;
		Left = false;
		Running = true;
		prompt = false;
		YesNo= new Prompt("GAME OVER", proX, proY);;
		// Timer t4 = new Timer();
		// t4.start();
		for (int i = 0; i < N; i++) {
			a[i].setX(a[i].getX() + postion);
		}
		postion = 0;
		// enemy thread
		Enemy[0].ReSeteE(1200, 901);
		Enemy[1].ReSeteE(4400,901);
		// end
		repaint();
	}
	// reset method ends
	public void die(){
		if (lives == 0) {
			score -= 1000;
			Finish = true;
			Timer.setRun(false);
			Timer.setTime(0);
			prompt = true;	
			YesNo.run();
			repaint();	
			
	/*		while (prompt) {
				// keep busy
				System.out.print("x");
				repaint();
			}*/

			if (YesNo.Stop == true) {
				lives = 3;
				score = 0;
				Timer.setRun(true);
				YesNo= new Prompt("GAME OVER", proX, proY);
				Reset();
				
			}
			
		} else {
			lives--;
			score -= 1000;
			Reset();
		}

	}
	public void fallEnemy(Enemy b){
		if(b.fall&&checkJump(b.posx,b.posy+5,b.width,b.hight)){
			if(screenSize.getHeight()+100<b.posy){	
				b.suspend();
				//b.Dead=true;
			}
			else
				b.posy=b.posy+5;	
		}
	}
}

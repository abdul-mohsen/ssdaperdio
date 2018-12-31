
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Prompt implements Runnable{
	Color color1 = Color.LIGHT_GRAY, color2 = Color.white;
	int posx, posy, width, hight;
	double proX, proY;
	String massage;
	int alpha=255;
	boolean Stop=false;
	ArrayList<Dimension> x1=new ArrayList<>();
	ArrayList<Dimension> x2=new ArrayList<>();
	ArrayList<Dimension> x3=new ArrayList<>();
	ArrayList<Dimension> x4=new ArrayList<>();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Prompt(String x, double proX, double proY) {
		this.massage = x;
		this.width = (int) (600*proX);
		this.hight = (int) (500*proY);
		this.posx = (int) ((screenSize.width / 2 )- width / 2);
		this.posy = (int) ((screenSize.height / 2 )- hight / 2);
		this.proX = proX;
		this.proY = proY;
		x1.add(new Dimension( posy+(int)(65*proY), (int) (780*proX)));
		x2.add(new Dimension( posy+(int)(65*proY), (int) (1365*proX)));
		x3.add(new Dimension( posy+(int)(65*proY), (int) (1050*proX)));
		x4.add(new Dimension( posy+(int)(65*proY), (int) (620*proX)));
	}

	public void draw(Graphics g) {
		g.setFont(new Font("Showcard Gothic", 1, (int) (140 * proX)));
		g.setColor(new Color(131, 3,3 ,255 ));
		g.drawString(massage, posx -(int)(100*proX), posy + hight / 7);
		g.setColor(Color.DARK_GRAY);
		g.drawString(massage, posx -(int)(100*proX), (posy + hight / 7)-7);
		g.setFont(new Font("Showcard Gothic", 1, (int) (36 * proX)));
		g.setColor(new Color(66, 66,66 ,alpha ));
		g.drawString("Press", posx +(int)(100*proX), (posy + hight / 7)+(int)(500*proY));
		g.setColor(new Color(131, 3,3 ,alpha ));
		g.drawString("Space", posx +(int)(230*proX), (posy + hight / 7)+(int)(500*proY));
		g.setColor(new Color(66, 66,66 ,alpha ));
		g.drawString("to Try Again", posx +(int)(360*proX), (posy + hight / 7)+(int)(500*proY));
		
		g.setColor(new Color(131, 3,3 ,255 ));
		
		for(int i=0;i<x1.size();i++) {
		g.fillOval(x1.get(i).height, x1.get(i).width, 12, 12);
		}
		for(int i=0;i<x2.size();i++) {
			g.fillOval(x2.get(i).height, x2.get(i).width, 10, 10);
			}
		for(int i=0;i<x3.size();i++) {
			g.fillOval(x3.get(i).height, x3.get(i).width, 14, 14);
			}
		for(int i=0;i<x4.size();i++) {
			g.fillOval(x4.get(i).height, x4.get(i).width, 18, 18);
			}
		
	}

	@Override
	public void run() {
		int y1= (posy+(int)(65*proY));
		int y2= (posy+(int)(65*proY));
		int y3= (posy+(int)(65*proY));
		int y4= (posy+(int)(65*proY));
		boolean turn= true;
		while(!Stop) {
			if(alpha==255)
				turn=true;
			else if(alpha==0)
				turn=false;
			
			if(turn) 
				alpha-=3;
			else 
				alpha+=3;
			
			if(y1<screenSize.getHeight()-310) {
				y1++;
				x1.add(new Dimension(y1, (int) (780*proX)));
			}
			if(y2<screenSize.getHeight()-350) {
				y2++;
				y2++;
				x2.add(new Dimension(y2, (int) (1365*proX)));
			}
			if(y3<screenSize.getHeight()-390) {
				y3++;
				x3.add(new Dimension(y3, (int) (1050*proX)));
				y3++;
				x3.add(new Dimension(y3, (int) (1050*proX)));
				y3++;
				x3.add(new Dimension(y3, (int) (1050*proX)));
			}
			if(y4<screenSize.getHeight()-280) {
				y4++;
				x4.add(new Dimension(y4, (int) (620*proX)));
				y4++;
				x4.add(new Dimension(y4, (int) (620*proX)));
			}
			
				
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}

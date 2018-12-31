
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

public class EscPrompt {
	Color color1 = Color.gray, color2 = Color.white;
	int posx, posy, width, hight, selected = 1;
	double proX, proY;
	String[] massage;

	public EscPrompt(String[] x, int posx, int posy, int width, int hight, double proX, double proY) {
		this.massage = x;
		this.posx = posx / 2 - width / 2;
		this.posy = posy / 2 - hight / 2;
		this.width = width;
		this.hight = hight;
		this.proX = proX;
		this.proY = proY;
	}

	public EscPrompt(String[] x, double proX, double proY) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.massage = x;
		this.width = 600;
		this.hight = 500;
		this.posx = (screenSize.width / 2 - width / 2)+width/5;
		this.posy = screenSize.height / 2 - hight / 2;
		this.proX = proX;
		this.proY = proY;
	}

	public void up() {
		if (selected >1)
			selected--;
		else
			selected=massage.length-1;
	}
	public void Down() {
		if (selected < massage.length-1) 
			selected++;
		else
			selected=1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(posx, posy, width / 2, hight);
		g.setColor(Color.magenta);
		g.drawRect(posx + 10, posy + 10, width / 2 - 20, (hight) - 20);
		g.setFont(new Font("Showcard Gothic", 1, (int) (36 * proX)));
		g.setColor(Color.RED);
		g.drawString(massage[0], posx + width / 7, posy + hight / 7);
		g.setColor(Color.magenta);
		g.drawLine(posx + width / 12, posy + hight/ 6,posx + width / 12+width/3, posy + hight/ 6);
		for (int i = 1; i < massage.length; i++) {
			if (selected == i)
				g.setColor(color2);
			else
				g.setColor(color1);
			g.drawString(massage[i],posx + width / 7, posy +hight/6 + i*80);
		}

	}
}

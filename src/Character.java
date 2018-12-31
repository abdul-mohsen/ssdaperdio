

import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

class Character {
	double proY;
	int posx , posy;
	int width, hight;
	boolean air = true;
	boolean Right=true,Left=false;
	
	BufferedImage playerR,playerL,playerAirR,playerAirL ;
	public Character(int posy,double proX,double proY) {
		this.proY=proY;
		this.posx=(int) (940 * proX);
		this.hight = (int) (100 * proY);
		this.width = (int) (50 * proX);
		if(proY<1)this.posy = (int)  Math.round(posy * proY);
		else this.posy = (int) (posy * proY);
		try {
			
			playerR = resourceLoader.ImageLoader("playerR.png");
			playerL = resourceLoader.ImageLoader("playerL.png");
			playerAirR= resourceLoader.ImageLoader("airR.png");
			playerAirL= resourceLoader.ImageLoader("airL.png");
			
			playerR= resourceLoader.getScaledInstance(
					playerR,(int)(70*proX) ,(int)(109*proY), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
			playerL= resourceLoader.getScaledInstance(
					playerL,(int)(70*proX),(int)(109*proY), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
			playerAirR= resourceLoader.getScaledInstance(
					playerAirR,(int)(70*proX) ,(int)(109*proY), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
			playerAirL= resourceLoader.getScaledInstance(
					playerAirL,(int)(70*proX) ,(int)(109*proY), RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public boolean isRight() {
		return Right;
	}
	public void setRight() {
		Right = true;
		Left=false;
	}
	public boolean isLeft() {
		return Left;
	}
	public void setLeft() {
		Left = true;
		Right=false;
	}
	public double getProY() {
		return proY;
	}

	public void setProY(double proY) {
		this.proY = proY;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		if(proY<1)this.posy = (int)  Math.round(posy * proY);
		else this.posy = (int) (posy * proY);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	public boolean isAir() {
		return air;
	}

	public void setAir(boolean air) {
		this.air = air;
	}



	public void draw(Graphics g) {
		
		//g.fillRect(posx, posy, width, hight / 2);
		//g.fillOval(posx, posy + (int) (49 * proY), width, hight / 2);
		if(Right) {
		if(air)	
		g.drawImage(playerR,posx, posy- (int) (5 * proY), null);
		else
		g.drawImage(playerAirR,posx, posy - (int) (5 * proY), null);	
		}
		else if(Left) {
		if(air)		
		g.drawImage(playerL,posx, posy - (int) (5 * proY), null);
		else
		g.drawImage(playerAirL,posx, posy- (int) (5 * proY), null);	
		}
		


	}

}
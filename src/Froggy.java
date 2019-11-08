import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/*class to represent a Frog object in a game of Frogger*/
public class Froggy {
	
	
	//attributes of a Frog
	private int x, y; 			//position of Froggy! :D
	private boolean alive;		
	private int width, height;	
	private Image img;			//image for Froggy
	private double vx, vy;			//x and y velocity of froggy
	
	/*
	 * write the constructor for Froggy which takes in a 
	 * String fileName that will be used for the image setup
	 * 
	 * set x and y to be in the middle of a 400x400 screen
	 * set width and height to 50
	 */
	
	/* 
	GENERAL FORMULA FOR CONSTRUCTOR:
	public nameOfClass(any parameters for constructor){
		assignment statements
	}
	*/
	
	//constructor for froggy
	 public Froggy(String fileName) {
		x = 425;
		y = 400;
		vx = 0;
		vy = 0;
		//helper functions - okay to be blackboxed and not explained
		img = getImage(fileName);
		init(x, y);
	}
	
	
	public Froggy(String fileName, int StartX, int StartY) {
		x = StartX;
		y = StartY;
		vx = 0;
		vy = 0;
		width = 50;
		height = 50;
		//helper functions - okay to be blackboxed and not explained
		img = getImage(fileName);
		init(x, y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	//setter for vx
	public void setvx (double newvx) {
	
			vx = newvx;
		
	}
	
	//setter for vy
	public void setvy (int newvy) {
		
			vy = newvy;
	}

	public int getx () {
		return x;
	}
	
	public int gety () {
		return y;
	}
	
	public double getvx () {
		return vx;
	}
	
	public double getvy () {
		return vy;
	}
	
	
	
	public boolean collided(int ox, int oy, int ow, int oh) {
		
		Rectangle obs= new Rectangle(ox, oy, ow, oh);			//from param
		Rectangle froggy = new Rectangle(x, y, width, height);	//from attributes
		return obs.intersects(froggy);
		
	}
	
	//move function that you don't need to understand rn 
	public void move() {
		tx.translate(vx, vy);
		x+=vx;
		y+=vy;
		
		//change based on direction of your vx
		//for teleporting after going off-screen
		if(x <0) {		//allows going back if they go off-screen
			x = 900;
			tx.setToTranslation(x, y);
		}
		
		if(x > 900) {	//allows going back if they go off-screen
			x = 0;
			tx.setToTranslation(x, y);
		}
		
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affinetransform
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
		}

		private void init(double a, double b) {
			tx.setToTranslation(a, b);
			tx.scale(1, 1);
		}

		// converts image to make it drawable in paint
		private Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = Froggy.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tempImage;
		}

	
}







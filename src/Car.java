import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Car {

	private int cx, cy;
	private double cvx, cvy;
	private int cw, ch;	
	private Image img;	

	//constructor for Car
	public Car(String fileName, int startX, int startY) {
		cx = startX;
		cy = startY;
		cw = 75;
		ch = 75;
		
		cvx = 3;
		cvy = 0;

		img = getImage(fileName);
		init(cx, cy);
	}
	public Car(String fileName) {
		cx = 0;
		cy = 0;
		cvx = 0.5;
		cw = 75;
		ch = 75;
		
		img = getImage(fileName);
		init(cx, cy);
	}

	public int getCx() {
		return cx;
	}
	public void setCx(int cx) {
		this.cx = cx;
	}
	public int getCy() {
		return cy;
	}
	public void setCy(int cy) {
		this.cy = cy;
	}
	public int getCw() {
		return cw;
	}
	public void setCw(int cw) {
		this.cw = cw;
	}
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	//getter for Car
	public double getcvx () {
		return cvx;
	}
	
	public int getcx () {
		return cx;
	}
	
	public int getcy () {
		return cy;
	}
	
	public int getcw () {
		return cw;
	}
	
	public int getch () {
		return ch;
	}
	
	//setter for Car
	public void setcvx (double cvx) {
		this.cvx = cvx;
	}
	
	public void setcx (int newcx) {
		cx = newcx;
	}
	
	
	
	//move function that you don't need to understand rn 
		public void move() {
			tx.translate(cvx, cvy);
			cx+=cvx;
			cy+=cvy;
			
			//change based on direction of your vx
			//for teleporting after going off-screen
			if(cx > 975 ) {		//allows going back if they go off-screen
				setcx(0);
				tx.setToTranslation(cx, cy);
			}
			if(cx < -75 ) {		//allows going back if they go off-screen
				setcx(900);
				tx.setToTranslation(cx, cy);
			}
		}
		
	private AffineTransform tx = AffineTransform.getTranslateInstance(cx, cy);

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



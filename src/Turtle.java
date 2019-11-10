import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Turtle {
	private int x, y;
	private double vx, vy;
	private int tw, th;
	private Image img;
	
	//constructor for Car
		public Turtle(String fileName, int startX, int startY) {
			x = startX;
			y = startY;
			tw = 75;
			th = 75;
			
			vx = -2;
			vy = 0;

			img = getImage(fileName);
			init(x, y);
		}
		public Turtle(String fileName) {
			x = 0;
			y = 0;
			tw = 75;
			th = 75;
			vx = -1;
			img = getImage(fileName);
			init(x, y);
		}

		//getter for turtle
		public double gettvx () {
			return vx;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getTw() {
			return tw;
		}
		public void setTw(int tw) {
			this.tw = tw;
		}
		public int getTh() {
			return th;
		}
		public void setTh(int th) {
			this.th = th;
		}
		//setter for turtle
		public void settvx (double vx) {
			this.vx = vx;
		}
		
		public void setx (int newx) {
			x = newx;
		}
		
		
		
		//move function that you don't need to understand rn 
			public void move() {
				tx.translate(vx, vy);
				x+=vx;
				y+=vy;
				
				
				//change based on direction of your vx
				//for teleporting after going off-screen
				if(x < -75 ) {		//allows going back if they go off-screen
					setx(1000);
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
					tx.scale(01, 01);
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



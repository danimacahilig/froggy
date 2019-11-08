import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {

	int screen_width = 900;
	int screen_height = 900;
	Froggy froggy;
	Froggy[] froggies = new Froggy[10];	//container of objects, none are instantiated
	Car[] carRow0 = new Car[10];
	Car[] carRow1 = new Car[10];
	Car[] carRow3 = new Car[10];
	Car[] carRow4 = new Car[10];
	Turtle[] turtleRow8 = new Turtle[10];
	Turtle[] turtleRow6 = new Turtle[10];
	
	Background bg;
	int my_variable = 3; //example
	
	String lose = "";
	String win = "";
	String lost = "";
	String reset = "";
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		bg.paint(g);
		
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString(("lives:")+Integer.toString(my_variable), 0, 860);
		g.setFont(font2);	
		
		//call the paint method of every car sprite
		for (int i = 0; i<carRow0.length;i++) {
			carRow0[i].paint(g);
		}
		
		for (int i = 0; i<carRow0.length;i++) {
			carRow1[i].paint(g);
		}
		
		for (int i = 0; i<carRow0.length;i++) {
			carRow3[i].paint(g);
		}
		
		for (int i = 0; i<carRow0.length;i++) {
			carRow4[i].paint(g);
		}
		
		//call the paint method of every turtle sprite
		for (int i = 0; i<turtleRow8.length;i++) {
			turtleRow8[i].paint(g);
		}
		
		for (int i = 0; i<turtleRow6.length;i++) {
			turtleRow6[i].paint(g);
		}
		
		//paint sprite 
		froggy.paint(g);
		
		g.drawString(lost, 300, 50);
		
		//if you lose all of your lives
		if(my_variable == 0) {
			lose = "you lose";
			g.drawString(lose, 400, 50);
			reset = "press space to reset";
		}
		
		//if you reach the end
		if(my_variable > 0 && froggy.getY() < 100) {
			lose = "";
			lost = "";
			win = "you win! :D";
			g.drawString(win, 400, 50);
			//froggy.setX(425);
			//froggy.setY(825);
			reset = "press space to reset";
		}
		
		g.drawString(reset, 300, 425);
		
	}
		

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	
	
	
	public void update() {
	    //System.out.println(froggy.getvy());
		froggy.move();
		
		//car row 0
		for (int i = 0; i<carRow0.length;i++) {
			carRow0[i].move();
			
			if(froggy.collided(
					carRow0[i].getcx(), 
					carRow0[i].getcy(), 
					carRow0[i].getcw(), 
					carRow0[i].getch())) {
				lost = "lost a life - collision";
				//reset frog if you have lives still
				if(my_variable > 0){
					froggy.setX(425);
					froggy.setY(825);
					my_variable--;	
				}
				if(my_variable == 0) {
					lost = "";
					lose = "";
				}
				
			}
			
		}
		
		//car row 1
		for (int i = 0; i<carRow1.length;i++) {

			carRow1[i].setcvx(-1);
			carRow1[i].move();
			
			if(froggy.collided(
					carRow1[i].getcx(), 
					carRow1[i].getcy(), 
					carRow1[i].getcw(), 
					carRow1[i].getch())) {
				lost = "lost a life - collision";
				//reset frog if you have lives still
				if(my_variable > 0){
					froggy.setX(425);
					froggy.setY(825);
					my_variable--;					
				}
				
			}
			
		}
		
		//car row 3
		for (int i = 0; i<carRow3.length;i++) {

			carRow3[i].setcvx(1);
			carRow3[i].move();
			
			if(froggy.collided(
					carRow3[i].getcx(), 
					carRow3[i].getcy(), 
					carRow3[i].getcw(), 
					carRow3[i].getch())) {
				lost = "lost a life - collision";
				//reset frog if you have lives still
				if(my_variable > 0){
					froggy.setX(425);
					froggy.setY(825);
					my_variable--;					
				}
				
			}
			
		}
		
		//car row 4
		for (int i = 0; i<carRow4.length;i++) {
			carRow4[i].move();
			carRow4[i].setcvx(-1.5);

			System.out.println(carRow4[0].getcx());
			
			if(froggy.collided(
					carRow4[i].getcx(), 
					carRow4[i].getcy(), 
					carRow4[i].getcw(), 
					carRow4[i].getch())) {
				lost = "lost a life - collision";
				//reset frog if you have lives still
				if(my_variable > 0){
					froggy.setX(425);
					froggy.setY(825);
					my_variable--;					
				}
				
			}
			
		}
		
		//turtle row 6
		for(int i = 0; i<turtleRow6.length; i++) {
			
			turtleRow6[i].settvx(-1.25);
			turtleRow6[i].move();
			
			if(froggy.collided(
					carRow4[i].getcx(), 
					carRow4[i].getcy(), 
					carRow4[i].getcw(), 
					carRow4[i].getch())) {
				froggy.setvx(turtleRow8[i].gettvx());
				break;
			}
		}
		
		//turtle row 8
		for (int i = 0; i<turtleRow8.length;i++) {
			
			turtleRow8[i].settvx(-2);
			turtleRow8[i].move();
			
			if(froggy.collided(
					turtleRow8[i].getX(), 
					turtleRow8[i].getY(), 
					turtleRow8[i].getTw(), 
					turtleRow8[i].getTh())) {
				froggy.setvx(turtleRow8[i].gettvx());
				break;
			}
			if(froggy.getY() != 100) {
				froggy.setvx(0);
			}
			
		}		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Frogger");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		
		//sprite instantiation
		froggy = new Froggy("froggy.png", 425, 825);
		
		//create the instances of objects for your specific arrays
		for (int i = 0; i<carRow0.length;i++) {
			carRow0[i] = new Car("newcar.png", i*300+200, 710);
		}
		for (int i = 0; i<carRow0.length;i++) {
			carRow1[i] = new Car("newcar.png", i*300+200, 635);
		}
		for (int i = 0; i<carRow0.length;i++) {
			carRow3[i] = new Car("newcar.png", i*300+200, 380);
		}
		for (int i = 0; i<carRow0.length;i++) {
			carRow4[i] = new Car("newcar.png", i*300+200, 462);
		}
		
		//create the instances of objects for your specific arrays
		for (int i = 0; i<turtleRow8.length;i++) {
			turtleRow8[i] = new Turtle("turtleee.png", i*150+75, 100); 
		}
		for (int i = 0; i<turtleRow6.length;i++) {
			turtleRow6[i] = new Turtle("turtleee.png", i*150+75, 240); 
		}
		//player.addMouseListener(this);
		bg = new Background("background.png");
		//do not add to frame, call paint on
		//these objects in paint method
		
		
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		//detect up, down, left, right arrow key presses
		//call setters for velocity attributes accordingly
		
		// up 		= 38
		// down 	= 40
		// left 	= 37
		// right 	= 39
		// space 	= 32
		//System.out.println(e.getKeyCode());
		
		if(e.getKeyCode() == 38) {
			if(my_variable == 0) {
				froggy.setvy(0);
			}
			else {
			froggy.setvy(-10);
			}
		}
		if(e.getKeyCode() == 40) {
			if(my_variable == 0) {
				froggy.setvy(0);
			}
			else{
			froggy.setvy(10);
			}
		}
		if(e.getKeyCode() == 37) {
			if(my_variable == 0) {
				froggy.setvx(0);
			}
			else{
				froggy.setvx(-10);
			}	
		}
		if(e.getKeyCode() == 39) {
			if(my_variable == 0) {
				froggy.setvx(0);
			}
			else{
			froggy.setvx(10);
			}
		}
		if(e.getKeyCode() == 32) {
			lose = "";
			my_variable = 3;
			reset = "";
			lost = "";
			froggy.setY(825);
			froggy.setX(425);
		}		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == 38) {
			froggy.setvy(0);
		}
		if(e.getKeyCode() == 40) {
			froggy.setvy(0);
		}
		if(e.getKeyCode() == 37) {
			froggy.setvx(0);
		}
		if(e.getKeyCode() == 39) {
			froggy.setvx(0);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void reset() {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}




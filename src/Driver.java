/*
 * i'm sorry my collision and a bunch of other stuff don't work T-T
 * 
 * love the turtles, they're super cuuuute
 * water collisions don't rlly work
 * movement is rlly smooth
 * the drawings are amazzinnnnngggg imma vote for u for best drawing
 * the turtle movement isnt consistent though u can tri to fix dat
 * dying before i touch water D:\
 * make it so that you can move the x position of the frog when on a lilypad
 * I like the pictures for enemies and the background,
 * Tommy:
 * -Can still move in the X direction when lost
 * -No collision for the top or bottom
 * -Turtle collision is a bit wonky
 *  
 *  cute
 *  
 *  completely unrelated to your code, you might want to adjust your monitor's color balance
 */

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
	
	//create objects
	Froggy froggy;
	
	Car[] carRow1 = new Car[10];
	Car[] carRow2 = new Car[10];
	Car[] carRow3 = new Car[10];
	Car[] carRow4 = new Car[10];
	
	Turtle[] turtleRow8 = new Turtle[10];
	Turtle[] turtleRow6 = new Turtle[10];
	
	Background bg;
	
	//if the frog is on the turtle
	boolean turtlecollision = false;
	
	//lives and wins
	int my_variable = 3; 
	int wins 		= 0;
	
	//strings to print out
	String lose = "";
	String win = "";
	String collision = "";
	String reset = "";
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		bg.paint(g);
	
		//call the paint method of every car sprite
		for (int i = 0; i<carRow1.length;i++) {
			carRow1[i].paint(g);
		}
		
		for (int i = 0; i<carRow1.length;i++) {
			carRow2[i].paint(g);
		}
		
		for (int i = 0; i<carRow1.length;i++) {
			carRow3[i].paint(g);
		}
		
		for (int i = 0; i<carRow1.length;i++) {
			carRow4[i].paint(g);
		}
		
		//call the paint method of every turtle sprite
		for (int i = 0; i<turtleRow8.length;i++) {
			turtleRow8[i].paint(g);
		}
		
		for (int i = 0; i<turtleRow6.length;i++) {
			turtleRow6[i].paint(g);
		}
		
		//paint frog sprite 
		froggy.paint(g);
		
		//lives and wins
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(("lives:")+Integer.toString(my_variable), 0, 860);
		g.drawString(("wins:")+Integer.toString(wins), 700, 860);
		
		
		//collision
		g.setFont(font2);	
		g.drawString(collision, 225, 50);
		g.drawString(reset, 300, 425);

		//if you lose all of your lives
		if(my_variable == 0) {
			lose = "you lose";
			collision = "";
			froggy.setX(425);
			froggy.setY(825);
			//froggy.setvx(0);
			//froggy.setvy(0);
			g.drawString(lose, 400, 50);
			reset = "press space to reset";
		}
		
		//if you reach the end (win)
		if(my_variable > 0 && froggy.getY() <= 50) {
			win = "you win! :D";
			froggy.setX(425);
			froggy.setY(825);
			//froggy.setvx(0);
			//froggy.setvy(0);
			lose = "";
			collision = "";
			reset = "press space to reset";
			wins++;
		}
		

		g.drawString(win, 355, 50);
				
	}
		

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	
	
	
	public void update() {
	    //System.out.println(froggy.getvy());
		froggy.move();
		
		//turtle row 6
		//disappears too early when velocity is not -2
		for(int i = 0; i<turtleRow6.length; i++) {
			
			turtleRow6[i].settvx(-2);
			turtleRow6[i].move();
					
			if(froggy.collided(
					turtleRow6[i].getX(), 
					turtleRow6[i].getY(), 
					turtleRow6[i].getTw(), 
					turtleRow6[i].getTh())) {
				froggy.setX(turtleRow6[i].getX());
				turtlecollision = true;
			}
			
			//if it is on the log, don't move side to side
			if(froggy.getY() != 240) {
				turtlecollision = false;
			}
				
		}
			
		//turtle row 8
		for (int i = 0; i<turtleRow8.length;i++) {
			
			turtleRow8[i].settvx(-1.5);
			turtleRow8[i].move();
			
			if(froggy.collided(
					turtleRow8[i].getX(), 
					turtleRow8[i].getY(), 
					turtleRow8[i].getTw(), 
					turtleRow8[i].getTh())) {
				froggy.setX(turtleRow8[i].getX());
				turtlecollision = true;
			}
					
			if(froggy.getY() != 100) {
				turtlecollision = false;
			}
					
		}		
		
		//car row 1
		for (int i = 0; i<carRow2.length;i++) {
			carRow1[i].move();
			
			if(froggy.collided(
					carRow1[i].getcx(), 
					carRow1[i].getcy(), 
					carRow1[i].getcw(), 
					carRow1[i].getch())) {
				collision = "lost a life - collision";
				//reset frog if you have lives still
				if(my_variable > 0){
					froggy.setX(425);
					froggy.setY(825);
					my_variable--;	
				}
				if(my_variable == 0) {
					collision = "";
					lose = "";
				}
				
			}
			
		}
		
		//car row 2
		for (int i = 0; i<carRow2.length;i++) {

			carRow2[i].setcvx(-1);
			carRow2[i].move();
			
			if(froggy.collided(
					carRow2[i].getcx(), 
					carRow2[i].getcy(), 
					carRow2[i].getcw(), 
					carRow2[i].getch())) {
				collision = "lost a life - collision";
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
				collision = "lost a life - collision";
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
			
			carRow4[i].setcvx(1.5);
			carRow4[i].move();

			if(froggy.collided(
					carRow4[i].getcx(), 
					carRow4[i].getcy(), 
					carRow4[i].getcw(), 
					carRow4[i].getch())) {
				collision = "lost a life - collision";
				//reset frog if you have lives still
				if(my_variable > 0){
					froggy.setX(425);
					froggy.setY(825);
					my_variable--;					
				}
				
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
		froggy = new Froggy("froggy.png", 425, 325);
		
		//create the instances of objects for your specific arrays
		for (int i = 0; i<carRow1.length;i++) {
			carRow1[i] = new Car("newcar.png", i*300+200, 710);
		}
		for (int i = 0; i<carRow2.length;i++) {
			carRow2[i] = new Car("newcar.png", i*300+200, 635);
		}
		for (int i = 0; i<carRow3.length;i++) {
			carRow3[i] = new Car("newcar.png", i*300+200, 462);
		}
		for (int i = 0; i<carRow4.length;i++) {
			carRow4[i] = new Car("newcar.png", i*300+200, 380);
		}
		
		//create the instances of objects for your specific arrays
		for (int i = 0; i<turtleRow8.length;i++) {
			turtleRow8[i] = new Turtle("turtleee.png", i*200+100, 100); 
		}
		for (int i = 0; i<turtleRow6.length;i++) {
			turtleRow6[i] = new Turtle("turtleee.png", i*200+100, 240); 
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
			else {
				froggy.setvy(10);
			
			}
		}
		if(e.getKeyCode() == 37) {
			if(my_variable == 0) {
				froggy.setvx(0);
			}
			else if (turtlecollision) {
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
			else if (turtlecollision) {
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
			collision = "";
			win = "";
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




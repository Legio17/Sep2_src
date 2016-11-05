package ca.main.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ca.main.game.Game;

public class Player {
	
	private double x;
	private double y;
	
	private double velX = 0; //both velocities are purely for increasing smoothness of movement
	private double velY = 0; //they are not necessary, but movement looks way nicer
	
	private BufferedImage player;
	
	public Player(double x, double y, Game game){
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());//fetch SpriteSheet from Game class
		
		player = ss.grabImage(3, 1, 96, 96, 96);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		//prevent player to leave s
	}
	
	public void render(Graphics g){
		g.drawImage(player, (int)x, (int)y, null); //null for image observer
												   //x,y need to be casted, doesn't take doubles
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
}

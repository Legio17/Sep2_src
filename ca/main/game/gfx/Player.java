package ca.main.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ca.main.game.Game;

public class Player {
	
	private double x;
	private double y;
	
	private double velX = 0; //both velocities are purely for increasing smoothness of movement
	private double velY = 0; //they are not necessary, but movement looks nicer
	
	
	SpriteSheetLoader sprite_sheet_loader;
	SpriteSheet player_sheet;
	
	private BufferedImage player;
	private BufferedImage player_right;
	private BufferedImage player_left;
	private BufferedImage player_up;
	private BufferedImage player_down;
	
	public Player(double x, double y, Game game, int model_nr){
		this.x = x;
		this.y = y;
		
		sprite_sheet_loader = game.getSpriteSheetLoader();//fetch SpriteSheet from Game class
		player_sheet = sprite_sheet_loader.retrivePlayerModel(model_nr);
		

		player_right = player_sheet.grabImage(3, 1, 96, 96, 96, 1);
		player_left = player_sheet.grabImage(14, 1, 96, 96, 96, 1);
		player_up = player_sheet.grabImage(1, 1, 96, 96, 96, 1);
		player_down = player_sheet.grabImage(5, 1, 96, 96, 96, 1);
		
		player = player_down; //player facing this pos after spawned
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
		if (velX + this.velX > this.velX){player = player_right;} //use turn right sprite
		else if(velX + this.velX == this.velX){} //keep last sprite used
		else{player = player_left;} //use left sprite
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		if (velY + this.velY > this.velY){player = player_down;} //use turn down sprite
		else if(velY + this.velY == this.velY){} //keep last sprite used
		else{player = player_up;} //use up sprite
		this.velY = velY;
	}
}

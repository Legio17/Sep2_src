package ca.main.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheetLoader{
	
	private SpriteSheet spriteSheet = null;
	private BufferImageLoader loader;
	
	private ArrayList<SpriteSheet> player_model_list = new ArrayList<SpriteSheet>();   
	
	public SpriteSheetLoader(){
		loader = new BufferImageLoader();
		loadPlayerModel("/sheets/player_models/", "applejack");
	}
	
	public void loadPlayerModel(String folder_path,String player_model_name){
		
		String path = folder_path + player_model_name + ".png";
		
		try {
			spriteSheet = new SpriteSheet(loader.loadImage(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player_model_list.add(spriteSheet);

		
	}
	
	public SpriteSheet retrivePlayerModel(int number){
		return player_model_list.get(number);
	}
}

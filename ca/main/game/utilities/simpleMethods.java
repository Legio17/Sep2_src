package ca.main.game.utilities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class simpleMethods {
	
	public static int retrivePosition(String name, ArrayList<String> list){
		int pos = 0;
		
		for (String s: list) {  
			if (s.equals(name)) return pos;
			pos ++;
		}
		return -1;
	}
	
	public static BufferedImage retriveFromListByName(String name, ArrayList<String> imageListNames, ArrayList<BufferedImage> imageList){
		
		int pos = retrivePosition(name, imageListNames);		
		return imageList.get(pos);
		
	}
}
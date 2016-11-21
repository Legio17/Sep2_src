package ca.main.game.gfx.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader {

	private static String[][] map;

	public MapReader(String path) {
		try {
			readFromFile(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void readFromFile(String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedReader temp_br = new BufferedReader(new FileReader(path)); //just to know how many lines does file have
		try {
			String line = br.readLine();
			String temp_line = temp_br.readLine();
			int currentLine = 0;
			int nrOfLines = 0;
			
			while (temp_line != null){
				nrOfLines ++;
				temp_line = temp_br.readLine();
			}
			temp_br.close();
			
			map = new String[line.length()][nrOfLines];

			while (line != null) {
				
				int column = 0;
				for (char ch : line.toCharArray()) {
					map[column][currentLine] = ch+"";
					column ++;
				}
				currentLine ++;
				line = br.readLine();
			}


		} finally {
			br.close();
		}
	}
	
	public String[][] getMap(){
		return map;
		
	}
}

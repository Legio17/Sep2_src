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

	static void readFromFile(String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			String line = br.readLine();
			int currentLine = 0;
			 map = new String[line.length()][line.length()];

			while (line != null) {
				
				int column = 0;
				for (char ch : line.toCharArray()) {
					map[currentLine][column] = ch+"";
					//System.out.print(map[currentLine][column]);
					column ++;
				}
				//System.out.println();
				currentLine ++;
				line = br.readLine();
			}


		} finally {
			br.close();
		}
	}
}

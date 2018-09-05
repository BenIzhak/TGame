package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import mainGame.ErrorHandler;

public class Utils {
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null){
				builder.append(line + System.lineSeparator());
			}
			br.close();
		} catch (IOException e) {
			ErrorHandler.srcBoardLoadError();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

}

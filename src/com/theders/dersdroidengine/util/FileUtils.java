package com.theders.dersdroidengine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;

public class FileUtils {
	
	private static Context context;
	
	public FileUtils(Context context){
		FileUtils.context = context;
	}

	public static String readFile(String filePath){
		AssetManager am = context.getAssets();
		StringBuilder text = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(am.open(filePath)));
			
			String line;
			
			while((line = reader.readLine()) != null){
				text.append(line);
				text.append('\n');
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text.toString();
	}
}

package com.theders.dersdroidengine.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapLoader {
	
	private static Activity activity;
	/** Hashmap used to cache all bitmaps used in the game */
	private static HashMap<String, Bitmap> bitmapLib = new HashMap<String, Bitmap>();
	
	public BitmapLoader(Activity activity){
		BitmapLoader.activity = activity;
		
		bitmapLib.put("test", loadBitmap("test"));
		bitmapLib.put("flappy", loadBitmap("flappy"));
		bitmapLib.put("atlas", loadBitmap("textureatlas"));
	}
	
	/**
	 * Loads a bitmap from a given source
	 * 
	 * @param path the location of the bitmap
	 * @return returns the bitmap located at the given source
	 */
	private static Bitmap loadBitmap(String path) {
		// get acces to the raw assets files
		AssetManager assetManager = activity.getAssets();
		InputStream inputStream = null;
		Bitmap bitmap = null;
		try {
			inputStream = assetManager.open("textures/" + path + ".png");
			bitmap = BitmapFactory.decodeStream(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bitmap;
	}
	
	/**
	 * retrieves and returns the bitmap associated with the given name
	 * from the hashmap 
	 * @param name
	 * @return
	 */
	public static Bitmap getBitmap(String name){
		return bitmapLib.get(name);
	}

}

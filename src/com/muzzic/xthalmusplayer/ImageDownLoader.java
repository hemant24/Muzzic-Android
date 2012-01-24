package com.muzzic.xthalmusplayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageDownLoader {

	public static Bitmap getImage(String urlString) {
		Bitmap StoreImg = null;
		try {

			URL url = new URL(urlString);

			URLConnection connection = url.openConnection();

			HttpURLConnection HCon = (HttpURLConnection) connection;

			int ResCode = HCon.getResponseCode();

			System.out.println("Responce Code is = " + ResCode);

			if (ResCode == HttpURLConnection.HTTP_OK) {

				InputStream ins = ((URLConnection) HCon).getInputStream();

				StoreImg = BitmapFactory.decodeStream(ins);

			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return StoreImg;

	}

}

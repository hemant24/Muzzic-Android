package com.muzzic.xthalmusplayer;

import java.net.URLEncoder;

import org.json.JSONException;

public class LyricsService {
	
	public static String getLyrics(SongInfoBean songInfoBean){
		String lyrics="Not Available";
		StringBuffer sb = new StringBuffer();
		try {
			String properSongName =  URLEncoder.encode(songInfoBean.getSongName(), "utf-8");
			String properArtistName =  URLEncoder.encode(songInfoBean.getArtist(), "utf-8");
			String URL= "http://lyrics.wikia.com/api.php?artist="+properArtistName+"&song="+properSongName+"&fmt=text";
			RestClient client = new RestClient(URL);
    	    client.Execute(RequestMethod.GET);
    	    String response = client.getResponse();
    	    sb = new StringBuffer();
    	    lyrics = response;
    	    for(int i = 0 ; i < 3 ; i++){
    	    	sb.append(response);
    	    	sb.append(" " + "demo..");
    	    }
    	}catch (JSONException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
    	    e.printStackTrace();
    	}
		return sb.toString();
		 
		
	}
}

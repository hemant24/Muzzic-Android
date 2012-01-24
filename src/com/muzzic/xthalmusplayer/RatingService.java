package com.muzzic.xthalmusplayer;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RatingService {

		 
		public static SongInfoBean getRating(String songName){
			SongInfoBean songInfoBean = new SongInfoBean();
			
			try {
				String properSongName =  URLEncoder.encode(songName, "utf-8");
				String URL= "http://ws.audioscrobbler.com/2.0/?method=track.search&track="+properSongName+"&limit=2&api_key=b25b959554ed76058ac220b7b2e0a026&format=json";
				RestClient client = new RestClient(URL);
				
	    	    client.Execute(RequestMethod.GET);
	    	    String response = client.getResponse();
	    	    JSONObject jArray = new JSONObject(response);
	    	      String song =  ((JSONObject)(jArray.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").get(0))).getString("name");
	    	      String artist=  ((JSONObject)(jArray.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").get(0))).getString("artist");
	    	      String listeners=  ((JSONObject)(jArray.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").get(0))).getString("listeners");
	    	      JSONObject trackJsonArray = (JSONObject)(jArray.getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track").get(0));
	    	      JSONObject imageJson = (JSONObject) trackJsonArray.getJSONArray("image").get(1);
	    	      String imageURL =imageJson.getString("#text");
	    	      String rating = calculateRating(listeners);
	    	      songInfoBean.setArtist(artist);
	    	      songInfoBean.setSongName(song);
	    	      songInfoBean.setRating(rating);
	    	      songInfoBean.setImageURL(imageURL);
	    	}catch (JSONException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
	    	    e.printStackTrace();
	    	}
			return songInfoBean;
		}
		
		private static String calculateRating(String listeners){
			Integer intListeners = 0 ;
			
			if(listeners == null )
				return intListeners.toString();
			try{
				intListeners = Integer.parseInt(listeners);
			}catch(Exception e){
				return intListeners.toString();
			}
			if(intListeners >= 20000){
				return "5";
			}else if(intListeners >= 10000 && intListeners <20000){
				return "4";
			}else if (intListeners >= 5000 && intListeners <10000){
				return "3";
			}else if (intListeners >= 2500 && intListeners <5000){
				return "2";
			}else if (intListeners >= 500 && intListeners <2500){
				return "1";
			}else{
				return "0";
			}
			
		}
}

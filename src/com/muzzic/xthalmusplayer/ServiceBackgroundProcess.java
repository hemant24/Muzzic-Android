package com.muzzic.xthalmusplayer;

import android.content.Context;
import android.os.AsyncTask;

public class ServiceBackgroundProcess extends AsyncTask<Object, Object, Object>{

	SongDetailsActivity activity=null;
	ServiceBackgroundProcess(Context cxt){
		this.activity = (SongDetailsActivity) cxt;
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		Object resultObj=null;
		String songName = (String) params[0];
		SongInfoBean songInfo = RatingService.getRating(songName);
		String lyrics = LyricsService.getLyrics(songInfo);
		SongAllInfoBean allInfoBean = new SongAllInfoBean();
		allInfoBean.setArtist(songInfo.getArtist());
		allInfoBean.setLyrics(lyrics);
		allInfoBean.setRating(songInfo.getRating());
		allInfoBean.setSongName(songInfo.getSongName());
		allInfoBean.setImageURL(songInfo.getImageURL());
		resultObj = allInfoBean;
		return resultObj;
	}
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		activity.showRating((SongAllInfoBean)result);
	}

}

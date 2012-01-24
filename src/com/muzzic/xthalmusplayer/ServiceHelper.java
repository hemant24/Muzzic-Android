package com.muzzic.xthalmusplayer;

import android.app.Activity;

public class ServiceHelper {

	public static void getRatingInfo(Activity activity,String songName){
		
	}
	public static void getLyrics(String songName){
		//TODO:to do
	}
	public static void getAllInfo(Activity activity,String songName){
		ServiceBackgroundProcess process = new ServiceBackgroundProcess(activity);
		process.execute(songName);
	}
	public static void runBackgroundMusic(){
		//TODO:to do
	}
}

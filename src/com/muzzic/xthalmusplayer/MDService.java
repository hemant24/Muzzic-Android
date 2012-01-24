package com.muzzic.xthalmusplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MDService extends Service {
	private MediaPlayer mp = new MediaPlayer();
	private MDSInterface.Stub coreRemoteServiceStub = new MDSInterface.Stub() {
		public void playSong(String file) throws RemoteException {
			try{
				mp.reset();
				mp.setDataSource(file);
				mp.prepare();
				mp.start();
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
		public void stopSong() throws RemoteException {
			try{
				mp.stop();
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
		
	};

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(getClass().getSimpleName(), "onCreate()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(getClass().getSimpleName(), "onDestroy()");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(getClass().getSimpleName(), "onStart()");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.d(getClass().getSimpleName(), "onBind()");
		return coreRemoteServiceStub;
	}

}
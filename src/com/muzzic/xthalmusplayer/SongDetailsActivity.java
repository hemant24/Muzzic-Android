package com.muzzic.xthalmusplayer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SongDetailsActivity extends Activity {
	private ProgressDialog progressdialog;
	public static final String PREFS_NAME = "MyPrefsFile";
	private final String defaultSongName = "Summer of 69";
	private String songPath = "";
	private MDSInterface mpInterface;
	private RemoteServiceConnection conn = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songdetailslayout);

		SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		String songName = pref.getString("song", defaultSongName);
		songPath = pref.getString("path", "/mnt/sdcard/Music/"
				+ defaultSongName + ".mp3");
		System.out.println(songPath);
		ServiceHelper.getAllInfo(this, songName);
		progressdialog = ProgressDialog.show(this, "",
				"Loading. Please wait...", true);

	}

	public void showRating(SongAllInfoBean result) {
		if (progressdialog.isShowing()) {
			progressdialog.dismiss();
		}
		EditText lyricsPane = (EditText) findViewById(R.id.txt_lyricspane);
		RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		TextView songNameTV = (TextView) findViewById(R.id.txt_songname);
		TextView albumNameTV = (TextView) findViewById(R.id.txt_albumname);
		TextView artistNameTV = (TextView) findViewById(R.id.txt_artistname);
		if (result.getRating() != null) {
			try {
				ratingBar.setRating(Float.parseFloat(result.getRating()));
			} catch (Exception e) {

			}
		}

		ImageView imageView = (ImageView) findViewById(R.id.img_imagepane);

		Bitmap imageToSet = ImageDownLoader.getImage(result.getImageURL());

		imageView.setImageBitmap(imageToSet);

		lyricsPane.setText(result.getLyrics());
		songNameTV.setText(result.getSongName());
		SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		String album = pref.getString("album", defaultSongName);
		albumNameTV.setText(album);
		artistNameTV.setText(result.getArtist());
		// Toast.makeText(this, result.getLyrics(), Toast.LENGTH_LONG).show();

	}

	public void playSong(View v) {
		if (mpInterface != null) {
			try {
				mpInterface.playSong(songPath);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			Intent i = new Intent();
			i.setClassName("com.muzzic.xthalmusplayer",
					"com.muzzic.xthalmusplayer.MDService");
			startService(i);
			if (conn == null)
				conn = new RemoteServiceConnection();
			bindService(i, conn, Context.BIND_AUTO_CREATE);
		}

	}

	public void stopSong(View v) {
		if (mpInterface != null) {
			try {
				mpInterface.stopSong();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	class RemoteServiceConnection implements ServiceConnection {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mpInterface = MDSInterface.Stub.asInterface((IBinder) service);
			try {
				mpInterface.playSong(songPath);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			System.out.println("service interface connected");
		}

		public void onServiceDisconnected(ComponentName className) {
			System.out.println("service interface disconnected");
			mpInterface = null;
		}
	};

}

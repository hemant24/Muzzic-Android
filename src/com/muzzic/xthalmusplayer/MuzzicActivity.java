package com.muzzic.xthalmusplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MuzzicActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button musicInfoButton = (Button) findViewById(R.id.btn_SystemMusicInfo);

		musicInfoButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				startActivity(new Intent(MuzzicActivity.this,
						SystemMusicList.class));

			}
		});

	}
	
	
}
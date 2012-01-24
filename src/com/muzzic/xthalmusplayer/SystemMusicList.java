package com.muzzic.xthalmusplayer;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SystemMusicList extends ListActivity {

	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musiclist);

		List<SongListViewBean> fileList = SystemInteractions
				.getAllMusicList(this);

		SongListAdapter songListAdapter = new SongListAdapter(
				getApplicationContext(), R.layout.musiclistlayout, fileList);
		setListAdapter(songListAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		SongListViewBean songViewBean = (SongListViewBean) this
				.getListAdapter().getItem(position);

		saveSongName(songViewBean);

		startActivity(new Intent(SystemMusicList.this,
				SongDetailsActivity.class));
	}

	private void saveSongName(SongListViewBean songListViewBean) {
		SharedPreferences pref = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("song", songListViewBean.getSongName());
		editor.putString("path", songListViewBean.getFilePath());
		editor.putString("album", songListViewBean.getAlbumName());
		editor.commit();
	}

}

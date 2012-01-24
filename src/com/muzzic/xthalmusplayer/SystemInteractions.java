package com.muzzic.xthalmusplayer;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.provider.MediaStore;

public class SystemInteractions {

	public static List<SongListViewBean> getAllMusicList(
			ListActivity SystemMusicList) {

		List<SongListViewBean> songList = new ArrayList<SongListViewBean>();

		String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

		String[] projection = { MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.TITLE,
				MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media.ALBUM_ID };

		String[] albumProjection = { MediaStore.Audio.Albums.ALBUM,
				MediaStore.Audio.Albums.ALBUM_ART,
				MediaStore.Audio.Albums.FIRST_YEAR,
				MediaStore.Audio.Albums.LAST_YEAR,
				MediaStore.Audio.Albums.NUMBER_OF_SONGS };

		Cursor cursor = SystemMusicList.managedQuery(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,
				selection, null, MediaStore.Audio.Media.TITLE);

		while (cursor.moveToNext()) {

			String albumDetails = MediaStore.Audio.Albums._ID;

			System.out.println(cursor.getString(3));
			SongListViewBean songListViewBean = new SongListViewBean();

			songListViewBean.setSongName(cursor.getString(2));
			songListViewBean.setArtistName(cursor.getString(1));
			songListViewBean.setDuration(cursor.getString(5));
			songListViewBean.setFilePath(cursor.getString(3));

			if (cursor.getString(6) != null | !cursor.getString(6).equals(null)) {
				albumDetails = albumDetails + "=" + cursor.getString(6);
				Cursor cursor2 = SystemMusicList.managedQuery(
						MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
						albumProjection, albumDetails, null, null);

				while (cursor2.moveToNext()) {
					songListViewBean.setAlbumName(cursor2.getString(0));
					songListViewBean.setImagePath(cursor2.getString(1));
				}
			}

			songList.add(songListViewBean);

		}

		return songList;
	}

}

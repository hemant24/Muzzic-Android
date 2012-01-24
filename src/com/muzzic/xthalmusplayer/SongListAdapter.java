package com.muzzic.xthalmusplayer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SongListAdapter extends ArrayAdapter<SongListViewBean> {

	private List<SongListViewBean> songList;

	public SongListAdapter(Context context, int textViewResourceId,
			List<SongListViewBean> objects) {
		super(context, textViewResourceId, objects);
		this.songList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		Context context = getContext();
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.musiclistlayout, null);
		}

		// v.setClickable(true);
		SongListViewBean o = songList.get(position);

		if (o != null) {
			TextView title = (TextView) v.findViewById(R.id.txtsongname);
			TextView songPath = (TextView) v.findViewById(R.id.txtsongpath);
			/*TextView artist = (TextView) v.findViewById(R.id.txtartist);
			ImageView thumbImage = (ImageView) v.findViewById(R.id.songimage);
			TextView albumName = (TextView) v.findViewById(R.id.txtablumname);*/
			if (title != null) {
				title.setText(o.getSongName());
			}
			if(songPath!=null){
				songPath.setText(o.getFilePath());
			}
			/*if (artist != null) {
				artist.setText(o.getArtistName());
			}
			if (duration != null) {
				double time= new Double(o.getDuration());
				Double timeInMins = time/60000f;
				DecimalFormat decimalFormat = new DecimalFormat("#.##");
				duration.setText(decimalFormat.format(timeInMins).toString());
			}
			if (albumName != null) {
				albumName.setText(o.getAlbumName());
			}
			if (thumbImage != null && o.getImagePath()!=null) {
				File imgFile = new File(o.getImagePath());
				if (imgFile.exists()) {
					Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
							.getAbsolutePath());
					thumbImage.setImageBitmap(myBitmap);
					
				}
			}*/

		}
		return v;
	}
}

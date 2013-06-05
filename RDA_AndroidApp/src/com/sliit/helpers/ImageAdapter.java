package com.sliit.helpers;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private List<ButtonClass> buttonClasses;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public ImageAdapter(Context c, List<ButtonClass> gridButton) {
		mContext = c;
		buttonClasses = gridButton;
	}

	public int getCount() {
		return buttonClasses.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout view;

		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			ButtonClass buttonClass = buttonClasses.get(position);

			view = new LinearLayout(mContext);
		
			view.setOrientation(LinearLayout.VERTICAL);
			imageView = new ImageView(view.getContext());
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(1, 1, 1, 1);
			imageView.setImageResource(buttonClass.getImageID());
			view.addView(imageView);
			TextView textView = new TextView(view.getContext());
			textView.setText(buttonClass.getTitle());
			textView.setTextColor(Color.parseColor("#59b4e2"));
			textView.setTextSize(14);
			view.addView(textView);
			view.setPadding(5, 5, 5, 5);

		} else {
			view = (LinearLayout) convertView;
		}

		return view;
	}

	/**
	 * // references to our images private Integer[] mThumbIds = {
	 * R.drawable.check_water_level, R.drawable.check_weather,
	 * R.drawable.confirm_water_supply, R.drawable.live_cctv,
	 * R.drawable.enter_water_level, R.drawable.recode_water_supply,
	 * R.drawable.view_message, R.drawable.view_past_recode };
	 * 
	 * private String[] mTitle = { "Check Water Level", "Check Weather",
	 * "Confirm Water Supply", "Live CCTV", "Enter Water Level",
	 * "Recode Water Supply", "View Message", "View Past Recode" };
	 **/

}

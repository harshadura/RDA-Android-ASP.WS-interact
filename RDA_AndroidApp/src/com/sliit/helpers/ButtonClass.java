package com.sliit.helpers;

import android.content.Context;
import android.content.Intent;

public  class ButtonClass {

	private Context context;
	private Class activeClass;
	protected String title;
	protected int imageID;

	public ButtonClass(Context context, Class activeClass, int title,
			int imageID) {
		this.context = context;
		this.activeClass = activeClass;
		this.title = context.getResources().getString(title);
		this.imageID = imageID;
	}

	public ButtonClass() {
		// TODO Auto-generated constructor stub
	}

	public void clickAction() {
		Intent intent = new Intent(context, activeClass);
		context.startActivity(intent);
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

}

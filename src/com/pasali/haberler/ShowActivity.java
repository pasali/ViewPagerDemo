package com.pasali.haberler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		TextView text = (TextView) findViewById(R.id.textView1);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int color = extras.getInt("color");
			if (color != 0) {
				text.setBackgroundColor(color);
			}
		}

	}
}

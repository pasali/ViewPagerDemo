package com.pasali.haberler;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class SquareFragment extends Fragment {

	int randomColor, randomx, randomy;
	ArrayList<Integer> c_list;

	public SquareFragment(int x, int y) {
		randomx = x;
		randomy = y;
		colorGenerate();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public void colorGenerate() {
		// Kare sayısı kadar renk üret
		c_list = new ArrayList<Integer>();
		int count = randomx * randomy;
		for (int i = 0; i < count; i++) {
			int r = MainActivity.randomGenerator.nextInt(255);
			int g = MainActivity.randomGenerator.nextInt(255);
			int b = MainActivity.randomGenerator.nextInt(255);
			int c = Color.rgb(r, g, b);
			c_list.add(c);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Kareleri, tablo formatından ekrana yerleştir
		TableLayout tbl = new TableLayout(getActivity());
		TableRow tr = null;
		int color = 0;
		for (int i = 0; i < randomx; i++) {
			tr = new TableRow(getActivity());
			for (int j = 0; j < randomy; j++) {
				final Button btnTag = new Button(getActivity());
				btnTag.setBackgroundColor(c_list.get(color));
				btnTag.setText("\t\t\t\n\t\t\t           \n\t\t");
				btnTag.setWidth(350);
				btnTag.setHeight(350);
				btnTag.setId(c_list.get(color));
				btnTag.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (btnTag.getId() == ((Button) v).getId()) {
							Intent intent = new Intent();
							intent.setClass(getActivity(), ShowActivity.class);
							intent.putExtra("color", btnTag.getId());
							startActivity(intent);
						}
					}
				});
				tr.addView(btnTag);
				color++;
			}
			tbl.addView(tr);
		}

		View view = tbl;

		return view;
	}

}

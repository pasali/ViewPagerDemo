package com.pasali.haberler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.pasali.haberler.R;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	private static final int NUM_PAGES = 3;
	static Random randomGenerator;
	public static SquareFragment frag1, frag2, frag3;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private Timer zamanlayici;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Fragment için kare sayılarını random olarak üret
		randomGenerator = new Random(System.currentTimeMillis());
		frag1 = new SquareFragment(randomGenerator.nextInt(3) + 1,
				randomGenerator.nextInt(2) + 1);
		frag2 = new SquareFragment(randomGenerator.nextInt(3) + 1,
				randomGenerator.nextInt(2) + 1);
		frag3 = new SquareFragment(randomGenerator.nextInt(3) + 1,
				randomGenerator.nextInt(2) + 1);
		
		// Viewpager ve adaptörünü oluşturuyoruz
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// sayfalar değiştikçe allatakı butunların durumunu guncelle
				invalidateOptionsMenu();
			}
		});

		// otomatik geçiş yapmak için küçük bir zamanlayıcı
		zamanlayici = new Timer();
		zamanlayici.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (mPager.getCurrentItem() == NUM_PAGES - 1) {
							mPager.setCurrentItem(0);
						} else {
							mPager.setCurrentItem(mPager.getCurrentItem() + 1);
						}

					}
				});
			}
		}, 500, 3000);
		// İndicator
		CirclePageIndicator mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

	}

	/**
	 * Basit bir Pageradpater çok küçük olduğu için inner class olarak yazdım.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);

		}
		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return frag1;
			case 1:
				return frag2;
			case 2:
				return frag3;
			}
			return null;
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
}

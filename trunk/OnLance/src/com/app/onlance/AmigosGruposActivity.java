package com.app.onlance;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class AmigosGruposActivity extends FragmentActivity implements
		TabListener {

	private ViewPager viewPager;
	private ActionBar actionBar;
	private PagerAdapter pAdapter;
	private String[] tabs = { "Amigos", "Grupos" };

	public class PagerAdapter extends FragmentPagerAdapter {

		AmigosFragment amigosFrag;
		GruposFragment gruposFrag;

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {

			switch (index) {
			case 0:

				if (amigosFrag == null) {
					amigosFrag = new AmigosFragment();
				}

				return amigosFrag;
			case 1:

				if (gruposFrag == null) {
					gruposFrag = new GruposFragment();
				}

				return gruposFrag;
			}

			return null;
		}

		@Override
		public int getCount() {
			// get item count - equal to number of tabs
			return 2;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amigos_grupos);

		viewPager = (ViewPager) findViewById(R.id.pagerAmigosGrupos);
		pAdapter = new PagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pAdapter);

		actionBar = getActionBar();
		actionBar.setTitle(R.string.comunidade_UPPER);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.color.green700)));

		for (String tab : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}

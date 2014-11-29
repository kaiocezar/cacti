package com.app.onlance;

import Utils.UtilsConstants;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

public class TimesPartidaActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Basicos", "Avançado" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecao_times);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
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

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
	
	
	public void proseguirConf(View view){
		
		JogadorFragment item1= (JogadorFragment) mAdapter.getItem(0);
		TimeFragment item2= (TimeFragment) mAdapter.getItem(1);
		
		Intent intent = new Intent(this, DefinirTimesActivity.class);
		
		Bundle paramns = new Bundle();
		
		paramns.putString(UtilsConstants.QUANT_GOLS, item1.getSpinnerQuantGols().getSelectedItem().toString());
		paramns.putString(UtilsConstants.QUANT_JOGADORES, item2.getSpinnerQuantJog().getSelectedItem().toString());
		paramns.putString(UtilsConstants.TEMPO_PARTIDA, item1.getSpinnerQuantTemp().getSelectedItem().toString());
		
		
		intent.putExtras(paramns);
		startActivity(intent);

		
		
		
	}
	
}
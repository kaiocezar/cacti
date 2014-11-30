
package com.app.onlance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	ConfigBasicaFragment jogarFrag;
	ConfigAvancadaFragment timeFrag;
	
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			
			if(jogarFrag == null){
				jogarFrag = new ConfigBasicaFragment();
			}
			
			return jogarFrag;
		case 1:
			
			if(timeFrag == null){
				timeFrag = new ConfigAvancadaFragment();
			}
			
			return timeFrag;
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
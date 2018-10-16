package org.metawatch.manager;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.Window;

public class ThemeContainer extends FragmentActivity {
    ActionBar mActionBar;
    ActionBar.Tab mGalleryTab;
    ActionBar.Tab mDownloadedTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	setContentView(R.layout.themes_container);
	setProgressBarIndeterminateVisibility(Boolean.FALSE);

	mActionBar = getActionBar();
	mActionBar.setDisplayHomeAsUpEnabled(true);
	mActionBar.setTitle("Themes Manager");
	mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	mGalleryTab = mActionBar.newTab().setText(R.string.ui_tab_downloaded_themes);
	mDownloadedTab = mActionBar.newTab().setText(R.string.ui_tab_theme_gallery);

	ThemeGallery galleryFragment = ThemeGallery.newInstance();
	ThemePicker downloadedFragment = ThemePicker.newInstance();

	mGalleryTab.setTabListener(new MyTabsListener(galleryFragment));
	mDownloadedTab.setTabListener(new MyTabsListener(downloadedFragment));

	mActionBar.addTab(mDownloadedTab);
	mActionBar.addTab(mGalleryTab);
    }

    class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;

	public MyTabsListener(Fragment fragment) {
	    this.fragment = fragment;
	}


		@Override
		public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

		}

		@Override
		public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

		}

		@Override
		public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

		}
	}

    public void setGalleryTabSelected() {
	mActionBar.selectTab(mGalleryTab);
    }

    public void setDownloadedTabSelected() {
	mActionBar.selectTab(mDownloadedTab);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
	    finish();
	    return true;
	default:
	    return false;
	}
    }
}
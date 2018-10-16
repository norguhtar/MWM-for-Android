package org.metawatch.manager;

import org.metawatch.manager.MetaWatchService.Preferences;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle bundle) {
	super.onCreate(bundle);
	requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	setContentView(R.layout.main_activity);
    }

    private ObjectAnimator downFromTop(final View view) {
	ObjectAnimator downFromTop = ObjectAnimator.ofFloat(view, "translationY", -1000, 0);
	downFromTop.setDuration(1250);
	downFromTop.setStartDelay(500);
	downFromTop.setInterpolator(new DecelerateInterpolator());
	downFromTop.addListener(new Animator.AnimatorListener() {
	    @Override
	    public void onAnimationStart(Animator animation) {
		view.setVisibility(View.VISIBLE);
	    }
	    public void onAnimationEnd(Animator animation) {
	    }
	    @Override
	    public void onAnimationCancel(Animator animation) {
	    }
	    @Override
	    public void onAnimationRepeat(Animator animation) {
	    }
	});
	return downFromTop;
    }
    
    private ObjectAnimator inFromLeft(final View view) {
	ObjectAnimator inFromLeft = ObjectAnimator.ofFloat(view, "translationX", -1000, 0);
	inFromLeft.setDuration(1000);
	inFromLeft.setStartDelay(500);
	inFromLeft.setInterpolator(new DecelerateInterpolator());
	inFromLeft.addListener(new Animator.AnimatorListener() {
	    @Override
	    public void onAnimationStart(Animator animation) {
		view.setVisibility(View.VISIBLE);
		MainActivity.this.setProgressBarIndeterminateVisibility(Boolean.TRUE);
	    }
	    public void onAnimationEnd(Animator animation) {
		setProgressBarIndeterminateVisibility(Boolean.FALSE);
	    }
	    @Override
	    public void onAnimationCancel(Animator animation) {
	    }
	    @Override
	    public void onAnimationRepeat(Animator animation) {
	    }
	});
	return inFromLeft;
    }
    
    @Override
    public void onResume() {
	super.onResume();
	if (Preferences.animations) {
	    View status = findViewById(R.id.status);
	    status.setVisibility(View.INVISIBLE);
	    downFromTop(status).start();
	    View widgets = findViewById(R.id.widget_setup);
	    widgets.setVisibility(View.INVISIBLE);
	    inFromLeft(widgets).start();
	}

    }
    
    MenuItem enableAnimations;
    MenuItem disableAnimations;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	super.onCreateOptionsMenu(menu);
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.main, menu);
	enableAnimations = menu.findItem(R.id.animations_enabled);
	disableAnimations = menu.findItem(R.id.animations_disabled);
	return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
	if (Preferences.animations) {
	    enableAnimations.setChecked(true);
	} else {
	    disableAnimations.setChecked(true);
	}
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	Editor editor;
	Intent intent;
	int itemId = item.getItemId();
	if (itemId == R.id.settings) {
	    intent = new Intent(this, Settings.class);
	    startActivity(intent);
	    return true;
	} else if (itemId == R.id.tests) {
	    intent = new Intent(this, Test.class);
	    startActivity(intent);
	    return true;
	} else if (itemId == R.id.animations_disabled) {
	    editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
	    editor.putBoolean("animations", false);
	    editor.commit();
	    Preferences.animations = false;
	    invalidateOptionsMenu();
	    return true;
	} else if (itemId == R.id.animations_enabled) {
	    editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
	    editor.putBoolean("animations", true);
	    editor.commit();
	    Preferences.animations = true;
	    invalidateOptionsMenu();
	    return true;
	} else {
	    return false;
	}
    }
}

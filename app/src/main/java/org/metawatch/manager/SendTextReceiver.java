package org.metawatch.manager;

import org.metawatch.manager.MetaWatchService.Preferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.metawatch.manager.Log;


public class SendTextReceiver extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	if (Preferences.logging)
	    Log.d(MetaWatchStatus.TAG, "SendTextReciever created");

	Intent i = getIntent();

	if (Preferences.logging)
	    Log.d(MetaWatchStatus.TAG, "action: " + i.getAction());
	// if (Preferences.logging) Log.d(MetaWatchStatus.TAG, "data: "+
	// i.getData().getPath() );

	if (Intent.ACTION_SEND.equals(i.getAction())) {

	    Bundle bundle = i.getExtras();

	    if (Preferences.logging)
		Log.d(MetaWatchStatus.TAG, "extras: " + bundle.keySet().toString());

	    String title = "Message";

	    if (bundle.containsKey(Intent.EXTRA_TITLE)) {

		title = bundle.getString(Intent.EXTRA_TITLE);
		title = title.replaceAll("\\p{Cntrl}", "");
	    }

	    if (bundle.containsKey(Intent.EXTRA_TEXT)) {

		String text = bundle.getString(Intent.EXTRA_TEXT);
		NotificationBuilder.createSmart(this, title, text);
		if (Preferences.logging)
		    Log.d(MetaWatchStatus.TAG, text);

	    }

	}

	finish();
    }
}

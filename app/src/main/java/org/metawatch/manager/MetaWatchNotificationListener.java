package org.metawatch.manager;

import java.util.Arrays;

import org.metawatch.manager.MetaWatchService.Preferences;

import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.service.notification.NotificationListenerService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.service.notification.StatusBarNotification;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MetaWatchNotificationListener extends NotificationListenerService {

    // TODO Make this nicer so it isn't duplicated between the accessibility and notification services
    public static boolean notificationListenerEnabled = false;
    
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
	// TODO Auto-generated method stub
	Notification  notification = sbn.getNotification();
	MetaWatchAccessibilityService.processNotification(this, notification, sbn.getPackageName());
    }

    @Override
    public IBinder onBind(Intent mIntent) {
	IBinder mIBinder = super.onBind(mIntent);
	notificationListenerEnabled = true;
	return mIBinder;
    }
    
    @Override
    public boolean onUnbind(Intent mIntent) {
	boolean mOnUnbind = super.onUnbind(mIntent);
	notificationListenerEnabled = false;
	return mOnUnbind;
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
	// TODO Auto-generated method stub

    }

}

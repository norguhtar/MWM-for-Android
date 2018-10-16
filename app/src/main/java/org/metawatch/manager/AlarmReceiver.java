/*****************************************************************************
 *  Copyright (c) 2011 Meta Watch Ltd.                                       *
 *  www.MetaWatch.org                                                        *
 *                                                                           *
 =============================================================================
 *                                                                           *
 *  Licensed under the Apache License, Version 2.0 (the "License");          *
 *  you may not use this file except in compliance with the License.         *
 *  You may obtain a copy of the License at                                  *
 *                                                                           *
 *    http://www.apache.org/licenses/LICENSE-2.0                             *
 *                                                                           *
 *  Unless required by applicable law or agreed to in writing, software      *
 *  distributed under the License is distributed on an "AS IS" BASIS,        *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. *
 *  See the License for the specific language governing permissions and      *
 *  limitations under the License.                                           *
 *                                                                           *
 *****************************************************************************/

/*****************************************************************************
 * AlarmReceiver.java                                                        *
 * AlarmReceiver                                                             *
 * Receiver for RTC wakeup intents used for weather updates                  *
 *                                                                           *
 *                                                                           *
 *****************************************************************************/

package org.metawatch.manager;

import org.metawatch.manager.MetaWatchService.Preferences;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.metawatch.manager.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

	if (Preferences.logging)
	    Log.d(MetaWatchStatus.TAG, "AlarmReceiver.onReceive(): received intent: " + intent.toString());

	if (intent.hasExtra("action_update")) {
	    Monitors.getInstance().updateWeatherData(context);
	    return;
	}

	if (intent.hasExtra("action_poll_voltage")) {
	    if (MetaWatchService.connectionState == MetaWatchService.ConnectionState.CONNECTED)
		Protocol.getInstance(context).readBatteryVoltage();
	    return;
	}
    }
}
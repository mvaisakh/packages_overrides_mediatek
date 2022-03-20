/*
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package org.statix.mediatek.override;

import android.content.Context;
import android.telephony.ims.ImsService;
import com.android.ims.FeatureUpdates;
import com.android.ims.ImsManager;
import com.android.telephony.Rlog;
import android.util.Log;

public class ImsManagerOverride extends ImsManager {

    private static final String TAG = "ImsManager";

    public ImsManagerOverride(Context context, int phoneId, MmTelFeatureConnectionFactory featuresFactory, SubscriptionManagerProxy subscriptionManager, SettingsProxy proxy) {
       super(context, phoneId, featuresFactory, subscriptionManager, proxy);
    }

    public static void updateImsServiceConfig(Context context, int phoneId, boolean force) {
        ImsManager mgr = ImsManager.getInstance(context, phoneId);
        if (mgr != null) {
            mgr.updateImsServiceConfig();
        }
        Rlog.e(TAG, "updateImsServiceConfig: ImsManager null, returning without update.");
    }
}

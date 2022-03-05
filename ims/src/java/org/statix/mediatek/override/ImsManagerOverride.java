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

public abstract class ImsManagerOverride implements FeatureUpdates {

    private static final String TAG = "ImsManager";

    public static void updateImsServiceConfig(Context context, int phoneId, boolean force) {
        ImsManager mgr = ImsManager.getInstance(context, phoneId);
        if (mgr != null) {
            mgr.updateImsServiceConfig();
        }
        Rlog.e(TAG, "updateImsServiceConfig: ImsManager null, returning without update.");
    }
}

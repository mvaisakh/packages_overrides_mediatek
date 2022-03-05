/*
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package org.statix.mediatek.override;

import com.android.server.wifi.BaseWifiService;
import com.android.server.wifi.WifiServiceImpl;
import statix.mediatek.override.IStaStateCallback;
import statix.mediatek.override.IWifiManagerOverride;

public class BaseWifiServiceOverride extends BaseWifiService {

    private WifiStaStateNotifier mWifiStaStateNotifier;

    public WifiServiceImpl(Context context, WifiInjector wifiInjector) {
        mWifiStaStateNotifier = mWifiInjector.getWifiStaStateNotifier();
    }

    @Override
    public void registerStaStateCallback(IBinder binder, IStaStateCallback callback,
                                                int callbackIdentifier) {
        if (binder == null) {
            throw new IllegalArgumentException("Binder must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        if (mVerboseLoggingEnabled) {
            mLog.info("registerStaStateCallback uid=%").c(Binder.getCallingUid()).flush();
        }
        mWifiThreadRunner.post(() ->
            mWifiStaStateNotifier.addCallback(binder, callback, callbackIdentifier));
    }

    @Override
    public void unregisterStaStateCallback(int callbackIdentifier) {
        if (mVerboseLoggingEnabled) {
            mLog.info("unregisterStaStateCallback uid=%").c(Binder.getCallingUid()).flush();
        }
        mWifiThreadRunner.post(() ->
            mWifiStaStateNotifier.removeCallback(callbackIdentifier));
    }

    @Override
    public void registerStaStateCallback(
            IBinder binder, IStaStateCallback callback, int callbackIdentifier) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unregisterStaStateCallback(int callbackIdentifier) {
        throw new UnsupportedOperationException();
    }
}

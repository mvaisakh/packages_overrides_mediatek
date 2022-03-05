/*
 * Copyright (C) 2020 The Android Open Source Project
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package org.statix.mediatek.override;

import android.annotation.NonNull;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import statix.mediatek.override.IStaStateCallback;
import statix.mediatek.override.IWifiManagerOverride;

import com.android.server.wifi.util.ExternalCallbackTracker;


public class WifiStaStateNotifier {
    private final ExternalCallbackTracker<IStaStateCallback> mRegisteredCallbacks;
    private static WifiInjector mWifiInjector;
    private static final String TAG = "WifiStaStateNotifier";
    private static final boolean DEBUG = false;

    WifiStaStateNotifier(@NonNull Looper looper, WifiInjector wifiInjector) {
        mRegisteredCallbacks = new ExternalCallbackTracker<IStaStateCallback>(new Handler(looper));
        mWifiInjector = wifiInjector;
    }

    public void addCallback(IBinder binder, IStaStateCallback callback,
                            int callbackIdentifier) {
        if (DEBUG) Log.d(TAG, "addCallback");
        if (mRegisteredCallbacks.getNumCallbacks() > 0) {
            if (DEBUG) Log.e(TAG, "Failed to add callback, only support single request!");
            return;
        }
        if (!mRegisteredCallbacks.add(binder, callback, callbackIdentifier)) {
            if (DEBUG) Log.e(TAG, "Failed to add callback");
            return;
        }
        mWifiInjector.getActiveModeWarden().registerStaEventCallback();
    }

    public void removeCallback(int callbackIdentifier) {
        if (DEBUG) Log.d(TAG, "removeCallback");
        mRegisteredCallbacks.remove(callbackIdentifier);
        mWifiInjector.getActiveModeWarden().unregisterStaEventCallback();
    }

    public void onStaToBeOff() {
        if (DEBUG) Log.d(TAG, "onStaToBeOff");
        for (IStaStateCallback callback : mRegisteredCallbacks.getCallbacks()) {
            try {
                if (DEBUG) Log.d(TAG, "callback onStaToBeOff");
                callback.onStaToBeOff();
            } catch (RemoteException e) {
                // do nothing
            }
        }
    }
}

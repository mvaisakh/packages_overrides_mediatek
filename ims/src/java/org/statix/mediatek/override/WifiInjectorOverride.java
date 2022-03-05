/*
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package org.statix.mediatek.override;

import com.android.server.wifi.WifiInjector;

public class WifiInjectorOverride extends WifiInjector {

    private final WifiStaStateNotifier mWifiStaStateNotifier;
    mWifiStaStateNotifier = new WifiStaStateNotifier(wifiLooper, this);

    public WifiStaStateNotifier getWifiStaStateNotifier() {
        return mWifiStaStateNotifier;
    }
}

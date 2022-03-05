/*
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package org.statix.mediatek.override;

import com.android.server.wifi.ActiveModeWarden;
import statix.mediatek.override.IWifiManagerOverride;

public class ActiveModeWardenOverride extends ActiveModeWarden {

    public void registerStaEventCallback() {}
    public void unregisterStaEventCallback() {}
}

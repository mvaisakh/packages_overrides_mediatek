/*
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package statix.mediatek.override;

import statix.mediatek.override.IStaStateCallback;

interface IWifiManagerOverride
{
    void registerStaStateCallback(in IBinder binder, in IStaStateCallback callback, int callbackIdentifier);

    void unregisterStaStateCallback(int callbackIdentifier);
}

/*
 * Copyright (C) 2015 The Android Open Source Project
 * Copyright (C) 2022 StatiX
 * SPDX-License-Identifer: Apache-2.0
 */

package org.statix.mediatek.override;

import com.android.internal.telephony.metrics.TelephonyMetrics;

public class TelephonyMetricsOverride extends TelephonyMetrics {
   /**
     * Write Send SMS event (backwards-compatible method for R and earlier IMS implementations)
     *
     * @param phoneId Phone id
     * @param rilSerial RIL request serial number
     * @param tech SMS RAT
     * @param format SMS format. Either {@link SmsMessage#FORMAT_3GPP} or
     *         {@link SmsMessage#FORMAT_3GPP2}.
     */
    public void writeRilSendSms(int phoneId, int rilSerial, int tech, int format) {
    	writeRilSendSms(phoneId, rilSerial, tech, format, 0);
    }

}

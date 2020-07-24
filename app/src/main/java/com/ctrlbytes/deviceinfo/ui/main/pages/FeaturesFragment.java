/*
 * MIT License
 *
 * Copyright (c) 2020 CtrlBytes Technologies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.ctrlbytes.deviceinfo.ui.main.pages;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.ctrlbytes.codekit.utils.YLog;
import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import androidx.annotation.Nullable;

public class FeaturesFragment extends InfoFragment {

    private static final String LOG_TAG = YLog.createLogTag("FeaturesFragment");

    PackageManager mPackageManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPackageManager = requireContext().getPackageManager();

        add(R.string.features_wifi, checkFeature(PackageManager.FEATURE_WIFI));
        add(R.string.features_wifi_direct, checkFeature(PackageManager.FEATURE_WIFI_DIRECT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            add(R.string.features_wifi_aware, checkFeature(PackageManager.FEATURE_WIFI_AWARE));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            add(R.string.features_wifi_passpoint, checkFeature(PackageManager.FEATURE_WIFI_PASSPOINT));
        }
        add(R.string.features_bluetooth, checkFeature(PackageManager.FEATURE_BLUETOOTH));
        add(R.string.features_bluetooth_le, checkFeature(PackageManager.FEATURE_BLUETOOTH_LE));

        add(R.string.features_camera, checkFeature(PackageManager.FEATURE_CAMERA));
        add(R.string.features_camera_autofocus, checkFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS));
        add(R.string.features_camera_external, checkFeature(PackageManager.FEATURE_CAMERA_EXTERNAL));
        add(R.string.features_camera_flash, checkFeature(PackageManager.FEATURE_CAMERA_FLASH));
        add(R.string.features_camera_level, checkFeature(PackageManager.FEATURE_CAMERA_LEVEL_FULL));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            add(R.string.features_camera_ar, checkFeature(PackageManager.FEATURE_CAMERA_AR));
        }

        add(R.string.feature_consumer_ir, checkFeature(PackageManager.FEATURE_CONSUMER_IR));

        add(R.string.feature_location, checkFeature(PackageManager.FEATURE_LOCATION));
        add(R.string.feature_location_gps, checkFeature(PackageManager.FEATURE_LOCATION_GPS));
        add(R.string.feature_location_network, checkFeature(PackageManager.FEATURE_LOCATION_NETWORK));

        add(R.string.feature_microphone, checkFeature(PackageManager.FEATURE_MICROPHONE));

        add(R.string.feature_nfc, checkFeature(PackageManager.FEATURE_NFC));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            add(R.string.feature_nfc_beam, checkFeature(PackageManager.FEATURE_NFC_BEAM));
        }

        add(R.string.feature_usb_host, checkFeature(PackageManager.FEATURE_USB_HOST));
        add(R.string.feature_usb_accesseries, checkFeature(PackageManager.FEATURE_USB_ACCESSORY));

        add(R.string.feature_print, checkFeature(PackageManager.FEATURE_PRINTING));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            add(R.string.feature_pip, checkFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE));
            add(R.string.feature_ethernet, checkFeature(PackageManager.FEATURE_ETHERNET));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            add(R.string.feature_midi, checkFeature(PackageManager.FEATURE_MIDI));
        }
        add(R.string.feature_gamepad, checkFeature(PackageManager.FEATURE_GAMEPAD));

        FeatureInfo[] features = mPackageManager.getSystemAvailableFeatures();
        for (FeatureInfo info : features) {
            if (info != null && info.name != null) {
                YLog.d(LOG_TAG, "onActivityCreated: " + info.name);
            }
        }
    }

    private int checkFeature(String feature) {
        return mPackageManager.hasSystemFeature(feature) ? R.string.available : R.string.unavailable;
    }
}

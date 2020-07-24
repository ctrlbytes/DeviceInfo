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
import android.os.Bundle;

import com.ctrlbytes.codekit.utils.YLog;
import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import androidx.annotation.Nullable;

import static android.content.pm.PackageManager.FEATURE_AUDIO_LOW_LATENCY;
import static android.content.pm.PackageManager.FEATURE_AUDIO_OUTPUT;
import static android.content.pm.PackageManager.FEATURE_AUDIO_PRO;
import static android.content.pm.PackageManager.FEATURE_BLUETOOTH;
import static android.content.pm.PackageManager.FEATURE_BLUETOOTH_LE;
import static android.content.pm.PackageManager.FEATURE_CAMERA;
import static android.content.pm.PackageManager.FEATURE_CAMERA_AR;
import static android.content.pm.PackageManager.FEATURE_CAMERA_AUTOFOCUS;
import static android.content.pm.PackageManager.FEATURE_CAMERA_EXTERNAL;
import static android.content.pm.PackageManager.FEATURE_CAMERA_FLASH;
import static android.content.pm.PackageManager.FEATURE_CAMERA_FRONT;
import static android.content.pm.PackageManager.FEATURE_CAMERA_LEVEL_FULL;
import static android.content.pm.PackageManager.FEATURE_CONSUMER_IR;
import static android.content.pm.PackageManager.FEATURE_ETHERNET;
import static android.content.pm.PackageManager.FEATURE_GAMEPAD;
import static android.content.pm.PackageManager.FEATURE_LOCATION;
import static android.content.pm.PackageManager.FEATURE_LOCATION_GPS;
import static android.content.pm.PackageManager.FEATURE_LOCATION_NETWORK;
import static android.content.pm.PackageManager.FEATURE_MICROPHONE;
import static android.content.pm.PackageManager.FEATURE_MIDI;
import static android.content.pm.PackageManager.FEATURE_NFC;
import static android.content.pm.PackageManager.FEATURE_NFC_BEAM;
import static android.content.pm.PackageManager.FEATURE_PICTURE_IN_PICTURE;
import static android.content.pm.PackageManager.FEATURE_PRINTING;
import static android.content.pm.PackageManager.FEATURE_USB_ACCESSORY;
import static android.content.pm.PackageManager.FEATURE_USB_HOST;
import static android.content.pm.PackageManager.FEATURE_WIFI;
import static android.content.pm.PackageManager.FEATURE_WIFI_AWARE;
import static android.content.pm.PackageManager.FEATURE_WIFI_DIRECT;
import static android.content.pm.PackageManager.FEATURE_WIFI_PASSPOINT;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.N;
import static android.os.Build.VERSION_CODES.O;
import static android.os.Build.VERSION_CODES.O_MR1;
import static android.os.Build.VERSION_CODES.P;
import static android.os.Build.VERSION_CODES.Q;

public class FeaturesFragment extends InfoFragment {

    private static final String LOG_TAG = YLog.createLogTag("FeaturesFragment");

    PackageManager mPackageManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPackageManager = requireContext().getPackageManager();

        add(R.string.features_wifi, checkFeature(FEATURE_WIFI));
        add(R.string.features_wifi_direct, checkFeature(FEATURE_WIFI_DIRECT));
        add(R.string.features_wifi_aware, SDK_INT >= O ? checkFeature(FEATURE_WIFI_AWARE) : R.string.not_supported);
        add(R.string.features_wifi_passpoint, SDK_INT >= O_MR1 ? checkFeature(FEATURE_WIFI_PASSPOINT) : R.string.not_supported);
        add(R.string.features_bluetooth, checkFeature(FEATURE_BLUETOOTH));
        add(R.string.features_bluetooth_le, checkFeature(FEATURE_BLUETOOTH_LE));

        add(R.string.features_camera, checkFeature(FEATURE_CAMERA));
        add(R.string.features_camera_front, checkFeature(FEATURE_CAMERA_FRONT));
        add(R.string.features_camera_autofocus, checkFeature(FEATURE_CAMERA_AUTOFOCUS));
        add(R.string.features_camera_external, checkFeature(FEATURE_CAMERA_EXTERNAL));
        add(R.string.features_camera_flash, checkFeature(FEATURE_CAMERA_FLASH));
        add(R.string.features_camera_level, checkFeature(FEATURE_CAMERA_LEVEL_FULL));
        add(R.string.features_camera_ar, SDK_INT >= P ? checkFeature(FEATURE_CAMERA_AR) : R.string.not_supported);

        add(R.string.features_consumer_ir, checkFeature(FEATURE_CONSUMER_IR));

        add(R.string.features_location, checkFeature(FEATURE_LOCATION));
        add(R.string.features_location_gps, checkFeature(FEATURE_LOCATION_GPS));
        add(R.string.features_location_network, checkFeature(FEATURE_LOCATION_NETWORK));

        add(R.string.features_audio_output, checkFeature(FEATURE_AUDIO_OUTPUT));
        add(R.string.features_audio_low_latency, checkFeature(FEATURE_AUDIO_LOW_LATENCY));
        add(R.string.features_audio_pro, SDK_INT >= M ? checkFeature(FEATURE_AUDIO_PRO) : R.string.not_supported);
        add(R.string.features_microphone, checkFeature(FEATURE_MICROPHONE));

        add(R.string.features_nfc, checkFeature(FEATURE_NFC));
        add(R.string.features_nfc_beam, SDK_INT >= Q ? checkFeature(FEATURE_NFC_BEAM) : R.string.not_supported);

        add(R.string.features_usb_host, checkFeature(FEATURE_USB_HOST));
        add(R.string.features_usb_accessories, checkFeature(FEATURE_USB_ACCESSORY));

        add(R.string.features_print, checkFeature(FEATURE_PRINTING));
        add(R.string.features_pip, SDK_INT >= N ? checkFeature(FEATURE_PICTURE_IN_PICTURE) : R.string.not_supported);
        add(R.string.features_ethernet, SDK_INT >= N ? checkFeature(FEATURE_ETHERNET) : R.string.not_supported);
        add(R.string.features_midi, SDK_INT >= M ? checkFeature(FEATURE_MIDI) : R.string.not_supported);

        add(R.string.features_gamepad, checkFeature(FEATURE_GAMEPAD));

        FeatureInfo[] features = mPackageManager.getSystemAvailableFeatures();
        for (FeatureInfo info : features) {
            if (info != null && info.name != null) {
                YLog.d(LOG_TAG, "onActivityCreated: " + info.name);
            }
        }
    }

    private int checkFeature(String feature) {
        return mPackageManager.hasSystemFeature(feature) ? R.string.available : R.string.not_supported;
    }
}

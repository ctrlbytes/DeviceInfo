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
package com.ctrlbytes.deviceinfo.ui.main.pages

import android.content.pm.PackageManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import com.ctrlbytes.deviceinfo.R
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment

class FeaturesFragment : InfoFragment() {
    var mPackageManager: PackageManager? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPackageManager = requireContext().packageManager
        add(R.string.features_wifi, checkFeature(PackageManager.FEATURE_WIFI))
        add(R.string.features_wifi_direct, checkFeature(PackageManager.FEATURE_WIFI_DIRECT))
        add(R.string.features_wifi_aware, if (VERSION.SDK_INT >= VERSION_CODES.O) checkFeature(PackageManager.FEATURE_WIFI_AWARE) else R.string.not_supported)
        add(R.string.features_wifi_passpoint, if (VERSION.SDK_INT >= VERSION_CODES.O_MR1) checkFeature(PackageManager.FEATURE_WIFI_PASSPOINT) else R.string.not_supported)
        add(R.string.features_bluetooth, checkFeature(PackageManager.FEATURE_BLUETOOTH))
        add(R.string.features_bluetooth_le, checkFeature(PackageManager.FEATURE_BLUETOOTH_LE))
        add(R.string.features_camera, checkFeature(PackageManager.FEATURE_CAMERA))
        add(R.string.features_camera_front, checkFeature(PackageManager.FEATURE_CAMERA_FRONT))
        add(R.string.features_camera_autofocus, checkFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS))
        add(R.string.features_camera_external, checkFeature(PackageManager.FEATURE_CAMERA_EXTERNAL))
        add(R.string.features_camera_flash, checkFeature(PackageManager.FEATURE_CAMERA_FLASH))
        add(R.string.features_camera_level, checkFeature(PackageManager.FEATURE_CAMERA_LEVEL_FULL))
        add(R.string.features_camera_ar, if (VERSION.SDK_INT >= VERSION_CODES.P) checkFeature(PackageManager.FEATURE_CAMERA_AR) else R.string.not_supported)
        add(R.string.features_consumer_ir, checkFeature(PackageManager.FEATURE_CONSUMER_IR))
        add(R.string.features_location, checkFeature(PackageManager.FEATURE_LOCATION))
        add(R.string.features_location_gps, checkFeature(PackageManager.FEATURE_LOCATION_GPS))
        add(R.string.features_location_network, checkFeature(PackageManager.FEATURE_LOCATION_NETWORK))
        add(R.string.features_audio_output, checkFeature(PackageManager.FEATURE_AUDIO_OUTPUT))
        add(R.string.features_audio_low_latency, checkFeature(PackageManager.FEATURE_AUDIO_LOW_LATENCY))
        add(R.string.features_audio_pro, if (VERSION.SDK_INT >= VERSION_CODES.M) checkFeature(PackageManager.FEATURE_AUDIO_PRO) else R.string.not_supported)
        add(R.string.features_microphone, checkFeature(PackageManager.FEATURE_MICROPHONE))
        add(R.string.features_nfc, checkFeature(PackageManager.FEATURE_NFC))
        add(R.string.features_nfc_beam, if (VERSION.SDK_INT >= VERSION_CODES.Q) checkFeature(PackageManager.FEATURE_NFC_BEAM) else R.string.not_supported)
        add(R.string.features_usb_host, checkFeature(PackageManager.FEATURE_USB_HOST))
        add(R.string.features_usb_accessories, checkFeature(PackageManager.FEATURE_USB_ACCESSORY))
        add(R.string.features_print, checkFeature(PackageManager.FEATURE_PRINTING))
        add(R.string.features_pip, if (VERSION.SDK_INT >= VERSION_CODES.N) checkFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE) else R.string.not_supported)
        add(R.string.features_ethernet, if (VERSION.SDK_INT >= VERSION_CODES.N) checkFeature(PackageManager.FEATURE_ETHERNET) else R.string.not_supported)
        add(R.string.features_midi, if (VERSION.SDK_INT >= VERSION_CODES.M) checkFeature(PackageManager.FEATURE_MIDI) else R.string.not_supported)
        add(R.string.features_gamepad, checkFeature(PackageManager.FEATURE_GAMEPAD))
    }

    private fun checkFeature(feature: String): Int {
        return if (mPackageManager!!.hasSystemFeature(feature)) R.string.available else R.string.not_supported
    }
}
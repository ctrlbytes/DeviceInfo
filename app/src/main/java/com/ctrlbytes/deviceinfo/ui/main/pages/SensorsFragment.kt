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

class SensorsFragment : InfoFragment() {
    var mPackageManager: PackageManager? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPackageManager = requireContext().packageManager
        add(R.string.sensors_touch, checkFeature(PackageManager.FEATURE_TOUCHSCREEN))
        add(R.string.sensors_touch_multi, checkFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH))
        add(R.string.sensors_fake_touch, checkFeature(PackageManager.FEATURE_FAKETOUCH))
        add(R.string.sensors_fingerprint, if (VERSION.SDK_INT >= VERSION_CODES.M) checkFeature(PackageManager.FEATURE_FINGERPRINT) else R.string.not_supported)
        add(R.string.sensors_face, if (VERSION.SDK_INT >= VERSION_CODES.Q) checkFeature(PackageManager.FEATURE_FACE) else R.string.not_supported)
        add(R.string.sensors_iris, if (VERSION.SDK_INT >= VERSION_CODES.Q) checkFeature(PackageManager.FEATURE_IRIS) else R.string.not_supported)
        add(R.string.sensors_voice_recognizers, checkFeature("android.software.voice_recognizers"))
        add(R.string.sensors_accelerometer, checkFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER))
        add(R.string.sensors_barometer, checkFeature(PackageManager.FEATURE_SENSOR_BAROMETER))
        add(R.string.sensors_compass, checkFeature(PackageManager.FEATURE_SENSOR_COMPASS))
        add(R.string.sensors_gyroscope, checkFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE))
        add(R.string.sensors_light, checkFeature(PackageManager.FEATURE_SENSOR_LIGHT))
        add(R.string.sensors_proximity, checkFeature(PackageManager.FEATURE_SENSOR_PROXIMITY))
        add(R.string.sensors_stepcounter, checkFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER))
        add(R.string.sensors_stepdetector, checkFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR))
        add(R.string.sensors_heartrate, checkFeature(PackageManager.FEATURE_SENSOR_HEART_RATE))
        add(R.string.sensors_heartrate_ecg, checkFeature(PackageManager.FEATURE_SENSOR_HEART_RATE_ECG))
        add(R.string.sensors_ambient_temperature, checkFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE))
        add(R.string.sensors_relative_humidity, checkFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY))
        add(R.string.sensors_hifi_sensors, if (VERSION.SDK_INT >= VERSION_CODES.M) checkFeature(PackageManager.FEATURE_HIFI_SENSORS) else R.string.not_supported)
    }

    private fun checkFeature(feature: String): Int {
        return if (mPackageManager!!.hasSystemFeature(feature)) R.string.available else R.string.not_supported
    }
}
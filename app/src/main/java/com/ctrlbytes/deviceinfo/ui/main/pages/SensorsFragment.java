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

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import androidx.annotation.Nullable;

import static android.content.pm.PackageManager.FEATURE_FACE;
import static android.content.pm.PackageManager.FEATURE_FAKETOUCH;
import static android.content.pm.PackageManager.FEATURE_FINGERPRINT;
import static android.content.pm.PackageManager.FEATURE_HIFI_SENSORS;
import static android.content.pm.PackageManager.FEATURE_IRIS;
import static android.content.pm.PackageManager.FEATURE_SENSOR_ACCELEROMETER;
import static android.content.pm.PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE;
import static android.content.pm.PackageManager.FEATURE_SENSOR_BAROMETER;
import static android.content.pm.PackageManager.FEATURE_SENSOR_COMPASS;
import static android.content.pm.PackageManager.FEATURE_SENSOR_GYROSCOPE;
import static android.content.pm.PackageManager.FEATURE_SENSOR_HEART_RATE;
import static android.content.pm.PackageManager.FEATURE_SENSOR_HEART_RATE_ECG;
import static android.content.pm.PackageManager.FEATURE_SENSOR_LIGHT;
import static android.content.pm.PackageManager.FEATURE_SENSOR_PROXIMITY;
import static android.content.pm.PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY;
import static android.content.pm.PackageManager.FEATURE_SENSOR_STEP_COUNTER;
import static android.content.pm.PackageManager.FEATURE_SENSOR_STEP_DETECTOR;
import static android.content.pm.PackageManager.FEATURE_TOUCHSCREEN;
import static android.content.pm.PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.Q;

public class SensorsFragment extends InfoFragment {

    PackageManager mPackageManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPackageManager = requireContext().getPackageManager();

        add(R.string.sensors_touch, checkFeature(FEATURE_TOUCHSCREEN));
        add(R.string.sensors_touch_multi, checkFeature(FEATURE_TOUCHSCREEN_MULTITOUCH));
        add(R.string.sensors_fake_touch, checkFeature(FEATURE_FAKETOUCH));
        add(R.string.sensors_fingerprint, SDK_INT >= M ? checkFeature(FEATURE_FINGERPRINT) : R.string.not_supported);
        add(R.string.sensors_face, SDK_INT >= Q ? checkFeature(FEATURE_FACE) : R.string.not_supported);
        add(R.string.sensors_iris, SDK_INT >= Q ? checkFeature(FEATURE_IRIS) : R.string.not_supported);
        add(R.string.sensors_voice_recognizers, checkFeature("android.software.voice_recognizers"));
        add(R.string.sensors_accelerometer, checkFeature(FEATURE_SENSOR_ACCELEROMETER));
        add(R.string.sensors_barometer, checkFeature(FEATURE_SENSOR_BAROMETER));
        add(R.string.sensors_compass, checkFeature(FEATURE_SENSOR_COMPASS));
        add(R.string.sensors_gyroscope, checkFeature(FEATURE_SENSOR_GYROSCOPE));
        add(R.string.sensors_light, checkFeature(FEATURE_SENSOR_LIGHT));
        add(R.string.sensors_proximity, checkFeature(FEATURE_SENSOR_PROXIMITY));
        add(R.string.sensors_stepcounter, checkFeature(FEATURE_SENSOR_STEP_COUNTER));
        add(R.string.sensors_stepdetector, checkFeature(FEATURE_SENSOR_STEP_DETECTOR));
        add(R.string.sensors_heartrate, checkFeature(FEATURE_SENSOR_HEART_RATE));
        add(R.string.sensors_heartrate_ecg, checkFeature(FEATURE_SENSOR_HEART_RATE_ECG));
        add(R.string.sensors_ambient_temperature, checkFeature(FEATURE_SENSOR_AMBIENT_TEMPERATURE));
        add(R.string.sensors_relative_humidity, checkFeature(FEATURE_SENSOR_RELATIVE_HUMIDITY));
        add(R.string.sensors_hifi_sensors, SDK_INT >= M ? checkFeature(FEATURE_HIFI_SENSORS) : R.string.not_supported);
    }

    private int checkFeature(String feature) {
        return mPackageManager.hasSystemFeature(feature) ? R.string.available : R.string.not_supported;
    }
}

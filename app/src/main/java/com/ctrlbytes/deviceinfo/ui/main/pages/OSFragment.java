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

import android.os.Build;
import android.os.Bundle;

import com.ctrlbytes.codekit.utils.YLog;
import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import java.util.Arrays;

import androidx.annotation.Nullable;

import static android.os.Build.VERSION.BASE_OS;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION.SECURITY_PATCH;
import static android.os.Build.VERSION_CODES.M;

public class OSFragment extends InfoFragment {

    private static final String LOG_TAG = YLog.createLogTag("OSFragment");

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        YLog.d(LOG_TAG, "onActivityCreated: " + Build.VERSION.RELEASE);

        add(R.string.os_android_version, Build.VERSION.RELEASE);
        add(R.string.os_android_flavor, getOsFlavor());
        add(R.string.sdk_version, String.valueOf(SDK_INT));
        add(R.string.base_os, SDK_INT >= M ? BASE_OS : getString(R.string.unknown));
        add(R.string.security_patch, SDK_INT >= M ? SECURITY_PATCH : getString(R.string.unknown));
        add(R.string.build_id, Build.DISPLAY);
        if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            add(R.string.abis, Arrays.toString(Build.SUPPORTED_ABIS));
            if (Build.SUPPORTED_32_BIT_ABIS.length > 0) {
                add(R.string.abis_32, Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
            }
            if (Build.SUPPORTED_64_BIT_ABIS.length > 0) {
                add(R.string.abis_64, Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
            }
        }
    }

    private String getOsFlavor() {
        double release = Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)", "$1"));
        String codeName = "Unsupported";
        if (release >= 4.1 && release < 4.4) codeName = "Jelly Bean";
        else if (release < 5) codeName = "Kit Kat";
        else if (release < 6) codeName = "Lollipop";
        else if (release < 7) codeName = "Marshmallow";
        else if (release < 8) codeName = "Nougat";
        else if (release < 9) codeName = "Oreo";
        else if (release < 10) codeName = "Pie";
        else if (release < 11) codeName = "TBA";
        return codeName;
    }
}

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

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import java.util.Arrays;

import androidx.annotation.Nullable;

public class OSFragment extends InfoFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            add(R.string.base_os, Build.VERSION.BASE_OS);
            add(R.string.security_patch, Build.VERSION.SECURITY_PATCH);
        }
        add(R.string.sdk_version, String.valueOf(Build.VERSION.SDK_INT));
        add(R.string.build_id, Build.DISPLAY);
        add(R.string.abis, Arrays.toString(Build.SUPPORTED_ABIS));
        if (Build.SUPPORTED_32_BIT_ABIS.length > 0){
            add(R.string.abis_32, Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
        }
        if (Build.SUPPORTED_64_BIT_ABIS.length > 0){
            add(R.string.abis_64, Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
        }
    }
}

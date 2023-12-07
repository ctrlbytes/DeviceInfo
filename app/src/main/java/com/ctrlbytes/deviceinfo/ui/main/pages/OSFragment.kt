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

import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import com.ctrlbytes.deviceinfo.R
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment
import java.util.Arrays

class OSFragment : InfoFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add(R.string.os_android_version, VERSION.RELEASE)
        add(R.string.os_android_flavor, osFlavor)
        add(R.string.sdk_version, VERSION.SDK_INT.toString())
        add(R.string.base_os, if (VERSION.SDK_INT >= VERSION_CODES.M) VERSION.BASE_OS else getString(R.string.unknown))
        add(R.string.security_patch, if (VERSION.SDK_INT >= VERSION_CODES.M) VERSION.SECURITY_PATCH else getString(R.string.unknown))
        add(R.string.build_id, Build.DISPLAY)
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            add(R.string.abis, Arrays.toString(Build.SUPPORTED_ABIS))
            if (Build.SUPPORTED_32_BIT_ABIS.size > 0) {
                add(R.string.abis_32, Arrays.toString(Build.SUPPORTED_32_BIT_ABIS))
            }
            if (Build.SUPPORTED_64_BIT_ABIS.size > 0) {
                add(R.string.abis_64, Arrays.toString(Build.SUPPORTED_64_BIT_ABIS))
            }
        }
    }

    private val osFlavor: String
        private get() {
            val release = VERSION.RELEASE.replace("(\\d+[.]\\d+)(.*)".toRegex(), "$1").toDouble()
            var codeName = "Unsupported"
            if (release >= 4.1 && release < 4.4) codeName = "Jelly Bean" else if (release < 5) codeName = "Kit Kat" else if (release < 6) codeName = "Lollipop" else if (release < 7) codeName = "Marshmallow" else if (release < 8) codeName = "Nougat" else if (release < 9) codeName = "Oreo" else if (release < 10) codeName = "Pie" else if (release < 11) codeName = "TBA"
            return codeName
        }
}
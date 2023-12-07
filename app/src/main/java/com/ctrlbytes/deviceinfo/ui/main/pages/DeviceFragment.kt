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
import android.os.Bundle
import android.view.View
import com.ctrlbytes.deviceinfo.R
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment
import java.util.Locale


class DeviceFragment : InfoFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add(R.string.device, Build.DEVICE)
        add(R.string.device_model, Build.MODEL)
        add(R.string.device_brand, Build.BRAND)
        add(R.string.device_manufacturer, Build.MANUFACTURER)
        add(R.string.device_hardware, Build.HARDWARE)
        add(R.string.device_product, Build.PRODUCT)
        add(R.string.device_board, Build.BOARD)
        add(R.string.device_bootloader, Build.BOOTLOADER)
        add(R.string.device_host, Build.HOST)
        add(R.string.locale, Locale.getDefault().toString())
    }
}
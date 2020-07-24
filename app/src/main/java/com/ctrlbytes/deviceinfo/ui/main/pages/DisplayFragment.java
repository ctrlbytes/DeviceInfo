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

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import java.util.Locale;

import androidx.annotation.Nullable;

public class DisplayFragment extends InfoFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        add(R.string.display_width, String.valueOf(displayMetrics.widthPixels));
        add(R.string.display_height, String.valueOf(displayMetrics.heightPixels));
        add(R.string.display_width_dp, String.format(Locale.getDefault(), "%ddp", (int) (displayMetrics.widthPixels / displayMetrics.density)));
        add(R.string.display_height_dp, String.format(Locale.getDefault(), "%ddp", (int) (displayMetrics.heightPixels / displayMetrics.density)));
        add(R.string.display_density, String.valueOf(displayMetrics.density));
        add(R.string.display_density_dpi, String.format(Locale.getDefault(), "%ddp", displayMetrics.densityDpi));
    }
}

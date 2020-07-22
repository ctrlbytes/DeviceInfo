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

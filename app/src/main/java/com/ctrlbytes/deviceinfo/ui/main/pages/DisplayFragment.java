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
        add(R.string.display_width,
                String.format(Locale.getDefault(),
                        "%d (%sdp)",
                        displayMetrics.widthPixels,
                        (int) displayMetrics.widthPixels / displayMetrics.density));
        add(R.string.display_width,
                String.format(Locale.getDefault(),
                        "%d (%sdp)",
                        displayMetrics.heightPixels,
                        (int) displayMetrics.heightPixels / displayMetrics.density));
        add(R.string.display_density, String.format(Locale.getDefault(), "%s(%ddp)", displayMetrics.density, displayMetrics.densityDpi));
    }
}

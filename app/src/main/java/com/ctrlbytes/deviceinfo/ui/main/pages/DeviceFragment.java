package com.ctrlbytes.deviceinfo.ui.main.pages;

import android.os.Build;
import android.os.Bundle;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import java.util.Locale;

import androidx.annotation.Nullable;

import static android.os.Build.BOARD;

public class DeviceFragment extends InfoFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        add(R.string.device, Build.DEVICE);
        add(R.string.device_model, Build.MODEL);
        add(R.string.device_brand, Build.BRAND);
        add(R.string.device_manufacturer, Build.MANUFACTURER);
        add(R.string.device_hardware, Build.HARDWARE);
        add(R.string.device_product, Build.PRODUCT);
        add(R.string.device_board, BOARD);
        add(R.string.locale, Locale.getDefault().toString());
    }
}

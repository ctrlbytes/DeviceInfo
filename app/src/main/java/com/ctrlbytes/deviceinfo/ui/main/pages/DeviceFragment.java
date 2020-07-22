package com.ctrlbytes.deviceinfo.ui.main.pages;

import android.os.Build;
import android.os.Bundle;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.base.InfoFragment;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Build.BOARD;
import static android.os.Build.BOOTLOADER;
import static android.os.Build.HOST;
import static android.os.Build.SERIAL;
import static android.os.Build.getSerial;

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
        add(R.string.device_bootloader, BOOTLOADER);
        add(R.string.device_host, HOST);
        if (ActivityCompat.checkSelfPermission(requireContext(), READ_PHONE_STATE) == PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                add(R.string.device_serial, getSerial());
            } else {
                add(R.string.device_serial, SERIAL);
            }
        }
        add(R.string.locale, Locale.getDefault().toString());
    }
}

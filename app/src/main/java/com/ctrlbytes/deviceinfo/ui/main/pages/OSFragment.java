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
        add(R.string.abis_32, Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
        add(R.string.abis_64, Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));
    }
}

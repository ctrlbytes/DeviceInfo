package com.ctrlbytes.deviceinfo.ui.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.data.InfoItem;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Build.BOARD;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.rv_info_list)
    RecyclerView rvInfoList;

    @BindDimen(R.dimen.app_bar_elevation)
    int appBarElevation;
    InfoItemAdapter mInfoItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, sx, scrollY, osx, osy) -> appBar.setElevation(scrollY > 10 ? appBarElevation : 0));

        mInfoItemAdapter = new InfoItemAdapter();
        rvInfoList.setLayoutManager(new LinearLayoutManager(this));
        rvInfoList.setAdapter(mInfoItemAdapter);

        add(R.string.sdk_version, String.valueOf(Build.VERSION.SDK_INT));
        add(R.string.sdk_version, String.valueOf(Build.VERSION.SDK));
        add(R.string.locale, Locale.getDefault().toString());

        Log.d("DeviceInfo", Locale.getDefault().toLanguageTag());

        add(R.string.device, Build.DEVICE);
        add(R.string.device_model, Build.MODEL);
        add(R.string.device_brand, Build.BRAND);
        add(R.string.device_manufacturer, Build.MANUFACTURER);
        add(R.string.device_hardware, Build.HARDWARE);
        add(R.string.device_product, Build.PRODUCT);
        add(R.string.device_board, BOARD);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            add(R.string.base_os, Build.VERSION.BASE_OS);
            add(R.string.security_patch, Build.VERSION.SECURITY_PATCH);
        }

        add(R.string.build_id, Build.DISPLAY);

        add(R.string.abis, Arrays.toString(Build.SUPPORTED_ABIS));
        add(R.string.abis_32, Arrays.toString(Build.SUPPORTED_32_BIT_ABIS));
        add(R.string.abis_64, Arrays.toString(Build.SUPPORTED_64_BIT_ABIS));

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        add(R.string.screen_width,
                String.format(Locale.getDefault(),
                        "%d (%sdp)",
                        displayMetrics.widthPixels,
                        (int) displayMetrics.widthPixels / displayMetrics.density));
        add(R.string.screen_width,
                String.format(Locale.getDefault(),
                        "%d (%sdp)",
                        displayMetrics.heightPixels,
                        (int) displayMetrics.heightPixels / displayMetrics.density));
        add(R.string.screen_density, String.format(Locale.getDefault(), "%s(%ddp)", displayMetrics.density, displayMetrics.densityDpi));
    }

    private void add(@StringRes int title, String value) {
        if (value.isEmpty()) return;
        mInfoItemAdapter.add(new InfoItem(title, value));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class InfoItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<InfoItem> mInfoItems = new ArrayList<>();

        public void add(InfoItem item) {
            mInfoItems.add(item);
            notifyItemInserted(mInfoItems.size() - 1);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new InfoItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.info_listitem, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((InfoItemView) holder).bind(mInfoItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mInfoItems.size();
        }

        public static class InfoItemView extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_info_title)
            TextView tvInfoTitle;
            @BindView(R.id.tv_info_value)
            TextView tvInfoValue;

            public InfoItemView(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void bind(InfoItem infoItem) {
                tvInfoTitle.setText(infoItem.getTitle());
                tvInfoValue.setText(infoItem.getValue());
                itemView.setOnClickListener(v -> {
                    ClipboardManager clipboard = (ClipboardManager) itemView.getContext().getSystemService(CLIPBOARD_SERVICE);
                    if (clipboard != null) {
                        ClipData clip = ClipData.newPlainText("label", infoItem.getValue());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(v.getContext(), R.string.copied, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
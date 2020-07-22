package com.ctrlbytes.deviceinfo.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.ui.main.pages.DeviceFragment;
import com.ctrlbytes.deviceinfo.ui.main.pages.DisplayFragment;
import com.ctrlbytes.deviceinfo.ui.main.pages.OSFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    InfoPagesAdapter mInfoPagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mInfoPagesAdapter = new InfoPagesAdapter(this);
        viewPager.setAdapter(mInfoPagesAdapter);
        tabs.setupWithViewPager(viewPager);
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

    public static class InfoPagesAdapter extends FragmentStatePagerAdapter {

        WeakReference<MainActivity> mMainActivityWeakReference;

        public InfoPagesAdapter(MainActivity activity) {
            super(activity.getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mMainActivityWeakReference = new WeakReference<>(activity);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DeviceFragment();
                case 1:
                    return new DisplayFragment();
                case 2:
                    return new OSFragment();
                default:
                    throw new NullPointerException();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mMainActivityWeakReference.get().getString(getTabTitle(position));
        }

        public int getTabTitle(int pos) {
            switch (pos) {
                case 0:
                    return R.string.tab_device;
                case 1:
                    return R.string.tab_display;
                case 2:
                    return R.string.tab_os;
                default:
                    throw new NullPointerException();
            }
        }
    }

}
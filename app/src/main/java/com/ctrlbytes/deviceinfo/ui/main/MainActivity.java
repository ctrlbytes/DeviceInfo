package com.ctrlbytes.deviceinfo.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ctrlbytes.deviceinfo.R;
import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindDimen(R.dimen.app_bar_elevation)
    int appBarElevation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, sx, scrollY, osx, osy) -> appBar.setElevation(scrollY > 10 ? appBarElevation : 0));
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
}
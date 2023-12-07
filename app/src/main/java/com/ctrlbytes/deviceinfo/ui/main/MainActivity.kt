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
package com.ctrlbytes.deviceinfo.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ctrlbytes.deviceinfo.R
import com.ctrlbytes.deviceinfo.databinding.ActivityMainBinding
import com.ctrlbytes.deviceinfo.ui.main.pages.DeviceFragment
import com.ctrlbytes.deviceinfo.ui.main.pages.DisplayFragment
import com.ctrlbytes.deviceinfo.ui.main.pages.FeaturesFragment
import com.ctrlbytes.deviceinfo.ui.main.pages.OSFragment
import com.ctrlbytes.deviceinfo.ui.main.pages.SensorsFragment
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var mInfoPagesAdapter: InfoPagesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 212)
        } else {
            loadPages()
        }
        loadPages()
        
    }

    private fun loadPages() {
        mInfoPagesAdapter = InfoPagesAdapter(this)
        binding.viewPager.adapter = mInfoPagesAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        loadPages()
    }

    class InfoPagesAdapter(activity: MainActivity) : FragmentStatePagerAdapter(activity.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private var mMainActivityWeakReference: WeakReference<MainActivity>

        init {
            mMainActivityWeakReference = WeakReference(activity)
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> DeviceFragment()
                1 -> DisplayFragment()
                2 -> OSFragment()
                3 -> FeaturesFragment()
                4 -> SensorsFragment()
                else -> throw NullPointerException()
            }
        }

        override fun getCount(): Int {
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mMainActivityWeakReference.get()!!.getString(getTabTitle(position))
        }

        fun getTabTitle(pos: Int): Int {
            return when (pos) {
                0 -> R.string.tab_device
                1 -> R.string.tab_display
                2 -> R.string.tab_os
                3 -> R.string.tab_features
                4 -> R.string.tab_sensors
                else -> throw NullPointerException()
            }
        }
    }
}
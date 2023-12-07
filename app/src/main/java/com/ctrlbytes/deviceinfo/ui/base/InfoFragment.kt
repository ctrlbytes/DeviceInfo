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
package com.ctrlbytes.deviceinfo.ui.base

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctrlbytes.codekit.ktx.toast
import com.ctrlbytes.deviceinfo.R
import com.ctrlbytes.deviceinfo.data.InfoItem
import com.ctrlbytes.deviceinfo.databinding.InfoFragmentBinding
import com.ctrlbytes.deviceinfo.databinding.InfoListitemBinding

abstract class InfoFragment : Fragment() {
    private lateinit var binding: InfoFragmentBinding
    private lateinit var mInfoItemAdapter: InfoItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = InfoFragmentBinding.bind(view)
        mInfoItemAdapter = InfoItemAdapter(object : InfoItemClickListener {
            override fun handleClick(item: InfoItem) {
                val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", item.value)
                clipboard.setPrimaryClip(clip)
                toast(R.string.copied)
            }
        })

        binding.rvInfoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInfoList.adapter = mInfoItemAdapter
        binding.rvInfoList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    protected fun add(@StringRes title: Int, value: String) {
        var value = value
        if (value.isEmpty()) value = getString(R.string.unknown)
        mInfoItemAdapter.add(InfoItem(title, value))
    }

    protected fun add(@StringRes title: Int, value: Int) {
        mInfoItemAdapter.add(InfoItem(title, getString(value)))
    }

    class InfoItemAdapter(private val listener: InfoItemClickListener) : RecyclerView.Adapter<InfoItemView>() {
        private val list: ArrayList<InfoItem> = arrayListOf();

        fun add(item: InfoItem) {
            list.add(item)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoItemView {
            val binding  = InfoListitemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            )
            return InfoItemView(binding, listener)
         }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: InfoItemView, position: Int) {
            holder.onBind(list.get(position))
        }

    }
    class InfoItemView(private val binding: InfoListitemBinding, val listener: InfoItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: InfoItem) {
            binding.root.setOnClickListener {
                listener.handleClick(item)
            }
            binding.tvInfoTitle.setText(item.title)
            binding.tvInfoValue.text = item.value
        }
    }


    interface InfoItemClickListener {
        fun handleClick(item: InfoItem)
    }
}
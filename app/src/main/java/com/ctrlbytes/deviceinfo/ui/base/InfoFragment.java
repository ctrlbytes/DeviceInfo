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

package com.ctrlbytes.deviceinfo.ui.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ctrlbytes.codekit.ui.recyclerview.RViewAdapter;
import com.ctrlbytes.codekit.ui.recyclerview.RViewHolder;
import com.ctrlbytes.codekit.ui.recyclerview.RViewListener;
import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.data.InfoItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.CLIPBOARD_SERVICE;

public abstract class InfoFragment extends Fragment {

    @BindView(R.id.rv_info_list)
    RecyclerView rvInfoList;

    InfoItemAdapter mInfoItemAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mInfoItemAdapter = new InfoItemAdapter();
        mInfoItemAdapter.setRViewListener(new RViewListener<InfoItem>() {
            @Override
            public void onItemClick(int position, InfoItem item, View mView) {

            }

            @Override
            public void onItemLongClick(int position, InfoItem item, View mView) {
                ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(CLIPBOARD_SERVICE);
                if (clipboard != null) {
                    ClipData clip = ClipData.newPlainText("label", item.getValue());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(requireContext(), R.string.copied, Toast.LENGTH_SHORT).show();
                }
            }
        });
        rvInfoList.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvInfoList.setAdapter(mInfoItemAdapter);
        rvInfoList.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    protected void add(@StringRes int title, String value) {
        if (value.isEmpty()) value = getString(R.string.unknown);
        mInfoItemAdapter.add(new InfoItem(title, value));
    }

    protected void add(@StringRes int title, int value) {
        mInfoItemAdapter.add(new InfoItem(title, getString(value)));
    }

    public static class InfoItemAdapter extends RViewAdapter<InfoItem, InfoItemAdapter.InfoItemView> {

        @NonNull
        @Override
        public InfoItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new InfoItemView(parent, R.layout.info_listitem, this);
        }

        public static class InfoItemView extends RViewHolder<InfoItem> {

            @BindView(R.id.tv_info_title)
            TextView tvInfoTitle;
            @BindView(R.id.tv_info_value)
            TextView tvInfoValue;

            public InfoItemView(ViewGroup parent, int resId, Callback callback) {
                super(parent, resId, callback);
                ButterKnife.bind(this, itemView);
            }

            @Override
            public void onBind(InfoItem item) {
                tvInfoTitle.setText(item.getTitle());
                tvInfoValue.setText(item.getValue());
            }
        }
    }

}

package com.ctrlbytes.deviceinfo.ui.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ctrlbytes.deviceinfo.R;
import com.ctrlbytes.deviceinfo.data.InfoItem;

import java.util.ArrayList;
import java.util.List;

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
        rvInfoList.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvInfoList.setAdapter(mInfoItemAdapter);
        rvInfoList.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    protected void add(@StringRes int title, String value) {
        if (value.isEmpty()) return;
        mInfoItemAdapter.add(new InfoItem(title, value));
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

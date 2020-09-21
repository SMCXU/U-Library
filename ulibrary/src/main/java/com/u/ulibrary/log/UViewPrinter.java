package com.u.ulibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.u.ulibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 给我一行代码，还你十个BUG
 *
 * @author：Mr.U 创建时间：2020/9/21
 * 更改时间：2020/9/21
 * 版本号：1
 * 文件描述：
 */
public class UViewPrinter implements ULogPrinter {
    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private UViewPrinterProvider provider;

    public UViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        provider = new UViewPrinterProvider(rootView, recyclerView);

    }

    public UViewPrinterProvider getViewProvider() {
        return provider;
    }

    @Override
    public void print(@NonNull ULogConfig config, int level, String tag, @NonNull String printString) {
        //添加数据
        adapter.addItem(new ULogMo(System.currentTimeMillis(), level, tag, printString));
        //recyclerView滚动到指定位置
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
        private List<ULogMo> logs = new ArrayList<>();
        private LayoutInflater inflater;

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(ULogMo logItem) {
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        private static class LogViewHolder extends RecyclerView.ViewHolder {
            TextView tagView;
            TextView messageView;

            public LogViewHolder(@NonNull View itemView) {
                super(itemView);
                tagView = itemView.findViewById(R.id.tv_1);
                messageView = itemView.findViewById(R.id.tv_2);
            }
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_ulog, parent, false);
            return new LogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            ULogMo logItem = logs.get(position);
            int color = getHighLightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);

        }

        /**
         * 根据log级别获取不同的高亮颜色
         */
        private int getHighLightColor(int level) {
            int highlight;
            switch (level) {
                case ULogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case ULogType.D:
                    highlight = 0xffffffff;
                    break;
                case ULogType.I:
                    highlight = 0xff6a8759;
                    break;
                case ULogType.W:
                    highlight = 0xffbbb529;
                    break;
                case ULogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }
}

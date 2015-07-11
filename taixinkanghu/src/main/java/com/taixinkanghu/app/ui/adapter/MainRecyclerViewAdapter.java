package com.taixinkanghu.app.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wjy.taixinkanghu.R;
import com.taixinkanghu.app.ui.data.MainButtonData;

/**
 * Created by Administrator on 2015/6/7.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int ID_ZCD_ZHG = 1;    //主菜单-找护工
    private static final int ID_ZCD_TXCP = 2;   //主菜单-泰心产品

    private MainButtonData[] data = new MainButtonData[]{};


    interface OnItemClickListener {

        void onClick(View v);

    }

    interface OnItemLongClickListener {

        void onLongClick(View v);

    }


    private OnItemClickListener onClickListener;

    private OnItemLongClickListener onLongClickListener;

    public MainRecyclerViewAdapter(MainButtonData[] data) {

        this(data, null, null);
    }

    public MainRecyclerViewAdapter(MainButtonData[] data, OnItemClickListener onClickListener) {

        this(data, onClickListener, null);

    }

    public MainRecyclerViewAdapter(MainButtonData[] data, OnItemClickListener onClickListener,

                                   OnItemLongClickListener onLongClickListener) {

        this.data = data;

        this.onClickListener = onClickListener;

        this.onLongClickListener = onLongClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private Button ib;
        private ImageView ib_line;

        public Button getIb() {
            return ib;
        }

        public ImageView getIb_line() {
            return ib_line;
        }

        public ViewHolder(View itemLayoutView, final OnItemClickListener onClickListener, final OnItemLongClickListener onLongClickListener) {
            super(itemLayoutView);

            ib = (Button) itemLayoutView.findViewById(R.id.ib_text);
            ib_line = (ImageView) itemLayoutView.findViewById(R.id.ib_line);

            ib.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //在监听器不为空的时候，进行回调
                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }
                }
            });
            itemLayoutView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    //在监听器不为空的时候，进行回调
                    if (onLongClickListener != null) {
                        onLongClickListener.onLongClick(v);
                    }
                    //返回true，消费掉该事件，阻止其继续传递
                    return true;
                }
            });
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_list, null),onClickListener,onLongClickListener);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vh = (ViewHolder) viewHolder;

        MainButtonData cd = data[i];
        vh.getIb().setText(cd.text);
        vh.getIb().setId(cd.id);
        vh.getIb_line();

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

}

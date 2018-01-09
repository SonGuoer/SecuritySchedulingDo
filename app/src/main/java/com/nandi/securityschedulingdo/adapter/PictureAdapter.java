package com.nandi.securityschedulingdo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nandi.securityschedulingdo.R;

import java.util.List;

/**
 * @author qingsong  on 2017/10/26.
 */


public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {
    private Context mContext;
    public PictureAdapter.OnItemClickListener mOnItemClickListener;

    public PictureAdapter(Context context) {
        mContext = context;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(PictureAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PictureAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_picture, parent, false);
        PictureAdapter.MyViewHolder holderA = new PictureAdapter.MyViewHolder(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(PictureAdapter.MyViewHolder holder, final int position) {

        if (mOnItemClickListener != null) {
            holder.picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.iv_picture);
        }
    }
}

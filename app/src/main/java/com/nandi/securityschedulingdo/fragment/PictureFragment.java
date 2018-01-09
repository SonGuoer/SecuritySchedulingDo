package com.nandi.securityschedulingdo.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.activity.MessageActivity;
import com.nandi.securityschedulingdo.adapter.PictureAdapter;

/**
 * Created by qingsong on 2018/1/8.
 */

public class PictureFragment extends Fragment {

    private SwipeRefreshLayout refreshShow;
    private RecyclerView recyclerView;
    private PictureAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        refreshShow = view.findViewById(R.id.refresh_show);
        initView();
        refresh();
        return view;
    }

    /**
     * 刷新当前界面
     */
    private void refresh() {
        refreshShow.setColorSchemeResources(R.color.colorPrimary);
        refreshShow.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshShow.setRefreshing(false);
            }
        });
    }

    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_enlarge_photo, null);
                PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_image);
                Glide.with(getActivity()).load("")
                        .placeholder(R.drawable.downloading).error(R.drawable.download_pass).into(photoView);
                new AlertDialog.Builder(getActivity(), R.style.Transparent)
                        .setView(view)
                        .show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}

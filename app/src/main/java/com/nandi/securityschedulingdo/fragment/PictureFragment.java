package com.nandi.securityschedulingdo.fragment;

import android.app.AlertDialog;
import android.app.PictureInPictureParams;
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
import com.google.gson.Gson;
import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.activity.MessageActivity;
import com.nandi.securityschedulingdo.adapter.PictureAdapter;
import com.nandi.securityschedulingdo.bean.PictureInfo;
import com.nandi.securityschedulingdo.utils.JsonFormat;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 显示图片页面
 * Created by qingsong on 2018/1/8.
 */

public class PictureFragment extends Fragment {

    private PictureAdapter ad_panorama;
    private PictureAdapter ad_plan;
    private PictureAdapter ad_feature;
    private PictureAdapter ad_profile;

    private RecyclerView rv_panorama;
    private RecyclerView rv_plan;
    private RecyclerView rv_feature;
    private RecyclerView rv_profile;

    private String[] imgUrlArray;
    private List<PictureInfo> pictureInfos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        rv_profile = view.findViewById(R.id.rv_profile);
        rv_panorama = view.findViewById(R.id.rv_panorama);
        rv_feature = view.findViewById(R.id.rv_feature);
        rv_plan = view.findViewById(R.id.rv_plan);
        rv_feature.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv_panorama.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv_plan.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv_profile.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        picRequest();
        return view;
    }

    private void picRequest() {
        OkHttpUtils.get().url(getString(R.string.base_url) + "/tabDisastersInfo/selectFeaPicById/5002322010050101")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("response = " + response);
                        JSONObject jo = null;
                        try {
                            jo = new JSONObject(response);
                            JSONObject meta = new JSONObject(jo.optString("meta"));
                            Boolean success = meta.optBoolean("success");
                            if (success) {
                                String data = jo.getString("data");
                                pictureInfos.addAll(JsonFormat.stringToList(data, PictureInfo.class));
                                for (PictureInfo info : pictureInfos) {
                                    if ("1".equals(info.getType())) {
                                        // 全景图
                                        String[] array1 = info.getUrl().split(",");
                                        ad_panorama = new PictureAdapter(getActivity(), array1);
                                        initView(ad_panorama, array1);
                                        rv_panorama.setAdapter(ad_panorama);

                                    } else if ("2".equals(info.getType())) {
                                        // 平面图
                                        String[] array2 = info.getUrl().split(",");
                                        ad_plan = new PictureAdapter(getActivity(), array2);
                                        initView(ad_plan, array2);
                                        rv_plan.setAdapter(ad_plan);

                                    } else if ("3".equals(info.getType())) {
                                        // 特征图
                                        String[] array3 = info.getUrl().split(",");
                                        ad_feature = new PictureAdapter(getActivity(), array3);
                                        initView(ad_feature, array3);
                                        rv_feature.setAdapter(ad_feature);

                                    } else if ("4".equals(info.getType())) {
                                        // 剖面图
                                        String[] array4 = info.getUrl().split(",");
                                        ad_profile = new PictureAdapter(getActivity(), array4);
                                        initView(ad_profile, array4);
                                        rv_profile.setAdapter(ad_profile);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initView(PictureAdapter adapter, final String[] array) {
        // 点击放大图片
        adapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_enlarge_photo, null);
                PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_image);
                Glide.with(getActivity()).load(getActivity().getString(R.string.base_url) + "/down/img?path="
                        + getActivity().getString(R.string.picPath) + "uploadImg/" + array[position])
                        .placeholder(R.drawable.downloading).error(R.drawable.download_pass).into(photoView);
                new AlertDialog.Builder(getActivity(), R.style.Transparent)
                        .setView(view)
                        .show();
            }
        });
    }


}

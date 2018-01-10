package com.nandi.securityschedulingdo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.bean.LocationPoint;

/**
 * 显示基础信息页面
 * Created by qingsong on 2018/1/8.
 */

public class BasicInformationFragment extends Fragment {

    public static BasicInformationFragment newInstance(LocationPoint baseMessage) {
        BasicInformationFragment fragment = new BasicInformationFragment();
        Bundle args = new Bundle();
        args.putSerializable("baseMessage",baseMessage);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_basic, container, false);
        return view;
    }





}

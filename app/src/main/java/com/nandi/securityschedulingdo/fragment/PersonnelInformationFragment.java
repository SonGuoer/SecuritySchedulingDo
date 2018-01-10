package com.nandi.securityschedulingdo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nandi.securityschedulingdo.R;

/**
 * 人员信息
 * Created by qingsong on 2018/1/8.
 */

public class PersonnelInformationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_personnel, container, false);
        return view;
    }
}

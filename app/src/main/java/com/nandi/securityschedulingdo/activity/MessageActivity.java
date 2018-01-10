package com.nandi.securityschedulingdo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nandi.securityschedulingdo.adapter.MessageAdapter;
import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.bean.LocationPoint;
import com.nandi.securityschedulingdo.fragment.BasicInformationFragment;
import com.nandi.securityschedulingdo.fragment.PersonnelInformationFragment;
import com.nandi.securityschedulingdo.fragment.PictureFragment;
import com.nandi.securityschedulingdo.fragment.VideoFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private ArrayList<Fragment> list;
    private ViewPager vpMessage;
    private TabLayout tabMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        vpMessage = findViewById(R.id.vp_message);
        tabMessage = findViewById(R.id.tab_message);
        LocationPoint baseMessage = (LocationPoint) getIntent().getSerializableExtra("baseMessage");

        initFragment();
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(new BasicInformationFragment());
        list.add(new PictureFragment());
        list.add(new VideoFragment());
        list.add(new PersonnelInformationFragment());
        MessageAdapter messageAdapter = new MessageAdapter(getSupportFragmentManager(), list);
        vpMessage.setAdapter(messageAdapter);
        tabMessage.setupWithViewPager(vpMessage);
    }
}

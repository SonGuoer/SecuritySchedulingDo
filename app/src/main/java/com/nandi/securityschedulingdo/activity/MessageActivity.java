package com.nandi.securityschedulingdo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    private ImageView iv_back;
    private TextView tv_title;
    private ArrayList<Fragment> list;
    private ViewPager vpMessage;
    private TabLayout tabMessage;
    private LocationPoint baseMessage;
    private BasicInformationFragment basicInformationFragment;
    private VideoFragment videoFragment;
    private PictureFragment pictureFragment;
    private PersonnelInformationFragment personnelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        vpMessage = findViewById(R.id.vp_message);
        tabMessage = findViewById(R.id.tab_message);
        baseMessage = (LocationPoint) getIntent().getSerializableExtra("baseMessage");
        System.out.println("baseMessage = " + baseMessage.toString());
        basicInformationFragment = BasicInformationFragment.newInstance(baseMessage);
        videoFragment = VideoFragment.newInstance(baseMessage.getVideo_name());
        pictureFragment = PictureFragment.newInstance(baseMessage.getDis_no());
        personnelFragment = PersonnelInformationFragment.newInstance(baseMessage.getDis_no());
        initFragment();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(basicInformationFragment);

        list.add(pictureFragment);
        list.add(videoFragment);


        list.add(personnelFragment);
        vpMessage.setOffscreenPageLimit(4);
        MessageAdapter messageAdapter = new MessageAdapter(getSupportFragmentManager(), list);
        vpMessage.setAdapter(messageAdapter);
        tabMessage.setupWithViewPager(vpMessage);
    }
}

package com.nandi.securityschedulingdo.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.nandi.securityschedulingdo.R;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * 视频播放
 * Created by qingsong on 2018/1/8.
 */

public class VideoFragment extends Fragment {

    private VideoView videoView;
    private JZVideoPlayerStandard JCVideoPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String type = "video/* ";
//
//        Uri uri = Uri.parse("http://183.230.182.149:19088/rend/down/img?path=d:/cmd/video/dis/5002322010050101.mp4");
//        intent.setDataAndType(uri, type);
//        startActivity(intent);
        JCVideoPlayer = (JZVideoPlayerStandard) view.findViewById(R.id.videoplayer);
        JCVideoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "5002322010050101.mp4");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
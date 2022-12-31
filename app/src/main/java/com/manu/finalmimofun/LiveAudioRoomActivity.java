package com.manu.finalmimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomConfig;
import com.zegocloud.uikit.prebuilt.liveaudioroom.ZegoUIKitPrebuiltLiveAudioRoomFragment;
import com.zegocloud.uikit.prebuilt.liveaudioroom.core.ZegoLiveAudioRoomLayoutAlignment;
import com.zegocloud.uikit.prebuilt.liveaudioroom.core.ZegoLiveAudioRoomLayoutRowConfig;

import java.util.Arrays;
import java.util.Collections;

public class LiveAudioRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_audio_room);
        addFragment();
    }

    private void addFragment() {
        long appID = getIntent().getLongExtra("appID", 0L);
        String appSign = getIntent().getStringExtra("appSign");
        String userID = getIntent().getStringExtra("userID");
        String userName = getIntent().getStringExtra("userName");
        boolean isHost = getIntent().getBooleanExtra("host", false);
        String roomID = getIntent().getStringExtra("roomID");
        String roomname = getIntent().getStringExtra("roomname");



        ZegoUIKitPrebuiltLiveAudioRoomConfig config;
        if (isHost) {
            config = ZegoUIKitPrebuiltLiveAudioRoomConfig.host();
        } else {
            config = ZegoUIKitPrebuiltLiveAudioRoomConfig.audience();
        }

        config.hostSeatIndexes = Collections.singletonList(0);
        config.layoutConfig.rowConfigs = Arrays.asList(
                new ZegoLiveAudioRoomLayoutRowConfig(1, ZegoLiveAudioRoomLayoutAlignment.CENTER),
                new ZegoLiveAudioRoomLayoutRowConfig(4, ZegoLiveAudioRoomLayoutAlignment.SPACE_AROUND),
                new ZegoLiveAudioRoomLayoutRowConfig(4, ZegoLiveAudioRoomLayoutAlignment.SPACE_AROUND));

        config.seatConfig.showSoundWaveInAudioMode = true;

        ZegoUIKitPrebuiltLiveAudioRoomFragment fragment = ZegoUIKitPrebuiltLiveAudioRoomFragment.newInstance(appID,
                appSign, userID, userName, roomID, config);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commitNow();

        AudioRoomBackgroundView roomBackgroundView = new AudioRoomBackgroundView(this);
        roomBackgroundView.setRoomID(roomID);
        roomBackgroundView.setRoomName(roomname);
        roomBackgroundView.setBackgroundResource(R.drawable.live_bg);
        fragment.setBackgroundView(roomBackgroundView);

    }
}
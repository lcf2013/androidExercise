package com.zhenai.media;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private static final int RECORD_REQUEST = 0;
    private Button playBtn;
    private Button intentBtn;
    private Button audioRecordBtn;
    private Button mediaRecorderBtn;
    private Uri audioUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        playBtn=(Button)findViewById(R.id.play_btn);
        intentBtn=(Button)findViewById(R.id.intent_btn);
        audioRecordBtn=(Button)findViewById(R.id.audioRecord_btn);
        mediaRecorderBtn=(Button)findViewById(R.id.mediaRecorder_btn);


        playBtn.setOnClickListener(this);
        intentBtn.setOnClickListener(this);
        audioRecordBtn.setOnClickListener(this);
        mediaRecorderBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.play_btn:
                playAutio();
                break;
            case R.id.intent_btn:
                playBtn.setVisibility(View.GONE);
                intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(intent, RECORD_REQUEST);
                break;
            case R.id.audioRecord_btn:
                playBtn.setVisibility(View.GONE);
                intent=new Intent(this,AudioRecordActivity.class);
                startActivity(intent);
                break;

            case R.id.mediaRecorder_btn:
                playBtn.setVisibility(View.GONE);
                intent=new Intent(this,MediaRecordActivity.class);
                startActivity(intent);
                break;


            default:break;
        }

    }

    private void playAutio() {
        playBtn.setVisibility(View.GONE);
        MediaPlayer mediaPlayer=MediaPlayer.create(this,audioUri);
        mediaPlayer.setOnCompletionListener(this);

        mediaPlayer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode==RECORD_REQUEST){
            audioUri=data.getData();
            playBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        playBtn.setVisibility(View.VISIBLE);
    }
}

package com.zhenai.media;

import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MediaRecordActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, View.OnClickListener {

    @Bind(R.id.amplitudeText)
    TextView amplitudeText;
    private TextView status;
    private Button start;
    private Button stop;
    private Button play;
    private Button finish;


    MediaRecorder recorder;
    MediaPlayer player;
    File audioFile;
    private boolean isRecording;
    private RecordAmplitudeTask recordAmplitudeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        status = (TextView) findViewById(R.id.status);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        play = (Button) findViewById(R.id.play);
        finish = (Button) findViewById(R.id.finish);

        stop.setEnabled(false);
        play.setEnabled(false);


        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        play.setOnClickListener(this);
        finish.setOnClickListener(this);


    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        play.setEnabled(true);
        stop.setEnabled(false);
        start.setEnabled(true);
        status.setText("ready");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                startRecord();

                break;
            case R.id.play:
                playRecord();
                break;
            case R.id.stop:

                stopRecord();


                break;

            case R.id.finish:
                finish();
                break;


            default:
                break;
        }
    }

    private void playRecord() {
        player.start();

        status.setText("playing");
        play.setEnabled(false);
        start.setEnabled(false);
        stop.setEnabled(false);
    }

    private void startRecord() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            audioFile = File.createTempFile("recording", ".3gp", getFilesDir());

        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.setOutputFile(audioFile.getAbsolutePath());


        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();

        status.setText("Recording");

        play.setEnabled(false);
        stop.setEnabled(true);
        start.setEnabled(false);

        isRecording=true;
        recordAmplitudeTask=new RecordAmplitudeTask();
        recordAmplitudeTask.execute();


    }

    private void stopRecord() {
        isRecording=false;
        recordAmplitudeTask.cancel(true);

        recorder.stop();
        recorder.release();

        saveAudioFile();


        player = new MediaPlayer();

        player.setOnCompletionListener(this);

        try {
            player.setDataSource(audioFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
        start.setText("ready to play");

        play.setEnabled(true);
        stop.setEnabled(false);
        start.setEnabled(true);
    }

    /**
     * 保存音频文件到mediastore
     */
    private void saveAudioFile() {
        ContentValues values=new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE,"this is a audio record");
        values.put(MediaStore.MediaColumns.DATE_ADDED,System.currentTimeMillis());
        values.put(MediaStore.MediaColumns.DATA,audioFile.getAbsolutePath());
        Uri newUri=getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                values);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        if(isRecording)
            stopRecord();
        if(player.isPlaying())
            player.stop();

        super.onDestroy();

    }
    private class RecordAmplitudeTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            while(isRecording){
                try {
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(recorder.getMaxAmplitude());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            amplitudeText.setText(values[0].toString());
        }
    }
}

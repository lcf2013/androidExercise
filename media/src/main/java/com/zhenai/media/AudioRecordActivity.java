package com.zhenai.media;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecordActivity extends AppCompatActivity {

    @Bind(R.id.satusTextView)
    TextView satusTextView;
    @Bind(R.id.startRecordBtn)
    Button startRecordBtn;
    @Bind(R.id.stopRecordBtn)
    Button stopRecordBtn;
    @Bind(R.id.playBtn)
    Button playBtn;
    @Bind(R.id.stopPlayBtn)
    Button stopPlayBtn;

    RecordAudioTask recordTask;
    PlayAudio playTask;

    private File recordFile;

    boolean isRecording = false;
    boolean isPlaying = false;

    int frequency = 11025;
    int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        ButterKnife.bind(this);

        stopRecordBtn.setEnabled(false);
        playBtn.setEnabled(false);
        stopPlayBtn.setEnabled(false);

        File path = getFilesDir();
        try {
            recordFile = File.createTempFile("recording", ".pcm", path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.startRecordBtn, R.id.stopRecordBtn, R.id.play_btn, R.id.stopPlayBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startRecordBtn:
                record();
                break;
            case R.id.stopRecordBtn:
                stopRecord();
                break;

            case R.id.play_btn:
                play();
                break;

            case R.id.stopPlayBtn:
                stopPlay();
                break;
        }
    }

    private void stopRecord() {

    }

    private void play() {
        playBtn.setEnabled(true);
        playTask = new PlayAudio();
    }

    private void record() {

    }

    private void stopPlay() {

    }

    class PlayAudio extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            isPlaying = true;
            int bufferSize = AudioTrack.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
            short[] audioData = new short[bufferSize / 4];
            try {
                DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(recordFile)));
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency,
                        channelConfiguration, audioEncoding, bufferSize, AudioTrack.MODE_STREAM);
                audioTrack.play();

                while (isPlaying && dis.available() > 0) {
                    int i = 0;
                    while (dis.available() > 0 && i < audioData.length) {
                        audioData[i] = dis.readShort();
                        i++;

                    }
                    audioTrack.write(audioData, 0, audioData.length);
                }
                dis.close();
                //playBtn.setEnabled(false);
                //stopPlayBtn.setEnabled(true);
            } catch (Exception e) {

            }


            return null;
        }
    }

    class RecordAudioTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            isRecording = true;

            try {
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(recordFile)));
                int bufferSize = AudioRecord.getMinBufferSize(frequency,channelConfiguration,
                        audioEncoding);
                AudioRecord audioRecord=new AudioRecord(MediaRecorder.AudioSource.MIC,
                        frequency,channelConfiguration,audioEncoding,bufferSize);
                short[]buffer=new short[bufferSize];
                audioRecord.startRecording();

                int r=0;
                while(isRecording){
                    int bufferReadResult=audioRecord.read(buffer,0,bufferSize);
                    for(int i=0;i<bufferReadResult;i++){
                        dos.writeShort(buffer[i]);

                    }
                    publishProgress(new Integer(r));
                    r++;
                }
                audioRecord.stop();
                dos.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            satusTextView.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startRecordBtn.setEnabled(true);
            stopRecordBtn.setEnabled(false);
            playBtn.setEnabled(true);
        }
    }

}

package com.zhenai.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView text;
    private ServiceConnection mConnection;
    private IAidlServerService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService=   IAidlServerService.Stub.asInterface(iBinder);
                try{

                    String mText ="Say:"+mService.sayHello()+"/n"+
                            "书名:"+mService.getBook().getBookName()+"/n"+
                            "价格:"+mService.getBook().getBookPrice();
                    text.setText(mText);
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    @Override
    public void onClick(View view) {
        if (view == btn) {
            Intent intent  =new Intent("com.zhenai.aidlserver.IAIDLServerService");
            bindService(intent,mConnection,BIND_AUTO_CREATE);
        }
    }
}

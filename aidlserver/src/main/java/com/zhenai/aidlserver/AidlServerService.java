package com.zhenai.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlServerService extends Service {
    public AidlServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IAidlServerService.Stub mBinder=new IAidlServerService.Stub() {
        @Override
        public String sayHello() throws RemoteException {
            return "Hello";
        }

        @Override
        public Book getBook() throws RemoteException {
            Book mBook=new Book();

            mBook.setBookName("android应用开发");
            mBook.setBookPrice(30);
            return mBook;
        }
    };
}

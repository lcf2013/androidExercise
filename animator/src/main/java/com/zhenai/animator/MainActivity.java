package com.zhenai.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=(TextView)findViewById(R.id.txt);
        /*
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0f,5f,3f,10f);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float cur = (float) valueAnimator.getAnimatedValue();
                Log.d("TAG", cur + "");
            }
        });
        valueAnimator.start();*/
/*
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(txt,"rotation",0f,360f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();*/
/*
        float cur=txt.getTranslationX();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(txt,"translationX",cur,-5000f,cur);
        objectAnimator.setDuration(3000);
        objectAnimator.start();*/

/*
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(txt,"scaleY",1f,3f,1f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();*/
/*
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(txt, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(txt, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(txt, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();*/

        txt.animate().setDuration(3000).alpha(0f);

    }


}

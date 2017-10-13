package com.evayinfo.grace.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;


/**
 * Created by hiviiup on 17/1/6.
 * 计时器,用于接收验证码的时候
 */

public class TimerButton extends AppCompatTextView {

    private TimeCount timeCount;

    public TimerButton(Context context) {
        this(context, null);
    }

    public TimerButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
    }

    //default
    private long millisInFuture = 60 * 1000, countDownInterval = 1000;

    public void setUnit(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;

        timeCount = new TimeCount(millisInFuture, countDownInterval);
    }

    public void cancel() {
        if (timeCount != null)
            timeCount.cancel();
    }

    public void startTime() {
        if (null == timeCount) {
            throw new RuntimeException("请先调用setUnit方法");
        }
        timeCount.start();
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setClickable(false);
            setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            setClickable(true);
            setText("获取验证码");
        }
    }
}
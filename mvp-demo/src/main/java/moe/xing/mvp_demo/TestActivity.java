package moe.xing.mvp_demo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import moe.xing.baseutils.utils.LogHelper;
import moe.xing.mvp_utils.BaseActivity;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Qi Xingchen on 2017-12-15.
 */

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgressDialog("2333", null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CountDownTimer(100 * 1000, 100) {

            public void onTick(long millisUntilFinished) {
                int now = (int) (1000 - millisUntilFinished / 100);
                LogHelper.i(String.valueOf(now));
                if (now < 500) {
                    showProgressDialog("3333", now, 1000);
                } else {
                    showProgressDialog("4444", now / 10, 500);
                }
            }

            public void onFinish() {
                LogHelper.i("end");
                showProgressDialog("5555", null, null);
            }
        }.start();
    }
}

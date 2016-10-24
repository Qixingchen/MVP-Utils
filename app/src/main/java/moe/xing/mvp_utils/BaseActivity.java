package moe.xing.mvp_utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.WindowManager;

import me.yokeyword.fragmentation.SupportActivity;
import moe.xing.baseutils.utils.LogHelper;

/**
 * Created by Qi xingchen on 2016/7/14 0014.
 * <p>
 * activity 基类
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class BaseActivity extends SupportActivity {

    protected Activity mActivity;

    protected ProgressDialog mDialog;
    protected boolean active = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String title) {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = new ProgressDialog(mActivity);
        WindowManager.LayoutParams params = mDialog.getWindow()
                .getAttributes();
        params.dimAmount = 0f;
        mDialog.getWindow().setAttributes(params);

        if (TextUtils.isEmpty(title)) {
            title = "加载中...";
        }
        mDialog.setTitle(title);
        mDialog.show();
    }

    public void dismissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void showMessage(String message) {
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        LogHelper.Snackbar(viewGroup, message);
    }

    public void showMessage(Throwable e) {
        showMessage(e.getLocalizedMessage());
    }

    public void showMessage(@StringRes int message) {
        showMessage(getString(message));
    }

    public void showMessage(@StringRes int message, String message2) {
        showMessage(getString(message) + message2);
    }

}

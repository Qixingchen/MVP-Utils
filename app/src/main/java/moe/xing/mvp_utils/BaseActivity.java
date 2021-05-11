package moe.xing.mvp_utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import me.yokeyword.fragmentation.SupportActivity;
import moe.xing.baseutils.utils.LogHelper;
import moe.xing.baseutils.utils.TextHelper;

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
        showProgressDialog(title, null, null);
    }

    public void dismissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void showProgressDialog(String title, @Nullable Integer now, @Nullable Integer max) {
        if (now == null && mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        if (mDialog == null) {
            mDialog = new ProgressDialog(mActivity, R.style.Theme_AppCompat_Light_Dialog_Alert);
        }
        if (now != null && max != null) {
            if (mDialog.isIndeterminate()) {
                mDialog.dismiss();
                mDialog = new ProgressDialog(mActivity, R.style.Theme_AppCompat_Light_Dialog_Alert);
            }
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            if (mDialog.getMax() == 100 && max != 100) {
                mDialog.setMax(max);
            }
            mDialog.setProgress(now);
        }

        if (!mDialog.isShowing()) {

            WindowManager.LayoutParams params = mDialog.getWindow()
                    .getAttributes();
            params.dimAmount = 0f;
            mDialog.getWindow().setAttributes(params);

            if (TextUtils.isEmpty(title)) {
                title = "加载中...";
            }
            mDialog.setTitle(title);
            mDialog.setCancelable(false);
            mDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消显示进度框", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDialog.dismiss();
                    mDialog = null;
                }
            });
            mDialog.show();
        }
    }

    public void showMessage(String message) {
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        LogHelper.Snackbar(viewGroup, message);
    }

    public void showMessage(Throwable e) {
        String message;
        if (TextHelper.isVisible(e.getLocalizedMessage())) {
            message = e.getLocalizedMessage();
        } else {
            message = e.toString();
        }
        showMessage(message);
    }

    public void showMessage(@StringRes int message) {
        showMessage(getString(message));
    }

    public void showMessage(@StringRes int message, String message2) {
        showMessage(getString(message) + message2);
    }

    /**
     * 设置暗色状态栏图标
     *
     * @param needDark {@code true} 需要暗色图标
     */
    public void setDarkStatusIcon(boolean needDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (needDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

}

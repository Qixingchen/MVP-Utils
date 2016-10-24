package moe.xing.mvp_utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import moe.xing.baseutils.utils.LogHelper;

/**
 * Created by Qi xingchen on 2016/7/14 0014.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseFragment extends SupportFragment {

    protected View mRootView;
    protected Context mContext;
    protected android.support.v7.app.ActionBar mActionBar;
    protected String title;
    protected Fragment mFragment = this;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = CreateView(inflater, container, savedInstanceState);
        assert mRootView != null;
        ViewFound(mRootView);
        return mRootView;
    }


    protected abstract View CreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void ViewFound(View view);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract String getTitle();

    @Override
    public void onResume() {
        super.onResume();
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        title = getTitle();
        if (mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Called when the fragment is no longer attached to its activity.  This
     * is called after {@link #onDestroy()}.
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showMessage(String message) {
        LogHelper.Snackbar(mRootView, message);
    }

    public void showMessage(Throwable e) {
        showMessage(e.getLocalizedMessage());
    }

    public void showMessage(@StringRes int message) {
        showMessage(getString(message));
    }

    public void showMessage(@StringRes int message, String message2) {
        showMessage(getString(message) + " " + message2);
    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String title) {
        ((BaseActivity) _mActivity).showProgressDialog(title);
    }

    public void dismissProgressDialog() {
        ((BaseActivity) _mActivity).dismissProgressDialog();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            return true;
        } else {
            return super.onBackPressedSupport();
        }
    }
}

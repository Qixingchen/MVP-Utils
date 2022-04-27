package moe.xing.mvp_utils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Created by Qi xingchen on 2016/7/14 0014.
 * <p>
 * Fragment 抽象类
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected Context mContext;
    protected androidx.appcompat.app.ActionBar mActionBar;
    protected String title;
    protected Fragment mFragment = this;
    protected boolean viewExisted = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = CreateView(inflater, container, savedInstanceState);
        assert mRootView != null;
        ViewFound(mRootView);
        viewExisted = true;
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

    protected abstract void setTitle(@NonNull String title);

    @Override
    public void onResume() {
        super.onResume();
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        title = getTitle();
        setTitle(title);
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
        ((BaseActivity) getActivity()).showMessage(message);
    }

    public void showMessage(Throwable e) {
        ((BaseActivity) getActivity()).showMessage(e);
    }

    public void showMessage(@StringRes int message) {
        ((BaseActivity) getActivity()).showMessage(message);
    }

    public void showMessage(@StringRes int message, String message2) {
        ((BaseActivity) getActivity()).showMessage(message, message2);
    }

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(@SuppressWarnings("SameParameterValue") String title) {
        showProgressDialog(title, null, null);
    }

    public void showProgressDialog(@SuppressWarnings("SameParameterValue") String title, @Nullable Integer now, @Nullable Integer max) {
        ((BaseActivity) getActivity()).showProgressDialog(title, now, max);
    }

    public void dismissProgressDialog() {
        ((BaseActivity) getActivity()).dismissProgressDialog();
    }

}

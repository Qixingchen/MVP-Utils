package moe.xing.mvp_utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * Created by Qi xingchen on 2016/7/14 0014.
 * <p>
 * 基础View
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface BaseView<T extends BasePresenter> {

    void setPresenter(@NonNull T presenter);

    /**
     * 显示消息
     *
     * @param message 消息文字
     */
    void showMessage(String message);

    void showMessage(Throwable e);

    void showMessage(@StringRes int message);

    void showMessage(@StringRes int message, String message2);

    void showProgressDialog();

    void showProgressDialog(String title);

    void showProgressDialog(String title, @Nullable Integer now, @Nullable Integer max);

    void dismissProgressDialog();

}

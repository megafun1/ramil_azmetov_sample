package prre.ttrr.com.azmetov_ramil.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.Serializable;
import java.util.List;

import prre.ttrr.com.azmetov_ramil.Utils;
import prre.ttrr.com.azmetov_ramil.activity.NewsView;
import prre.ttrr.com.azmetov_ramil.fragment.ErrorDialogFragment;
import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.model.NewsImpl;
import prre.ttrr.com.azmetov_ramil.network.NewsRequest;
import prre.ttrr.com.azmetov_ramil.network.RequestsService;

public class NewsPresenter implements ErrorDialogFragment.OnErrorDialogButtonClickListener {
    private static final String KEY_NEWS = "key_news";

    private NewsView mView;
    private SpiceManager mManager = new SpiceManager(RequestsService.class);
    private Activity mActivity;
    private List<News> mLoadedNews;

    public NewsPresenter(NewsView view) {
        mView = view;
    }

    public void onCreate(Activity activity, Bundle savedInstanceState) {
        mActivity = activity;
        mView.showProgress(true);
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_NEWS)) {
            mLoadedNews = (List<News>) savedInstanceState.get(KEY_NEWS);
        }
    }

    public void onStart() {
        mManager.start(mActivity);
        if (mLoadedNews == null) {
            loadNews();
        } else {
            mView.showNews(mLoadedNews);
            mView.showProgress(false);
        }
    }

    private void loadNews() {
        mView.showProgress(true);
        RequestListener<NewsImpl.NewsList> requestListener = new PresenterRequestListener();
        mManager.execute(new NewsRequest(), requestListener);
    }

    public void onStop() {
        mManager.shouldStop();
    }

    @Override
    public void onOkClicked() {
        loadNews();
    }

    @Override
    public void onCancelClicked() {

    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_NEWS, (Serializable) mLoadedNews);
    }

    public class PresenterRequestListener implements RequestListener<NewsImpl.NewsList> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            spiceException.printStackTrace();
            mView.showErrorDialog();
            mView.showProgress(false);
        }

        @Override
        public void onRequestSuccess(NewsImpl.NewsList newses) {
            NewsPresenter.this.onRequestSuccess(newses);
        }
    }

    public void onRequestSuccess(NewsImpl.NewsList newses) {
        List<News> sortedList = Utils.sortByDate(newses);
        mView.showNews(sortedList);
        mLoadedNews = sortedList;
        mView.showProgress(false);
    }
}
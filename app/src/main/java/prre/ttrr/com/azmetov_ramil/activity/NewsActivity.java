package prre.ttrr.com.azmetov_ramil.activity;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import prre.ttrr.com.azmetov_ramil.R;
import prre.ttrr.com.azmetov_ramil.adapter.NewsAdapter;
import prre.ttrr.com.azmetov_ramil.fragment.ErrorDialogFragment;
import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.presenter.NewsPresenter;

public class NewsActivity extends ActionBarActivity implements NewsView {
    private NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new NewsPresenter(this);
        mPresenter.onCreate(this, savedInstanceState);
    }

    @Override
    public void showProgress(boolean show) {
        View progressBar = findViewById(R.id.ma_progress_bar);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNews(List<News> newsList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ma_news_resyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new NewsAdapter(newsList, ContextCompat.getDrawable(this, R.drawable.img_no_img)));
    }

    @Override
    public void showErrorDialog() {
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        fragment.show(getSupportFragmentManager(), "");
        fragment.setListener(mPresenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public NewsPresenter getPresenter() {
        return mPresenter;
    }
}

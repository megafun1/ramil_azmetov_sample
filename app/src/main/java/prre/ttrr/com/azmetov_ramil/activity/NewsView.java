package prre.ttrr.com.azmetov_ramil.activity;

import java.util.List;

import prre.ttrr.com.azmetov_ramil.model.News;

public interface NewsView {

    void showProgress(boolean show);

    void showNews(List<News> newsList);

    void showErrorDialog();
}

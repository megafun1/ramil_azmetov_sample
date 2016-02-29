package prre.ttrr.com.azmetov_ramil.model;


import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEnclosure;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsImpl implements News,Serializable {
    private String mImageUrl;
    private String mTitle;
    private String mText;
    private News.NewsSite mSite;
    private Date mPubDate;
    public static NewsList sNewsList = new NewsList();

    public Date getPubDate() {
        return mPubDate;
    }

    public NewsImpl(SyndEntry entry, News.NewsSite site) {
        mTitle = entry.getTitle();
        mSite = site;
        List<SyndEnclosure> enclosures = entry.getEnclosures();
        if (enclosures != null && enclosures.size() > 0) {
            SyndEnclosure syndEnclosure = enclosures.get(0);
            mImageUrl = syndEnclosure.getUrl();
        }
        mPubDate = entry.getPublishedDate();
        mText = entry.getDescription().getValue();
    }

    @Override
    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getText() {
        return mText;
    }

    @Override
    public NewsSite getSite() {
        return mSite;
    }

    public static class NewsList extends ArrayList<News>{

    }
}

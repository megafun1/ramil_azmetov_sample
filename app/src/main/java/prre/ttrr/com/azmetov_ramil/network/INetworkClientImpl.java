package prre.ttrr.com.azmetov_ramil.network;


import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.model.NewsImpl;

public class INetworkClientImpl implements INetworkClient {
    private static final String LENTA_RSS_URL = "http://lenta.ru/rss";
    private static final String GAZETA_RSS_URL = "http://gazeta.ru/export/rss/lenta.xml";

    @Override
    public List<News> getRSSFromLenta() {
        return getRSSFromLenta(LENTA_RSS_URL, News.NewsSite.LENTA);
    }

    @Override
    public List<News> getRSSFromGazeta() {
        return getRSSFromLenta(GAZETA_RSS_URL, News.NewsSite.GAZETA);
    }

    private List<News> getRSSFromLenta(String stringUrl, News.NewsSite from) {
        NewsImpl.NewsList newsList = new NewsImpl.NewsList();
        try {
            URL url = new URL(stringUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry ent : entries) {
                NewsImpl news = new NewsImpl(ent, from);
                newsList.add(news);
            }
            return newsList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package prre.ttrr.com.azmetov_ramil.modules;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.model.NewsImpl;
import prre.ttrr.com.azmetov_ramil.network.INetworkClient;
import prre.ttrr.com.azmetov_ramil.network.INetworkClientImpl;
import prre.ttrr.com.azmetov_ramil.network.NewsRequest;
import prre.ttrr.com.azmetov_ramil.network.tasks.GetNewsFromGazeta;
import prre.ttrr.com.azmetov_ramil.network.tasks.GetNewsFromLenta;
@Module(injects = {
        GetNewsFromGazeta.class,
        GetNewsFromLenta.class,
        NewsRequest.class
})
public class TestModule {
    @Provides
    INetworkClient provideClient() {
        News newsMock = Mockito.mock(NewsImpl.class);
        Mockito.when(newsMock.getPubDate()).thenReturn(new Date());
        Mockito.when(newsMock.getSite()).thenReturn(News.NewsSite.GAZETA);
        Mockito.when(newsMock.getText()).thenReturn("");
        Mockito.when(newsMock.getTitle()).thenReturn("");
        INetworkClientImpl networkClientMock = Mockito.mock(INetworkClientImpl.class);
        NewsImpl.NewsList newsList = new NewsImpl.NewsList();
        newsList.add(newsMock);
        Mockito.when(networkClientMock.getRSSFromGazeta()).thenReturn(newsList);
        Mockito.when(networkClientMock.getRSSFromLenta()).thenReturn(newsList);
        return networkClientMock;
    }

    @Provides
    @Singleton
    List<Callable<List<News>>> provideGetNewsTasks() {
        List<Callable<List<News>>> list = new ArrayList<>();
        list.add(new GetNewsFromGazeta());
        list.add(new GetNewsFromLenta());
        return list;
    }
}

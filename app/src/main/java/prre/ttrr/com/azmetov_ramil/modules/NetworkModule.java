package prre.ttrr.com.azmetov_ramil.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import prre.ttrr.com.azmetov_ramil.model.News;
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
public class NetworkModule {
    @Provides
    INetworkClient provideClient() {
        return new INetworkClientImpl();
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

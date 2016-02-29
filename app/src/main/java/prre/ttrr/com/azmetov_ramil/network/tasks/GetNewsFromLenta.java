package prre.ttrr.com.azmetov_ramil.network.tasks;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.modules.Injector;
import prre.ttrr.com.azmetov_ramil.network.INetworkClient;

public class GetNewsFromLenta implements Callable<List<News>> {
    @Inject
    INetworkClient mClient;

    {
        Injector.inject(this);
    }

    @Override
    public List<News> call() throws Exception {
        return mClient.getRSSFromLenta();
    }
}
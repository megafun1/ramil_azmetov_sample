package prre.ttrr.com.azmetov_ramil;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.Assert;

import java.util.List;

import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.network.INetworkClient;
import prre.ttrr.com.azmetov_ramil.network.INetworkClientImpl;

public class NetworkClientTest extends ApplicationTestCase<Application> {
    public NetworkClientTest() {
        super(Application.class);
    }

    @LargeTest
    public void testGetRSSFromLenta() throws Exception {
        INetworkClient client = new INetworkClientImpl();
        List<News> newsList = client.getRSSFromLenta();
        Assert.assertFalse(newsList.isEmpty());
    }

    @LargeTest
    public void testGetRSSFromGazeta() throws Exception {
        INetworkClient client = new INetworkClientImpl();
        List<News> newsList = client.getRSSFromGazeta();
        Assert.assertFalse(newsList.isEmpty());
    }
}
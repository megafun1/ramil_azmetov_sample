import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.model.NewsImpl;
import prre.ttrr.com.azmetov_ramil.modules.Injector;
import prre.ttrr.com.azmetov_ramil.modules.TestModule;
import prre.ttrr.com.azmetov_ramil.network.INetworkClient;
import prre.ttrr.com.azmetov_ramil.network.INetworkClientImpl;
import prre.ttrr.com.azmetov_ramil.network.NewsRequest;

@Config(manifest = "../app/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class RequestsTest {

    @Test
    public void testNewsRequest() throws Exception {
        Injector.init(new TestModule());
        INetworkClient client = new INetworkClientImpl();
        List<News> newsList = client.getRSSFromLenta();
        Assert.assertFalse(newsList.isEmpty());
        NewsRequest request = new NewsRequest();
        NewsImpl.NewsList newses = request.loadDataFromNetwork();
        Assert.assertTrue(newses.size() == 2);
    }
}
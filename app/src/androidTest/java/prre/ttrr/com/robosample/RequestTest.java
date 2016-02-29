package prre.ttrr.com.azmetov_ramil;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;



import prre.ttrr.com.azmetov_ramil.model.NewsImpl;
import prre.ttrr.com.azmetov_ramil.network.NewsRequest;


public class RequestTest extends ApplicationTestCase<Application> {

    public RequestTest() {
        super(Application.class);
    }
    public void testNewsRequest() throws Exception {
        NewsRequest request = new NewsRequest();
        NewsImpl.NewsList newses = request.loadDataFromNetwork();
        Assert.assertFalse(newses.isEmpty());
    }
}

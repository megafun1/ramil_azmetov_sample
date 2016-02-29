import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;


import prre.ttrr.com.azmetov_ramil.BuildConfig;
import prre.ttrr.com.azmetov_ramil.R;
import prre.ttrr.com.azmetov_ramil.activity.NewsActivity;
import prre.ttrr.com.azmetov_ramil.model.NewsImpl;
import prre.ttrr.com.azmetov_ramil.modules.Injector;
import prre.ttrr.com.azmetov_ramil.modules.TestModule;
import prre.ttrr.com.azmetov_ramil.network.NewsRequest;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,manifest="../app/src/main/AndroidManifest.xml", constants = BuildConfig.class)
public class NewsActivityTests {

    @Test
    public void testShowNews() throws Exception {
        Injector.init(new TestModule());
        NewsRequest request = new NewsRequest();
        NewsImpl.NewsList newses = request.loadDataFromNetwork();
        ActivityController controller = Robolectric.buildActivity(NewsActivity.class).create().start();
        NewsActivity activity = (NewsActivity) controller.get();
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.ma_news_resyclerview);
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 10000);
        activity.getPresenter().onRequestSuccess(newses);
        controller.resume().visible();
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.ma_progress_bar);
        Assert.assertTrue(recyclerView.getChildCount() > 0);
        Assert.assertTrue(progressBar.getVisibility() == View.GONE);
    }
}

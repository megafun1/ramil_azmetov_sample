package prre.ttrr.com.azmetov_ramil.network;

import com.octo.android.robospice.request.SpiceRequest;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import prre.ttrr.com.azmetov_ramil.model.News;
import prre.ttrr.com.azmetov_ramil.model.NewsImpl;
import prre.ttrr.com.azmetov_ramil.modules.Injector;

public class NewsRequest extends SpiceRequest<NewsImpl.NewsList> {
    @Inject
    List<Callable<List<News>>> mTaskList;
    {
        Injector.inject(this);
    }
    public NewsRequest() {
        super(NewsImpl.NewsList.class);
    }

    @Override
    public NewsImpl.NewsList loadDataFromNetwork() throws Exception {
        final NewsImpl.NewsList list = new NewsImpl.NewsList();
        for (Callable<List<News>> callable : mTaskList) {
            Thread workerThread = new Thread(new WorkerRunnable(callable, list));
            workerThread.start();
            workerThread.join();
        }
        return list;
    }

    private static class WorkerRunnable implements Runnable {

        private Callable<List<News>> submit;
        private NewsImpl.NewsList list;

        public WorkerRunnable(Callable<List<News>> submit, NewsImpl.NewsList list) {
            this.submit = submit;
            this.list = list;
        }

        @Override
        public void run() {
            List<News> newsList = null;
            try {
                newsList = submit.call();
                synchronized (NewsRequest.class) {
                    list.addAll(newsList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

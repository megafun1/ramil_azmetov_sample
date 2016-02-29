package prre.ttrr.com.azmetov_ramil.network;

import java.util.List;

import prre.ttrr.com.azmetov_ramil.model.News;

public interface IRSSLoader {

    List<News> getNewsFromLenta();

    List<News> getNewsFromGazeta();
}

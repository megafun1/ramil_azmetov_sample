package prre.ttrr.com.azmetov_ramil.model;

import java.io.Serializable;
import java.util.Date;

public interface News {

    String getImageUrl();

    String getTitle();

    String getText();

    NewsSite getSite();

    Date getPubDate();


    public enum NewsSite implements Serializable {
        LENTA("лента.ру"), GAZETA("газета.ру");

        private final String name;

        NewsSite(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}

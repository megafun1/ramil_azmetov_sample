package prre.ttrr.com.azmetov_ramil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import prre.ttrr.com.azmetov_ramil.model.News;

public class Utils {

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static List<News> sortByDate(List<News> newsList) {
        Collections.sort(newsList, new Comparator<News>() {
            @Override
            public int compare(News lhs, News rhs) {
                if (lhs.getPubDate().getTime() > rhs.getPubDate().getTime()) return -1;
                if (lhs.getPubDate().getTime() < rhs.getPubDate().getTime()) return 1;
                return 0;
            }
        });
        return newsList;
    }


}

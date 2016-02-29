package prre.ttrr.com.azmetov_ramil.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import prre.ttrr.com.azmetov_ramil.R;
import prre.ttrr.com.azmetov_ramil.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNewsList;
    private Drawable mImg;
    /**
     * Содержит в себе ViewHolder`ы, в которых показан текст новости.
     */
    private HashMap<News,ViewHolder> mHolderHashMap = new HashMap<>();

    public NewsAdapter(List<News> newsList, Drawable img) {
        mNewsList = newsList;
        mImg = img;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_news, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final News news = mNewsList.get(position);
        if (TextUtils.isEmpty(news.getImageUrl())) {
            holder.image.setImageDrawable(mImg);
        } else {
            ImageLoader.getInstance().displayImage(news.getImageUrl(), holder.image);
        }
        holder.title.setText(news.getTitle());
        holder.text.setText(news.getText());
        holder.from.setText(news.getSite().getName());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String stringDate = format.format(news.getPubDate());
        holder.date.setText(stringDate);
        holder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) holder.rowView.findViewById(R.id.row_news_text);
                if (text.getVisibility() == View.VISIBLE){
                    text.setVisibility(View.GONE);
                    mHolderHashMap.remove(news);
                }else {
                    text.setVisibility(View.VISIBLE);
                    mHolderHashMap.put(news, holder);
                }
            }
        });
        handleTextVisibility(holder, news);
    }

    /**
     * Метод отвечает за открытие или закрытие текста новости.
     */
    private void handleTextVisibility(ViewHolder holder, News news) {
        ViewHolder viewHolder = mHolderHashMap.get(news);
        if (viewHolder != null){
            viewHolder.text.setVisibility(View.VISIBLE);
        }else {
            holder.text.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView from;
        private TextView text;
        private TextView date;
        private View rowView;

        public ViewHolder(View rowView) {
            super(rowView);
            image = (ImageView) rowView.findViewById(R.id.row_news_image);
            title = (TextView) rowView.findViewById(R.id.row_news_title);
            from = (TextView) rowView.findViewById(R.id.row_news_from);
            text = (TextView) rowView.findViewById(R.id.row_news_text);
            date = (TextView) rowView.findViewById(R.id.row_news_date);
            this.rowView = rowView;
        }
    }
}

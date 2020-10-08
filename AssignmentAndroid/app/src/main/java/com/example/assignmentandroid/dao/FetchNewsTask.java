package com.example.assignmentandroid.dao;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignmentandroid.models.News;
import com.example.assignmentandroid.screens.newsactivity.NewsContextActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchNewsTask extends AsyncTask<Void,Void,Boolean> {
    private List<News> news;
    private ListView listView;
    private Context context;
    private String mFeedTitle;
    private String mFeedLink;
    private String mFeedDescription;

    public FetchNewsTask(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            String urlLink = "https://vnexpress.net/rss/giao-duc.rss";
            URL url = new URL(urlLink);
            InputStream inputStream = url.openConnection().getInputStream();
            news = fetchData(inputStream);
            return true;
        }catch (Exception ex){
            Log.d("Error", ex.getMessage());
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            if(news!= null){
                ArrayAdapter<News> newsAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,news);
                listView.setAdapter(newsAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context,NewsContextActivity.class);
                        intent.putExtra(NewsContextActivity.LINK,news.get(position+1).getLink());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    private List<News> fetchData(InputStream inputStream) throws XmlPullParserException, IOException {
        String title = null;
        String link = null;
        String description = null;
        boolean isItem = false;
        List<News> items = new ArrayList<>();
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);
            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();
                String name = xmlPullParser.getName();
                if(name == null)
                    continue;
                if(eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                }

                if (title != null && link != null && description != null) {
                    if(isItem) {
                        News item = new News(title, link, description);
                        items.add(item);
                    }
                    else {
                        mFeedTitle = title;
                        mFeedLink = link;
                        mFeedDescription = description;
                    }
                    title = null;
                    link = null;
                    description = null;
                    isItem = false;
                }
            }
        return items;
        }finally {
            inputStream.close();
        }
    }
}

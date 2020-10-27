package com.example.cocoshop.database.responsive;

import android.content.Context;
import android.os.AsyncTask;

import com.example.cocoshop.database.dao.Database;
import com.example.cocoshop.database.dao.TopicProgressDao;
import com.example.cocoshop.database.entity.TopicProgressEntity;

public class TopicProgressResponsive {
    private TopicProgressDao dao;

    public TopicProgressResponsive(Context context) {
        dao = Database.getInstance(context).topicProgressDao();
    }

    public TopicProgressEntity getProgressive(int id){
        return dao.getProgress(id);
    }

    public void insert(TopicProgressEntity entity){
        new insertAsyncTask(dao).execute(entity);
    }

    public void update(TopicProgressEntity entity){
        new updateAsyncTask(dao).execute(entity);
    }

    static class insertAsyncTask extends AsyncTask<TopicProgressEntity,Void,Void>{
        private TopicProgressDao dao;

        public insertAsyncTask(TopicProgressDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TopicProgressEntity... topicProgressEntities) {
            dao.insert(topicProgressEntities[0]);
            return null;
        }
    }

    static class updateAsyncTask extends AsyncTask<TopicProgressEntity,Void,Void>{
        private TopicProgressDao dao;

        public updateAsyncTask(TopicProgressDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TopicProgressEntity... topicProgressEntities) {
            dao.update(topicProgressEntities[0]);
            return null;
        }
    }
}

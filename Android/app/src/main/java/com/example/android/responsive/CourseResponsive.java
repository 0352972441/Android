package com.example.android.responsive;
import android.content.Context;
import android.os.AsyncTask;
import com.example.android.dao.Appdatabase;
import com.example.android.dao.CourseDao;
import com.example.android.entity.CourseEntity;
import java.util.List;

public class CourseResponsive {
    private List<CourseEntity> getAll;
    private CourseDao courseDao;
    public CourseResponsive(Context context) {
        courseDao = Appdatabase.getINSTANCE(context).courseDao();
        getAll = courseDao.getAll();
    }

    public List<CourseEntity> getGetAll() {
        return getAll;
    }

    public void insert(CourseEntity course){
        new insertAsyncTask(courseDao).execute(course);
    }
    static class insertAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
        private CourseDao courseDao;

        insertAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(CourseEntity... data) {
            courseDao.insert(data[0]);
            return null;
        }
    }

    public void delete(CourseEntity course){
        new DeleteAsyncTask(courseDao).execute(course);
    }
    static class DeleteAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
        private CourseDao courseDao;

        public DeleteAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(CourseEntity... data) {
            courseDao.delete(data[0]);
            return null;
        }
    }
    // Update
    public void update(CourseEntity course){
        new UpdateAsyncTask(courseDao).execute(course);
    }
    static class UpdateAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
        private CourseDao courseDao;

        public UpdateAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(CourseEntity... data) {
            courseDao.update(data[0]);
            return null;
        }
    }
}

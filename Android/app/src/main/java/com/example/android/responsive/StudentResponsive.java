package com.example.android.responsive;
import android.content.Context;
import android.os.AsyncTask;
import com.example.android.dao.Appdatabase;
import com.example.android.dao.StudentDao;
import com.example.android.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentResponsive {
    private List<StudentEntity> studentEntityArrayList;
    private StudentDao dao;
    public StudentResponsive(Context context) {
        this.dao = Appdatabase.getINSTANCE(context).studentDao();
        studentEntityArrayList = dao.getAll();
    }

    public List<StudentEntity> getStudentEntityArrayList() {
        return studentEntityArrayList;
    }

    public void insert(StudentEntity studentEntity){
        new InsertAsyncTask(dao).execute(studentEntity);
    }
    public void delete(StudentEntity studentEntity){
        new DeleteAsyncTask(dao).execute(studentEntity);
    }

    public void update(StudentEntity studentEntity){
        new UpdateAsyncTask(dao).execute(studentEntity);
    }

    static class InsertAsyncTask extends AsyncTask<StudentEntity,Void,Void>{
        private StudentDao dao;

        public InsertAsyncTask(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentEntity... item) {
            dao.insert(item[0]);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<StudentEntity,Void,Void>{
        private StudentDao dao;

        public DeleteAsyncTask(StudentDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            dao.delete(studentEntities[0]);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<StudentEntity,Void,Void>{
        private StudentDao dao;

        public UpdateAsyncTask(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            dao.update(studentEntities[0]);
            return null;
        }
    }
}

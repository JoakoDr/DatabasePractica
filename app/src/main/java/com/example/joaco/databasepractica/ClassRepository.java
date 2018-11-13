package com.example.joaco.databasepractica;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;


    public class ClassRepository {
        private static com.example.joaco.databasepractica.ClassRepository ourInstance;

        private ClassDAO mWordDao;
        private LiveData<List<ModelClass>> mAllWords = new MutableLiveData<>();

        private ClassRepository(Application application) {
            ClassBbdd db = ClassBbdd.getDatabase(application);
            mWordDao = db.classDAO();
            mAllWords = mWordDao.getAllWords();
        }

        LiveData<List<ModelClass>> getAllWords() {
            return mAllWords;
        }

        public void insert (ModelClass word) {
            new insertAsyncTask(mWordDao).execute(word);
        }

        private static class insertAsyncTask extends AsyncTask<ModelClass, Void, Void> {

            private ClassDAO mAsyncTaskDao;

            insertAsyncTask(ClassDAO dao) {
                mAsyncTaskDao = dao;
            }


            @Override
            protected Void doInBackground(ModelClass... params) {
                mAsyncTaskDao.insert(params[0]);
                return null;
            }
        }


        private static ClassRepository INSTANCE;
        static ClassRepository getInstance(final Application context) {
            if (INSTANCE == null) {
                synchronized (ClassRepository.class) {
                    if (INSTANCE == null) INSTANCE = new ClassRepository(context);
                }
            }

            return INSTANCE;
        }

    }


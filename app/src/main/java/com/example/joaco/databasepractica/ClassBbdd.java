package com.example.joaco.databasepractica;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


@Database(entities = {ModelClass.class}, version = 1)
public abstract class ClassBbdd extends RoomDatabase {

    public abstract ClassDAO classDAO();
    private static ClassBbdd INSTANCE;
    static ClassBbdd getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClassBbdd.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClassBbdd.class, "ClassBBDD")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }

            }

        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
                    private final ClassDAO mDao;
                    String[] words = {"2018-2019", "Clase1", "200","Pedro","7.65"};
                    PopulateDbAsync(ClassBbdd db) {
                        mDao = db.classDAO();
                    }

                    @Override
                    protected Void doInBackground(final Void... params) {
                        // Start the app with a clean database every time.
                        // Not needed if you only populate the database
                        // when it is first created

                        List<ModelClass> data = new ArrayList<ModelClass>();
                        ModelClass uno = new ModelClass("2017-2018","Clase2","2000","Jaime","6.45");
                        ModelClass dos = new ModelClass("2016-2017","Clase3","22200","David","7.45");
                        ModelClass tres = new ModelClass("2014-2015","Clase4","550","Maraca","9.455");
                        ModelClass cuatro = new ModelClass("2013-2014","Clase5","11000","Luna","5.44");
                        data.add(uno);
                        data.add(dos);
                        data.add(tres);
                        data.add(cuatro);

                        mDao.deleteAll();

                        mDao.insertAll(data);

                        return null;
                    }
                }

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
}

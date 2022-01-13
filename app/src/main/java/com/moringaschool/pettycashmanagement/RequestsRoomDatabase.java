package com.moringaschool.pettycashmanagement;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.moringaschool.pettycashmanagement.Dao.PettyCashDao;
import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;

@Database(entities = {PettyCashRequest.class}, version = 21)
public abstract class RequestsRoomDatabase extends RoomDatabase {

    private static RequestsRoomDatabase requestsRoomDatabaseInstance;

    public abstract PettyCashDao pettyCashDao ();

    //Needs Dao instance.

    public static synchronized RequestsRoomDatabase getInstance(Context context){

        if (requestsRoomDatabaseInstance==null){
            requestsRoomDatabaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), RequestsRoomDatabase.class, "pettyCash_table")
                    .fallbackToDestructiveMigration()
                    .build();

        };
        return requestsRoomDatabaseInstance;
    }
}

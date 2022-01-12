package com.moringaschool.pettycashmanagement;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;

@Database(entities = PettyCashRequest.class, version = 1)
public abstract class RequestsRoomDatabase extends RoomDatabase {

    private static RequestsRoomDatabase requestsRoomDatabaseInstance;

    //Needs Dao instance.

    public static synchronized RequestsRoomDatabase getInstance(Context context){

        if (requestsRoomDatabaseInstance==null){
            requestsRoomDatabaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), RequestsRoomDatabase.class, "")
                    .fallbackToDestructiveMigration()
                    .build();

        };
        return requestsRoomDatabaseInstance;
    }
}
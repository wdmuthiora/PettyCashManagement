package com.moringaschool.pettycashmanagement.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PettyRepository {
    private RequestDao requestDao;

    private LiveData<List<Request>> allRequests;


    //constructor
    public PettyRepository (Application application){
        RequestsRoomDatabase database = RequestsRoomDatabase.getInstance(application);
        requestDao = database.requestDao();
        allRequests = requestDao.getAllRequest();
    }



    public void insert(Request request){
        //Use AsyncTask below.
        new InsertRequestAsyncTask(requestDao).execute(request);
    }

    public void update(Request request){
        //Use AsyncTask below.
        new UpdateRequestAsyncTask(requestDao).execute(request);
    }

    public void delete(Request request){
        //Use AsyncTask below.
        new DeleteRequestAsyncTask(requestDao).execute(request);
    }

    public void deleteAllNotes(){
        //Use AsyncTask below.
        new DeleteAllRequestAsyncTask(requestDao).execute();
    }

    public LiveData<List<Request>> getAllRequests(){//Room automatically execute database operations that return LiveData in the background thread, thus we don't have to do anything else to return LiveData.
        return allRequests;
    }


    //Make inserting a note to db an asynchronous task
    private static class InsertRequestAsyncTask extends AsyncTask<Request, Void, Void> { //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'pass Note object', 'no progress update', 'return nothing'>

        private RequestDao requestDao; //Used to make NoteDatabase operations.

        //constructor
        private InsertRequestAsyncTask(RequestDao requestDao){ // Since 'InsertNoteAsyncTask' is static, we cannot access the Repository's member NoteDao directly, thus we use the constructor.
            this.requestDao=requestDao;
        }

        @Override
        protected Void doInBackground(Request... requests) { //'Note... note' the parameters act like array.
            requestDao.insert(requests[0]);
            return null;
        }

    }

    //Make Updating a note to db an asynchronous task
    private static class UpdateRequestAsyncTask extends AsyncTask<Request, Void, Void>{ //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'pass Note object', 'no progress update', 'return nothing'>

        private RequestDao requestDao; //Used to make NoteDatabase operations.
        //constructor
        private UpdateRequestAsyncTask(RequestDao requestDao){ // Since 'InsertNoteAsyncTask' is static, we cannot access the Repository NoteDao directly, thus we use the constructor.
            this.requestDao=requestDao;
        }

        @Override
        protected Void doInBackground(Request... requests) { //'Note... note' the parameters act like array.
            requestDao.update(requests[0]);
            return null;
        }

    }

    //Make Deleting a note to db an asynchronous task
    private static class DeleteRequestAsyncTask extends AsyncTask<Request, Void, Void>{ //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'pass Note object', 'no progress update', 'return nothing'>

        private RequestDao requestDao; //Used to make NoteDatabase operations.

        //constructor
        private DeleteRequestAsyncTask(RequestDao requestDao){ // Since 'DeleteNoteAsyncTask' is static, we cannot access the Repository NoteDao directly, thus we use the constructor.
            this.requestDao=requestDao;
        }

        @Override
        protected Void doInBackground(Request... requests) { //'Note... note' the parameters act like array.
            requestDao.delete(requests[0]);
            return null;
        }

    }

    //Make Deleting all notes to db an asynchronous task
    private static class DeleteAllRequestAsyncTask extends AsyncTask<Void, Void, Void>{ //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'don't pass Note object', 'no progress update', 'return nothing'>

        private RequestDao requestDao; //Used to make NoteDatabase operations.

        //constructor
        private DeleteAllRequestAsyncTask(RequestDao noteDao){ // Since 'DeleteAllNoteAsyncTask' is static, we cannot access the Repository NoteDao directly, thus we use the constructor.
            this.requestDao=requestDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            requestDao.deleteAll();
            return null;
        }

    }
}

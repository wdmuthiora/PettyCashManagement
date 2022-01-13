package com.moringaschool.pettycashmanagement.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.moringaschool.pettycashmanagement.Dao.PettyCashDao;
import com.moringaschool.pettycashmanagement.RequestsRoomDatabase;

import java.util.List;

public class PettyRepository {

    private PettyCashDao pettyCashDao;

    private LiveData<List<PettyCashRequest>> allRequests;


    //constructor
    public PettyRepository (Application application){
        RequestsRoomDatabase database = RequestsRoomDatabase.getInstance(application);
        pettyCashDao = database.pettyCashDao();
        allRequests = pettyCashDao.getAllRequests();
    }



    public void insert(PettyCashRequest pettyCashRequest){
        //Use AsyncTask below.
        new InsertRequestAsyncTask(pettyCashDao).execute(pettyCashRequest);
    }

    public void update(PettyCashRequest pettyCashRequest){
        //Use AsyncTask below.
        new UpdateRequestAsyncTask(pettyCashDao).execute(pettyCashRequest);
    }

    public void delete(PettyCashRequest pettyCashRequest){
        //Use AsyncTask below.
        new DeleteRequestAsyncTask(pettyCashDao).execute(pettyCashRequest);
    }

    public void deleteAllRequests(){
        //Use AsyncTask below.
        new DeleteAllRequestAsyncTask(pettyCashDao).execute();
    }

    public LiveData<List<PettyCashRequest>> getAllRequests() {//Room automatically execute database operations that return LiveData in the background thread, thus we don't have to do anything else to return LiveData.
        return allRequests;
    }




    //Async calls to db.
    //Make inserting a Petty Cash Request to db an asynchronous task
    private static class InsertRequestAsyncTask extends AsyncTask<PettyCashRequest, Void, Void> { //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'pass Note object', 'no progress update', 'return nothing'>

        private PettyCashDao pettyCashDao; //Used to make Database operations.

        //constructor
        private InsertRequestAsyncTask(PettyCashDao pettyCashDao){ // Since 'InsertNoteAsyncTask' is static, we cannot access the Repository's member PettyCashDao directly, thus we use the constructor.
            this.pettyCashDao = pettyCashDao;
        }

        @Override
        protected Void doInBackground(PettyCashRequest... requests) { //'Note... note' the parameters act like array.
            pettyCashDao.insert(requests[0]);
            return null;
        }

    }

    //Make Updating a note to db an asynchronous task
    private static class UpdateRequestAsyncTask extends AsyncTask<PettyCashRequest, Void, Void>{ //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'pass PettyCashRequest object', 'no progress update', 'return nothing'>

        private PettyCashDao pettyCashDao; //Used to make NoteDatabase operations.
        //constructor
        private UpdateRequestAsyncTask(PettyCashDao pettyCashDao){ // Since 'InsertNoteAsyncTask' is static, we cannot access the Repository PettyCashDao directly, thus we use the constructor.
            this.pettyCashDao=pettyCashDao;
        }

        @Override
        protected Void doInBackground(PettyCashRequest... requests) { //'Note... note' the parameters act like array.
            pettyCashDao.update(requests[0]);
            return null;
        }

    }

    //Make Deleting a note to db an asynchronous task
    private static class DeleteRequestAsyncTask extends AsyncTask<PettyCashRequest, Void, Void>{ //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'pass PettyCash object', 'no progress update', 'return nothing'>

        private PettyCashDao pettyCashDao; //Used to make NoteDatabase operations.

        //constructor
        private DeleteRequestAsyncTask(PettyCashDao pettyCashDao){ // Since 'DeleteNoteAsyncTask' is static, we cannot access the Repository NoteDao directly, thus we use the constructor.
            this.pettyCashDao=pettyCashDao;
        }

        @Override
        protected Void doInBackground(PettyCashRequest... requests) { //'Note... note' the parameters act like array.
            pettyCashDao.delete(requests[0]);
            return null;
        }

    }

    //Make Deleting all notes to db an asynchronous task
    private static class DeleteAllRequestAsyncTask extends AsyncTask<Void, Void, Void>{ //'Static' so that it does NOT contain reference to the Repository itself, else a memory leak could occur.
        //AsyncTask<'don't pass Note object', 'no progress update', 'return nothing'>

        private PettyCashDao pettyCashDao; //Used to make NoteDatabase operations.

        //constructor
        private DeleteAllRequestAsyncTask(PettyCashDao pettyCashDao){ // Since 'DeleteAllNoteAsyncTask' is static, we cannot access the Repository NoteDao directly, thus we use the constructor.
            this.pettyCashDao=pettyCashDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pettyCashDao.deleteAllRequests();
            return null;
        }

    }
}

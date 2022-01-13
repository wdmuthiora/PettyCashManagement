package com.moringaschool.pettycashmanagement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;

import java.util.List;

public class RequestsViewModel extends AndroidViewModel {


    //We need to perform CRUD operations via Repository
    //private RequestsRepository repository;

    private LiveData<List<PettyCashRequest>> allRequests;

    public RequestsViewModel(@NonNull Application application) {
        super(application);
        //ask repository to fetch our data
        //repository = new RequestsRepository(application);
        //allRequests = repository.getAllRequests();
    }

    public void insert(PettyCashRequest pettyCashRequest) {
        //repository.insert(pettyCashRequest);
    }

    public void update(PettyCashRequest pettyCashRequest) {
        //repository.update(pettyCashRequest);
    }

    public void delete(PettyCashRequest pettyCashRequest) {
        //repository.delete(pettyCashRequest);
    }

    public void deleteAll(PettyCashRequest pettyCashRequest) {
        //repository.deleteAll(pettyCashRequest);
    }

    public LiveData<List<PettyCashRequest>> getAllRequests() {
        return allRequests;
    }

}

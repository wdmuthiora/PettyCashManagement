package com.moringaschool.pettycashmanagement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;
import com.moringaschool.pettycashmanagement.Models.PettyRepository;

import java.util.List;

public class RequestsViewModel extends AndroidViewModel {

    private PettyRepository pettyRepository;

    //We need to perform CRUD operations via Repository
    //private RequestsRepository repository;

    private LiveData<List<PettyCashRequest>> allRequests;

    //Constructor
    public RequestsViewModel(@NonNull Application application) {
        super(application);
        //ask repository to fetch our data
        pettyRepository = new PettyRepository(application);
        allRequests = pettyRepository.getAllRequests();
    }

    public void insert(PettyCashRequest pettyCashRequest) {
        pettyRepository.insert(pettyCashRequest);
    }

    public void update(PettyCashRequest pettyCashRequest) {
        pettyRepository.update(pettyCashRequest);
    }

    public void delete(PettyCashRequest pettyCashRequest) {
        pettyRepository.delete(pettyCashRequest);
    }

    public void deleteAll() {
        pettyRepository.deleteAllRequests();
    }

    public LiveData<List<PettyCashRequest>> getAllRequests() {
        return allRequests;
    }

}

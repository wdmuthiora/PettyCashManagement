package com.moringaschool.pettycashmanagement.Dao;


import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;

import retrofit2.http.Query;

@Dao
public interface PettyCashDao {

    @Insert
    void insert(PettyCashRequest pettyCashRequest);

    @Update
    void update(PettyCashRequest pettyCashRequest);



}

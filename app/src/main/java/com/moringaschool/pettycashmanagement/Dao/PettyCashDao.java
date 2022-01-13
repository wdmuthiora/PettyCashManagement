package com.moringaschool.pettycashmanagement.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;

import java.util.List;


@Dao
public interface PettyCashDao {

    @Insert
    void insert(PettyCashRequest pettyCashRequest);

    @Update
    void update(PettyCashRequest pettyCashRequest);

    @Delete
    void delete(PettyCashRequest pettyCashRequest);

    @Query("DELETE FROM `pettyCash_table`")
    void deleteAllRequests();

    @Query("SELECT * FROM `pettyCash_table` ORDER BY priority DESC")
    LiveData<List<PettyCashRequest>> getAllRequests(); //Return LiveData  of List of Petty Cash Requests. You can observe LiveData

}

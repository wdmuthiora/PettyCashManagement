package com.moringaschool.pettycashmanagement.Dao;


import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;



@Dao
public interface PettyCashDao {

    @Insert
    void insert(PettyCashRequest pettyCashRequest);

    @Update
    void update(PettyCashRequest pettyCashRequest);

    @Delete
    void delete(PettyCashRequest pettyCashRequest);

    @Query("DELETE FROM pettyCash_table")
    void deleteAllRequests();




}

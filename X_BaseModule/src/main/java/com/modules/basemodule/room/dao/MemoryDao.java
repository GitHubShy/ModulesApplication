package com.modules.basemodule.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.modules.basemodule.room.entity.Memory;

import java.util.List;

/**
 * https://developer.android.com/training/data-storage/room
 * https://blog.csdn.net/wuditwj/article/details/84381112
 * https://developer.android.com/training/data-storage/room/accessing-data
 */
@Dao
public interface MemoryDao {

    @Query("SELECT * FROM memory")
    List<Memory> getAll();

    @Query("SELECT id FROM memory")
    List<Integer> getAllIds();

    @Insert
    void insert(Memory... memories);

    @Delete
    void delete(Memory... memories);


    @Query("DELETE FROM memory where id = :id")
    int delete(int id);

    @Query("DELETE FROM memory")
    int deleteAll();



}

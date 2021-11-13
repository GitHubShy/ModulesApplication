package com.modules.basemodule.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.modules.basemodule.room.entity.Memory;

import java.util.List;

/**
 * https://developer.android.com/training/data-storage/room
 */
@Dao
public interface MemoryDao {

    @Query("SELECT * FROM memory")
    List<Memory> getAll();

    @Insert
    void insert(Memory... memories);


}

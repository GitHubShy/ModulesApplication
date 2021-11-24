package com.modules.basemodule.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** Android ROOM
 * https://developer.android.com/training/data-storage/room
 */
@Entity
public class Memory {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "path_key")
    public String pathKey;

    @ColumnInfo(name = "value_key")
    public String valueKey;

    public Memory(String pathKey, String valueKey) {
        this.pathKey = pathKey;
        this.valueKey = valueKey;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "id=" + id +
                ", pathKey='" + pathKey + '\'' +
                ", valueKey='" + valueKey + '\'' +
                '}';
    }
}

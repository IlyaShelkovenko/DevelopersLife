/**
 * Created by Ilia Shelkovenko on 30.08.2020.
 */
package com.gmail.hostov47.developerslife.data.local.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GifDao {
    @Query("select * from databaselatestgif")
    fun getLatestGifs(): LiveData<List<DatabaseLatestGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLatest(gifs: List<DatabaseLatestGif>)

    @Query("select * from databasetopgif")
    fun getTopGifs(): LiveData<List<DatabaseTopGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTop(gifs: List<DatabaseTopGif>)

    @Query("select * from databasehotgif")
    fun getHotGifs(): LiveData<List<DatabaseHotGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHot(gifs: List<DatabaseHotGif>)
}


@Database(entities = [DatabaseLatestGif::class,DatabaseTopGif::class,DatabaseHotGif::class], exportSchema = false, version = 1)
abstract class GifsDatabase : RoomDatabase() {
    abstract val gifDao: GifDao
}

private lateinit var INSTANCE: GifsDatabase

fun getDatabase(context: Context): GifsDatabase {
    synchronized(GifsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                GifsDatabase::class.java,
                "gifs").build()
        }
    }
    return INSTANCE
}
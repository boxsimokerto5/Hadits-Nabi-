package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HadithDao {
    @Query("SELECT * FROM hadiths ORDER BY hadithNumber ASC")
    fun getAllHadiths(): Flow<List<HadithEntity>>

    @Query("SELECT * FROM hadiths WHERE isBookmarked = 1 ORDER BY lastReadTimestamp DESC")
    fun getBookmarkedHadiths(): Flow<List<HadithEntity>>

    @Query("SELECT * FROM hadiths WHERE id = :id LIMIT 1")
    suspend fun getHadithById(id: String): HadithEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hadiths: List<HadithEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHadith(hadith: HadithEntity)

    @Query("UPDATE hadiths SET isBookmarked = :bookmarked, bookmarkFolder = :folder, lastReadTimestamp = :timestamp WHERE id = :id")
    suspend fun updateBookmark(id: String, bookmarked: Boolean, folder: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE hadiths SET personalNote = :note, isBookmarked = CASE WHEN :note != '' THEN 1 ELSE isBookmarked END, lastReadTimestamp = :timestamp WHERE id = :id")
    suspend fun updatePersonalNote(id: String, note: String, timestamp: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM hadiths")
    suspend fun getHadithCount(): Int
}

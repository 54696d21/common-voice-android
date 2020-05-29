package org.commonvoice.saverio_lib.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.commonvoice.saverio_lib.utils.getTimestampOfNowPlus
import java.sql.Timestamp

@Suppress("ArrayInDataClass")
@Entity(tableName = "recordings")
data class Recording(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val sentenceId: String,

    @ColumnInfo(name = "text")
    val sentenceText: String,

    @ColumnInfo(name = "lang")
    val language: String,

    @ColumnInfo(name = "audio", typeAffinity = ColumnInfo.BLOB)
    val audio: ByteArray,

    @ColumnInfo(name = "expiry")
    val expiryDate: Timestamp = getTimestampOfNowPlus(days = 7)

) {

    fun toFailedRecording() = FailedRecording(sentenceId, sentenceText, language, audio, expiryDate, 0)

}
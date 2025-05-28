import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReplyDto(
    val id: Int,
    val text: String,
    val author: String,
    val date: String
) : Parcelable

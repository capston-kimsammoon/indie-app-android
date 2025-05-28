import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentDto(
    val id: Int,
    val text: String,
    val author: String,
    val date: String,
    val replies: MutableList<ReplyDto> = mutableListOf()
) : Parcelable

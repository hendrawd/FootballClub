package ganteng.hendrawd.footballclub.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClubItem(
        val name: String,
        val description: String,
        val image: Int
) : Parcelable
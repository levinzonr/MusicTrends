package cz.levinzonr.trendee.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by nomers on 3/8/18.
 */
class ArtistImage(
        @SerializedName("#text") val link: String,
        val size: String) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun toString(): String {
        return "ArtistImage(link='$link', size='$size')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(link)
        parcel.writeString(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArtistImage> {
        override fun createFromParcel(parcel: Parcel): ArtistImage {
            return ArtistImage(parcel)
        }

        override fun newArray(size: Int): Array<ArtistImage?> {
            return arrayOfNulls(size)
        }
    }
}
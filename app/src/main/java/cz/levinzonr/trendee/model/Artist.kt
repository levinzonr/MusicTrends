package cz.levinzonr.trendee.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by levinrom on 3/7/18.
 */
class Artist(val name: String,
             val playcount: Int,
             val listeners: Int,
             val mbid: String,
             val url: String,
             @SerializedName("image") private val images: ArrayList<ArtistImage>?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readList(ArrayList<ArtistImage>())
            )


    constructor(name: String,
                playcount: Int,
                listeners: Int,
                mbid: String,
                url: String,
                images: ArrayList<ArtistImage>,
                ontour: Int,
                bio: Bio
    ) : this(name, playcount, listeners, mbid, url, images)


    fun getImage(size: Int = 0): String {
        if (images != null ) return images[size].link
        return name
    }


    inner class Bio(val published: String, val summary: String,  val content: String)




    companion object {

        const val IMAGE_SMALL = 0
        const val IMAGE_MEDIUM = 1
        const val IMAGE_LARGE = 2
        const val IMAGE_XLARGE = 3
        const val IMAGE_MEGA = 4


        @JvmField val CREATOR = object : Parcelable.Creator<Artist> {
            override fun createFromParcel(parcel: Parcel): Artist {
                return Artist(parcel)
            }

            override fun newArray(size: Int): Array<Artist?> {
                return arrayOfNulls(size)
            }
        }

    }

    override fun toString(): String {
        return "Artist(playcount=$playcount, listeners=$listeners, mbid='$mbid', images=$images)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(playcount)
        parcel.writeInt(listeners)
        parcel.writeString(mbid)
        parcel.writeString(url)
        parcel.writeList(images)
    }

    override fun describeContents(): Int {
        return 0
    }



}

private fun Parcel.readList(list: ArrayList<ArtistImage>) : ArrayList<ArtistImage> {
    readList(list, ArtistImage::class.java.classLoader)
    return list
}



package cz.levinzonr.trendee.model

import com.google.gson.annotations.SerializedName

/**
 * Created by levinrom on 3/7/18.
 */
class Artist( val name : String,
              val playcount: Int,
              val listeners: Int,
              val mbid: String,
             @SerializedName("image") private val images: ArrayList<ArtistImage>){

    constructor(name: String,
                playcount: Int,
                listeners: Int,
                mbid: String,
                images: ArrayList<ArtistImage>,
                 ontour: Int,
                 bio: Bio
                ) : this (name, playcount, listeners, mbid, images)


    companion object {
        const val IMAGE_SMALL = 0
        const val IMAGE_MEDIUM = 1
        const val IMAGE_LARGE = 2
        const val IMAGE_XLARGE = 3
        const val IMAGE_MEGA = 4
    }

    fun getImage(size: Int = 0) :  String {
        return images[size].link
    }

    inner class ArtistImage(@SerializedName("#text")val link: String, val size: String) {
        override fun toString(): String {
            return "ArtistImage(link='$link', size='$size')"
        }

    }

    inner class Bio(published: String,
                    summary: String,
                    content: String)

    override fun toString(): String {
        return "Artist(playcount=$playcount, listeners=$listeners, mbid='$mbid', images=$images)"
    }


}
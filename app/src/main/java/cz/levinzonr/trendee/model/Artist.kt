package cz.levinzonr.trendee.model

import com.google.gson.annotations.SerializedName

/**
 * Created by levinrom on 3/7/18.
 */
class Artist(private val name : String,
             private val playcount: Int,
             private val listeners: Int,
             private val mbid: String,
             @SerializedName("image") private val images: ArrayList<ArtistImage>){

    constructor(name: String,
                playcount: Int,
                listeners: Int,
                mbid: String,
                images: ArrayList<ArtistImage>,
                 ontour: Int,
                 bio: Bio
                ) : this (name, playcount, listeners, mbid, images)

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
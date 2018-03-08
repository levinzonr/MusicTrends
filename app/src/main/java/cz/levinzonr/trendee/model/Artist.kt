package cz.levinzonr.trendee.model

/**
 * Created by levinrom on 3/7/18.
 */
class Artist(private val name : String,
             private val playcount: Int,
             private val listeners: Int,
             private val mbid: String,
             private val images: ArrayList<ArtistImage>){

    constructor(name: String,
                playcount: Int,
                listeners: Int,
                mbid: String,
                images: ArrayList<ArtistImage>,
                ontour: Int,
                bio: Bio
                ) : this (name, playcount, listeners, mbid, images)

    inner class ArtistImage(link: String, size: String)

    inner class Bio(published: String,
                    summary: String,
                    content: String)
}
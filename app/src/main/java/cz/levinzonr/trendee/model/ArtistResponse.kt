package cz.levinzonr.trendee.model

import com.google.gson.annotations.SerializedName


/**
 * Created by nomers on 3/8/18.
 */
class ArtistResponse(val artists: Data ) {


    inner class Data(val artist: List<Artist>, @SerializedName("@attr") val attrs: Attributes) {
        override fun toString(): String {
            var str = "Data(attrs=$attrs)"
            for (artist in artist) {
                str += "\n$artist"
            }
            return str
        }
    }

    inner class Attributes(val page: Int,
                           val perPage: Int,
                           val totalPages: Int,
                           val total: Int) {
        override fun toString(): String {
            return "Attributes(page=$page, perPage=$perPage, totalPages=$totalPages, total=$total)"
        }
    }


}
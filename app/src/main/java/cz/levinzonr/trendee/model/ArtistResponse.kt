package cz.levinzonr.trendee.model

import com.google.gson.annotations.SerializedName


/**
 * Created by nomers on 3/8/18.
 */
class ArtistResponse {

    var artists: ListData? = null
    var artist: Artist? = null

    inner class ListData(val artist: List<Artist>, @SerializedName("@attr") val attrs: Attributes)


    inner class Attributes(val page: Int,
                           val perPage: Int,
                           val totalPages: Int,
                           val total: Int)


}
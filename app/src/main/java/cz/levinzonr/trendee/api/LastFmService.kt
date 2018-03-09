package cz.levinzonr.trendee.api

import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.model.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by nomers on 3/8/18.
 */
interface LastFmService {

    @GET("?method=chart.gettopartists&api_key=${LastFmClient.API_KEY}&format=json")
    fun getTrendingArtists() : Observable<ArtistResponse>

    @GET("?method=artist.getinfo&api_key=${LastFmClient.API_KEY}&format=json")
    fun getArtistInfo(@Query("mbid") artistId:String) : Observable<ArtistResponse>

}
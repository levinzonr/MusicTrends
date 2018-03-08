package cz.levinzonr.trendee.api

import cz.levinzonr.trendee.model.ArtistResponse
import retrofit2.http.GET
import rx.Observable

/**
 * Created by nomers on 3/8/18.
 */
interface LastFmService {

    @GET("?method=chart.gettopartists&api_key=${LastFmClient.API_KEY}&format=json")
    fun getTrendingArtists() : Observable<ArtistResponse>
}
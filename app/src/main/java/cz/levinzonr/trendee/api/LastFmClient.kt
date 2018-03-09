package cz.levinzonr.trendee.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cz.levinzonr.trendee.model.ArtistResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by nomers on 3/8/18.
 */
class LastFmClient {

    private val gson: Gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setLenient().create()

    private val retrofit : Retrofit  = Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    var service: LastFmService = retrofit.create(LastFmService::class.java)

    companion object {
        const val ROOT_URL = "http://ws.audioscrobbler.com/2.0/"
        const val API_KEY = "0b3c38cc4001ab622a02719ef52b0563"

        var instance: LastFmClient? = null

        fun instance(): LastFmClient {
            if (instance == null) {
                instance = LastFmClient()
            }
            return instance as LastFmClient
        }
    }

    fun getTrendingArtists(): Observable<ArtistResponse> {
        return service.getTrendingArtists()
    }

    fun getArtistDetail(artistId: String) : Observable<ArtistResponse> {
        return service.getArtistInfo(artistId)
    }

}
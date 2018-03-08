package cz.levinzonr.trendee.presenter

import android.util.Log
import cz.levinzonr.trendee.api.LastFmClient
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.model.ArtistResponse
import cz.levinzonr.trendee.screens.artistslist.ArtistsListFragment
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by nomers on 3/8/18.
 */
class ArtistListPresenter : Presenter<ArtistsListFragment>, Subscriber<ArtistResponse>(){

    companion object {
        const val TAG = "ArtistListPresenter"
    }

    private var view: ArtistsListFragment? = null
    private var subscription: Subscription? = null
    private var items: List<Artist>? = null


    override fun attachView(view: ArtistsListFragment) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    fun fetchTrendingPage(){
        if (subscription != null){
            subscription?.unsubscribe()
        }
        subscription = LastFmClient.instance().getTrendingArtists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this)


    }

    override fun onNext(t: ArtistResponse?) {
        if (t != null) {
            Log.d(TAG, t.artists.toString())

        } else {
            Log.d(TAG, "responce null")

        }
    }

    override fun onCompleted() {
        Log.d(TAG, "Items Loaded ${this.items}")
    }

    override fun onError(e: Throwable?) {
        Log.d(TAG, e.toString())
    }
}
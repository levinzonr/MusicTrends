package cz.levinzonr.trendee.presenter

import android.content.Context
import android.util.Log
import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.api.LastFmClient
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.model.ArtistResponse
import cz.levinzonr.trendee.screens.ViewCallbacks
import cz.levinzonr.trendee.screens.artistslist.ArtistsListFragment
import rx.Scheduler
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by nomers on 3/8/18.
 */
class ArtistListPresenter : Presenter<ViewCallbacks<List<Artist>>>, Subscriber<ArtistResponse>(){

    companion object {
        const val TAG = "ArtistListPresenter"
    }

    private var view: ViewCallbacks<List<Artist>>?= null
    private var subscription: Subscription? = null
    private lateinit var items: List<Artist>
    private var scheduler: Scheduler? = null

    override fun attachView(view: ViewCallbacks<List<Artist>>) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    fun fetchTrendingPage(){
        subscription?.unsubscribe()
        view?.onLoadingStart()
        if (scheduler == null ) {
            scheduler = Schedulers.io()
        }
        subscription = LastFmClient.instance().getTrendingArtists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .subscribe(this)


    }

    override fun onNext(t: ArtistResponse?) {
        if (t != null) {
            this.items = t.artists!!.artist
            Log.d(TAG, this.items.toString())
        } else {
            Log.d(TAG, "responce null")

        }
    }

    override fun onCompleted() {
        Log.d(TAG, "Items Loaded ${this.items}")
        view?.onLoadingFinished(this.items)
    }

    override fun onError(e: Throwable?) {
        view?.onError(e.toString())
        Log.d(TAG, e.toString())
    }
}
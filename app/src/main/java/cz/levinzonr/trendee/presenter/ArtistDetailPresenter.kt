package cz.levinzonr.trendee.presenter

import android.util.Log
import cz.levinzonr.trendee.api.LastFmClient
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.model.ArtistResponse
import cz.levinzonr.trendee.screens.ViewCallbacks
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by nomers on 3/9/18.
 */
class ArtistDetailPresenter : Presenter<ViewCallbacks<Artist>>, Subscriber<ArtistResponse>() {

    companion object {
        const val TAG = "ArtistDetailPResenter"
    }

    private var view : ViewCallbacks<Artist>? = null
    private var subscription: Subscription? = null

    private lateinit var artist: Artist

    override fun attachView(view: ViewCallbacks<Artist>) {
        this.view = view
    }

    fun fetchArtistDetail(artistId: String){
        subscription?.unsubscribe()
        view?.onLoadingStart()
        LastFmClient.instance().getArtistDetail(artistId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this)

    }

    override fun onNext(t: ArtistResponse?) {
        artist = t?.artist!!
        Log.d(TAG, "onNext: $artist")
    }

    override fun onCompleted() {
        Log.d(TAG, "onCompleted: $artist")
        view?.onLoadingFinished(artist)
    }

    override fun onError(e: Throwable?) {
        Log.d(TAG, "onError: $e")
        view?.onError()
    }

    override fun detachView() {
        view = null
        subscription?.unsubscribe()
    }
}
package cz.levinzonr.trendee.screens

/**
 * Created by nomers on 3/8/18.
 */
interface ViewCallbacks<in V> {

    fun onLoadingStart()
    fun onLoadingFinished(result: V)
    fun onError()
}
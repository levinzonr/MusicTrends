package cz.levinzonr.trendee.presenter

/**
 * Created by nomers on 3/8/18.
 */
interface Presenter<in V> {

    fun attachView(view: V)
    fun detachView()
}
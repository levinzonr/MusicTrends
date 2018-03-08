package cz.levinzonr.trendee.presenter

import android.view.View

/**
 * Created by nomers on 3/8/18.
 */
interface Presenter<in V> {

    fun attachView(view: V)
    fun detachView()
}
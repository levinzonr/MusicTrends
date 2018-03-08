package cz.levinzonr.trendee.screens.artistslist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.presenter.ArtistListPresenter


/**
 * A simple [Fragment] subclass.
 */
class ArtistsListFragment : Fragment() {

    private lateinit var presenter: ArtistListPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        presenter = ArtistListPresenter()
        presenter.attachView(this)
        presenter.fetchTrendingPage()
        return inflater!!.inflate(R.layout.fragment_artists_list, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}// Required empty public constructor

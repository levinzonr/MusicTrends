package cz.levinzonr.trendee.screens.artistslist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife

import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.presenter.ArtistListPresenter
import cz.levinzonr.trendee.screens.ViewCallbacks
import java.time.LocalDate


/**
 * A simple [Fragment] subclass.
 */
class ArtistsListFragment : Fragment(), ViewCallbacks<List<Artist>>{

    companion object {
        const val TAG = "ArtistListFragment"
    }

    private lateinit var adapter: ArtistsAdapter
    private lateinit var presenter: ArtistListPresenter

    @BindView(R.id.recycler_view)  lateinit var recyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        presenter = ArtistListPresenter()
        presenter.attachView(this)
        presenter.fetchTrendingPage()
        val view =  inflater!!.inflate(R.layout.fragment_artists_list, container, false)
        ButterKnife.setDebug(true)
        ButterKnife.bind(this, view)
        recyclerViewSetUp()
        return view

    }

    private fun recyclerViewSetUp() {
        adapter = ArtistsAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onLoadingStart() {
        Log.d(TAG, "Loading start")
    }

    override fun onLoadingFinished(result: List<Artist>) {
        Log.d(TAG, "Loading finished: $result")
        adapter.addItems(result)
    }

    override fun onError() {
        Log.d(TAG, "Error")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}// Required empty public constructor

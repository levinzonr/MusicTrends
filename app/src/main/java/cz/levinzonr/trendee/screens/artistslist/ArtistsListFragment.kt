package cz.levinzonr.trendee.screens.artistslist


import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

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
    @BindView(R.id.progress_indicator) lateinit var progressBar: ProgressBar
    @BindView(R.id.error_cotainer) lateinit var errorLayout: LinearLayout
    @BindView(R.id.error_message) lateinit var errorMessage: TextView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_artists_list, container, false)
        ButterKnife.setDebug(true)
        ButterKnife.bind(this, view)
        presenter = ArtistListPresenter()
        presenter.attachView(this)
        presenter.fetchTrendingPage()
        recyclerViewSetUp()
        return view

    }

    private fun recyclerViewSetUp() {
        adapter = ArtistsAdapter(context, context as ArtistsAdapter.ArtistAdapterListener)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(VerticalSpaceDecoration())
    }

    override fun onLoadingStart() {
        Log.d(TAG, "Loading start")
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        errorLayout.visibility = View.GONE
    }

    override fun onLoadingFinished(result: List<Artist>) {
        Log.d(TAG, "Loading finished: $result")
        adapter.addItems(result)
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
    }

    override fun onError(msg : String) {
        Log.d(TAG, "Error")
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        errorMessage.text = msg

    }
    @OnClick(R.id.button_retry)
    fun retry(){
        presenter = ArtistListPresenter()
        presenter.attachView(this)
        presenter.fetchTrendingPage()
    }



    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}// Required empty public constructor

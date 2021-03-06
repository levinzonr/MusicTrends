package cz.levinzonr.trendee.screens.artistdetail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.presenter.ArtistDetailPresenter
import cz.levinzonr.trendee.screens.MainActivity
import cz.levinzonr.trendee.screens.ViewCallbacks


/**
 * A simple [Fragment] subclass.
 */
class ArtistDetailFragment : Fragment(), ViewCallbacks<Artist>{


    companion object {
        const val TAG = "ArtistDetailFragment"
    }

    @BindView(R.id.artist_ontour_label) lateinit var onTourLabel : TextView
    @BindView(R.id.artist_content) lateinit var artistAbout : TextView

    @BindView(R.id.progress_indicator) lateinit var progressBar: ProgressBar
    @BindView(R.id.detail_layout) lateinit var detailLayout: LinearLayout
    @BindView(R.id.error_cotainer) lateinit var errorLayout: LinearLayout
    @BindView(R.id.error_message) lateinit var errorMessage: TextView

    @BindView(R.id.artist_playcount) lateinit var playcountTextView: TextView
    @BindView(R.id.artist_listeners) lateinit var listenersTextView: TextView

    private lateinit var presenter: ArtistDetailPresenter
    lateinit var artist: Artist

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val artist = activity.intent.getParcelableExtra<Artist>(MainActivity.EXTRA_ARTIST)
        val view =  inflater!!.inflate(R.layout.fragment_artist_detail, container, false)
        ButterKnife.bind(this, view)
        presenter = ArtistDetailPresenter()
        presenter.attachView(this)
        updateViews(artist)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_artist_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.menu_item_in_browser -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(artist.url)
                startActivity(intent)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onLoadingStart() {
        Log.d(TAG, "Started loading")
        detailLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
    }

    override fun onLoadingFinished(result: Artist) {
        detailLayout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.GONE
        artistAbout.text = result.bio?.content
        if (result.ontour == 1) {
            onTourLabel.visibility = View.VISIBLE
        }
    }

    override fun onError(msg : String) {
        Log.d(TAG, "Error: ${artist.mbid}")
        detailLayout.visibility = View.GONE
        onTourLabel.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        errorMessage.text = msg

    }

    private fun updateViews(artist: Artist) {
        this.artist = artist
        listenersTextView.text = getString(R.string.artist_listeners, this.artist.listeners)
        playcountTextView.text = getString(R.string.artist_playcounter, this.artist.playcount)
        presenter.fetchArtistDetail(artist.mbid)
    }

    @OnClick(R.id.button_retry)
    fun retry(){
        presenter = ArtistDetailPresenter()
        presenter.attachView(this)
        presenter.fetchArtistDetail(artist.mbid)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}// Required empty public constructor

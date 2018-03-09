package cz.levinzonr.trendee.screens.artistdetail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso

import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.screens.MainActivity
import de.hdodenhof.circleimageview.CircleImageView


/**
 * A simple [Fragment] subclass.
 */
class ArtistDetailFragment : Fragment() {


    @BindView(R.id.artist_bio) lateinit var artistBio: TextView
    lateinit var artist: Artist

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val artist = activity.intent.getParcelableExtra<Artist>(MainActivity.EXTRA_ARTIST)
        Toast.makeText(context, artist.toString(), Toast.LENGTH_SHORT).show()
        val view =  inflater!!.inflate(R.layout.fragment_artist_detail, container, false)
        ButterKnife.bind(this, view)
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

    private fun updateViews(artist: Artist) {
        this.artist = artist
    }

}// Required empty public constructor

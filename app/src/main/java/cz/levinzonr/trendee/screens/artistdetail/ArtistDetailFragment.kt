package cz.levinzonr.trendee.screens.artistdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    @BindView(R.id.artist_image) lateinit var artistImage: ImageView
    @BindView(R.id.artist_name) lateinit var artistName: TextView
    @BindView(R.id.artist_bio) lateinit var artistBio: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val artist = activity.intent.getParcelableExtra<Artist>(MainActivity.EXTRA_ARTIST)
        Toast.makeText(context, artist.toString(), Toast.LENGTH_SHORT).show()
        val view =  inflater!!.inflate(R.layout.fragment_artist_detail, container, false)
        ButterKnife.bind(this, view)
        updateViews(artist)
        return view
    }

    private fun updateViews(artist: Artist) {
        Picasso.get().load(artist.getImage(Artist.IMAGE_MEGA)).into(artistImage)
        artistName.text = artist.name
    }

}// Required empty public constructor

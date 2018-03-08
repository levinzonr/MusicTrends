package cz.levinzonr.trendee.screens.artistdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.screens.MainActivity


/**
 * A simple [Fragment] subclass.
 */
class ArtistDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val artist = activity.intent.getParcelableExtra<Artist>(MainActivity.EXTRA_ARTIST)
        Toast.makeText(context, artist.toString(), Toast.LENGTH_SHORT).show()
        return inflater!!.inflate(R.layout.fragment_artist_detail, container, false)
    }

}// Required empty public constructor

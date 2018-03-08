package cz.levinzonr.trendee.screens.artistslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by nomers on 3/8/18.
 */
class ArtistsAdapter(val context: Context) : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>(){
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private val items: ArrayList<Artist> = ArrayList()
    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        private val nameView: TextView = view.findViewById(R.id.artist_name)
        private val imageView: CircleImageView = view.findViewById(R.id.artist_image)
        private val timesPlayedView: TextView = view.findViewById(R.id.artist_playcount)
        private val listenersView: TextView = view.findViewById(R.id.artist_listeners)
        private val topView: TextView = view.findViewById(R.id.artist_top)

        fun bindView(artist: Artist, num: Int) {
            nameView.text = artist.name
            timesPlayedView.text= context.getString(R.string.artist_playcounter, artist.playcount)
            listenersView.text = context.getString(R.string.artist_listeners, artist.listeners)
            topView.text = context.getString(R.string.artist_top_number, num)
            Picasso.get().load(artist.getImage(Artist.IMAGE_MEDIUM)).into(imageView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindView(items[position], position+1)
    }

    fun addItems(list : List<Artist>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

}
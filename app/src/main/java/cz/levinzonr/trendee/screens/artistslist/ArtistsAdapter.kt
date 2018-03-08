package cz.levinzonr.trendee.screens.artistslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist

/**
 * Created by nomers on 3/8/18.
 */
class ArtistsAdapter(context: Context) : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>(){
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private val items: ArrayList<Artist> = ArrayList()
    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        private val nameView: TextView = view.findViewById(R.id.artist_name)

        fun bindView(artist: Artist) {
            nameView.text = artist.name
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
        holder?.bindView(items[position])
    }

    fun addItems(list : List<Artist>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

}
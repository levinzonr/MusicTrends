package cz.levinzonr.trendee.screens

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.screens.artistdetail.ArtistDetailActivity
import cz.levinzonr.trendee.screens.artistslist.ArtistsAdapter

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ArtistsAdapter.ArtistAdapterListener {

    companion object {
        const val EXTRA_ARTIST = "Artist"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onArtistSelected(artist: Artist) {
        val intent = Intent(this, ArtistDetailActivity::class.java)
        intent.putExtra(EXTRA_ARTIST, artist)
        startActivity(intent)
    }
}

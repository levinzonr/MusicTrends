package cz.levinzonr.trendee.screens.artistdetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import cz.levinzonr.trendee.R

import kotlinx.android.synthetic.main.activity_artist_detail.*

class ArtistDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)
        setSupportActionBar(toolbar)

    }

}

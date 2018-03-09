package cz.levinzonr.trendee.screens.artistdetail

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import cz.levinzonr.trendee.R
import cz.levinzonr.trendee.model.Artist
import cz.levinzonr.trendee.screens.MainActivity

import kotlinx.android.synthetic.main.activity_artist_detail.*

class ArtistDetailActivity : AppCompatActivity() {

    @BindView(R.id.toolbar_imageView)
    lateinit var toolbarImageView: ImageView

    @BindView(R.id.collapsing_toolbar)
    lateinit var collapsingToolbar: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)
        val artist = intent.getParcelableExtra<Artist>(MainActivity.EXTRA_ARTIST)
        Picasso.get().load(artist.getImage(Artist.IMAGE_MEGA)).into(toolbarImageView)
        collapsingToolbar.title = artist.name
    }

}

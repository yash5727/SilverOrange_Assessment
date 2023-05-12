package com.example.silverorange_assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.silverorange_assessment.databinding.ActivityMainBinding
import com.example.silverorange_assessment.presentation.viewmodel.VideoPlayerViewModel
import com.example.silverorange_assessment.presentation.viewmodel.VideoPlayerViewModelFactory
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var factory: VideoPlayerViewModelFactory
    private lateinit var viewModel: VideoPlayerViewModel

    private var player: ExoPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this, factory)[VideoPlayerViewModel::class.java]

        player = ExoPlayer.Builder(this)
            .build()
        viewBinding.playView.player = player
        viewModel.getVideosList()

        viewModel.videos.observe(this) {
            viewModel.totalItems = it.size
            setExoPlayer()
        }
        exoplayerListener()
        clickListeners()
    }

    private fun clickListeners(){
        viewBinding.btnPause.setOnClickListener {
            player?.playWhenReady = false
        }
        viewBinding.btnPlay.setOnClickListener {
            if (viewModel.isVideoEnded) {
                player?.seekTo(0)
            }
            viewBinding.progressBar.visibility = View.VISIBLE
            player?.playWhenReady = true
            player?.prepare()
            player?.play()
        }
        viewBinding.btnNext.setOnClickListener {
            viewBinding.btnNext.isClickable = ++viewModel.currentItem != (viewModel.totalItems - 1)
            viewBinding.btnPrev.isClickable = viewModel.currentItem != 0
            viewModel.playbackPosition = 0L
            setExoPlayer()
        }
        viewBinding.btnPrev.setOnClickListener {
            viewBinding.btnPrev.isClickable = --viewModel.currentItem != 0
            viewBinding.btnNext.isClickable = viewModel.currentItem != (viewModel.totalItems - 1)
            viewModel.playbackPosition = 0L
            setExoPlayer()
        }
    }

    private fun exoplayerListener(){
        player?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    viewBinding.btnPause.visibility = View.VISIBLE
                    viewBinding.btnPlay.visibility = View.GONE
                    viewBinding.progressBar.visibility = View.GONE
                } else {
                    viewBinding.btnPause.visibility = View.GONE
                    viewBinding.btnPlay.visibility = View.VISIBLE
                }
            }

            override fun onPlaybackStateChanged(@PlaybackStateCompat.State state: Int) {
                if (state == Player.STATE_IDLE) {
                    val error: ExoPlaybackException? = player!!.playerError
                    if (error != null) {
                        Toast.makeText(this@MainActivity,"Can't play this video",Toast.LENGTH_LONG).show()
                        viewBinding.progressBar.visibility = View.GONE
                    }
                }
                else if (state == Player.STATE_ENDED) {
                    viewBinding.btnPause.visibility = View.GONE
                    viewBinding.btnPlay.visibility = View.VISIBLE
                    viewModel.isVideoEnded = true
                } else
                    viewModel.isVideoEnded = false
            }
        })
    }

    private fun setExoPlayer() {
        player?.also {
            val item = viewModel.videos.value?.get(viewModel.currentItem)

            val mediaItem = MediaItem.fromUri(item!!.fullURL)

            it.setMediaItem(mediaItem)
            it.seekTo(viewModel.playbackPosition)
            it.playWhenReady = false

            viewBinding.title.text = item.title
            viewBinding.author.text = item.author.name
            viewBinding.description.text = item.description

            it.prepare()
        }
    }

    private fun releasePlayer() {
        viewBinding.btnPlay.visibility = View.VISIBLE
        viewBinding.btnPause.visibility = View.GONE
        player?.let { exoPlayer ->
            viewModel.playbackPosition = exoPlayer.currentPosition
            exoPlayer.release()
        }
        player = null
    }

    public override fun onResume() {
        super.onResume()
        if (player == null) {
            player = ExoPlayer.Builder(this)
                .build()
            viewBinding.playView.player = player
            viewModel.getVideosList()
        }

        player?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    viewBinding.btnPause.visibility = View.VISIBLE
                    viewBinding.btnPlay.visibility = View.GONE
                    viewBinding.progressBar.visibility = View.GONE
                } else {
                    viewBinding.btnPause.visibility = View.GONE
                    viewBinding.btnPlay.visibility = View.VISIBLE
                }
            }

            override fun onPlaybackStateChanged(@PlaybackStateCompat.State state: Int) {
                if (state == Player.STATE_IDLE) {
                    val error: ExoPlaybackException? = player!!.playerError
                    if (error != null) {
                        Toast.makeText(this@MainActivity,"Can't play this video",Toast.LENGTH_LONG).show()
                        viewBinding.progressBar.visibility = View.GONE
                    }
                }
                else if (state == Player.STATE_ENDED) {
                    viewBinding.btnPause.visibility = View.GONE
                    viewBinding.btnPlay.visibility = View.VISIBLE
                    viewModel.isVideoEnded = true
                } else
                    viewModel.isVideoEnded = false
            }
        })
    }

    public override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    public override fun onStop() {
        super.onStop()
        releasePlayer()
    }

}
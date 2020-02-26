package com.robincaroff.profilerdemo.ui.progressbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.core.ScopedAppActivity
import kotlinx.android.synthetic.main.activity_progressbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

class ProgressBarActivity : ScopedAppActivity() {

    private val startingTime = Date().time

    private val wakeLock: PowerManager.WakeLock by lazy {
        (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
            newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ProfilerDemo::ProgressBarWakelockTag")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progressbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        donutprogressbar_progressbar.maxProgress = PROGRESS_TARGET
        updateProgress()
    }

    override fun onStart() {
        super.onStart()
        wakeLock.acquire()
    }

    override fun onStop() {
        super.onStop()
        wakeLock.release()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateProgress() {
        launch {
            withContext(Dispatchers.IO) {
                delay(UPDATE_DELAY_MILLISECONDS)

                val elapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(Date().time - startingTime)
                withContext(Dispatchers.Main) {
                    if (donutprogressbar_progressbar.progress < PROGRESS_TARGET) {
                        donutprogressbar_progressbar.progress = elapsedSeconds.toFloat()
                        updateProgress()
                    }
                }
            }
        }
    }

    companion object {
        const val PROGRESS_TARGET = 100f
        const val UPDATE_DELAY_MILLISECONDS = 20L

        fun start(context: Context) {
            val intent = Intent(context, ProgressBarActivity::class.java)
            context.startActivity(intent)
        }
    }
}
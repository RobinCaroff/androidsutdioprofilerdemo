package com.robincaroff.profilerdemo.ui.progressbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.core.ScopedAppActivity
import kotlinx.android.synthetic.main.activity_progressbar.*

class ProgressBarActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progressbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        donutprogressbar_progressbar.maxProgress = PROGRESS_TARGET
        updateProgress()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateProgress() {
        Handler().postDelayed({
            val currentProgress = donutprogressbar_progressbar.progress
            if (currentProgress < PROGRESS_TARGET) {
                donutprogressbar_progressbar.progress = currentProgress + 1f
                updateProgress()
            }
        }, UPDATE_DELAY_MILLISECONDS)
    }

    companion object {
        const val PROGRESS_TARGET = 100f
        const val UPDATE_DELAY_MILLISECONDS = 100L

        fun start(context: Context) {
            val intent = Intent(context, ProgressBarActivity::class.java)
            context.startActivity(intent)
        }
    }
}
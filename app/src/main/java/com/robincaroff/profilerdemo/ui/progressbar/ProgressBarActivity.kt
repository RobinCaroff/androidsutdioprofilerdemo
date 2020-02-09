package com.robincaroff.profilerdemo.ui.progressbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.core.ScopedAppActivity
import kotlinx.android.synthetic.main.activity_progressbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        launch {
            withContext(Dispatchers.Default) {
                delay(UPDATE_DELAY_MILLISECONDS)
            }
            if (donutprogressbar_progressbar.progress < PROGRESS_TARGET) {
                donutprogressbar_progressbar.progress += 1f
                updateProgress()
            }
        }
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
package com.robincaroff.profilerdemo.ui

import android.os.Bundle
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.core.ScopedAppActivity
import com.robincaroff.profilerdemo.ui.apods.APODsListActivity
import com.robincaroff.profilerdemo.ui.progressbar.ProgressBarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ScopedAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button_main_apods.setOnClickListener {
            APODsListActivity.start(this)
        }

        button_main_progressbar.setOnClickListener {
            ProgressBarActivity.start(this)
        }
    }
}

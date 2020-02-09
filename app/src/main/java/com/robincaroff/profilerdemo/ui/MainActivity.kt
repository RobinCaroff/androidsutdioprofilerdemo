package com.robincaroff.profilerdemo.ui

import android.os.Bundle
import android.view.View
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.core.ScopedAppActivity
import com.robincaroff.profilerdemo.model.APOD
import com.robincaroff.profilerdemo.service.APODServiceImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ScopedAppActivity() {

    private val apodsAdapter = APODsAdapter {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button_main_start.setOnClickListener {
            fetchData()
        }

        with(recyclerview_main_apods) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                )
            )
            adapter = apodsAdapter
        }
    }

    private fun fetchData() {
        recyclerview_main_apods.visibility = View.VISIBLE
        button_main_start.visibility = View.GONE

        launch {
            var apods = emptyList<APOD>()
            withContext(Dispatchers.Default) {
                apods = APODServiceImpl.getAPODs()
            }
            apodsAdapter.updateData(apods)
        }
    }
}

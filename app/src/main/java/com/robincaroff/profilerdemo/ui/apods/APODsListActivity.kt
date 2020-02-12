package com.robincaroff.profilerdemo.ui.apods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.robincaroff.profilerdemo.R
import com.robincaroff.profilerdemo.core.ScopedAppActivity
import com.robincaroff.profilerdemo.model.APOD
import com.robincaroff.profilerdemo.service.APODServiceImpl
import kotlinx.android.synthetic.main.activity_apods_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APODsListActivity : ScopedAppActivity() {

    private val apodsAdapter =
        APODsAdapter {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apods_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //button_apodslist_start.setOnClickListener {
        fetchData()
        //}

        with(recyclerview_apodslist) {
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun fetchData() {
        progressbar_apodslist.visibility = View.VISIBLE
        button_apodslist_start.visibility = View.GONE

        launch {
            var apods = emptyList<APOD>()
            withContext(Dispatchers.Default) {
                apods = APODServiceImpl.getAPODs()
            }
            apodsAdapter.updateData(apods)
            progressbar_apodslist.visibility = View.GONE
            recyclerview_apodslist.visibility = View.VISIBLE
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, APODsListActivity::class.java)
            context.startActivity(intent)
        }
    }
}
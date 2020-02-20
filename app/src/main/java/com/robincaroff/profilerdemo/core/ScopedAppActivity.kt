package com.robincaroff.profilerdemo.core

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class ScopedAppActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onStop() {
        cancel()
        super.onStop()
    }
}
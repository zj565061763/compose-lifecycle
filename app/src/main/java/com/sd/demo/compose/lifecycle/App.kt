package com.sd.demo.compose.lifecycle

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.eventFlow
import androidx.lifecycle.repeatOnLifecycle
import com.sd.lib.compose.lifecycle.fAppLifecycle
import com.sd.lib.compose.lifecycle.fAppLifecycleScope
import kotlinx.coroutines.launch

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        fAppLifecycleScope.launch {
            fAppLifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                logMsg { "AppLifecycle repeatOnLifecycle" }
            }
        }

        fAppLifecycleScope.launch {
            fAppLifecycle.eventFlow.collect { event ->
                logMsg { "AppLifecycle event:$event" }
            }
        }
    }
}
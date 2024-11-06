package com.sd.demo.compose.lifecycle

import android.app.Application
import androidx.lifecycle.eventFlow
import com.sd.lib.compose.lifecycle.fAppLifecycle
import com.sd.lib.compose.lifecycle.fAppLifecycleScope
import kotlinx.coroutines.launch

class App : Application() {
   override fun onCreate() {
      super.onCreate()
      fAppLifecycleScope.launch {
         fAppLifecycle.eventFlow.collect { event ->
            logMsg { "AppLifecycle event:$event" }
         }
      }
   }
}
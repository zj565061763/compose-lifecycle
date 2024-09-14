package com.sd.demo.compose.lifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.sd.lib.compose.lifecycle.fAtLeastState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SampleAtLeastState : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      lifecycleScope.launch {
         while (true) {
            fAtLeastState()
            logMsg { "while" }
            delay(1_000)
         }
      }
   }
}
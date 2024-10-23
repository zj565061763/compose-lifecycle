package com.sd.lib.compose.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume

/**
 * 如果当前状态小于[state]，则挂起
 */
suspend fun LifecycleOwner.fAtLeastState(
   state: Lifecycle.State = Lifecycle.State.STARTED,
): Boolean {
   return lifecycle.fAtLeastState(state = state)
}

/**
 * 如果当前状态小于[state]，则挂起
 *
 * @return true-调用此方法时已经满足[state]；false-调用此方法时还未满足[state]挂起等待之后满足
 */
suspend fun Lifecycle.fAtLeastState(
   state: Lifecycle.State = Lifecycle.State.STARTED,
): Boolean {
   if (currentState == Lifecycle.State.DESTROYED) throw CancellationException()
   if (currentState.isAtLeast(state)) return true
   suspendCancellableCoroutine { continuation ->
      val observer = object : LifecycleEventObserver {
         override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event != Lifecycle.Event.ON_ANY && event.targetState >= state) {
               removeObserver(this)
               continuation.resume(Unit)
            }
         }
      }
      addObserver(observer)
      continuation.invokeOnCancellation { removeObserver(observer) }
   }
   return false
}
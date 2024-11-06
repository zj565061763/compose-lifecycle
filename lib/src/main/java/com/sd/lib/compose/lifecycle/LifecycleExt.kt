package com.sd.lib.compose.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.first
import kotlin.coroutines.cancellation.CancellationException

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
   require(state != Lifecycle.State.DESTROYED)
   if (currentState == Lifecycle.State.DESTROYED) throw CancellationException()
   if (currentState.isAtLeast(state)) return true
   currentStateFlow.first { it.isAtLeast(state) }
   return false
}
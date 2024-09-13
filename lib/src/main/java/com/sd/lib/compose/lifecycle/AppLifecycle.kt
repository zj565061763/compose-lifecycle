package com.sd.lib.compose.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

/** App生命周期 */
val fAppLifecycle: Lifecycle
    get() = ProcessLifecycleOwner.get().lifecycle

/** App生命周期绑定的[CoroutineScope] */
val fAppLifecycleScope: CoroutineScope
    get() = ProcessLifecycleOwner.get().lifecycleScope

/** App生命周期是否至少处于[Lifecycle.State.STARTED]状态 */
val fAppIsStarted: Boolean
    get() = fAppLifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
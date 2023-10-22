package com.teknophase.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun <T, M> StateFlow<T>.map(
    coroutineScope: CoroutineScope,
    sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(stopTimeoutMillis = DEFAULT_STOP_TIMEOUT_IN_MILLIS),
    mapper: (value: T) -> M
): StateFlow<M> = map { mapper(it) }.stateIn(
    coroutineScope,
    sharingStarted,
    mapper(value)
)

fun <T> StateFlow<T>.toFlow(): Flow<T> = this.drop(1)

const val DEFAULT_STOP_TIMEOUT_IN_MILLIS = 5_000L

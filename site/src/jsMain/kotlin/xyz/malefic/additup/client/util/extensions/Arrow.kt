package xyz.malefic.additup.client.util.extensions

import arrow.core.Either.Left
import arrow.core.Either.Right
import arrow.core.raise.Raise
import arrow.core.raise.catch
import arrow.core.raise.context.bind
import xyz.malefic.additup.common.model.Issue
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
context(_: Raise<Issue>)
inline fun <T> catching(f: () -> T): T {
    contract { callsInPlace(f, InvocationKind.AT_MOST_ONCE) }
    return catch(f, ::Right, ::Left).mapLeft { Issue from it }.bind()
}

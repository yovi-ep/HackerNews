package yovi.putra.hackernews.core.utils.state

sealed class LoaderState {
    object Show: LoaderState()
    object Hide: LoaderState()
}

sealed class ResultState {
    data class Success<out T: Any>(val data: T): ResultState()
    data class Error(val error: Throwable?): ResultState()
}
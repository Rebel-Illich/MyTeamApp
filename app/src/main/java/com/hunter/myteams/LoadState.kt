package com.hunter.myteams

data class LoadState<T>(val state: State, val data: T?, val error: String?){

    companion object{

        fun <T> success(data:T?): LoadState<T>{
            return LoadState(State.SUCCESS, data,null)
        }

        fun <T> error(msg: String?):LoadState<T>{
            return LoadState(State.ERROR,null, msg)
        }

        fun <T> loading(): LoadState<T>{
            return LoadState(State.LOADING,null,null)
        }
    }

}

enum class State {
    SUCCESS,
    ERROR,
    LOADING
}

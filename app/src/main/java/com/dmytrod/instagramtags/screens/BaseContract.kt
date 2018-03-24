package com.dmytrod.instagramtags.screens

interface BaseContract {
    interface Presenter<T> {
        fun setView(view : T)
        fun destroy()
    }
}
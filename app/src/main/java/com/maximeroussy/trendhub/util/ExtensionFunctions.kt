package com.maximeroussy.trendhub.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
  compositeDisposable.add(this)
}

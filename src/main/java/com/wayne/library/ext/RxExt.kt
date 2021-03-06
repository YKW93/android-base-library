package com.wayne.library.ext

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> = applyScheduler(Schedulers.io())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.applySchedulers(): Observable<T> = applyScheduler(Schedulers.io())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun Completable.applySchedulers(): Completable = applyScheduler(Schedulers.io())

private fun Completable.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())
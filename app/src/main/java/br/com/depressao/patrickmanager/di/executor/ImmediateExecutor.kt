package br.com.depressao.patrickmanager.di.executor

import rx.Scheduler
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Anderson Silva on 12/01/17.
 */
class ImmediateExecutor @Inject constructor() {

    val scheduler: Scheduler
        get() = Schedulers.immediate()
}
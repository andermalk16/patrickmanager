/*
 * Copyright (c) 2016. Anderson C F Silva
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package br.com.depressao.patrickmanager.di.executor

import rx.Scheduler
import rx.schedulers.Schedulers
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject


/**
 * Decorated [java.util.concurrent.ThreadPoolExecutor]
 */

class JobExecutor @Inject constructor() : ThreadExecutor {

    override val scheduler: Scheduler
        get() {
            return Schedulers.from(myThreadPoolExecutor)
        }

    override val executor: Executor
        get() {
            return myThreadPoolExecutor
        }

    private val COUNT_PROC = Runtime.getRuntime().availableProcessors()
    private val CORE_POOL_SIZE: Int = COUNT_PROC + 1
    private val MAXIMUM_POOL_SIZE: Int = COUNT_PROC * 2 + 1
    private val KEEP_ALIVE: Long = 1
    private val CALL_STACK: Long = 2000000

    private val yourFactory = object : ThreadFactory {
        private val mCount = AtomicInteger(1)

        override fun newThread(r: Runnable): Thread {
            val group = ThreadGroup("OxxyThreadGroup")
            return Thread(group, r, "OxxyBackgroundThread #" + mCount.andIncrement, CALL_STACK)
        }
    }

    private val sPoolWorkQueue = LinkedBlockingQueue<Runnable>(128)


    val myThreadPoolExecutor: Executor = ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, sPoolWorkQueue, yourFactory)
}

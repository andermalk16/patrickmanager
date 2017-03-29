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

package br.com.depressao.patrickmanager

import timber.log.Timber

/**
 * Created by anderson.silva on 20/06/2016.
 *
 */
object MyLog {
    val TAG = "OxxyMovelExterna"


    fun v(msg: String) {
        Timber.tag(TAG).v(msg)
    }

    fun v(msg: String, tr: Throwable) {
        Timber.tag(TAG).v(msg, tr)
    }

    fun d(msg: String) {
        Timber.tag(TAG).d(msg)
    }

    fun d(msg: String, tr: Throwable) {
        Timber.tag(TAG).d(msg, tr)
    }

    fun i(msg: String) {
        Timber.tag(TAG).i(msg)
    }

    fun i(msg: String, tr: Throwable) {
        Timber.tag(TAG).i(msg, tr)
    }

    fun w(msg: String) {
        Timber.tag(TAG).w(msg)
    }

    fun w(msg: String, tr: Throwable) {
        Timber.tag(TAG).w(msg, tr)
    }

    fun w(tr: Throwable) {
        Timber.tag(TAG).w(tr)
    }

    fun e(msg: String) {
        Timber.tag(TAG).e(msg)
    }

    fun e(msg: String, tr: Throwable) {
        Timber.tag(TAG).e(msg, tr)
    }

    fun e(tr: Throwable) {
        Timber.tag(TAG).e(tr)
    }
}

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

package br.com.depressao.patrickmanager.di.modules

import android.content.Context
import br.com.depressao.patrickmanager.MyApplication
import br.com.depressao.patrickmanager.di.executor.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(val application: MyApplication) {


    @Provides @Singleton fun provideApplicationContext(): Context = this.application

    @Provides @Singleton fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides @Singleton fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides @Singleton fun provideImmediateExecutorThread(): ImmediateExecutor = ImmediateExecutor()

}
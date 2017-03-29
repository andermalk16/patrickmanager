package br.com.depressao.patrickmanager.job

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import br.com.depressao.patrickmanager.MyApplication
import br.com.depressao.patrickmanager.MyLog
import br.com.depressao.patrickmanager.R
import io.hypertrack.smart_scheduler.Job
import io.hypertrack.smart_scheduler.SmartScheduler
import org.joda.time.DateTime


/**
 * Created by Anderson Silva on 12/01/17.
 */
object CumprimentoJob : SmartScheduler.JobScheduledCallback {

    private val JOB_PERIODIC_TASK_TAG = "br.com.depressao.patrickmanager.job.CumprimentoJob"

    fun iniciarJob() {
        val jobScheduler = SmartScheduler.getInstance(MyApplication.Components.applicationComponent.context())
        if (!jobScheduler.contains(JobIdentity.JOB_ID_CUMPRIMENTO)) {
            val job = createJob()
            if (job != null) {
                // Schedule current created job
                if (jobScheduler.addJob(job)) {
                    MyLog.d("Job CumprimentoJob agendado!!")
                }
            }
        }
    }

    override fun onJobScheduled(context: Context?, job: Job?) {
        val hora = DateTime.now().hourOfDay
        val minuto = DateTime.now().minuteOfHour

        val msg = if(hora == 7 && minuto == 0) {
            "Bom dias"
        }else if(hora == 12 && minuto == 0) {
            "Boa Tardes"
        }else if(hora == 18 && minuto == 0) {
            "Boas Noites"
        }else{
            null
        }
        msg?.let {
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentText("Patrick pergunta")
                    .setContentTitle(msg)
                    .setAutoCancel(true)

            notificationManager?.notify(JobIdentity.JOB_ID_CUMPRIMENTO, mBuilder.build())
        }

    }

    private fun createJob(): Job? =
            Job.Builder(JobIdentity.JOB_ID_CUMPRIMENTO, this, Job.Type.JOB_TYPE_PERIODIC_TASK, JOB_PERIODIC_TASK_TAG)
                    .setRequiredNetworkType(Job.NetworkType.NETWORK_TYPE_ANY)
                    .setRequiresCharging(false)
                    .setPeriodic(1000 * 60)
                    .build()

}
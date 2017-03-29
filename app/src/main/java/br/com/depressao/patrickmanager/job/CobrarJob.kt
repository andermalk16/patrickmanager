package br.com.depressao.patrickmanager.job

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import br.com.depressao.patrickmanager.MyApplication
import br.com.depressao.patrickmanager.MyLog
import br.com.depressao.patrickmanager.R
import io.hypertrack.smart_scheduler.Job
import io.hypertrack.smart_scheduler.SmartScheduler
import org.joda.time.DateTime
import java.util.*
import android.content.Intent




/**
 * Created by Anderson Silva on 12/01/17.
 */
object CobrarJob : SmartScheduler.JobScheduledCallback {

    private val JOB_PERIODIC_TASK_TAG = "br.com.depressao.patrickmanager.job.CobrarJob"

    fun iniciarJob() {
        val jobScheduler = SmartScheduler.getInstance(MyApplication.Components.applicationComponent.context())
        if (!jobScheduler.contains(JobIdentity.JOB_ID_COBRAR)) {
            val job = createJob()
            if (job != null) {
                // Schedule current created job
                if (jobScheduler.addJob(job)) {
                    MyLog.d("Job CobrarJob agendado!!")
                }
            }
        }
    }

    override fun onJobScheduled(context: Context?, job: Job?) {
        val minuto = DateTime.now().minuteOfHour().get()
        if(minuto % 2 ==0) {
            val randomGenerator = Random()
            val list = arrayListOf("Já lançou as horas?", "Quando você termina a tarefa?", "Quando vai subir?", "Quando libera para testes?")
            val index = randomGenerator.nextInt(list.size)
            val item = list[index]
            System.out.println("Managers choice this week" + item + "our recommendation to you");
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentText("Patrick disse")
                    .setContentTitle(item)
                    .setAutoCancel(true)

            //Yes intent
            val yesReceive = Intent()
            yesReceive.action = "YES_ACTION"
            val pendingIntentYes = PendingIntent.getBroadcast(context, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT)
            mBuilder.addAction(R.drawable.ic_announcement_black_24dp, "Meu Pau!", pendingIntentYes)

            //No intent
            val noReceive = Intent()
            noReceive.action = "NO_ACTION"
            val pendingIntentNo = PendingIntent.getBroadcast(context, 12345, noReceive, PendingIntent.FLAG_UPDATE_CURRENT)
            mBuilder.addAction(R.drawable.ic_announcement_black_24dp, "Não sei", pendingIntentNo)

            notificationManager?.notify(JobIdentity.JOB_ID_COBRAR, mBuilder.build())
        }
    }

    private fun createJob(): Job? =
            Job.Builder(JobIdentity.JOB_ID_COBRAR, this, Job.Type.JOB_TYPE_PERIODIC_TASK, JOB_PERIODIC_TASK_TAG)
                    .setRequiredNetworkType(Job.NetworkType.NETWORK_TYPE_ANY)
                    .setRequiresCharging(false)
                    .setPeriodic(1000 * 60)
                    .build()

}
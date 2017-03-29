package br.com.depressao.patrickmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.depressao.patrickmanager.job.CobrarJob;
import br.com.depressao.patrickmanager.job.CumprimentoJob;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CobrarJob.INSTANCE.iniciarJob();
        CumprimentoJob.INSTANCE.iniciarJob();
    }
}

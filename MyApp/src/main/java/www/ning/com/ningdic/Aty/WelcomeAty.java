package www.ning.com.ningdic.Aty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import www.ning.com.ningdic.R;


/**
 * Created by win10 on 2017/1/8.
 */
public class WelcomeAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeAty.this, MainActivity.class);
                startActivity(intent);
                WelcomeAty.this.finish();
            }
        }, 1000);
    }
}

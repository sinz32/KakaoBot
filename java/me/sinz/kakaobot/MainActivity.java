package me.sinz.kakaobot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        Switch bot = new Switch(this);
        bot.setText("봇 활성화");
        bot.setChecked(SinZ.loadSettings(this, "bot_onoff", false));
        bot.setOnCheckedChangeListener((cb, b) -> SinZ.saveSettings(this, "bot_onoff", false));
        layout.addView(bot);

        Button noti = new Button(this);
        noti.setText("알림 접근 권한 부여");
        noti.setOnClickListener(view -> startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)));
        layout.addView(noti);

        TextView txt = new TextView(this);
        txt.setText("소스 파일 위치 : " + SinZ.SOURCE_PATH);
        layout.addView(txt);

        layout.addView(SinZ.copyright(this));

        int pad = SinZ.dip2px(this, 16);
        layout.setPadding(pad, pad, pad, pad);
        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }
}
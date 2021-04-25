package com.example.makepinnedshortcutfromwidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortcutManager shortcutManager =
                        getApplication().getSystemService(ShortcutManager.class);

                if (shortcutManager.isRequestPinShortcutSupported()) {

                    int shortcutCount = shortcutManager.getDynamicShortcuts().size();

                    Intent intent = new Intent(Intent.ACTION_VIEW, null ,getApplication(), MainActivity3.class);

                    ShortcutInfo info = new ShortcutInfo.Builder(getApplication(), "fuga" + shortcutCount)
                            .setShortLabel("LGTM " + shortcutCount)
                            .setIcon(Icon.createWithResource(getApplication(), R.drawable.ic_launcher_background))
                            .setIntent(intent)
                            .build();

                    Intent pinnedShortcutCallbackIntent =
                            shortcutManager.createShortcutResultIntent(info);

                    PendingIntent successCallback = PendingIntent.getBroadcast(getApplication(),
                            /* request code */ 0,
                            pinnedShortcutCallbackIntent,
                            /* flags */ 0);

                    shortcutManager.requestPinShortcut(info,
                            successCallback.getIntentSender());
                }

            }
        });
    }
}
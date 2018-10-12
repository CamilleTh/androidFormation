package com.societe.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button ajoutNotificationBouton;
    private Button suppressionNotificationBouton;
    public static final int NOTIFICATION_ID = 42;
    private int nombreNotification =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajoutNotificationBouton = (Button) findViewById(R.id.ajouteNotification);
        ajoutNotificationBouton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Ajout d'une notification",
                        Toast.LENGTH_SHORT).show();
                creerNotification();
            }
        });

        suppressionNotificationBouton =
                (Button)findViewById(R.id.supprimeNotification);
        suppressionNotificationBouton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Suppression d'une notification",
                        Toast.LENGTH_SHORT).show();
                deleteNotification();
            }
        });
    }

    private final void creerNotification(){
        //Récupération du titre et description de la notification
        final String notificationTitre =
                getResources().getString(R.string.notification_titre);
        final String notificationDesc =
                getResources().getString(R.string.notification_desc);

        final NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Création de la notification avec spécification de l'icone de la
        // notification et le texte qui apparait à la création de la notification
        final NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this);
        builder.setContentTitle(notificationTitre);
        builder.setContentText(notificationDesc);
        builder.setSmallIcon(R.mipmap.notification);
        builder.setNumber(++nombreNotification);
        builder.setVibrate(new long[] {1000,500,1000});

        // Definition de la redirection au moment du clique sur la notification.
        // Dans notre cas la notification redirige vers notre application
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.mipmap.ic_launcher, "Mon Action", pendingIntent);

        // Notification
        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void deleteNotification(){
        final NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // la suppression de la notification se fait grâce a son ID
        notificationManager.cancel(NOTIFICATION_ID);
    }

}

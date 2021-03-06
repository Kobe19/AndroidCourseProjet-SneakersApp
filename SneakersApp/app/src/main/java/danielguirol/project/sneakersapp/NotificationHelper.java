package danielguirol.project.sneakersapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel = "channelid";
    public static final String channel2 = "channelid2";
    public static final String channel3 = "channelid3";
    public static final String channelname = "channel";
    public static final String channelname2 = "channel2";
    public static final String channelname3 = "channel3";


    private NotificationManager nmanager;

    //To avoid to call this notification with an lower android version 8.0
    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel(){
        //state zone to show a little lighting on the left and we set as default for that to not interrupting drop down text !
        NotificationChannel notchan = new NotificationChannel(channel, channelname, NotificationManager.IMPORTANCE_DEFAULT);
        notchan.enableLights(true);
        notchan.enableVibration(true);
        notchan.setLightColor(R.color.design_default_color_primary);
        notchan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManage().createNotificationChannel(notchan);

        NotificationChannel notchan2 = new NotificationChannel(channel2, channelname2, NotificationManager.IMPORTANCE_DEFAULT);
        notchan2.enableLights(true);
        notchan2.enableVibration(true);
        notchan2.setLightColor(R.color.design_default_color_primary);
        notchan2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManage().createNotificationChannel(notchan2);

        NotificationChannel notchan3 = new NotificationChannel(channel3, channelname3, NotificationManager.IMPORTANCE_DEFAULT);
        notchan3.enableLights(true);
        notchan3.enableVibration(true);
        notchan3.setLightColor(R.color.design_default_color_primary);
        notchan3.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManage().createNotificationChannel(notchan3);
    }



    //Building the channel
    public NotificationManager getManage(){
        if(nmanager == null){
            nmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return nmanager;
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       CREATING METHOD FOR CREATING LABEL IN THE CALL ACTIVITY                                                                                                                                          *                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    public NotificationCompat.Builder getchanelnotif(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channel)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.icon);
    }

    public NotificationCompat.Builder getchanelnotif2(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channel2)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.icon);
    }

    public NotificationCompat.Builder getchanelnotif3(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channel3)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.icon);
    }
}

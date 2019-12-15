package com.example.a4_5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.util.Date;

public class XqWidgetProvider extends AppWidgetProvider{
    @Override
    public void onDisabled(Context context)
    {
        context.stopService(new Intent(context,MyService.class));
    }

    private TimeMsg tm;
    @Override
    public void onEnabled (Context context)
    {
        //tm = new TimeMsg();
        //tm.start(context);
        context.startService(new Intent(context,MyService.class));
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
        PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, new Intent(context, editActive.class),PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setOnClickPendingIntent(R.id.textView1, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds, rv);
        return;
    }
    public void onReceive(Context context, Intent intent){

        /*
        if (intent.getAction().equals("wyf.action.update_xq")) {

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, new Intent(context, editActive.class),PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.textView1, pendingIntent);
            String msg = intent.getStringExtra("xxq");
            rv.setTextViewText(R.id.textView1, msg);



            AppWidgetManager appWidgetManger = AppWidgetManager.getInstance(context);
            int[] appIds = appWidgetManger.getAppWidgetIds
                    (
                            new ComponentName
                                    (
                                            context,
                                            XqWidgetProvider.class
                                    )
                    );
            appWidgetManger.updateAppWidget(appIds, rv);
            return;
        }
        else */
        if (intent.getAction().equals("wyf.action.time_xq")) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, new Intent(context, editActive.class),PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.textView1, pendingIntent);
            String msg = intent.getStringExtra("xxq");
            rv.setTextViewText(R.id.textView2, msg);
            String tx = intent.getStringExtra("xxq1");
            rv.setTextViewText(R.id.textView1, tx);

            AppWidgetManager appWidgetManger = AppWidgetManager.getInstance(context);
            int[] appIds = appWidgetManger.getAppWidgetIds
                    (
                            new ComponentName
                                    (
                                            context,
                                            XqWidgetProvider.class
                                    )
                    );
            appWidgetManger.updateAppWidget(appIds, rv);
            return;
        }


        super.onReceive(context, intent);
    }

}

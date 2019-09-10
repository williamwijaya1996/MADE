package com.example.movieandtvshowcatalogue;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.movieandtvshowcatalogue.service.StackWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class ImagesFavoriteWidget extends AppWidgetProvider {

    private static final String TOAST_ACTION = "com.dicoding.picodiploma.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.dicoding.picodiploma.EXTRA_ITEM";
    private static String WIDGET_ID_EXTRA = "widget_id_extra";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.image_favorite_widget);
        views.setRemoteAdapter(R.id.stack_view, intent);
        views.setEmptyView(R.id.stack_view, R.id.empty_view);
        Intent toastIntent = new Intent(context, ImagesFavoriteWidget.class);
        toastIntent.setAction(ImagesFavoriteWidget.TOAST_ACTION);
        toastIntent.putExtra(WIDGET_ID_EXTRA, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, appWidgetId, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null) {

                int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
                Toast.makeText(context, "Touched view " + viewIndex, Toast.LENGTH_SHORT).show();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.image_favorite_widget);
                views.setRemoteAdapter(R.id.stack_view, intent);
                views.setEmptyView(R.id.stack_view, R.id.empty_view);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.stack_view);
                appWidgetManager.updateAppWidget(appWidgetId,views);


        }
    }
}


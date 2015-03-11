package com.android.vk_gram;

import android.app.Application;

import com.vk.sdk.VKScope;

/**
 * Created by Yurij on 10.03.2015.
 */
public class VKGramApplication extends Application {
    public static final String APP_KEY_TOKEN = "VK_ACCESS_TOKEN";
    public static final String APP_ID = "4821077";
    public static MainActivity activity ;
    public static final String[] APP_SCOPE = new String[]{
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.NOHTTPS,
            VKScope.AUDIO,
            VKScope.VIDEO,
            VKScope.DIRECT,
            VKScope.GROUPS,
            VKScope.PAGES,
            VKScope.NOTES,
            VKScope.OFFLINE,
            VKScope.FRIENDS,
            VKScope.DOCS,
            VKScope.STATS,
            VKScope.STATUS,
            VKScope.NOTIFY,
            VKScope.ADS,
            VKScope.MESSAGES,
            VKScope.NOTIFICATIONS
    };



}

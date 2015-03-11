package com.android.vk_gram;

import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

/**
 * Created by Yurij on 10.03.2015.
 */
public class APIUtil {
    public static void getMyselfInfo(VKRequest.VKRequestListener listener) {
        VKParameters params = new VKParameters();
        params.put("fields", "photo_100");
        final VKRequest request = new VKRequest("users.get", params);
        request.executeWithListener(listener);
    }
}

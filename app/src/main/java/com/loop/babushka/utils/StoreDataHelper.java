package com.loop.babushka.utils;

import android.app.Activity;

import com.loop.babushka.model.User;

/**
 * Created by Alyona on 03.02.2018.
 */

public class StoreDataHelper {
    public static final String TAG = "Alyo";
    private Activity activity;
    private PreferenceHelper preferenceHelper;


    public StoreDataHelper(Activity activity) {
        this.activity = activity;
        preferenceHelper = new PreferenceHelper(activity);
    }


    public void storeUserToPrefs(User user) {
        preferenceHelper.putUserId(user.getId().toString());
        preferenceHelper.putFirstName(user.getFname());
        preferenceHelper.putLastName(user.getLname());
        preferenceHelper.putPhone(user.getPhone());
        preferenceHelper.putEmail(user.getEmail());
        preferenceHelper.putGender(user.getGender());
        preferenceHelper.putPicture(user.getPictureUrl());
        preferenceHelper.putSessionToken(user.getSessionToken());
        //preferenceHelper.putTokenExpiry(user.getTokenExpiry());
       // preferenceHelper.putLoginBy(user.getLoginBy());
       // preferenceHelper.putSocialId(user.getSocialUniqueId());
        preferenceHelper.putPaymentMode(user.getPayModeStatus());
        preferenceHelper.putCurrency(user.getCurrency());
        //preferenceHelper.putCountry(user.getCountry());
    }
}

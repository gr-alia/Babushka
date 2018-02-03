package com.loop.babushka.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alyona on 03.02.2018.
 */

public class PreferenceHelper {
    private SharedPreferences appPrefs;

    private final String USER_ID = "user_id";
    private final String FNAME = "first_name";
    private final String LNAME = "last_name";
    private final String PHONE = "phone";
    private final String EMAIL = "email";
    private final String GENDER = "gender";
    private final String PICTURE_URL = "picture_url";
    private final String SESSION_TOKEN = "session_token";
    private final String TOKEN_EXPIRY = "token_expiry";
    private final String LOGIN_BY = "login_by";
    private final String SOCIAL_ID = "social_id";
    private final String PAYMENT_MODE = "payment_mode";
    private final String CURRENCY = "currency";
    private final String COUNTRY = "country";

    //TODO consider remove these because of way of handling notifications
    private final String DEVICE_TOKEN = "device_token";
    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";

    //for taxi request
    private final String REQUEST_ID = "request_id";
    private final String DRIVER_ID = "driver_id";
    private final String REQUEST_TYPE = "type";

    public PreferenceHelper(Context context) {
        appPrefs = context.getSharedPreferences(Const.PREF_NAME,
                Context.MODE_PRIVATE);

    }


    public void putUserId(String userId) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(USER_ID, userId);
        edit.commit();
    }

    public void putFirstName(String fname) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(FNAME, fname);
        edit.commit();
    }

    public String getFirstName() {
        return appPrefs.getString(FNAME, "");
    }
    public void putLastName(String name) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(LNAME, name);
        edit.commit();
    }

    public String getLastName() {
        return appPrefs.getString(LNAME, "");
    }


    public void putPhone(String phone) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(PHONE, phone);
        edit.commit();
    }

    public String getPhone() {
        return appPrefs.getString(PHONE, "");
    }

    public void putEmail(String email) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(EMAIL, email);
        edit.commit();
    }

    public String getEmail() {
        return appPrefs.getString(EMAIL, null);
    }
    public void putGender(String gender) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(GENDER, gender);
        edit.commit();
    }

    public String getGender() {
        return appPrefs.getString(GENDER, null);
    }
    public void putPicture(String picture) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(PICTURE_URL, picture);
        edit.commit();
    }

    public void putRequestId(int reqId) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putInt(REQUEST_ID, reqId);
        edit.commit();
    }

    public int getRequestId() {
        return appPrefs.getInt(REQUEST_ID, Const.NO_REQUEST);
    }


    public String getPicture() {
        return appPrefs.getString(PICTURE_URL, null);
    }

    public void putSocialId(String id) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(SOCIAL_ID, id);
        edit.commit();
    }

    public String getSocialId() {
        return appPrefs.getString(SOCIAL_ID, null);
    }

    public String getUserId() {
        return appPrefs.getString(USER_ID, null);

    }

    public void putDeviceToken(String deviceToken) {
        SharedPreferences.Editor edit = appPrefs.edit();
        //edit.putString(DEVICE_TOKEN, "qwertyu");
        edit.putString(DEVICE_TOKEN, deviceToken);
        edit.commit();
    }

    public String getDeviceToken() {
        return appPrefs.getString(DEVICE_TOKEN, null);

    }

    public void putSessionToken(String sessionToken) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(SESSION_TOKEN, sessionToken);
        edit.commit();
    }

    public String getSessionToken() {
        return appPrefs.getString(SESSION_TOKEN, null);

    }
    public void putDriverId(String driverId) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(DRIVER_ID, driverId);
        edit.commit();
    }

    public String getDriverId() {
        return appPrefs.getString(DRIVER_ID, "");

    }

    public void putRequestType(String req_type) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(REQUEST_TYPE, req_type);
        edit.commit();
    }

    public String getRequestType() {
        return appPrefs.getString(REQUEST_TYPE, "1");

    }

    public void putLoginBy(String loginBy) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(LOGIN_BY, loginBy);
        edit.commit();
    }


    public void putPaymentMode(String payment_mode) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(PAYMENT_MODE, payment_mode);
        edit.commit();
    }

    public String getPaymentMode() {
        return appPrefs.getString(PAYMENT_MODE, "");
    }

    public void putRegisterationID(String RegID) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(PROPERTY_REG_ID, RegID);
        edit.apply();
    }

    public String getRegistrationID() {
        return appPrefs.getString(PROPERTY_REG_ID, "");
    }


    public void putAppVersion(int version) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putInt(PROPERTY_APP_VERSION, version);
        edit.apply();
    }

    public int getAppVersion() {
        return appPrefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
    }

    public void putCurrency(String currency) {
        SharedPreferences.Editor edit = appPrefs.edit();
        edit.putString(CURRENCY, currency);
        edit.commit();
    }

    public String getCurrency() {
        return appPrefs.getString(CURRENCY, "");

    }




    public void logout() {
        putUserId(null);
        putSessionToken(null);
        putSocialId(null);
        putLoginBy(null);
        putPaymentMode(null);
    }
}

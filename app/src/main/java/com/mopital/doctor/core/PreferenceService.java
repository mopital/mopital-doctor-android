package com.mopital.doctor.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.mopital.doctor.utils.Constants;

public class PreferenceService {

	/**
	 * Called to save supplied value in shared preferences against given key.
	 * 
	 * @param context
	 *            Context of caller activity
	 * @param key
	 *            Key of value to save against
	 * @param value
	 *            Value to save
	 */
	public static void saveToPrefs(Context context, String key, String value) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		final Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * Called to retrieve required value from shared preferences, identified by
	 * given key. Default value will be returned of no value found or error
	 * occurred.
	 * 
	 * @param context
	 *            Context of caller activity
	 * @param key
	 *            Key to find value against
	 * @param defaultValue
	 *            Value to return if no data found against given key
	 * @return Return the value found against given key, default if not found or
	 *         any error occurs
	 */
	public static String getFromPrefs(Context context, String key,
			String defaultValue) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		try {
			return sharedPrefs.getString(key, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	public static String getEmail(Context context) {
		return getFromPrefs(context, Constants.PREFS_LOGIN_USERNAME_KEY,
				Constants.PREFS_LOGIN_DEFAULT_USERNAME_KEY);
	}

	public static String getPassword(Context context) {
		return getFromPrefs(context, Constants.PREFS_LOGIN_PASSWORD_KEY,
				Constants.PREFS_LOGIN_DEFAULT_PASSWORD_KEY);
	}

    public static String getName(Context context) {
        return getFromPrefs(context, Constants.PREFS_LOGIN_NAME_KEY,
                Constants.PREFS_LOGIN_DEFAULT_NAME_KEY);
    }

    public static void saveEmail(Context context, String email) {
        saveToPrefs(context, Constants.PREFS_LOGIN_USERNAME_KEY, email);
    }

    public static void saveName(Context context, String name) {
		saveToPrefs(context, Constants.PREFS_LOGIN_NAME_KEY, name);
	}

	public static void savePassword(Context context, String password) {
		saveToPrefs(context, Constants.PREFS_LOGIN_PASSWORD_KEY, password);
	}

	public static void removeCredentials(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPrefs.edit();
		editor.remove(Constants.PREFS_LOGIN_USERNAME_KEY);
		editor.remove(Constants.PREFS_LOGIN_PASSWORD_KEY);
		editor.commit();
	}

	public static boolean hasCredentials(Context context) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPrefs.contains(Constants.PREFS_LOGIN_USERNAME_KEY)
				&& sharedPrefs.contains(Constants.PREFS_LOGIN_PASSWORD_KEY);

	}

}

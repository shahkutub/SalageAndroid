package com.salage.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.salage.MainActivityDemo;
import com.salage.model.RowItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PersistData {

	private static final String PREFS_FILE_NAME = "AppPreferences";

	/*
	 * default value is empty string
	 */

	public static String getStringData(final Context ctx, String key) {
		return ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getString(key, "");
	}

	public static void setStringData(final Context ctx, final String key,
			final String data) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(key, data);
		editor.commit();
	}

	public static void setStringArrayPref(final Context ctx, String key, List<RowItem> values) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		SharedPreferences.Editor editor = prefs.edit();
		JSONArray a = new JSONArray();
		for (int i = 0; i < values.size(); i++) {
			a.put(values.get(i));
		}
		if (!values.isEmpty()) {
			editor.putString(key, a.toString());
		} else {
			editor.putString(key, null);
		}
		editor.commit();
	}

	public static List<RowItem> getStringArrayPref(Context context, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String json = prefs.getString(key, null);
		ArrayList<RowItem> urls = new ArrayList<RowItem>();
		if (json != null) {
			try {
				JSONArray a = new JSONArray(json);
				for (int i = 0; i < a.length(); i++) {
					String url = a.optString(i);
					//urls.add(new RowItem());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return urls;
	}



	/*
	 * default value is -1
	 */

	public static int getIntData(final Context ctx, String key) {
		return ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getInt(key, -1);
	}

	public static void setIntData(final Context ctx, final String key,
			final int value) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/*
	 * default value is false
	 */
	public static boolean getBooleanData(final Context ctx, String key) {
		return ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getBoolean(key, false);
	}

	public static void setBooleanData(final Context ctx, final String key,
			final boolean data) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putBoolean(key, data);
		editor.commit();
	}

	/*
	 * default value is 0.0f
	 */

	public static float getFloatData(final Context ctx, String key) {
		return ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getFloat(key, 0.0f);
	}

	public static void setFloatData(final Context ctx, final String key,
			final float value) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/*
	 * default value is -1
	 */

	public static long getLongData(final Context ctx, String key) {
		return ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getLong(key, -1);
	}

	public static void setLongData(final Context ctx, final String key,
			final long value) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putLong(key, value);
		editor.commit();

	}

}

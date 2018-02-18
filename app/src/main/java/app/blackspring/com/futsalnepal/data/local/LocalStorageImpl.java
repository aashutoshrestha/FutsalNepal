package app.blackspring.com.futsalnepal.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by utsavstha on 2/15/18.
 */

public class LocalStorageImpl implements LocalStorage{
    private static LocalStorageImpl sharedPrefs = null;
    private SharedPreferences preferences;

    private LocalStorageImpl(Context context) {
        preferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
    }

    synchronized public static LocalStorageImpl getInstance(Context context) {
        if (sharedPrefs == null) {
            return new LocalStorageImpl(context);
        }else {
            return sharedPrefs;
        }

    }

    @Override
    public void saveData(String propertyName, String propertyValue) {
        preferences.edit().putString(propertyName, propertyValue).apply();
    }

    @Override
    public String getData(String propertyName) {
        return preferences.getString(propertyName, null);
    }
}

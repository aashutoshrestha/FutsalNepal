package app.blackspring.com.futsalnepal.data.local;

/**
 * Created by utsavstha on 2/15/18.
 */

public interface LocalStorage {
    void saveData(String propertyName, String propertyValue);
    String getData(String propertyName);
}

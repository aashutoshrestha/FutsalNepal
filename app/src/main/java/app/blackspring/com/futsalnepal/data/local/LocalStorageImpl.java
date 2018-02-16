package app.blackspring.com.futsalnepal.data.local;

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
    Properties prop;
    OutputStream output = null;
    InputStream input = null;

    public LocalStorageImpl() {
         prop = new Properties();
    }

    @Override
    public void saveData(String propertyName, String propertyValue) {
        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty(propertyName, propertyValue);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public String getData(String propertyName) {
        try {

            input = new FileInputStream("config.properties");
            prop.load(input);
            return prop.getProperty(propertyName);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}

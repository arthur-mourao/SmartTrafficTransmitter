package puc.iot.smarttraffic.smarttraffictransmitter;

import android.bluetooth.le.AdvertiseSettings;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by arthur on 24/09/2017.
 */

public class BeaconPreferences {

    private static SharedPreferences sharedPreferences;

    public static final String PREFS_NAME = "BEACON_PREFS";

    public BeaconPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(
                this.PREFS_NAME , Context.MODE_PRIVATE);
    }

    public String getMajor() {
        return sharedPreferences.getString("MAJOR", BeaconDefaultValues.DEFAULT_MAJOR);
    }

    public String getMinor() {
        return sharedPreferences.getString("MINOR", BeaconDefaultValues.DEFAULT_MINOR);
    }

    public int getFrequency() {
        return sharedPreferences.getInt("FREQUENCY", BeaconDefaultValues.DEFAULT_FREQUENCY);
    }

    public int getTxPower() {
        return sharedPreferences.getInt("TX_POWER", BeaconDefaultValues.DEFAULT_TX_POWER);
    }

    public int getTxPowerLevel() {
        return sharedPreferences.getInt("TX_POWER_LEVEL", BeaconDefaultValues.DEFAULT_TX_POWER_LEVEL);
    }

    public String getUuid() {
        return sharedPreferences.getString("UUID", BeaconDefaultValues.DEFAULT_UUID);
    }

    public String getBeaconLayout() {
        return BeaconDefaultValues.BEACON_LAYOUT;
    }

    public int getBeaconManufacturer() {
        return BeaconDefaultValues.BEACON_MANUFACTURER;
    }

    public void saveMajor( String value ){
        saveString("MAJOR",value);
    }

    public void saveMinor( String value ){
        saveString("MINOR",value);
    }

    public void saveTxPower( Integer value ){
        saveInt("TX_POWER",value);
    }

    public void saveFrequency( Integer value ){
        saveInt("FREQUENCY",value);
    }

    public void saveString( String Key, String text ) {
        Editor editor;
        editor = sharedPreferences.edit(); //2
        editor.putString(Key, text); //3
        editor.commit(); //4
    }

    public void saveInt(String Key, Integer value ) {
        Editor editor;
        editor = sharedPreferences.edit(); //2
        editor.putInt(Key, value); //3
        editor.commit(); //4
    }

}

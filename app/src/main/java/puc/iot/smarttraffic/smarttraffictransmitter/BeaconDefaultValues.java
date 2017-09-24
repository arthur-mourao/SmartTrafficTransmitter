package puc.iot.smarttraffic.smarttraffictransmitter;

import android.bluetooth.le.AdvertiseSettings;

/**
 * Created by arthur on 23/09/2017.
 */

public final class BeaconDefaultValues {

    public static final String DEFAULT_MAJOR = "0";

    public static final String DEFAULT_MINOR = "0";

    public static final int DEFAULT_FREQUENCY = AdvertiseSettings.ADVERTISE_MODE_BALANCED;

    public static final int DEFAULT_TX_POWER = -59;

    public static final int DEFAULT_TX_POWER_LEVEL = AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM;

    public static final String DEFAULT_UUID = "2f234454-cf6d-4a0f-adf2-f4911ba9ffa6";

    public static final String BEACON_LAYOUT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";

    public static final int BEACON_MANUFACTURER = 0x004C;

}

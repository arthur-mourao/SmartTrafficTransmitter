package puc.iot.smarttraffic.smarttraffictransmitter;

import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    BeaconTransmitter beaconTransmitter;
    BeaconPreferences beaconPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beaconPreferences = new BeaconPreferences(getApplicationContext());

        final Button button = (Button)findViewById(R.id.button_emergency);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (beaconTransmitter != null){
                    stopAdvertising();
                    button.setText("Iniciar Modo Emergência");
                }else{
                    newBeacon();
                    button.setText("Parar Modo Emergência");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.settings:
                stopAdvertising();
                Intent intent = new Intent(this, settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void newBeacon(){
        Beacon beacon = new Beacon.Builder()
                .setId1(beaconPreferences.getUuid())
                .setId2(beaconPreferences.getMajor())
                .setId3(beaconPreferences.getMinor())
                .setManufacturer(beaconPreferences.getBeaconManufacturer()) // ibeacon
                .setTxPower(beaconPreferences.getTxPower())
                .setDataFields(Arrays.asList(new Long[] {0l}))
                .build();

        BeaconParser beaconParser = new BeaconParser()
                .setBeaconLayout(beaconPreferences.getBeaconLayout());
        beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);
        beaconTransmitter.setAdvertiseTxPowerLevel(beaconPreferences.getTxPowerLevel());
        beaconTransmitter.setAdvertiseMode(beaconPreferences.getFrequency());
        beaconTransmitter.startAdvertising(beacon, new AdvertiseCallback() {
            @Override
            public void onStartFailure(int errorCode) {
                showMessage("Advertisement start failed with code: "+errorCode);
                Log.e("" ,"Advertisement start failed with code: "+errorCode);
            }

            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                showMessage("Advertisement start succeeded.");
                Log.i("", "Advertisement start succeeded.");
            }
        });

    }

    public void stopAdvertising(){
        if (beaconTransmitter != null) {
            beaconTransmitter.stopAdvertising();
            beaconTransmitter = null;
        }

        showMessage("Advertisement stopped!");
    }

    public void showMessage(String msg){
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

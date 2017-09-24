package puc.iot.smarttraffic.smarttraffictransmitter;

import android.bluetooth.le.AdvertiseSettings;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class settings extends AppCompatActivity {
    BeaconPreferences beaconPreferences;

    public EditText editMajor;
    public EditText editMinor;
    public EditText editTxPower;
    public RadioGroup radioFrequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        beaconPreferences = new BeaconPreferences(getApplicationContext());
        editMajor = (EditText)findViewById(R.id.editMajor);
        editMinor = (EditText)findViewById(R.id.editMinor);
        editTxPower = (EditText)findViewById(R.id.editTxPower);
        radioFrequency = (RadioGroup)findViewById(R.id.radio_frq);


        editMajor.setText(beaconPreferences.getMajor());
        editMinor.setText(beaconPreferences.getMinor());
        editTxPower.setText(String.valueOf(beaconPreferences.getTxPower()));

        switch (beaconPreferences.getFrequency()){
            case AdvertiseSettings.ADVERTISE_MODE_BALANCED:
               radioFrequency.check(R.id.frq_balanced);
                break;
            case AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY:
               radioFrequency.check(R.id.frq_low_lat);
                break;
            case AdvertiseSettings.ADVERTISE_MODE_LOW_POWER:
               radioFrequency.check(R.id.frq_low_power);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save_settings:
                beaconPreferences.saveMajor(editMajor.getText().toString());
                beaconPreferences.saveMinor(editMinor.getText().toString());
                beaconPreferences.saveTxPower(Integer.parseInt(editTxPower.getText().toString()));

                switch (radioFrequency.getCheckedRadioButtonId()){
                    case R.id.frq_balanced:
                        beaconPreferences.saveFrequency(AdvertiseSettings.ADVERTISE_MODE_BALANCED);
                        break;
                    case R.id.frq_low_lat:
                        beaconPreferences.saveFrequency(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY);
                        break;
                    case R.id.frq_low_power:
                        beaconPreferences.saveFrequency(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER);
                        break;
                }

                showMessage("Configurações Salvas!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showMessage(String msg){
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

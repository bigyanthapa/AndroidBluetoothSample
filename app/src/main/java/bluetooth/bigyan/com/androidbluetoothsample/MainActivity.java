package bluetooth.bigyan.com.androidbluetoothsample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button scanButton;

    private BluetoothAdapter BA;

    private Set<BluetoothDevice> pairedDevices;

    private ListView devicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        devicesList = (ListView)findViewById(R.id.blueToothDeviceListView);


        scanButton = (Button) findViewById(R.id.buttonScanBluetooth);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BA = BluetoothAdapter.getDefaultAdapter();

                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                //takes intent and bundle
                startActivity(turnOn,null);

                pairedDevices = BA.getBondedDevices();

                ArrayList list = new ArrayList();
                int count = 1;
                for(BluetoothDevice bd: pairedDevices){
                    list.add(bd.getName());
                    Toast.makeText(MainActivity.this, "Bluetooth Device " + count + ": " + bd.getName(), Toast.LENGTH_SHORT).show();
                    count = count +1;
                }

                final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
                devicesList.setAdapter(adapter);



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

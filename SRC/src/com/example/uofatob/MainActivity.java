package com.example.uofatob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private String[] spinnerItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        final Button goButton = (Button) findViewById(R.id.button);
        final EditText startText = (EditText) findViewById(R.id.editText);
        final EditText destText = (EditText) findViewById(R.id.editText2);
        final Spinner startSpin = (Spinner) findViewById(R.id.StartPoint);
        final Spinner destSpin = (Spinner) findViewById(R.id.EndPoint);
        this.spinnerItems = new String[]{
                "ETLC","CAB"
        };
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        startSpin.setAdapter(spinnerAdapter);
        destSpin.setAdapter(spinnerAdapter);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startRoom = startText.getText().toString();
                String destRoom = destText.getText().toString();
                String startBuilding = startSpin.getItemAtPosition(startSpin.getSelectedItemPosition()).toString();
                String destBuilding = destSpin.getItemAtPosition(destSpin.getSelectedItemPosition()).toString();

                //Build Intent to Route Activity
                Intent intent = new Intent(getBaseContext(), RouteActivity.class);
                intent.putExtra("Start Building", startBuilding);
                intent.putExtra("Start Room", startRoom);
                intent.putExtra("Dest Building", destBuilding);
                intent.putExtra("Dest Room", destRoom);
                startActivity(intent);

            }

        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

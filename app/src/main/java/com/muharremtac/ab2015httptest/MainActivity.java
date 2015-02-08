package com.muharremtac.ab2015httptest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.muharremtac.ab2015httptest.constants.AppConstants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    TextView httpText;
    String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpText= (TextView) findViewById(R.id.httpText);

        setHttpValue();
    }

    private void setHttpValue() {
        new Thread(new Runnable() {
            @Override
             public void run() {
                HttpURLConnection huc=null;
                BufferedReader br=null;
                URL url=null;
                String line="";
                InputStreamReader is=null;
                try {
                    url = new URL(AppConstants.HTTP_M2BILISIM_COM_TR_PERSON_JSON);
                    huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("GET");
                    is=new InputStreamReader(huc.getInputStream());
                    br = new BufferedReader(is);
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        httpText.setText(result);
                    }
                });

            }
        }).start();
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

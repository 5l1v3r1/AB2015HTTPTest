package com.muharremtac.ab2015httptest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.muharremtac.ab2015httptest.constants.AppConstants;
import com.muharremtac.ab2015httptest.model.Person;
import com.muharremtac.ab2015httptest.model.Result;

import org.apache.http.Header;

import java.util.List;


public class AsyncActivity extends ActionBarActivity {

    TextView isimText;
    TextView soyIsimText;
    TextView httpAsyncText;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        isimText=(TextView)findViewById(R.id.isimText);
        soyIsimText=(TextView)findViewById(R.id.soyIsimText);
        httpAsyncText=(TextView)findViewById(R.id.httpAsyncText);

        final Gson gson = new Gson();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(AppConstants.HTTP_M2BILISIM_COM_TR_PERSON_JSON, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                result = new String(responseBody);
                Result resultObj = gson.fromJson(result,Result.class);
                List<Person> personList = resultObj.getPersonList();
                String isimler="";
                String soyIsimler="";
                for(Person person:personList){
                    isimler+=person.getIsim()+"\n";
                    soyIsimler+=person.getSoyIsim()+"\n";
                }

                isimText.setText(isimler);
                soyIsimText.setText(soyIsimler);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                result = new String(responseBody);
                httpAsyncText.setText(error.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async, menu);
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

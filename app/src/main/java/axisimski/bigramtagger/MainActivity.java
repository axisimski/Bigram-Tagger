package axisimski.bigramtagger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private EditText text_edt;
    private Button toCyrillic;
    private Button toLatin;
    private Integer alphaType;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*AdMob Code*/
        //Banner Ads

        MobileAds.initialize(this,
                "ca-app-pub-8271447368800027~5986668867");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        text_edt=findViewById(R.id.text_edt);
        toCyrillic=findViewById(R.id.toCyrillic);
        toLatin=findViewById(R.id.toLatin);

        toCyrillic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatinToCyrillic();
             }
        });

        toLatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CyrillicToLatin();
            }
        });

    }//end onCreate


    private void CyrillicToLatin(){

        SharedPreferences prefs = this.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        alphaType = prefs.getInt("RUSBUL", 0);

        String lat="";

        String cyr=text_edt.getText().toString();
        toLatin convToLat=new toLatin();


        if(alphaType==5) {
             lat = convToLat.convertRU(cyr);
        }

        else if(alphaType==10) {
             lat = convToLat.convertBG(cyr);
        }

        else if(alphaType==15) {
            lat = convToLat.convertMO(cyr);
        }

        else if(alphaType==20) {
            lat = convToLat.convertSRB(cyr);
        }

        else{
            lat = convToLat.convertRU(cyr);
        }

        text_edt.setText(lat);

    }

    public void LatinToCyrillic(){

        SharedPreferences prefs = this.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        alphaType = prefs.getInt("RUSBUL", 0);

        String lat=text_edt.getText().toString();
        toCyrillic convToCyr=new toCyrillic();

        String cyr="";

        convToCyr.convertToCyrBG(lat);

        if(alphaType==5) {
            cyr = convToCyr.convertToCyrRU(lat);
        }

        else if(alphaType==10) {
            cyr = convToCyr.convertToCyrBG(lat);
        }

        else if(alphaType==15) {
            cyr = convToCyr.convertToCyrMO(lat);
        }

        else if(alphaType==20) {
            cyr = convToCyr.convertToCyrSrb(lat);
        }

        else{
            cyr = convToCyr.convertToCyrRU(lat);
        }
        text_edt.setText(cyr);

    }

    //Add Settings button to Action Bar (Takes the user to settings activity)
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem settingsItem=menu.findItem(R.id.item_settings);
        settingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent =new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

     }//end onCreateOptionsMenu()



}//end class{}



























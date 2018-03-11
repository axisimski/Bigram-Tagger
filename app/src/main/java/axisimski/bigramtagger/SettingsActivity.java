package axisimski.bigramtagger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    static RadioButton Bul;
    static RadioButton Rus;
    static RadioGroup RG;
    static Button Save;
    static int langNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Bul=findViewById(R.id.Bul);
        Rus=findViewById(R.id.Rus);
        RG=findViewById(R.id.RG);
        Save=findViewById(R.id.save);

        this.loadSettings();


        SharedPreferences sharedPreferences= this.getSharedPreferences("Setting", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        int checkedRadioButtonId = RG.getCheckedRadioButtonId();

        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);

        editor.apply();

        this.loadSettings();


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
                loadSettings();

            }
        });


    }



    public void loadSettings()  {
        SharedPreferences sharedPreferences= this.getSharedPreferences("Setting", Context.MODE_PRIVATE);

        if(sharedPreferences!= null) {
            int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", 0);

            this.RG.check(checkedRadioButtonId);

        } else {
            this.RG.check(R.id.Bul);
            Toast.makeText(this,"Use the default setting",Toast.LENGTH_LONG).show();
        }


        if(Rus.isChecked()){
            MainActivity.langVal=1;
            langNum=1;}
        else if (Bul.isChecked()) {
            MainActivity.langVal=0;
            langNum=0;
        }


    }


    public void saveSettings()  {

        SharedPreferences sharedPreferences= this.getSharedPreferences("Setting", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        int checkedRadioButtonId = RG.getCheckedRadioButtonId();

        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);

        editor.apply();

        Toast.makeText(this,"Setting saved!",Toast.LENGTH_LONG).show();


    }



}





























//Can work on this latter

      /*  SharedPreferences SharedPref=getSharedPreferences("userInput", Context.MODE_PRIVATE);
        Integer prefs=SharedPref.getInt("Rus", 1);*/
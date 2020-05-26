package com.example.jowaasker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView txtCount;
    Button btnJowa, btnReset;
    int nCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        btnJowa = findViewById(R.id.idJowa);
        btnReset = findViewById(R.id.idReset);
        txtCount = findViewById(R.id.txtCount);

        nCount = sharedPreferences.getInt("count", 31);
        txtCount.setText(""+nCount);
        savePress();
    }

    public void savePress()
    {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("count", nCount);
        edit.commit();
    }

    public void reset()
    {
        nCount += 31;
        txtCount.setText(""+nCount);
        savePress();
    }

    public void jowaPress(View v)
    {
        if (nCount <= 0)
            Toast.makeText(getApplicationContext(), "You have reached the limit for the day.",Toast.LENGTH_SHORT).show();
        else
        {
            nCount--;
            txtCount.setText(""+nCount);
            savePress();
        }
    }

    public void resetPress(View v)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Reset the count? Unasked values will get carried over to the next day.");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(MainActivity.this,"Count has been reset",Toast.LENGTH_LONG).show();
                                MainActivity.this.reset();
                            }

                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

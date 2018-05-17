package com.samadtalukder.androiddiscountcalculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private EditText txtOriginalAmount;
    private TextView txtPerOfftxtPercentOff;
    private TextView txtResult;
    private TextView txtSaving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            seekBar = findViewById(R.id.seekBarPercentage);
            txtOriginalAmount = findViewById(R.id.txtOriginalAmount);
            txtPerOfftxtPercentOff = findViewById(R.id.txtPercentOff);
            txtResult = findViewById(R.id.textResult);
            txtSaving = findViewById(R.id.txtSaving);
            //
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint({"ResourceAsColor", "SetTextI18n"})
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    try {
                        if (fromUser && progress != Integer.parseInt(txtOriginalAmount.getText().toString())){
                            txtPerOfftxtPercentOff.setTextColor(Color.RED);
                            //txtPerOfftxtPercentOff.setTextColor(Color.parseColor("#000"));
                            txtPerOfftxtPercentOff.setText(String.valueOf(progress) + "% OFF");
                            // calculate discount
                            DiscountCalculation(progress);
                        }

                    }catch (Exception ex){
                        Toast.makeText(MainActivity.this, "Pleas Enter First Price", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        catch (Exception ex){
            txtSaving.setText(ex.toString());
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void DiscountCalculation(int progress) {
        try {
            if (!txtOriginalAmount.getText().toString().equals("")){
                double originalAmount = Double.parseDouble(txtOriginalAmount.getText().toString());
                double sellingPrice = originalAmount * (1.0d -(((double)progress)/100.0d));
                DecimalFormat decimalFormat = new DecimalFormat("##.00");
                txtResult.setTextColor(Color.BLACK);
                txtResult.setText("Final Price: "+decimalFormat.format(sellingPrice));
                double savingAmount = originalAmount - sellingPrice;
                txtSaving.setTextColor(Color.RED);
                txtSaving.setText("Saved Money: "+decimalFormat.format(savingAmount));
            }
        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.myorderplace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    RadioGroup sizeGroup;
    RadioGroup mainItem;
    RadioButton exCheese;

    Button orderButton;
    Button showsummary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // selection of the pizza size
        sizeGroup = (RadioGroup) findViewById(R.id.pizzaSize);

        // selection of the pizza Main flavour
        mainItem = (RadioGroup) findViewById(R.id.pizzaMain);

        // extra cheese
        exCheese = (RadioButton) findViewById(R.id.extraCheese);

        // order button
        orderButton = (Button) findViewById(R.id.order);
        // summary shower
        showsummary = (Button) findViewById(R.id.summary);


        // summary button function

        showsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkFilling()){
                    // items collected by radio buttons
                    String  size = ((RadioButton) findViewById(sizeGroup.getCheckedRadioButtonId())).getText().toString();
                    String maintopping = ((RadioButton) findViewById(mainItem.getCheckedRadioButtonId())).getText().toString();
                    boolean  extraCheese = exCheese.isChecked();

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                    String summary = getSummary(size, maintopping, extraCheese);

                    intent.putExtra("item", summary);


                    // send data to other intent
                    intent.putExtra("size", size);
                    intent.putExtra("maintopping", maintopping );
                    intent.putExtra("extracheese", extraCheese);


                    startActivity(intent);

                }

            }
        });

        //order button function to send mail

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkFilling()){
                    // items collected by radio buttons
                    String  size = ((RadioButton) findViewById(sizeGroup.getCheckedRadioButtonId())).getText().toString();
                    String maintopping = ((RadioButton) findViewById(mainItem.getCheckedRadioButtonId())).getText().toString();
                    boolean  extraCheese = exCheese.isChecked();

                    String mailtext = getSummary(size, maintopping, extraCheese);
                    String mailId = "meesalavineeth@gmail.com";
                    String subject = "order through my pizza place app!";

                    Intent email = new Intent(getIntent().ACTION_SEND);
                    email.setType("message/rfc822");
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"meesalavineeth@gmail.com"} );
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, mailtext);

                    startActivity(Intent.createChooser(email, "choose an email platform: "));

                }



            }
        });

    }

        public String getSummary (String size,String maintopping,boolean extraCheese) {

            String summaryString = "order of a "+size+" "+maintopping+" pizza"+"\n"+
                    "with extra cheese "+"("+extraCheese+")."+"\n"+
                    "price:    .";

            return summaryString;


        }

        public boolean checkFilling() {


            if (sizeGroup.getCheckedRadioButtonId() == -1 || mainItem.getCheckedRadioButtonId() == -1) {
                AlertDialog.Builder alertSelection = new AlertDialog.Builder(MainActivity.this);

                alertSelection.setTitle("selection error");
                alertSelection.setMessage("please select the size and main topping of pizza!");


                alertSelection.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertSelection.show();
                return false;
            }
            else {
                return true;
            }


        }

}
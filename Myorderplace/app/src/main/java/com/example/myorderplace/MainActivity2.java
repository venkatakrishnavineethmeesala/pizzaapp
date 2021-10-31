package com.example.myorderplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity {
    TextView textArea;

    Button backButton;
    Button orderCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String orderSummary = getIntent().getStringExtra("item");

        textArea = (TextView) findViewById(R.id.summaryArea);
        textArea.setText(orderSummary);

        orderCompleted = (Button) findViewById(R.id.completeOrder);
        backButton = (Button) findViewById(R.id.back);

        // getting data from other activity
        String size = getIntent().getStringExtra("size");
        String maintopping = getIntent().getStringExtra("maintopping");
        Boolean extraCheese = getIntent().getBooleanExtra("extracheese", false);


        // order complete button function.
        orderCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mailtext = getSummary(size, maintopping, extraCheese);
                String mailId = "meesalavineeth@gmail.com";
                String subject = "order through my pizza place app!";

                Intent email = new Intent(getIntent().ACTION_SEND);
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"meesalavineeth@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, mailtext);

                startActivity(Intent.createChooser(email, "choose an email platform: "));


            }
        });


        // back to order page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);

                startActivity(intent);


            }
        });

    }

    public String getSummary (String size,String maintopping,boolean extraCheese) {

        String summaryString = "order of a "+size+" "+maintopping+" pizza"+"\n"+
                "with extra cheese "+"("+extraCheese+")."+"\n"+
                "price:    .";

        return summaryString;


    }

}
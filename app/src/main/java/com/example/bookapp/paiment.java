package com.example.bookapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.io.IOException;
import java.math.BigDecimal;

public class paiment extends AppCompatActivity {

    Button paymentButton;

    private static final String clientId = "ASAIJkhFQbhyW2u2Dyu_C3CjcXXjEfUFQ9FWSgLQdllg0JiaQFz3-Ec0nSmNR5r-YEfGEzXLhiDjFztg";

    private static final int PAYPAL_REQUEST_CODE = 123;

    private  static PayPalConfiguration configuration;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiment);

        paymentButton = findViewById(R.id.payment_button);

        configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(clientId);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        startService(intent);



        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayPalPayment payment = new PayPalPayment(new BigDecimal("10.00"), "USD", "Payment Description", PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(paiment.this, PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                startActivityForResult(intent, PAYPAL_REQUEST_CODE);

            }

        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    String paymentDetails = confirmation.toJSONObject().toString();
                    // Process the payment details
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Payment was canceled by the user
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // Invalid payment or configuration submitted

            }
        }
    }


}
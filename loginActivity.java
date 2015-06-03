package com.yourapp.yourapp; //Change this to match your app package

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.beepxtra.beepapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends ActionBarActivity {

    private Button backButton;
    private Button loginButton;
    private TextView emailText;
    private TextView passwordText;
    private TextView msgText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        Onclick get fields and post to array - return error or redirect to dashboard
         */

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }

        });
        loginButton.setEnabled(false);


        /*
        Validate email address
        */
        final EditText emailValidate = (EditText)findViewById(R.id.emailText);
        //This will trigger everytime the field is changed
        emailValidate .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                //Gets the inputed value of the field (the email address)
                String email = emailValidate.getText().toString().trim();
                if (emailValidator(email)) {
                    emailValidate.setError(null); //Destroys the error in editText field (See below)
                    loginButton.setEnabled(true); //Enables your submitButton
                } else {
                    emailValidate.setError(email+ " is not a valid email address"); // Displays an error bubble inside your editText field
                    loginButton.setEnabled(false); //Disables button until email is valid
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });


        //Go back home button
        backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }

        });
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,4})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void tryLogin() {

        /*
        Get user/pass from textfields
         */
        String email = getEmail();
        String password = getPassword();
        
        //ATTENTION
        //This is where you can parse email and password to your service for validation.
        //If you will be using http requests, i recommend using OkHttp (Very reliable and robust library)
        

        
        //Currently this will display the submitted data in the msgText above the input fields
        /*
        validate return data
         */
        msgText = (TextView) findViewById(R.id.msgText);
        msgText.setText(email + " - " + password);
        //emailText.setError("Invalid Email");

    }

    public String getEmail() {
        emailText = (TextView) findViewById(R.id.emailText);
        return emailText.getText().toString();
    }

    public String getPassword(){
        passwordText = (TextView) findViewById(R.id.passwordText);
        return passwordText.getText().toString();
    }

    public void goHome(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


/*
* Insert this where needed (e.g. OnCreate) and make appropriate changes to match your code 
*/
    //Change to match your submit button if any (Remove if not applicable)
    loginButton = (Button) findViewById(R.id.loginButton);
    loginButton.setEnabled(false); //Disables button until email is valid (See below)
    
    //Change emailText to match your input field name
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


/*
* And the function with regex validator
*/

public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,4})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        //Returns true if email is valid // false if email is invalid
        return matcher.matches();
    }

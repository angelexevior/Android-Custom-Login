
/*
* Insert this where needed (e.g. OnCreate) and make appropriate changes to match your code 
*/
    final EditText emailValidate = (EditText)findViewById(R.id.emailText);
        
        emailValidate .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String email = emailValidate.getText().toString().trim();
                if (emailValidator(email)) {
                    emailValidate.setError(null);
                    loginButton.setEnabled(true);
                } else {
                    emailValidate.setError(email+ " is not a valid email address");
                    loginButton.setEnabled(false);
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

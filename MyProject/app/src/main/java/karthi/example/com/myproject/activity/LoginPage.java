package karthi.example.com.myproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import karthi.example.com.myproject.MainActivity;
import karthi.example.com.myproject.R;
import karthi.example.com.myproject.data.User;
import karthi.example.com.myproject.helper.DataBaseHelper;
import karthi.example.com.myproject.validation.InputValidation;

/**
 * Created by AshokKumar on 18/10/2017.
 */

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginPage";
    private SharedPreferences sharedPreferences;
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutemail;
    private TextInputLayout textInputLayoutpassword;

    private TextInputEditText textInputEditTextemail;
    private TextInputEditText textInputEditTextpassword;

    private AppCompatButton appCompatButtonlogin;
    private AppCompatButton appCompatButtongotoregisterpage;

    private InputValidation inputValidation;
    private DataBaseHelper dataBaseHelper;
    private User user;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);

        initViewes();
        initListener();
        initObjects();

    }

    private void initViewes() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedview);

        textInputLayoutemail = (TextInputLayout) findViewById(R.id.textInputLayoutemail);
        textInputLayoutpassword = (TextInputLayout) findViewById(R.id.textInputLayoutpassword);

        textInputEditTextemail = (TextInputEditText) findViewById(R.id.textInputEditTextemail);
        textInputEditTextpassword = (TextInputEditText) findViewById(R.id.textInputEditTextpassword);

        appCompatButtonlogin = (AppCompatButton) findViewById(R.id.loginButton);
        appCompatButtongotoregisterpage = (AppCompatButton) findViewById(R.id.registerPageButton);

    }

    private void initListener() {
        appCompatButtonlogin.setOnClickListener(this);
        appCompatButtongotoregisterpage.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        dataBaseHelper = new DataBaseHelper(this);

        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("EMAIL",false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            editor = sharedPreferences.edit();
            editor.putBoolean("EMAIL",true);
            editor.commit();
        }else {
            editor = sharedPreferences.edit();
            editor.putBoolean("EMAIL",false);
            editor.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                ValidationFunction();
                break;
            case R.id.registerPageButton:
                Intent intent = new Intent(this,RegisterPage.class);
                startActivity(intent);
                break;
        }
    }

    private void ValidationFunction() {

        if (!inputValidation.editTextFill(textInputLayoutemail,textInputEditTextemail,"Enter Email")){
            return;
        }
        if (!inputValidation.editTextEmail(textInputLayoutemail,textInputEditTextemail,"Valid Email")){
            return;
        }
        if (!inputValidation.editTextFill(textInputLayoutpassword,textInputEditTextpassword,"Enter Password")){
            return;
        }
//
        if (dataBaseHelper.CheckUser(textInputEditTextemail.getText().toString(),
                textInputEditTextpassword.getText().toString())){

            sharedPreferences = this.getSharedPreferences("User",MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putBoolean("EMAIL",true);
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            EmptyTextInput();
            startActivity(intent);
        }
        else {
            Snackbar.make(nestedScrollView,"Wrong Email or Password",Snackbar.LENGTH_LONG).show();
        }

    }

    private void EmptyTextInput() {
        textInputEditTextemail.setText(null);
        textInputEditTextpassword.setText(null);
    }
}

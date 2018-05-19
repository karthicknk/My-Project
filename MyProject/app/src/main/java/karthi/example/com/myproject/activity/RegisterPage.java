package karthi.example.com.myproject.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.data.User;
import karthi.example.com.myproject.helper.DataBaseHelper;
import karthi.example.com.myproject.validation.InputValidation;

/**
 * Created by AshokKumar on 18/10/2017.
 */

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterPage";
    private byte[] myimage;
    String encodedImage;
    private static final int SELECT_IMAGE = 1;
    private NestedScrollView nestedScrollView;

    private ImageView imageView;

    private TextInputLayout textInputLayoutname;
    private TextInputLayout textInputLayoutemail;
    private TextInputLayout textInputLayoutpassword;
    private TextInputLayout textInputLayoutconformpassword;

    private TextInputEditText textInputEditTextname;
    private TextInputEditText textInputEditTextemail;
    private TextInputEditText textInputEditTextpassword;
    private TextInputEditText textInputEditTextconformpassword;

    private AppCompatButton appCompatButtonregister;
    private AppCompatButton appCompatButtongotologinpage;

    private InputValidation inputValidation;
    private DataBaseHelper dataBaseHelper;
    private User user;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);
        getSupportActionBar().hide();

        initViewes();
        initListener();
        initObjects();

    }

    private void initViewes() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        imageView = (ImageView) findViewById(R.id.imageView) ;

        textInputLayoutname = (TextInputLayout) findViewById(R.id.textInputLayoutname);
        textInputLayoutemail = (TextInputLayout) findViewById(R.id.textInputLayoutemail);
        textInputLayoutpassword = (TextInputLayout) findViewById(R.id.textInputLayoutpassword);
        textInputLayoutconformpassword = (TextInputLayout) findViewById(R.id.textInputLayoutconformpassword);

        textInputEditTextname = (TextInputEditText) findViewById(R.id.textInputEditTextname);
        textInputEditTextemail = (TextInputEditText) findViewById(R.id.textInputEditTextemail);
        textInputEditTextpassword = (TextInputEditText) findViewById(R.id.textInputEditTextpassword);
        textInputEditTextconformpassword = (TextInputEditText) findViewById(R.id.textInputEditTextconformpassword);

        appCompatButtonregister = (AppCompatButton) findViewById(R.id.registerButton);
        appCompatButtongotologinpage = (AppCompatButton) findViewById(R.id.loginPageButton);

    }

    private void initListener() {
        appCompatButtonregister.setOnClickListener(this);
        appCompatButtongotologinpage.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        dataBaseHelper = new DataBaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerButton:
                ValidationFunction();
                break;
            case R.id.loginPageButton:
                finish();
                break;
            case R.id.imageView:
                ImageClickFunction();
                break;
        }
    }

    private void ImageClickFunction() {
        Log.e(TAG, "ImageClickFunctionnnnnnnnnnnnnnnnnnnn: " );
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SELECT_IMAGE:
                if (resultCode == RESULT_OK){
                    Uri uri =data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        imageView.setImageBitmap(bitmap);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        myimage = stream.toByteArray();
                        encodedImage = Base64.encodeToString(myimage, Base64.DEFAULT);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    private void ValidationFunction() {

        if (!inputValidation.editTextFill(textInputLayoutname,textInputEditTextname,"Enter Name")){
            return;
        }
        if (!inputValidation.editTextFill(textInputLayoutemail,textInputEditTextemail,"Enter Email")){
            return;
        }
        if (!inputValidation.editTextEmail(textInputLayoutemail,textInputEditTextemail,"Valid Email")){
            return;
        }
        if (!inputValidation.editTextFill(textInputLayoutpassword,textInputEditTextpassword,"Enter Password")){
            return;
        }
        if (!inputValidation.editTextFill(textInputLayoutconformpassword,textInputEditTextconformpassword,"Enter ConformPassword")){
            return;
        }
        if (!inputValidation.editTextMatch(textInputLayoutconformpassword,textInputEditTextpassword,textInputEditTextconformpassword,"Password Not Match")){
            return;
        }

        if (!dataBaseHelper.CheckUser(textInputEditTextemail.getText().toString())){
            user = new User();
            user.setImage(myimage);
            user.setName(textInputEditTextname.getText().toString());
            user.setEmail(textInputEditTextemail.getText().toString());
            user.setPassword(textInputEditTextpassword.getText().toString());
            dataBaseHelper.Insert(user);

            SharedPreferences sharedPreferences = getSharedPreferences("User1", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("MyName", textInputEditTextname.getText().toString());
            editor.putString("MyEmail",textInputEditTextemail.getText().toString());
            editor.putString("Image",encodedImage);
            editor.commit();

            EmptyTextInput();
            Snackbar.make(nestedScrollView,"Successfull Register",Snackbar.LENGTH_LONG).show();
        }
        else {
            Snackbar.make(nestedScrollView,"Enter Another Email",Snackbar.LENGTH_LONG).show();
        }
    }

    private void EmptyTextInput() {
        textInputEditTextname.setText(null);
        textInputEditTextemail.setText(null);
        textInputEditTextpassword.setText(null);
        textInputEditTextconformpassword.setText(null);
    }
}

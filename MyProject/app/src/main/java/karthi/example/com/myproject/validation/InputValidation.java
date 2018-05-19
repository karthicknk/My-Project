package karthi.example.com.myproject.validation;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class InputValidation {

    private Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    public boolean editTextFill(TextInputLayout textInputLayout, TextInputEditText textInputEditText,String message){
        String value = textInputEditText.getText().toString();
        if (value.isEmpty()){
            textInputLayout.setError(message);
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean editTextEmail(TextInputLayout textInputLayout, TextInputEditText textInputEditText,String message){
        String value = textInputEditText.getText().toString();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean editTextMatch(TextInputLayout textInputLayout,TextInputEditText textInputEditText1, TextInputEditText textInputEditText2,String message){
        String value1 = textInputEditText1.getText().toString();
        String value2 = textInputEditText2.getText().toString();
        if (!value1.equals(value2)){
            textInputLayout.setError(message);
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


}

package gunonz.bookmeetingpbru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static gunonz.bookmeetingpbru.R.id.ragoffice;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surameEditText, idCardEditText, userEditText,
            passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton officeRadioButton, outofficeRadioButton;
    private String nameString, surnameString, idCardString,
            userString, passwordString,officeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bing Widget

        bindWidget();


    }// main method

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surameEditText = (EditText) findViewById(R.id.editText2);
        idCardEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(ragoffice);
        officeRadioButton = (RadioButton) findViewById(R.id.radioButton);
        outofficeRadioButton = (RadioButton) findViewById(R.id.radioButton2);

    }

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        surnameString = surameEditText.getText().toString().trim();
        idCardString = idCardEditText.getText().toString().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (checkSpace()) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณากรอกทุกช่อง ค่ะ");
        }

    }// clickSign

    private boolean checkSpace() {

        boolean result = true;
        result = nameString.equals("") ||
                surnameString.equals("") ||
                idCardString.equals("") ||
                userString.equals("") ||
                passwordString.equals("");

        return result;
    }

}// main class

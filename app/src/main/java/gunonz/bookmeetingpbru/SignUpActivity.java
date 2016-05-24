package gunonz.bookmeetingpbru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
       //Radio Controller
        radioController();

    }// main method

    private void radioController() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton:
                        officeString = "0";
                        break;
                    case R.id.radioButton2:
                        officeString = "1";
                        break;
                    default:
                        officeString = "0";
                        break;

                }

            }
        });
    }

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
        } else if (idCardString.length() != 13) {//check id
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"รหัสบัตรผิด","รหัสบัตรต้องมี 13 หลักเท่านั้น");
        } else if (checkRadioChoose()) {
            //non check
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"ยังไม่เลือกสถานะ","โปรดเลือกสถานะด้วยค่ะ");
        } else {
            uploadValuetoServwe();

        }


    }// clickSign

    private void uploadValuetoServwe() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd","true")
                .add("Name",nameString)
                .add("Surname",surnameString)
                .add("IDcard",idCardString)
                .add("Office",officeString)
                .add("User",userString)
                .add("Password",passwordString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/pbru/add_user_master.php")
                .post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });

    } // uploadValue

    private boolean checkRadioChoose() {
        boolean result = true;
        result = officeRadioButton.isChecked() || outofficeRadioButton.isChecked();
        return !result;
    }


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

package gunonz.bookmeetingpbru;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //explicit
    private MyManage myManage;
    private String strURL = "http://swiftcodingthai.com/pbru/get_user.php";
    private String strLogo = "http://swiftcodingthai.com/pbru/Image/logo_pbru.png";
    private ImageView imageView;
    private EditText userEditext,passwordEditext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(this);
        //bing
        imageView = (ImageView) findViewById(R.id.imageView);
        userEditext = (EditText) findViewById(R.id.editText6);
        passwordEditext = (EditText) findViewById(R.id.editText7);

        //show logo
        Picasso.with(this).load(strLogo).into(imageView);



        //Test Add User
        //myManage.addUser("name","sur","idcard","off","user","pass");

        //Delete All SQLite
        deleteAllSQLite();

        synJSON();
    } // main method

    private void synJSON() {
        SymUser symUser = new SymUser();
        symUser.execute();
    }

    //create inner class
    public class SymUser extends AsyncTask<Void,Void,String> {


        @Override
        protected String doInBackground(Void... Void) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                return null;
            }


        }//doinback


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("pbruV1", "strJSON ==>" + s);

            try {
                JSONArray jsonArray = new JSONArray(s) ;

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(MyManage.column_Name);
                    String strSurname = jsonObject.getString(MyManage.column_Surname);
                    String strIDcard = jsonObject.getString(MyManage.column_IDcard);
                    String strOffice = jsonObject.getString(MyManage.column_Office);
                    String strUser = jsonObject.getString(MyManage.column_User);
                    String strPassword = jsonObject.getString(MyManage.column_Password);
                    myManage.addUser(strName, strSurname, strIDcard,
                            strOffice, strUser, strPassword);

                }//for

            } catch (Exception e) {
                e.printStackTrace();
            }


        }//onpost
    }// strUser Class

    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE,null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);

    }

    public void clickSingUpMain(View view) {
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }
} // main class

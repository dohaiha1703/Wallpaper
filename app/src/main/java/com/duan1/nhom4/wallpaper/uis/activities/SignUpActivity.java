package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.rest.RestClientSignUp;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.duan1.nhom4.wallpaper.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    private Button btnSignup;
    private EditText edtUserNameSign;
    private EditText edtEmailSign;
    private EditText edtDisplaySign;
    private EditText edtPasswordSign;
    private Toolbar toolbar;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void intialView() {
        btnSignup =  findViewById(R.id.btnSignup);
        edtUserNameSign = (EditText) findViewById(R.id.edtUserNameSign);
        edtEmailSign = (EditText) findViewById(R.id.edtEmailSign);
        edtPasswordSign = (EditText) findViewById(R.id.edtPasswordSign);
        edtDisplaySign = findViewById(R.id.edtUserNameSign);
        toolbar = findViewById(R.id.toolbarSignUp);

    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        eventClick();
    }

    public void eventClick() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtUserNameSign.getText().toString())
                        && TextUtils.isEmpty(edtPasswordSign.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Empty field is invalid", Toast.LENGTH_SHORT).show();
                } else {
                    setBtnBack(edtUserNameSign.getText().toString(),
                            edtEmailSign.getText().toString(),
                            edtDisplaySign.getText().toString(),
                            edtPasswordSign.getText().toString());
                }
            }
        });
    }

    public void setBtnBack(final String user, final String email, final String display, final String password) {
        Call<JsonElement> callSignup = RestClientSignUp.getApiSignUp()
                .callGetNonceSignUp("user"
                        , "register");
        callSignup.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement jsonElement = response.body();
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String nonce = jsonObject.get("nonce").getAsString();
                Call<JsonElement> callSignUp = RestClientSignUp
                        .getApiSign()
                        .signup(user, email, nonce, display, password, "cool");
                callSignUp.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                       try{
                           JsonElement element = response.body();
                           JsonObject object = element.getAsJsonObject();
                           String status = object.get("status").getAsString();
                           if (status.equals("ok")) {
                               Toast.makeText(getApplicationContext(), "Sign up Succeed", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                           }
                           else {
                               Toast.makeText(SignUpActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
                           }
                       }catch (NullPointerException e){
                           Toast.makeText(SignUpActivity.this, "Invalid data(User name or Email)", Toast.LENGTH_SHORT).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}


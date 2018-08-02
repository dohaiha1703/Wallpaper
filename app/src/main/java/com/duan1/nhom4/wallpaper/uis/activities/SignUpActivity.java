package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatButton;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    AppCompatButton imgBack;
    private Button btnSignup;
    private EditText edtUserNameSign;
    private EditText edtEmailSign;
    private EditText edtDisplaySign;
    private EditText edtPasswordSign;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void intialView() {
        imgBack = findViewById(R.id.btnBack);
        btnSignup =  findViewById(R.id.btnSignup);
        edtUserNameSign = (EditText) findViewById(R.id.edtUserNameSign);
        edtEmailSign = (EditText) findViewById(R.id.edtEmailSign);
        edtPasswordSign = (EditText) findViewById(R.id.edtPasswordSign);
        edtDisplaySign = findViewById(R.id.edtUserNameSign);


    }

    @Override
    public void intialVariables() {
        eventClick();
    }

    public void eventClick() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtUserNameSign.getText().toString())
                        && TextUtils.isEmpty(edtPasswordSign.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "khong the Sign Up", Toast.LENGTH_SHORT).show();
                } else {
                    setBtnBack(edtUserNameSign.getText().toString(),
                            edtEmailSign.getText().toString(),
                            edtDisplaySign.getText().toString(),
                            edtPasswordSign.getText().toString());
                    Toast.makeText(SignUpActivity.this, "test", Toast.LENGTH_SHORT).show();
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
                        JsonElement element = response.body();
                        JsonObject object = element.getAsJsonObject();
                        String status = object.get("status").getAsString();
                        if (status.equals("ok")) {
                            Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
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


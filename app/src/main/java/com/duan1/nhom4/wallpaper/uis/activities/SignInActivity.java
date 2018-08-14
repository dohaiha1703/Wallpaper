package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.rest.RestClient;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    AppCompatButton btnSignUp, btnLogIn;
    EditText username;
    EditText password;
    CheckBox checkBox;
    private ProgressDialog dialog;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void intialView() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogin);
        username = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        checkBox = findViewById(R.id.cbCheckRemember);
        dialog = new ProgressDialog(SignInActivity.this);

    }

    @Override
    public void intialVariables() {
        restoringPreferences();
        eventClick();
        getImcomingData();
    }

    public void showSpinerProgress() {

        //lap thong tin
//        dialog.setTitle("open");
        dialog.setMessage("Sign in");
//
//        dialog.setButton(ProgressDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

        //thiet lap k the huy - co the huy
        dialog.setCancelable(false);

        //show dialog
        dialog.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 3000000);
    }

    public void eventClick() {

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(username.getText().toString()) && TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Empty field is invalid", Toast.LENGTH_SHORT).show();
                } else {

                    setBtnLogIn(username.getText().toString(), password.getText().toString());
                    savingPreferences();
                    showSpinerProgress();
                }

//                setBtnLogIn(username.getText().toString(),password.getText().toString());
//                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
            }
        });

    }

    //PHong lam API
    private void setBtnLogIn(final String user, final String password) {
        Call<JsonElement> callNonce = RestClient.getApiInterface().callGetNonceLogin("auth", "generate_auth_cookie");
        callNonce.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement jsonElement = response.body();
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String nonce = jsonObject.get("nonce").getAsString();
//                Log.e("ok",nonce);
                Call<JsonElement> callLogin = RestClient.getApiLogin().login(nonce, user, password, "cool");
                callLogin.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        JsonElement element = response.body();
                        JsonObject object = element.getAsJsonObject();
                        String status = object.get("status").getAsString();
                        if (status.equals("ok")) {
                            dialog.dismiss();
                            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                            intent.putExtra("user_name", username.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(SignInActivity.this, "Wrong User name or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    //nut Remember
    private void savingPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences("phongdeptrai", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String user = username.getText().toString();
        String pass = password.getText().toString();
        boolean chk = checkBox.isChecked();
        if (!chk) {
            editor.clear();
        } else {
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.putBoolean("savestatus", chk);
        }
        editor.apply();
//        editor.commit();
    }

    private void restoringPreferences() {
        SharedPreferences preferences = getSharedPreferences("phongdeptrai", MODE_PRIVATE);
        boolean chk = preferences.getBoolean("savestatus", false);
        if (chk) {
            String user = preferences.getString("username", "");
            String pass = preferences.getString("password", "");
            username.setText(user);
            password.setText(pass);
        }
        checkBox.setChecked(chk);
    }

    private void getImcomingData(){
        if (getIntent().hasExtra("user_name")){
            String user = getIntent().getStringExtra("user_name");
            username.setText(user);
        }
    }
}

package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.rest.RestClientSignUp;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;

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
    private ProgressDialog dialog;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void intialView() {
        btnSignup = findViewById(R.id.btnSignup);
        edtUserNameSign = findViewById(R.id.edtUserNameSign);
        edtEmailSign = findViewById(R.id.edtEmailSign);
        edtPasswordSign = findViewById(R.id.edtPasswordSign);
        toolbar = findViewById(R.id.toolbarSignUp);
        dialog = new ProgressDialog(SignUpActivity.this);

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


    public void showSpinerProgress() {

        //lap thong tin
//        dialog.setTitle("open");
        dialog.setMessage("Sign up");
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
        }, 30000000);
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
                            edtUserNameSign.getText().toString(),
                            edtPasswordSign.getText().toString());
                    showSpinerProgress();
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
                        try {
                            JsonElement element = response.body();
                            JsonObject object = element.getAsJsonObject();
                            String status = object.get("status").getAsString();
                            if (status.equals("ok")) {
                                Toast.makeText(getApplicationContext(), "Sign up Succeed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                intent.putExtra("user_name", edtUserNameSign.getText().toString());
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        } catch (NullPointerException e) {
                            dialog.dismiss();
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


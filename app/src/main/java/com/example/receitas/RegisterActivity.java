package com.example.receitas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText editUsername, editPassword;
    Button btnRegister;
    SharedPreferences sharedPreferences;
    RequestQueue requestQueue;

    private static final String BASE_URL = "http://10.0.2.2:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnRegister);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this);

        btnRegister.setOnClickListener(v -> {
            String email = editUsername.getText().toString();
            String senha = editPassword.getText().toString();

            if (!email.isEmpty() && !senha.isEmpty()) {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("email", email);
                    jsonBody.put("senha", senha);
                } catch (JSONException e) {
                    Toast.makeText(this, "Erro ao criar JSON", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url = BASE_URL + "/usuarios/cadastrar";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        jsonBody,
                        response -> {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            editor.putString("senha", senha);
                            editor.apply();

                            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        },
                        error -> {
                            if (error.networkResponse != null && error.networkResponse.data != null) {
                                String data = new String(error.networkResponse.data);
                                try {
                                    JSONObject obj = new JSONObject(data);
                                    String mensagem = obj.optString("mensagem", "Erro ao cadastrar");
                                    Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

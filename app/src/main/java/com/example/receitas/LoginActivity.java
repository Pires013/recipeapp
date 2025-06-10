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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {
    EditText editUsername, editPassword;
    Button btnLogin, btnToRegister;
    SharedPreferences sharedPreferences;
    RequestQueue requestQueue;
    private static final String BASE_URL = "http://10.0.2.2:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(v -> {
            String email = editUsername.getText().toString().trim();
            String senha = editPassword.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = BASE_URL + "/usuarios/login";

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    response -> {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String mensagem = jsonResponse.getString("mensagem");

                            if (mensagem.equals("Login bem-sucedido")) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                editor.putString("senha", senha);
                                editor.apply();

                                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Erro ao interpretar resposta do servidor", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(this, "Erro no login. Verifique suas credenciais.", Toast.LENGTH_SHORT).show()
            ) {
                @Override
                public byte[] getBody() {
                    try {
                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("email", email);
                        jsonBody.put("senha", senha);
                        return jsonBody.toString().getBytes(StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };

            requestQueue.add(stringRequest);
        });

        btnToRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });
    }
}

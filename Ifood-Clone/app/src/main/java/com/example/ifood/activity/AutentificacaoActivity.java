package com.example.ifood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ifood.R;
import com.example.ifood.helper.ConfiguracaoFirebase;
import com.example.ifood.helper.Usuariofirebas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class AutentificacaoActivity extends AppCompatActivity {

    private Button botaoAcessar;

    private EditText campoEmail;
    private EditText campoSenha;

    private Switch tipoAcesso;
    private Switch tipoUsuario;

    private FirebaseAuth autenticacao;

    private LinearLayout linearTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentificacao);


        inicialiarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();

        //Verificar usuario logado
        verificarUsuarioLogado();

        tipoAcesso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){//emperesa
                    linearTipoUsuario.setVisibility(View.VISIBLE);

                }else{//usuario
                    linearTipoUsuario.setVisibility(View.GONE);

                }
            }
        });

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {

                        //Verificar estado do Switch
                        if (tipoAcesso.isChecked()) {// Cadastro
                            autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AutentificacaoActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        String tipoUsuario = getTipoUsuario();
                                        Usuariofirebas.atualizarTipoUsuario(tipoUsuario);
                                        abrirTelaPrincipal(tipoUsuario);

                                    } else {
                                        String erroExcecao = "";
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            erroExcecao = "Digite uma senha mais forte!";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            erroExcecao = "Por favor, Digite um E-mail valido!";
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            erroExcecao = "Esta conta ja foi cadastrada";
                                        } catch (Exception e) {
                                            erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(AutentificacaoActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {//Login
                            autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AutentificacaoActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                                       String tipousuario = task.getResult().getUser().getDisplayName();
                                        abrirTelaPrincipal(tipousuario);

                                    } else {
                                        Toast.makeText(AutentificacaoActivity.this, "Erro ao fazer login!" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    } else {
                        Toast.makeText(AutentificacaoActivity.this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AutentificacaoActivity.this, "Preencha o E-mail!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicialiarComponentes() {
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);

        botaoAcessar = findViewById(R.id.buttonAcesso);

        tipoAcesso = findViewById(R.id.switch_Acesso);
        tipoUsuario = findViewById(R.id.switch_Tipo_Usuario);
        linearTipoUsuario = findViewById(R.id.linear_Tipo_Usuario);

    }

    private void abrirTelaPrincipal(String tipoUsuario) {
        if (tipoUsuario.equals("E")){//Empresa
            startActivity(new Intent(getApplicationContext(), EmpresaActivity.class));
        }else{//usuario
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    }

    private void verificarUsuarioLogado(){
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if (usuarioAtual != null){
            String tipoUsuario = usuarioAtual.getDisplayName();
            abrirTelaPrincipal(tipoUsuario);
        }
    }

    private String getTipoUsuario(){
        return tipoUsuario.isChecked() ? "E" : "U";
    }
}

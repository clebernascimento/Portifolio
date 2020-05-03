package com.example.ifood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ifood.R;
import com.example.ifood.helper.ConfiguracaoFirebase;
import com.example.ifood.helper.Usuariofirebas;
import com.example.ifood.model.Empresa;
import com.example.ifood.model.Produto;
import com.google.firebase.auth.FirebaseAuth;

public class NovoProdutoEmpresaActivity extends AppCompatActivity {

    private EditText editNomeProduto;
    private EditText editDescricaoProduto;
    private EditText editPrecoProduto;

    private ImageView imagePerfiEmpresa;

    private FirebaseAuth autentificcao;

    private String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto_empresa);

        inicializarComponentes();
        idUsuarioLogado = Usuariofirebas.getIdUsuario();

        // Configuraçoes Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Produto");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void validarDadosProduto(View view){

        //Valida se os campos foram preenchidos
        String nome = editNomeProduto.getText().toString();
        String descricao = editDescricaoProduto.getText().toString();
        String preco = editPrecoProduto.getText().toString();

        if( !nome.isEmpty()){
            if( !descricao.isEmpty()){
                if( !preco.isEmpty()){
                    Produto produto = new Produto();
                    produto.setIdUsuario(idUsuarioLogado);
                    produto.setNome(nome);
                    produto.setDescricao(descricao);
                    produto.setPreco(Double.parseDouble(preco));
                    produto.salvar();
                    
                    finish();
                    exibirMensagem("Produto salvo com successo!");

                }else{
                    exibirMensagem("Digite um preço para o Produto");
                }
            }else{
                exibirMensagem("Digite uma descrição para o Produto");
            }
        }else{
            exibirMensagem("Digite um nome para a Produto");
        }

    }

    private void exibirMensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT)
                .show();
    }

    private void inicializarComponentes() {
        editNomeProduto = findViewById(R.id.editNomeProduto);
        editDescricaoProduto = findViewById(R.id.editDescricaoProduto);
        editPrecoProduto = findViewById(R.id.editPrecoProduto);

        imagePerfiEmpresa = findViewById(R.id.imagePerfilEmpresa);
    }
}

package com.cleber.financeiro.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cleber.financeiro.R;

import com.cleber.financeiro.controller.BankController;
import com.cleber.financeiro.controller.CartaoController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fabAdd;
    private FloatingActionButton fabDespesa;
    private FloatingActionButton fabGanho;

    private Toolbar toolbarMain;
    private DrawerLayout drawerLayout;

    private boolean fabsHide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ao clicar algum menu ele vai procurar o item clicado
        NavigationView navigationMenu = findViewById(R.id.navigationMenu);
        navigationMenu.setNavigationItemSelectedListener(this);
        navigationMenu.bringToFront();

        //chamando Toolbar
        toolbarMain = findViewById(R.id.toolbar_Main);
        setSupportActionBar(toolbarMain);

        drawerLayout = findViewById(R.id.drawer);
        // definindo o button toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarMain, R.string.open_drawer, R.string.close_drawer);
        //adciona o button toolbar
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Carregando o fragmento principal
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragmentContainer, new MainFragment()).
                commit();

        //Carregar os botoes fabs
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(this);

        fabDespesa = findViewById(R.id.fabDespesa);
        fabDespesa.setOnClickListener(this);

        fabGanho = findViewById(R.id.fabGanho);
        fabGanho.setOnClickListener(this);
    }

    void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Acao do botao flutuante ADD
    @Override
    public void onClick(View view) {
        //Cada vez que clicar no FAB add chama o metodo show hide
        if (view.getId() == R.id.fabAdd) {
            showHideFabs();
        } else if (view.getId() == R.id.fabDespesa) {
            loadDespesaForm();
        } else if (view.getId() == R.id.fabGanho) {
            loadganhoForm();
        }
    }

    // Metodo para chamar a tela de ganho
    private void loadganhoForm() {
        // chamando o metodo para adionar o titulo na tela de Ganho
        changeActionBar(R.string.actionBar_Ganho, true);

        //Carregando o fragmento ganho
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GanhoFormFragment()).commit();
        showHideFabs();
    }

    // chamando tela de despesa com titulo
    private void loadDespesaForm() {
        // chamando o metodo para adionar o titulo na tela de Despesa
        changeActionBar(R.string.actionBar_Despesa, true);
        //Carregando o fragmento Despesa
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new DespesaFormFragment()).commit();
        showHideFabs();
    }

    // Metodo para adicionar titulo na actionbar na tela de Despesa
    private void changeActionBar(int stringID, boolean isNotHome) {
        //Adicionando titulo prara actionBar para tela de Despesa
        getSupportActionBar().setTitle(stringID);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(isNotHome);
//        getSupportActionBar().setDisplayShowHomeEnabled(isNotHome);
    }

    // Metodo para ação do da seta voltar na actionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            backToHome();
        }
        return super.onOptionsItemSelected(item);
    }

    // Metodo para voltar para a tela principal
    private void backToHome() {
        changeActionBar(R.string.app_name, false);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragmentContainer, new MainFragment()).
                commit();
        //showHideFabs();
    }

    //botoes ficam visiveis ao serem chamados
    @SuppressLint("RestrictedApi")
    public void showHideFabs() {
        //Define a animação
        // 0 siginifica invisivel e 1 visivel
        // Indo de 0 a 1
        AlphaAnimation fadeAnimation = new AlphaAnimation(0, 1);
        //Define a duaracao
        fadeAnimation.setDuration(1000);
        //Define o atraso
        fadeAnimation.setStartOffset(100);

        if (fabsHide) {
            fabDespesa.setVisibility(View.VISIBLE);
            fabDespesa.setAnimation(fadeAnimation);
            fabGanho.setVisibility(View.VISIBLE);
            fabGanho.setAnimation(fadeAnimation);
        } else {
            fabDespesa.setVisibility(View.GONE);// GONE DEIXA OS BOTOES FLUANTES INVISIVEIS E DESATIVADOS
            fabDespesa.clearAnimation();
            fabGanho.setVisibility(View.GONE);
            fabGanho.clearAnimation();
        }
        fabsHide = !fabsHide;
    }

    // Navegando entre os Itens de menus mo drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.itemMenuMain:
                backToHome();
                break;
            case R.id.itemMenuBanco:
                //EXISTE UMA CONTA BANCARIA CADASTRADA
                if (BankController.get() != null) {
                    changeActionBar(R.string.app_name, false);
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new BankInforFragment()).
                            commit();
                    //Se não existe
                } else {
                    changeActionBar(R.string.app_name, false);
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new NoBankFragment()).
                            commit();
                }
                break;
            case R.id.itemMenuCredito:
                //EXISTE UM CARTAO DE CREDITO CADSTRADO
                if (CartaoController.get() != null) {
                    changeActionBar(R.string.app_name, false);
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new InforCartaoCreditoFragment()).
                            commit();
                    //Se não existe
                } else {
                    changeActionBar(R.string.app_name, false);
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new NoCartaoCreditoFragment()).
                            commit();
                }
                break;
            case R.id.itemMenuContas:
                changeActionBar(R.string.app_name, false);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new ContasListFragment()).
                        commit();
                break;
            case R.id.itemMenuParcelamento:
                changeActionBar(R.string.app_name, false);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new ParcelamentoListFragment()).
                        commit();
                break;
            case R.id.itemMenuRelatorio:
                changeActionBar(R.string.app_name, false);
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new RelatorioFragment()).
                        commit();
                break;
        }
        closeDrawer();
        return false;
    }

//    @Override
//    public void onBackPressed() { //Botão BACK padrão do android
//        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
//        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
//        return;
//    }
}

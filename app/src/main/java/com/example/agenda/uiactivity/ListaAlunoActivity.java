package com.example.agenda.uiactivity;

import static com.example.agenda.uiactivity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.module.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITLE_APPBAR);
        configuraFabBotao();
        configuraLista();
        dao.salvar(new Aluno("Thales Nishida", "medeiros.tn@gmail.com", "123456"));
        dao.salvar(new Aluno("Eliara Nishida", "eliara.tn@gmail.com", "123456"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_de_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if(itemId == R.id.activity_lista_alunos_mennu_remover){
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEcolhido = adapter.getItem(menuInfo.position);
            remove(alunoEcolhido);
        }
        return super.onContextItemSelected(item);

    }

    private void configuraFabBotao() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_main_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioInsereAluno();
            }
        });
    }

    private void abreFormularioInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos_listview);
        configuraAdpter(listaDeAlunos);
        configuraListinerPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }


    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListinerPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdpter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        listaDeAlunos.setAdapter(adapter);
    }
}

package com.example.agenda.uiactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.module.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.formulario_activity_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> {
            Aluno alunoCriado = criaaluno();
            salva(alunoCriado);
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.formulario_activity_aluno_nome);
        campoEmail = findViewById(R.id.formulario_activity_aluno_email);
        campoTelefone = findViewById(R.id.formulario_activity_aluno_telefone);
    }

    private void salva(Aluno alunoCriado) {
        dao.salvar(alunoCriado);

        finish();
    }

    @NonNull
    private Aluno criaaluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        return new Aluno(nome, email, telefone);
    }

    ;
}

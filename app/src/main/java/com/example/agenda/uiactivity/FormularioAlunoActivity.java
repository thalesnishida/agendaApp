package com.example.agenda.uiactivity;

import static com.example.agenda.uiactivity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.module.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Novo Aluno";
    public static final String CHAVE_ALUNO = "aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "EDITAR ALUNO";
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            aluno = (Aluno) dados.getSerializableExtra(ConstantesActivities.CHAVE_ALUNO);
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.formulario_activity_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaAluno();
            }
        });
    }

    private void finalizaAluno() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salvar(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.formulario_activity_aluno_nome);
        campoEmail = findViewById(R.id.formulario_activity_aluno_email);
        campoTelefone = findViewById(R.id.formulario_activity_aluno_telefone);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
    }

    ;
}

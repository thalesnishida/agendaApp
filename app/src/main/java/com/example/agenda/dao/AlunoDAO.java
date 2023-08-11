package com.example.agenda.dao;

import androidx.annotation.Nullable;

import com.example.agenda.module.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    private static int contadoIds = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(contadoIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private static void atualizaIds() {
        contadoIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private static Aluno buscaAlunoPorId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPorId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}

package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        criaAlunosDeTeste();
    }

    private static void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alex", "11112222", "alex@email.com"));
        dao.salva(new Aluno("Fran", "33334444", "fran@email.com"));
    }
}

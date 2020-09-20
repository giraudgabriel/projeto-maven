package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.entity.Professor;
import br.gov.sp.fatec.projetomaven.entity.Trabalho;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("avaliacao");

        EntityManager manager = factory.createEntityManager();

        Aluno aluno = new Aluno();

        aluno.setNomeUsuario("aluno fatec");
        aluno.setSenha("senha");
        aluno.setRa(123412512512L);

        Professor professor = new Professor();
        professor.setNomeUsuario("professorlab4");
        professor.setSenha("senhaForte");

        Trabalho trabalho = new Trabalho();
        trabalho.setTitulo("Trabalho 2 Lab IV");
        trabalho.setDataHoraEntrega(new Date());
        trabalho.setLocalArquivo("trabalhos");
        trabalho.setAvaliador(professor);
        trabalho.setAlunos(new HashSet<Aluno>());
        trabalho.getAlunos().add(aluno);

        try {
            manager.getTransaction().begin();
            manager.persist(aluno);
            manager.persist(professor);
            manager.persist(trabalho);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }

        manager.clear();

        aluno = manager.find(Aluno.class, aluno.getId());
        System.out.println(aluno.getId());
        System.out.println(aluno.getNomeUsuario());

        for (Trabalho trab : aluno.getTrabalhos()) {
            System.out.println(trab.getTitulo());
        }

        trabalho = manager.find(Trabalho.class, trabalho.getId());
        System.out.println(trabalho.getTitulo());

        for (Aluno alu : trabalho.getAlunos()) {
            System.out.println(alu.getNomeUsuario());
        }

        trabalho.setTitulo("Novo trabalho 2 - JPA");

        try {
            manager.getTransaction().begin();
            manager.merge(trabalho);
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }

        String queryString = "select t from Trabalho t inner join t.alunos a where a.nomeUsuario like :nome";
        TypedQuery<Trabalho> query = manager.createQuery(queryString, Trabalho.class);
        query.setParameter("nome", "%aluno%");

        List<Trabalho> resultados = query.getResultList();
        for (Trabalho trab : resultados) {
            System.out.println("Título: "+ trab.getTitulo());
        }

       try {
           manager.getTransaction().begin();
           professor = trabalho.getAvaliador();
           trabalho.setAvaliador(null);
           Set<Aluno> alunos = trabalho.getAlunos();
           trabalho.setAlunos(null);
           manager.remove(trabalho);
           manager.remove(professor);
           for (Aluno alu : alunos) {
               manager.remove(alu);
           }
           manager.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           manager.getTransaction().rollback();
       }

        manager.close();
    }
}

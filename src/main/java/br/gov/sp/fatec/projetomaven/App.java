package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        manager.close();
    }
}

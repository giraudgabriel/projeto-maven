package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Aluno;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class AlunoDaoJpa implements AlunoDao {

    private EntityManager em;

    public AlunoDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public AlunoDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Aluno cadastrarAluno(String nomeUsuario, String senha, Long ra) {
        Aluno aluno = new Aluno(nomeUsuario, senha, ra);
        return salvarAluno(aluno);
    }

    @Override
    public Aluno buscarAlunoPorRa(Long ra) {
        String jpql = "select a from Aluno a where a.ra = :ra";
        TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
        query.setParameter("ra", ra);
        return query.getSingleResult();
    }

    @Override
    public void removerAluno(Long ra) {
        Aluno aluno = buscarAlunoPorRa(ra);
        if (aluno == null) {
            throw new RuntimeException("Aluno não cadastrado!");
        }
        em.getTransaction().begin();
        em.remove(aluno);
        em.getTransaction().commit();
    }

    @Override
    public Aluno salvarAluno(Aluno aluno) {
        try {
            em.getTransaction().begin();
            salvarAlunoSemCommit(aluno);
            em.getTransaction().commit();
            return aluno;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }

    @Override
    public Aluno salvarAlunoSemCommit(Aluno aluno) {
        if (aluno.getId() == null) {
            em.persist(aluno);
        } else {
            em.merge(aluno);
        }
        return aluno;
    }

}
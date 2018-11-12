/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.service;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mauricio
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    
    public AbstractFacade() {
    }

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public T findLogin(String clave, String modo) {

        EntityManager em = getEntityManager();
        try {
            T resultado = (T) em.createNamedQuery("Cliente.findByLoginName")
                    .setParameter("loginName", modo)
                    //.setParameter("modo", modo)
                    .getSingleResult();
            return resultado;
        } finally {
            em.close();
        }

    }
    
    public List<T> findClienteTransaccion(String clave, String modo) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setParameter("idCliente", modo);
        //q.setFirstResult(range[0]);
        return q.getResultList();
//        EntityManager em = getEntityManager();
//        try {
//            List<T> resultado = em.createNamedQuery("TransaccionesCab.findByIdCliente")
//                    .setParameter("idCliente", modo)
//                    //.setParameter("modo", modo)
//                    .getResultList();
//            return resultado;
//        } finally {
//            em.close();
//        }

    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}

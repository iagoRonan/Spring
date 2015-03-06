package br.com.springsecurity.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class GenericDAO <T, ID> {
	
	protected EntityManager em;	
	
	public GenericDAO(EntityManager em){
		this.em = em;			
	}
	
	public T insert(T t){		
		EntityTransaction et = em.getTransaction();		
		try{
			et.begin();
			em.persist(t);
			et.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(et.isActive())
				et.rollback();
		}finally{				
			if(et.isActive())
				et.rollback();
		}					
		return t;
	}
	
	public T update(T t){		
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			em.merge(t);
			et.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(et.isActive())
				et.rollback();
		}finally{					
			if(et.isActive())
				et.rollback();
		}							
		return t;
	}
	
	public T find(Class<T> t,ID id){		
		return em.find(t, id);
	}
	
	public boolean remove(T t){		
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			em.remove(t);
			et.commit();			
		}catch(Exception e){
			if(et.isActive())
				et.rollback();
			return false;
		}finally{
			if(et.isActive())
				et.rollback();			
		}							
		return true;
	}
	
	public boolean remove(Class<T> t,ID id){		
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
			em.remove(em.getReference(t, id));
			et.commit();
		}catch(Exception e){
			if(et.isActive())
				et.rollback();
			return false;
		}finally{
			if(et.isActive())
				et.rollback();
		}					
		return true;
	}		
	
	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String hsql, Map<String, Object> params) {
				
		Query query = em.createNamedQuery(hsql);
		if (params != null) {
			for (String i : params.keySet()) {
				query.setParameter(i, params.get(i));
			}
		}		
		
		return query.getResultList();				
	}
	
	@SuppressWarnings("unchecked")
	public T findWithNamedQuerySingle(String hsql, Map<String, Object> params) {
				
		Query query = em.createNamedQuery(hsql);
		if (params != null) {
			for (String i : params.keySet()) {
				query.setParameter(i, params.get(i));
			}
		}		
		
		return (T) query.getSingleResult();				
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findWithCreateQuery(String hsql, Map<String, Object> params) {
		List<T> util = null;	
		try{
			Query query = em.createQuery(hsql);
			if (params != null) {
				for (String i : params.keySet()) {
					query.setParameter(i, params.get(i));
				}
			}
			
			util = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}					
		return util;
	}
	
	public void close(){
		if(em.isOpen()){
			em.close();			
		}
		if(em != null){
			em = null;
		}
	}
}

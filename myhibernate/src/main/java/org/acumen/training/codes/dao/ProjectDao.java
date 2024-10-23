package org.acumen.training.codes.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;



public class ProjectDao {
	private static final Logger LOGGER = Logger.getLogger(ProjectDao.class);
	
	private SessionFactory sf;
	
	public ProjectDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	public boolean insert(Project proj)	{
		Session sess = sf.openSession();
		Transaction tx= sess.beginTransaction();
		try {
			sess.persist(proj); // Insert into
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateProjname(Short id, String newProjname) {
		Session sess = sf.openSession();
		Transaction tx= sess.beginTransaction();
		try {
			Project proj = sess.get(Project.class, id);// It should be PK
			proj.setProjname(newProjname);
			sess.merge(proj); // UPDATE
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateProjDateForKeyword(String keyword, LocalDate newProjdate) {
	    Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
	    try {
	        String hql = "update Project p set p.projdate = ?1 where p.projname ilike ?2";
	        MutationQuery query = sess.createMutationQuery(hql);
	        query.setParameter(1,newProjdate);
	        query.setParameter(2, keyword);
	        int row = query.executeUpdate();
	        LOGGER.info("Number of rows: %d".formatted(row));
	        tx.commit();
	        return true;
	    } catch (Exception e) {
	        if (tx != null) {
	            try {
	                tx.rollback();
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        if (sess != null) {
	            try {
	                sess.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	    }
	    return false;
	}
	public boolean deletebyProjname(String keyword) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			String hql = "delete from Project p where p.projname ilike :pname";
			MutationQuery query = sess.createMutationQuery(hql);
			query.setParameter("pname",keyword);
			int row = query.executeUpdate();
			LOGGER.info("Number of rows: %d".formatted(row));
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (sess != null) {
				try {
					sess.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return false;
	}
	
	

	
	
	
	public boolean deleteById(Short id) {
		Session sess = sf.openSession();
		Transaction tx= sess.beginTransaction();
		try {
			Project proj = sess.get(Project.class, id);// It should be PK
			sess.remove(proj); 
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	//Query Transaction
	public List<Project> selectAllProject(){
		List<Project> records = new ArrayList<>();
		try(Session sess = sf.openSession();) {
			String hql = "from Project p"; //Selection in HQL
			Query<Project> query = sess.createQuery(hql, Project.class);
			records = query.getResultList();
			int recordCount = (int) query.getResultCount();
			LOGGER.info("Number of rows: %d".formatted(recordCount));
				return Collections.unmodifiableList(records);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return null;
			
		}
	
	public List<Project>selectAllProjectCriteria(){
		List<Project> records = new ArrayList<>();
		try(Session sess = sf.openSession();) {
			
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Project> sql= builder.createQuery(Project.class);
			Root<Project> from = sql.from(Project.class);
			sql.select(from);
			
			Query<Project> query = sess.createQuery(sql);
			records = query.getResultList();
			int recordCount = (int) query.getResultCount();
			LOGGER.info("Number of rows: %d".formatted(recordCount));
				return Collections.unmodifiableList(records);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return null;
	}
	public Project selectSingleProject(Short id){
		Project record = new Project();
		try(Session sess = sf.openSession();) {
			
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Project> sql= builder.createQuery(Project.class);
			Root<Project> root = sql.from(Project.class);
			sql.select(root).where(builder.equal(root.get("id"), id));
			
			Query<Project> query = sess.createQuery(sql);
			record = query.getSingleResult();
			LOGGER.info("Number of rows: %d".formatted(1));
			return record;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return record;
	}
	
	
	public List<Object[]>showSomeProject(String keyword){
		List<Object[]> records = new ArrayList<>();
		try(Session sess = sf.openSession();) {
			
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql= builder.createQuery(Object[].class);
			Root<Project> from = sql.from(Project.class);
			sql.multiselect(from.get("id"), from.get("projdate"))
			.where(builder.like(from.get("projname"), keyword))
			.orderBy(builder.asc(from.get("projname")));
			
			Query<Object[]> query = sess.createQuery(sql);
			records = query.getResultList();
			int recordCount = (int) query.getResultCount();
			LOGGER.info("Number of rows: %d".formatted(recordCount));
				return Collections.unmodifiableList(records);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return null;
	}
	
	


}

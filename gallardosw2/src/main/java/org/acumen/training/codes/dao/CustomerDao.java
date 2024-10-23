package org.acumen.training.codes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CustomerDao {
	private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

	private SessionFactory sf;

	public CustomerDao(SessionFactory sf) {
		this.sf = sf;
	}

	// CREATE
	public boolean insert(Customer customer) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			LOGGER.infof("Inserting customer: %s", customer);
			sess.persist(customer); // Insert into
			tx.commit();
			LOGGER.infof("Customer inserted successfully: %s", customer);
			return true;
		} catch (Exception e) {
			LOGGER.errorf("Failed to insert customer: %s", customer, e);
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}
		return false;
	}

	// UPDATE
	public boolean updateCustomername(Integer id, String newCustname) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			LOGGER.infof("Updating customer with ID: %d to new name: %s", id, newCustname);
			Customer cust = sess.get(Customer.class, id); // It should be PK
			if (cust != null) {
				cust.setCust_name(newCustname);
				sess.merge(cust); // UPDATE
				tx.commit();
				LOGGER.infof("Customer updated successfully: %s", cust);
				return true;
			} else {
				LOGGER.warnf("Customer with ID: %d not found.", id);
			}
		} catch (Exception e) {
			LOGGER.errorf("Failed to update customer with ID: %d", id, e);
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	// DELETE
	public boolean deleteById(Integer id) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			LOGGER.infof("Deleting customer with ID: %d", id);
			Customer cust = sess.get(Customer.class, id);
			if (cust != null) {
				sess.remove(cust);
				tx.commit();
				LOGGER.infof("Customer deleted successfully: %s", cust);
				return true;
			} else {
				LOGGER.warnf("Customer with ID: %d not found.", id);
			}
		} catch (Exception e) {
			LOGGER.errorf("Failed to delete customer with ID: %d", id, e);
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	// READ
	public List<Customer> selectAllCustomer() {
		List<Customer> records = new ArrayList<>();
		try (Session sess = sf.openSession()) {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Customer> sql = builder.createQuery(Customer.class);
			Root<Customer> from = sql.from(Customer.class);
			sql.select(from);

			Query<Customer> query = sess.createQuery(sql);
			records = query.getResultList();
			int recordCount = (int) query.getResultCount();
			LOGGER.infof("Number of rows: %d", recordCount);
			return Collections.unmodifiableList(records);
		} catch (Exception e) {
			LOGGER.error("Failed to retrieve customers.", e);
		}
		return null;
	}
	public Customer selectSingleCustomer(Integer id) {
	    Customer record = new Customer(); 
	    try (Session sess = sf.openSession()) {
	        CriteriaBuilder builder = sess.getCriteriaBuilder();
	        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
	        Root<Customer> root = criteriaQuery.from(Customer.class);
	        
	        criteriaQuery.select(root).where(builder.equal(root.get("customer_id"), id));
	        
	        Query<Customer> query = sess.createQuery(criteriaQuery);
	        record = query.getSingleResult();
	        
	        LOGGER.infof("Successfully retrieved customer with ID: %d", id);
	    } catch (Exception e) {
	    	LOGGER.warnf("No customer found with ID: %d", id);
	    	e.printStackTrace();
	    }
	    return record; 
	}

	
	
	
	
	
	//Other operations using HQL
    public boolean updateCustNameByCity(String city, String newCustname) {
        Session sess = sf.openSession();
        Transaction tx = sess.beginTransaction();
        try {
            String hql = "update Customer c set c.cust_name = ?1 where c.city ilike ?2";
            MutationQuery query = sess.createMutationQuery(hql);
            query.setParameter(1, newCustname);
            query.setParameter(2, city);
            int rowsAffected = query.executeUpdate();
            LOGGER.infof("Number of rows updated: %d for city: %s", rowsAffected, city);
            tx.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            LOGGER.errorf("Failed to update customer name for city: %s", city, e);
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception e1) {
                  e1.printStackTrace();
                }
            }
        } finally {
            try {
                sess.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
    public boolean deleteByCity(String city) {
        Session sess = sf.openSession();
        Transaction tx = sess.beginTransaction();
        try {
            String hql = "delete from Customer c where c.city ilike :city";
            MutationQuery query = sess.createMutationQuery(hql);
            query.setParameter("city", city);
            int rowsAffected = query.executeUpdate();
            LOGGER.infof("Number of rows deleted for city: %s is %d", city, rowsAffected);
            tx.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            LOGGER.errorf("Failed to delete customers for city: %s", city, e);
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
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


}

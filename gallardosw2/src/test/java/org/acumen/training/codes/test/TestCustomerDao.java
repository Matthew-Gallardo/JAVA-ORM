package org.acumen.training.codes.test;

import org.acumen.training.codes.BestBuyConfiguration;
import org.acumen.training.codes.dao.CustomerDao;
import org.acumen.training.codes.model.Customer;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCustomerDao {
    private BestBuyConfiguration config;
    private SessionFactory sf;
    private CustomerDao dao;

    @BeforeEach
    public void setup() {
        config = new BestBuyConfiguration();
        config.createConfiguration(); 
        sf = config.getSessionFactory(); 
        dao = new CustomerDao(sf); 
    }

    @AfterEach
    public void teardown() {
        if (sf != null) {
            sf.close(); 
        }
    }

    @Test
    public void testInsert() {
        Customer cust = new Customer();
        cust.setCust_name("Kanye West");
        cust.setCity("Chicago");
        cust.setGrade(300);
        cust.setSalesman_id(5001);
        dao.insert(cust);
    }

    @Test
    public void testUpdateById() {
        dao.updateCustomername(3012, "Travis Scott");
    }

    @Test
    public void testDeleteById() {
        dao.deleteById(3012);
    }

    @Test
    public void testSelectAllCustomer() {
        System.out.println(dao.selectAllCustomer());
    }

    @Test
    public void testSelectSingleCustomer() {
        Customer rec = dao.selectSingleCustomer(3011);
        System.out.println(rec);
    }

    @Test
    public void testUpdateCustNameByCity() {
        dao.updateCustNameByCity("%Paris%", "Walter White");
    }

    @Test
    public void testDeleteByCity() {
        dao.deleteByCity("New York");
    }
}

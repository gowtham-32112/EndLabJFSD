package com.model;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {

	private final SessionFactory sessionFactory = null;

    public CustomerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> getCustomersByLocation(String location) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            query.select(root).where(cb.equal(root.get("location"), location));
            return session.createQuery(query).getResultList();
        }
    }

    public List<Customer> getCustomersByAgeRange(int minAge, int maxAge) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            query.select(root).where(cb.between(root.get("age"), minAge, maxAge));
            return session.createQuery(query).getResultList();
        }
    }

    public List<Customer> getCustomersWithEmailContaining(String emailPattern) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            query.select(root).where(cb.like(root.get("email"), "%" + emailPattern + "%"));
            return session.createQuery(query).getResultList();
        }
    }
}
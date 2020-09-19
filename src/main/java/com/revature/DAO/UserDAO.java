package com.revature.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

@Repository
public class UserDAO implements IUserDAO {

	private static Logger log = Logger.getLogger(BreweryDAO.class);
	
	public UserDAO() {
		super();
	}
	
	//handles user info and the database
	@Override
	public List<User> findAll(){
		// init to empty list instead than null
		List<User> list = new ArrayList<User>(); 
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);	
		query.select(root);	
		list = s.createQuery(query).getResultList();	
		
		tx.commit();	
		
		return list;
	}

	@Override

	public User findUser(String username) {
		User ret = null;
		try {
			ret = HibernateUtil.getSession().get(User.class, username);
		} catch (IllegalArgumentException e) {
			// TODO log
		} catch (NullPointerException e) {
			// TODO log
		}
		return ret;
	}
	
	@Override
	public boolean saveUser(User u) {
		// also need to check empty id
		if (u == null || u.getUsername().length() < 1) {
			return false;
		}
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		Serializable ret = s.save(u);
		
		if (ret == u.getUsername()) {
			tx.commit();
			log.info("saved " + u.getUsername() + " into database");
			return true;
		} else {
			tx.rollback();
			return false;
		}
	}

	public User findByUsername(String username) {
		return findUser(username);
	}

	@Override
	public boolean updateUser(User u) {
		if (!(u instanceof User)) {
			return false;
		}
		
		Session s = HibernateUtil.getSession();
		User ret = new User();
		Transaction tx = s.beginTransaction();

		try {
			ret = (User) s.merge(u);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return ret.equals(u);
	}

	@Override
	public boolean deleteUser(User u) {
		if (u == null) {
			return false;
		}
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(u);
		
		tx.commit();
		log.info("user deleted");
		return true;
	}
}

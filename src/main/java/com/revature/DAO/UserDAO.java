package com.revature.DAO;

import java.io.Serializable;
import org.apache.log4j.Logger;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Brewery;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

@Repository
public class UserDAO implements IUserDAO{

	private IUserDAO userDAO;
	private static Logger log = Logger.getLogger(UserDAO.class);
	
	//for JUnit testing
	public UserDAO(IUserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}
	
	public UserDAO() {
		super();
	}
	
	//handles user info and the database
	@Override
	public List<User> findAll(){
		List<User> list = null; 
		
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
		return HibernateUtil.getSession().get(User.class, username);
	}
	
	@Override
	public boolean saveUser(User u) {
		if (u == null) {
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
		User user=null;

		try {
			Session s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			
			user = s.get(User.class,new String(username));
			tx.commit();
			log.info("found " + user.getFirstName() + " from database");
		}catch (Exception e)  {
			e.printStackTrace();
			return null;
		}
		
		return user;
	}

	@Override
	public boolean insert(User user) {
		try {
			Session s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			
			//User user = new User(username, password, firstName, lastName, email);		
			
			s.save(user);
			tx.commit();
			log.info("inserted " + user.getFirstName() + " into database");
		}catch (Exception e)  {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	@Override
	public void updateFav(List<Brewery> favorite) {
		
	}

	@Override
	public void updateFirstName(User user, String newFirstname) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		user = s.load(User.class, user.getUsername());
		user.setFirstName(newFirstname);
		s.merge(user);
		tx.commit();
		log.info("updated " + user.getUsername() + " first name to " + newFirstname);
		
	}

	@Override
	public void updateLastName(User user, String newLastname) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		user = s.load(User.class, user.getUsername());
		user.setLastName(newLastname);
		s.merge(user);
		tx.commit();
		log.info("updated " + user.getUsername() + " last name to " + newLastname);
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		user = s.load(User.class, user.getUsername());
		user.setPassword(newPassword);
		s.merge(user);
		tx.commit();
		log.info("updated " + user.getUsername() + " password to " + newPassword);

	}

	@Override
	public void updateEmail(User user, String email) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		user = s.load(User.class, user.getUsername());
		user.setEmail(email);
		s.merge(user);
		tx.commit();
		log.info("updated " +user.getUsername() + " email to " + email);

	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public void setUser(String username, String password, String firstname, String lastname, String email) {
		// TODO Auto-generated method stub
		
	}
}

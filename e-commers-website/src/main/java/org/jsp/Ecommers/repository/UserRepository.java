package org.jsp.Ecommers.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.Ecommers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.name=?1")
	public List<User> findByName(String name);
	@Query("select u from User u where u.phone=?1 and u.password=?2")
	public Optional<User> verify(long phone,String password);
	@Query("select u from User u where u.email=?1 and u.password=?2")
	public Optional<User> verify(String email,String password);
	public Optional<User> findByToken(String token);
}

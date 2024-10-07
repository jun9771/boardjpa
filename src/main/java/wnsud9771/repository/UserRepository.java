package wnsud9771.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import wnsud9771.model.BoardUser;

public interface UserRepository extends JpaRepository<BoardUser, Long>{
	Optional<BoardUser> findByUsername(String username);
}

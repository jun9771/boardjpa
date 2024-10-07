package wnsud9771.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wnsud9771.model.NoticeBoard;

public interface BoardRepository extends JpaRepository<NoticeBoard, Integer>{
	
}

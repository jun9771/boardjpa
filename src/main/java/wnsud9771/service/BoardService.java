package wnsud9771.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import wnsud9771.model.NoticeBoard;
import wnsud9771.repository.BoardRepository;

@Service
public class BoardService {
	private final BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<NoticeBoard> listArticles(){
		List<NoticeBoard> articleList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		return articleList;
	}
	
	public void addArticle(NoticeBoard noticeboard) {
		boardRepository.save(noticeboard);
	}
	
	public NoticeBoard viewArticle(int articleNo) {
 		Optional<NoticeBoard> optional = boardRepository.findById(articleNo);
 		NoticeBoard article = null;
 		if(optional.isPresent()) {
 			article = optional.get();
 		}
		return article;
	}
	
	public void editArticle(NoticeBoard noticeboard) {
		Optional <NoticeBoard> optional = boardRepository.findById(noticeboard.getId());
		NoticeBoard article = null;
		if(optional.isPresent()) {
			article = optional.get();
			article.setTitle(noticeboard.getTitle());
			article.setContent(noticeboard.getContent());
			boardRepository.save(article);
		}
	}
	
	public void deleteArticle(int articleNo) {
		boardRepository.deleteById(articleNo);
	}
}


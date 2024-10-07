package wnsud9771.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import wnsud9771.model.BoardUser;
import wnsud9771.model.UserRole;
import wnsud9771.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<BoardUser> _boardUser = userRepository.findByUsername(username);
		if(_boardUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		BoardUser boardUser = _boardUser.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		if("admin".equals(username)) { // username이 관리자 인지 확인하는 과정
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue())); // 관리자이면 관리자 권한 부여
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));	// 유저이면 유저 권한 부여
		}
		
		
		return new User(boardUser.getUsername(), boardUser.getPassword(), authorities);
	}
	
	
}

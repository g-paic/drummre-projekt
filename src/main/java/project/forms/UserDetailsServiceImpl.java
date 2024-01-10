package project.forms;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.entities.User;
import project.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		
		try {
			user = userRepository.getUserByUsername(username).get();
		} catch(NoSuchElementException no) {
			user = null;
		}
		
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(user);
	}
}

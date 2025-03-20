package com.example.payyoupayme.configuration;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UtilisateurRepository dbUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = dbUserRepository.findByUsername(username).get();

		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
	}

	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}

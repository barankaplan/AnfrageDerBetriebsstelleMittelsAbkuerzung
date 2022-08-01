package com.deutschebahn.rest.data.security;


import com.deutschebahn.rest.data.entity.Role;
import com.deutschebahn.rest.data.entity.User;
import com.deutschebahn.rest.data.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository iUserRepository;

    public CustomUserDetailsService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEMail) throws UsernameNotFoundException {
        User user = iUserRepository.findByUserNameOrEMail(usernameOrEMail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" +
                        "with username or email" + usernameOrEMail));

        return new org.springframework.security.core.userdetails.User(user.getEMail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

package com.example.security.securitydemo.config;

import com.example.security.securitydemo.model.Costumer;
import com.example.security.securitydemo.repository.CostumerRepository;
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
public class CustomUserDetails implements UserDetailsService {

    private final CostumerRepository costumerRepository;

    @Autowired
    public CustomUserDetails(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Costumer> costumerList = costumerRepository.findByEmail(username);

        if (costumerList.size() == 0)
            throw new UsernameNotFoundException("User details not found for the user: " + username);

        Costumer costumer = costumerList.get(0);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(costumer.getRole()));
        return new User(costumer.getEmail(), costumer.getPwd(), authorities);
    }
}

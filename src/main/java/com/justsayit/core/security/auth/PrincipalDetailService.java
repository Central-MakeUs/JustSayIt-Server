package com.justsayit.core.security.auth;

import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Member member = memberRepository.findByToken(token).orElseThrow();
        return new User(member.getToken(), null, mapToSimpleGrandAuthority(member));
    }

    private List<SimpleGrantedAuthority> mapToSimpleGrandAuthority(Member member) {
        return member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }
}

package com.shelby.firstAPI.common.securities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shelby.firstAPI.modules.user.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"email"})
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String email;
    @JsonIgnore
    private String password;

    public static UserDetailsImpl build(UserEntity user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword()
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

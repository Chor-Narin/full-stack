package com.chornarin.site.full_stack.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chornarin.site.full_stack.enums.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private RoleEnum role  = RoleEnum.ADMIN;

    @Column(nullable = true)
    @Builder.Default
    private Boolean accountLocked = false;         

    @Column(nullable =  true)
    @Builder.Default
    private int failedLoginAttempts = 0;           
    private Instant lockTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getAuthority()));

        role.getPermissions().forEach(
            permissions -> authorities.add(new SimpleGrantedAuthority(permissions.name()))
        );

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}

package com.example.firstproject.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserPrincipal implements UserDetails
{
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserPrincipal()
    {

    }

    public MyUserPrincipal(User user)
    {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = new ArrayList<>();
    }

    public MyUserPrincipal(String email, String password, Collection<? extends GrantedAuthority> authorities)
    {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MyUserPrincipal create(User user)
    {
        MyUserPrincipal myUserPrincipal = new MyUserPrincipal();
        myUserPrincipal.email = user.getEmail();
        myUserPrincipal.password = user.getPassword();
        myUserPrincipal.authorities = new ArrayList<>();
        return myUserPrincipal;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }
}
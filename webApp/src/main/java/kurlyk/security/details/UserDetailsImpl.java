package kurlyk.security.details;

import kurlyk.models.Usver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private Usver usver;

    public UserDetailsImpl(Usver usver) {
        this.usver = usver;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usver.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usver.getPassword();
    }

    @Override
    public String getUsername() {
        return usver.getLogin();
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

    public Usver getUsver() {
        return usver;
    }
}

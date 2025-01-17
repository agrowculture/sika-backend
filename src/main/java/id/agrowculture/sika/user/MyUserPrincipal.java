package id.agrowculture.sika.user;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class MyUserPrincipal implements UserDetails {

  private User user;

  public MyUserPrincipal(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.stream(StringUtils.tokenizeToStringArray(this.user.getRoles(), " "))
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isEnabled() {
    return this.user.isEnabled();
  }

}

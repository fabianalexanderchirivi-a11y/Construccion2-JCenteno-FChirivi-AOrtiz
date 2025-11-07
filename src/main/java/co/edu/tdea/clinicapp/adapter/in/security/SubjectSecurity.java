package co.edu.tdea.clinicapp.adapter.in.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class SubjectSecurity {
  public boolean sameUser(String id, Authentication authentication) {
    if (!(authentication instanceof JwtAuthenticationToken token)) return false;
    String sub = token.getToken().getSubject();
    return sub != null && sub.equals(id);
  }
}

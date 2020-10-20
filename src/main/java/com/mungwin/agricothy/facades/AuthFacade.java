package com.mungwin.agricothy.facades;


import org.springframework.security.core.Authentication;

public interface AuthFacade {
  Authentication getAuthentication();
}

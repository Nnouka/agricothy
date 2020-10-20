package com.mungwin.agricothy.controllers;

import com.mungwin.agricothy.domain.dtos.data.PasswordDTO;
import com.mungwin.agricothy.domain.dtos.data.SimpleMailDTO;
import com.mungwin.agricothy.domain.dtos.request.LoginRequestDTO;
import com.mungwin.agricothy.domain.dtos.request.RefreshTokenRequest;
import com.mungwin.agricothy.domain.dtos.request.UserRegistrationDto;
import com.mungwin.agricothy.security.SecurityRestClient;
import com.mungwin.agricothy.security.props.AuthServerProps;
import com.mungwin.agricothy.security.utils.OAuth2Token;
import com.mungwin.agricothy.services.UserService;
import com.mungwin.agricothy.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Base64;

@RestController
@RequestMapping("api/public/user")
public class UserController {
  private UserService userService;
  private AuthServerProps authServerProps;
  private SecurityRestClient securityRestClient;

  @Autowired
  public void setAuthServerProps(AuthServerProps authServerProps) {
    this.authServerProps = authServerProps;
  }

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setSecurityRestClient(SecurityRestClient securityRestClient) {
    this.securityRestClient = securityRestClient;
  }
  @GetMapping("/logout/success")
  public ResponseEntity<String> logout() {
    return ResponseEntity.ok("logout successful");
  }

  @PostMapping("/login")
  public ResponseEntity<OAuth2Token> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO, HttpServletRequest request) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("username", loginRequestDTO.getUsername());
    params.add("password", loginRequestDTO.getPassword());
    HttpUtils.setBaseUrl(request.getScheme(), request.getServerName(), request.getServerPort());
    return ResponseEntity.ok(securityRestClient.getAccessToken(params));
  }
  @PostMapping("/token/refresh")
  public ResponseEntity<OAuth2Token> refreshToken(@RequestBody @Valid RefreshTokenRequest tokenRequest, HttpServletRequest request) {
    HttpUtils.setBaseUrl(request.getScheme(), request.getServerName(), request.getServerPort());
    return ResponseEntity.ok(securityRestClient.getAccessTokenViaRefreshToken(tokenRequest));
  }
  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody @Valid UserRegistrationDto dto, HttpServletRequest request) {
    HttpUtils.setBaseUrl(request.getScheme(), request.getServerName());
    userService.registerUser(dto);
    return ResponseEntity.noContent().build();
  }
  @GetMapping("/verify")
  public RedirectView verifyAccount(@RequestParam("ref") String email, HttpServletRequest request) {
    HttpUtils.setBaseUrl(request.getScheme(), request.getServerName());
    userService.verifyAccount(email);
    String m = Base64.getUrlEncoder().encodeToString("Account verification Successful. Please login".getBytes());
    return new RedirectView("/#/public/login?m=" + m);
  }

  @PostMapping("/password/forgot")
  public ResponseEntity initForgotPasswordRequest(@RequestBody @Valid SimpleMailDTO mailDTO, HttpServletRequest request) {
    HttpUtils.setBaseUrl(request.getScheme(), request.getServerName());
    userService.forgotPassword(mailDTO);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/password/init")
  public RedirectView initResetPasswordRequest(@RequestParam("ref") String code) {
    return new RedirectView("/#/public/password/reset?ref=" +
            Base64.getUrlEncoder().encodeToString(code.getBytes()));
  }

  @PostMapping("/password/reset")
  public ResponseEntity resetPassword(@RequestBody @Valid PasswordDTO dto,
                                      @RequestParam("ref") String code,
                                      HttpServletRequest request) {
    HttpUtils.setBaseUrl(request.getScheme(), request.getServerName());
    userService.resetPassword(code, dto);
    return ResponseEntity.noContent().build();
  }
  private String getBaseUri(String protocol, String serverName, Integer port) {
    return port == null || port == 0 ? protocol + "://" + serverName : protocol + "://" + serverName + ":" + port;
  }
}

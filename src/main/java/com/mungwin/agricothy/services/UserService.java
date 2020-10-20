package com.mungwin.agricothy.services;


import com.mungwin.agricothy.domain.dtos.UserDTO;
import com.mungwin.agricothy.domain.dtos.data.PasswordDTO;
import com.mungwin.agricothy.domain.dtos.data.SimpleMailDTO;
import com.mungwin.agricothy.domain.dtos.request.UserRegistrationDto;
import com.mungwin.agricothy.domain.models.User;

import java.util.List;

public interface UserService {
  UserDTO getUserInfo();
  User getCurrentAuthUser();
  void registerUser(UserRegistrationDto userRegistrationDto);
  void verifyAccount(String email);
  List<User> getAdmins();
  void forgotPassword(SimpleMailDTO simpleMailDTO);
  void resetPassword(String code, PasswordDTO dto);
}

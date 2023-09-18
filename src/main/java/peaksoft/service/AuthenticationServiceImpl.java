package peaksoft.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.controller.UserRepository;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.entity.User;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.BadCredentialException;
import peaksoft.exception.NotFoundException;
import peaksoft.securiy.JwtService;

@Service
@RequiredArgsConstructor
@Transactional

public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())){
            throw new AlreadyExistException("User with email: " + signUpRequest.email() +
                    "already exist !");
        }
       User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role()).build();
 userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token)
                .email(user.getEmail()).role(user.getRole()).build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
      peaksoft.entity.User user =  userRepository.getUserByEmail(signInRequest.email()).orElseThrow(()
                ->new NotFoundException("User with email: " + signInRequest.email() + " doesn't exist ! "));

        if (signInRequest.email().isBlank()){
            throw  new BadCredentialException("Email is blank ! ");
        }
        if (!passwordEncoder.matches(signInRequest.password(),user.getPassword())){
            throw new BadCredentialException(("Wrong password !"));
        }
        String token = jwtService.generateToken(user) ;
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole()).build();

    }
}

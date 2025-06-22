//package com.coachbar.lms.config;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
////    @Autowired
////    private AppUserRepository usersRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<String> userRes = Optional.empty();//usersRepository.findPassword(email);
////        if(!userRes.isPresent())
////            throw new UsernameNotFoundException("Could not findUser with email = " + email);
////        AppUser user = userRes.get();
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new org.springframework.security.core.userdetails.User(
//                email,
////                encoder.encode(user.getPassword()),
//                encoder.encode(userRes.get()),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//
//}
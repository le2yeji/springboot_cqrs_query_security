package com.kt.edu.thirdproject.common.controller;


import com.kt.edu.thirdproject.common.config.RsaUtil;
import com.kt.edu.thirdproject.common.domain.JwtRequest;
import com.kt.edu.thirdproject.common.domain.JwtResponse;
import com.kt.edu.thirdproject.common.service.DecryptService;
import com.kt.edu.thirdproject.common.service.JwtUserDetailsService;
import com.kt.edu.thirdproject.common.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
public class JwtAuthenticationController {

    //@Autowired
    private final AuthenticationManager authenticationManager;

    //@Autowired
    private final JwtTokenUtil jwtTokenUtil;

    //@Autowired
    private final JwtUserDetailsService userDetailsService;



    @PostMapping("/api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.info("[{}] ***************authenticationRequest start",authenticationRequest);
//        // SHA256 PW비교 START
//        if ("db03de15b8000fc35ad975c1322f98124a22521e0616a55c926807eb7225fa38".equals(authenticationRequest.getPassword())) {
//            authenticationRequest.setPassword("edu1234");
//        }
//        // SHA256 PW비교 END
        // RSA 복호화 진행
        DecryptService decryptService = new DecryptService();

        String decryptedData = decryptService.login(authenticationRequest.getPassword());
        authenticationRequest.setPassword(decryptedData);
        log.info("authenticationRequest.getPassword(): " + authenticationRequest.getPassword());
        // RSA 복호화 진행 끝
        log.info("[{}] ***************authenticationRequest end",authenticationRequest);
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

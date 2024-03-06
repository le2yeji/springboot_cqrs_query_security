package com.kt.edu.thirdproject.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
//    private static String publicKeyStr = "";
    // RSA Key 생성
    @PostMapping("/init-rsa")
    public ResponseEntity<?> initrsa(@RequestBody String data) {
        log.debug("*********************Received data******************: {}" + data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
//
//        try {
//            String publicKeyStr = RsaUtil.generateRSAKey();
//            RestMessage restMessage = new RestMessage("success", publicKeyStr);
//            return ResponseEntity.ok(restMessage);
//        } catch (Exception e) {
//            log.error("Error occurred while RSA key generating", e);
//            RestMessage errorMessage = new RestMessage("error", "Error occurred while generating RSA key");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//        }
//    }

//    @PostMapping("/signin")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody SigninRequest request, HttpServletRequest httpServletRequest) throws InterruptedException  {
//        String username = request.getUsername();
//
//         // password RSA 복호화
//        request.setPassword(RsaUtil.passwordDescryptRSA(request.getPassword()));
//
//        try {
//            // 여기서 사용자의 ID와 복호화된 비밀번호를 가지고 로직을 수행하고, 맞는지 여부를 확인하는 작업을 수행합니다.
//            // 이하 예시는 가상의 로직으로 실제 코드에 맞게 변경해주셔야 합니다.
//            if (username.equals("edu") && decryptedPassword.equals("caravan")) {
//                // 인증에 성공하면 토큰을 생성하고 클라이언트에게 반환합니다.
//                UserDetails userDetails = (UserDetails) authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(username, decryptedPassword)
//                ).getPrincipal();
//
//                String token = jwtTokenUtil.generateToken(userDetails);
//                return ResponseEntity.ok(new SigninResponse(token, userDetails));
//            } else {
//                // 인증에 실패하면 클라이언트에게 적절한 오류 메시지를 반환합니다.
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(RestMessage.builder()
//                                .returnCode(RestMessage.NG)
//                                .message("아이디 또는 비밀번호가 올바르지 않습니다.")
//                                .build());
//            }
//        } catch (Exception e) {
//            // 예상치 못한 오류가 발생한 경우 서버 오류 응답을 반환합니다.
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(RestMessage.builder()
//                            .returnCode(RestMessage.NG)
//                            .message("서버 오류가 발생했습니다. 관리자에게 문의하세요.")
//                            .build());
//        }
//    }


//    @PostMapping("/signin")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody SigninRequest request, HttpServletRequest httpServletRequest) throws InterruptedException  {
//        String username = request.getUsername();
//
//        RestMessage restMessage = new RestMessage("success", publicKeyStr);
//        return ResponseEntity.ok(restMessage);
//
//        // password RSA 복호화
//        request.setPassword(RsaUtil.passwordDescryptRSA(request.getPassword()));
//
//        try {
//            UsernamePasswordIpAuthenticationToken token = new UsernamePasswordIpAuthenticationToken(request, clientIp);
//
//            // AuthenticationManager를 사용한 default 인증 시도. (Sha256 암호화 방식 이전 그대로 사용)
//            userDetails = (UserDetails) authenticationManager.authenticate(token).getPrincipal();
//            generateToken = jwtTokenUtil.generateToken(userDetails);
//
//            return ResponseEntity.ok(new SigninResponse(generateToken, userDetails));
//
//        } catch (HttpClientErrorException e) {
//
//            return new ResponseEntity<>(RestMessage.builder()
//                    .returnCode(RestMessage.NG)
//                    .message("로그인에 오류가 발생되었습니다. 관리자에게 문의가 필요합니다.")
//                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

//package com.hongdaestudy.recipebackend.user.application;
//
//import com.hongdaestudy.recipebackend.user.application.in.EmailCodeCommand;
//import com.hongdaestudy.recipebackend.user.application.in.EmailCommand;
//import com.hongdaestudy.recipebackend.user.application.out.EmailCodeResult;
//import com.hongdaestudy.recipebackend.util.RedisUtils;
//import io.netty.util.internal.StringUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class EmailSenderService {
//    private final JavaMailSender emailSender;
//    private final RedisUtils redisUtil;
//
//    public EmailCodeResult sendEmailMessage(EmailCommand email) {
//        String code = createCode();
//        redisUtil.setDataExpire(code, email.getEmail(),  60 * 30L);
//
//        log.debug("이메일 전송 중...");
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email.getEmail());
//        message.setSubject("회원가입 인증 메일입니다. ");
//        message.setText(code);
//        emailSender.send(message);
//        log.debug("이메일 전송 완료");
//
//        return EmailCodeResult.from(code);
//    }
//
//    public Boolean verifyEmail(EmailCodeCommand req) {
//        try {
//            String email = redisUtil.getData(req.getCode());
//            if (email.equals(req.getEmail())) {
//                redisUtil.deleteData(req.getCode());
//                return true;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }
//
//    private static String createCode() {
//        StringBuilder code = new StringBuilder();
//        Random rnd = new Random();
//        for (int i = 0; i < 7; i++) {
//            int rIndex = rnd.nextInt(3);
//            switch (rIndex) {
//                case 0:
//                    code.append((char) (rnd.nextInt(26) + 97));
//                    break;
//                case 1:
//                    code.append((char) (rnd.nextInt(26) + 65));
//                    break;
//                case 2:
//                    code.append((rnd.nextInt(10)));
//                    break;
//            }
//        }
//        return code.toString();
//    }
//}

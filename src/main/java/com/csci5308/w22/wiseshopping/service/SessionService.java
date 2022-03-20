package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Merchant;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Elizabeth James
 */
@Service
public class SessionService {
    public void setSession(int id, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("merchantId", id);
        Cookie merchantID = new Cookie("merchantId", String.valueOf(id));
        merchantID.setHttpOnly(true);
        merchantID.setSecure(true);
        merchantID.setPath("/");
        System.out.println("yat" + merchantID);
        response.addCookie(merchantID);
    }

    // TODO: interceptor, logout, timeout
}

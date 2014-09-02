package com.oksijen.lbs.lbas.functest.pages

import geb.Page

/**
 * Created by cpekyaman on 3/25/2014.
 */
class LoginPage extends Page {
    static url = "login"
    static at = { loginForm.displayed == true }

    static content = {
        loginForm(required: true) { $("form#loginForm") }

        username(required: true) { loginForm.username() }
        password(required: true) { loginForm.password() }        
        
        captcha(required: true) { $('span#captcha') }

        loginError(required: true) { $('div#loginError') }
        
        loginButton(required: true, wait: true, to: [WelcomePage, LoginPage]) {
            $("input#btn_login")}
			
		loginPrivacy(required: true, wait: true) {
				$("input#btn_login")
				
				logoutBtn(required: true) 		{ $("a#btn_logout") }
    }
}
}
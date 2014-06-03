package com.oksijen.lbs.lbas.functest.specs

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.util.TestParams

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by cpekyaman on 3/25/2014.
 */
@Stepwise
class LoginSpec extends GebSpec {
	@Shared params = new TestParams('TestParams')
	
	def setupSpec() {
    	driver.manage().window().maximize()
    } 
    
    def "LoginPage should render successfully"() {
        when: "we want to reach LoginPage url"
        to LoginPage

        then: "LoginPage should be rendered"
        waitFor {at LoginPage}
    }

	
    @Unroll("Empty auth input should keep user at LoginPage for username [#uname] and password [#pwd]")
    def "Empty auth input should keep user at LoginPage without sending request"() {
        given: "we are at the LoginPage"
        to LoginPage

        when: "we enter empty credentials"
        username = uname
        password = pwd

        and: "click login button"
        loginButton.click()

        then: "error message should be displayed on login page"
        expect waitFor { loginError }.text().length(), is(greaterThan(0))

        where:
        uname	<< params.get('login.empty.username')
        pwd		<< params.get('login.empty.password')
    }

    
    @Ignore("Invalid auth input should keep user at LoginPage for username [#uname] and password [#pwd]")
    def "Invalid auth input should keep user at LoginPage"() {
        given: "we are at the LoginPage"
        to LoginPage

        when: "we enter invalid credentials"
        username = uname
        password = pwd

        and: "click login button"
        loginButton.click()

        then: "error message should be displayed on login page"
        waitFor {at LoginPage}
        expect loginError.displayed, is(true)
        expect loginError.text().length(), is(greaterThan(0))

        where:
        uname	<< params.get('login.invalid.username')
        pwd		<< params.get('login.invalid.password')
    }
	
	@Unroll ("Valid auth input should take user to WelcomePage")
	def "Valid auth input should take user to WelcomePage"() {
		given: "we are at the LoginPage"
		to LoginPage

		when: "we enter valid credentials"
		username = params.get('username')
		password = params.get('password')

		and: "click login button"
		loginButton.click()

		then: "we should see WelcomePage"
		waitFor {at WelcomePage}
		
		cleanup: "logout after we finish"
		logoutBtn.click()
	}
	
}

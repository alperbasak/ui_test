package com.oksijen.lbs.lbas.functest.specs.privacyterms

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.privacyterms.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@Stepwise
class PrivacySpec extends LocateSpec {
	@RetryOnFailure(times=5)
	 def "Vodafone Privacy Statements will be rendered"() {
    	given: "We are at the WelcomePage"
    	at WelcomePage
    	
        when: "I click Privacy Statements"
        privacyTerms.click()

        then: "Vodafone Privacy Statements should be rendered"
        waitFor {at PrivacyTermsHomePage}
    }
	 @RetryOnFailure(times=5)
	def "Editing showing the statements at login and rejecting to agree at login won't let me login"(){
		given: "We are at the WelcomePage"
		at PrivacyTermsHomePage
		
		when: "I uncheck the box"
		showAtLogin.click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		then: "When Logout-login, privacy statement will be shown"
		$('a#btn_logout').click()
		at LoginPage
		username << params.get('username')
		password << params.get('password')
		loginPrivacy.click()
		
		and:"Privacy Statement will be shown and select Reject"
		waitFor {$('#privacy-statement').displayed==true}
		$('a.multi_user_button').click()
		waitFor {at LoginPage}
		}
	@RetryOnFailure(times=5)
	def "Agreeing to statement will let me login"() {
		given: "We are at the WelcomePage"
		at LoginPage
		username << params.get('username')
		password << params.get('password')
		loginPrivacy.click()
		waitFor {$('#privacy-statement').displayed==true}
		
		when: "I click agree"
		$('a#acceptPrivacyStatement').click()
		
		then: "I am at Welcome Page"
		waitFor {at WelcomePage}
		}
	@RetryOnFailure(times=5)
	def "Company Privacy Statements will be rendered"() {
		given: "We are at the WelcomePage"
		at WelcomePage
		
		 when: "I click Privacy Statements"
        privacyTerms.click()
		
		then: "Company Privacy Statements should be rendered"
        waitFor {at PrivacyTermsHomePage}
		companyPrivacyTab.click()
		waitFor {companyPrivacyPage.displayed==true}
		waitFor {showAtLoginCompany.displayed==true}
		}
	
}


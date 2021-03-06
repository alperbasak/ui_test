package com.oksijen.lbs.lbas.functest.specs

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.pages.calendar.CalendarHomePage
import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.privacyterms.PrivacyTermsHomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.InboxHomePage
import com.oksijen.lbs.lbas.functest.pages.account.AccountHomePage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.InboxPopupMenu
import com.oksijen.lbs.lbas.functest.pages.inbox.InboxHomePage
import com.oksijen.lbs.lbas.functest.pages.availability.AvailabilityHomePage
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * Created by cpekyaman on 3/25/2014.
 */
@Stepwise
class WelcomeMenuSpec extends LocateSpec {
	@RetryOnFailure(times=5)
	def "Map should be rendered when menu is clicked"() {
		given: "We are at the WelcomePage"
		at WelcomePage
		
		when: "I click map link"
		mapMenu.click()

		then: "Map page should be rendered"
		waitFor {at MapHomePage}
	}
	@RetryOnFailure(times=5)
	def "Calendar should be rendered when menu is clicked"() {
    	given: "We are at the WelcomePage"
    	at WelcomePage
    	
        when: "I click calendar link"
        calendarMenu.click()

        then: "Calendar page should be rendered"
        waitFor {at CalendarHomePage}
    }
	@RetryOnFailure(times=5)
    def "Privacy should be rendered when menu is clicked"() {
    	given: "We are at the WelcomePage"
    	at WelcomePage
    	
        when: "I click privacy link"
        privacyMenu.click()

        then: "Privacy page should be rendered"
        waitFor {at AvailabilityHomePage}
    }
	@RetryOnFailure(times=5)
	def "Popup should be rendered when mouse hovered over it "() {
    	given: "We are at the WelcomePage"
    	at WelcomePage
    	
        when: "I hover over inbox link"
        inboxMenu.jquery.mouseover()

        then: "Inbox popup menu should be rendered"
        waitFor {at InboxPopupMenu}
    }
	@RetryOnFailure(times=5)
    def "Account should be rendered when account button is clicked"() {
    	given: "We are at the WelcomePage"
    	at WelcomePage
    	
        when: "I click account button"
        accountBtn.click()

        then: "Account page should be rendered"
        waitFor {at AccountHomePage}
    }
	@RetryOnFailure(times=5)
	def "Privacy statements should be rendered when privacy statements button is clicked"() {
		given: "We are at the WelcomePage"
		at WelcomePage
		
		when: "I click Privacy Statement button"
		privacyTerms.click()

		then: "Privact terms page should be rendered"
		waitFor {at PrivacyTermsHomePage}
	}
	@RetryOnFailure(times=5)
	def "Inbox should be rendered when menu is clicked"() {
		given: "We are at the WelcomePage"
		at WelcomePage
		
		when: "I click inbox link"
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		popupInbox.jquery.mouseover()
		popupInbox.click()

		then: "Inbox page should be rendered"
		waitFor {at InboxHomePage}
	}
}
package com.oksijen.lbs.lbas.functest.specs.inbox

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.*


/**
 * 
 */
@Stepwise
class InboxPopupSpec extends LocateSpec { 
	
def "Clicking messages takes me to messages page"(){
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		
		when: "I click messages"
		popupTabs.$('li',0).click()
				
		then: "Messages page should render"
		waitFor {at InboxHomePage}
		locateLogo.click()
			}
		

	def "Clicking Incoming Requests takes me to incoming requests page"(){
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		
		when: "I click incoming requests"
		popupTabs.$('li',1).click()
		
		then: "Incoming Requests page should render"
		waitFor {requestsTab.hasClass('ui-state-active')==true}
		locateLogo.click()
			}

	def "Clicking sent messages takes me to sent messages page"() {
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		
		when: "I click Sent messages"
		popupTabs.$('li',2).click()
		
		then:"Sent page should render"
		waitFor {at SentPage}
		locateLogo.click()
		}
	

	def "Clicking sent requests takes me to sent requests page"() {
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		
		when: "I click Sent requests"
		popupTabs.$('li',3).click()
		
		then: "Sent Requests page should render"
		waitFor {requestsTab.hasClass('ui-state-active')==true}
		locateLogo.click()
		}
	}




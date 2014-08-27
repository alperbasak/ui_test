package com.oksijen.lbs.lbas.functest.specs.inbox

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.*

import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*

/**
 * 
 */

@Stepwise
class InboxPopupSpec extends LocateSpec { 
	@RetryOnFailure(times=5)
def "Clicking messages takes me to messages page"(){
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		
		when: "I click messages"
		$('ul.tab-access').find('li',0).click()
				
		then: "Messages page should render"
		waitFor {at InboxHomePage}
		locateLogo.click()
			}
		
@RetryOnFailure(times=5)
	def "Clicking Incoming Requests takes me to incoming requests page"(){
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		
		when: "I click incoming requests"
		$('ul.tab-access').find('li',1).click()
		
		then: "Incoming Requests page should render"
		waitFor {$("li.tab-requests").hasClass('ui-state-active')==true}
		locateLogo.click()
			}
	
	@RetryOnFailure(times=5)
	def "Clicking sent messages takes me to sent messages page"() {
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		
		when: "I click Sent messages"
		$('ul.tab-access').find('li',2).click()
		
		then:"Sent page should render"
		waitFor {at SentPage}
		locateLogo.click()
		}
	
	@RetryOnFailure(times=5)
	def "Clicking sent requests takes me to sent requests page"() {
		given: "We are at the InboxPopupMenu"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		
		when: "I click Sent requests"
		$('ul.tab-access').find('li',3).click()
		
		then: "Sent Requests page should render"
		waitFor {$("li.tab-requests").hasClass('ui-state-active')==true}
		locateLogo.click()
		}
	}




package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

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
class SentReqSpec extends LocateSpec {
	@RetryOnFailure(times=5)
	def "Sending a reminder to a pending request"(){
		given:"I am at Sent Requests Page"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		$('ul.tab-access').find('li',3).jquery.mouseover()
		$('ul.tab-access').find('li',3).click()
		waitFor {at SentPage}
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select the pending request"
		waitFor {$("table#sentList tbody").children().size() > 0}
		if (pendingMsg.size()>0){
		pendingMsg.click()
		waitFor {sendReminder.hasClass('multi_user_button_inactive')==false}
		}
			
		then:"I click Send a reminder"
		sendReminder.click()
		if (pendingMsg.size()>0){
		waitFor {successSent.displayed==true}
		waitFor {successSent.displayed==false}
		}
		
		}
	@RetryOnFailure(times=5)
	def "Sending a reminder to an accepted request"(){
		given:"I am at Sent Requests Page"
		at SentPage
		$('li.SENT_menu').click()
		waitFor { sentTab.hasClass('ui-state-active')==true}
		requestsTab.click()
		waitFor {at SentPage}
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select the accepted request"
		waitFor {$("table#sentList tbody").children().size() > 0}
		if (acceptedMsg.size()>0){
		acceptedMsg.click()
		waitFor {sendReminder.hasClass('multi_user_button_inactive')==false}
		}
			
		then:"I click Send a reminder"
		sendReminder.click()
		if (acceptedMsg.size()>0){
		waitFor {errorSent.displayed==true}
		waitFor {errorSent.displayed==false}
		}
		
		}
	@RetryOnFailure(times=5)
	def "Deleting a sent request"(){
		given:"I am at Sent Requests Page"
		at SentPage
		$('li.SENT_menu').click()
		waitFor { sentTab.hasClass('ui-state-active')==true}
		requestsTab.click()
		waitFor {at SentPage}
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select a sent request"
		waitFor {$("table#sentList tbody").children().size() > 0}
		$('div#sentListCover').find('input.selector').click()
		waitFor {deleteReminder.hasClass('multi_user_button_inactive')==false}
					
		then:"I click delete"
		deleteReminder.click()
//		waitFor {successSent.displayed==true}
//		waitFor {successSent.displayed==false}
		}
			
}



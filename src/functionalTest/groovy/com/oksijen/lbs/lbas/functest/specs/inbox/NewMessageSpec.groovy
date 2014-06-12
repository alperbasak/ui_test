package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.util.TestParams

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.*


/**
 *
 */

class NewMessageSpec extends LocateSpec {
	@IgnoreRest
	def "Clicking cancel button closes new message dialog"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }	
		waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and:"Click cancel button"
		clickButton('cancel')							//dont click
		waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == false }
		waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == false }
	}
	
	def "Empty recipient input returns error"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens; Click send without recipient"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
        waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		clickButton($('form#sendMessage'),'Send')								//dont click
		
		and:"Error message is displayed"
		waitFor { errorMessage.displayed == true}
		
		and:"Detailed dialog opens when clicked on error message"
		errorMessage.click()
		waitFor	{ errorMessageDetail.displayed == true}
	}
		
		
	def "Clicking New message creates a new message"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
        waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }						 //addressList displayed false donuyor
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		clickFirstItem(addressListItems)
		subjectInput << params.get('newMessage.subjectInput')
		messageInput << params.get('newMessage.messageInput')
		
		and:"Click send button"
		clickButton($('div.ui-dialog'), 'Send')						//dont click
		waitfor { successSent.displayed==true }
	}
	
	def "Checking also SMS sends SMS to recipient"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }						 //addressList displayed false donuyor
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		clickFirstItem(addressListItems)
		subjectInput << params.get('newMessage.subjectInput')
		messageInput << params.get('newMessage.messageInput')
	
		and:"Check 'send via SMS' box and send message"
		$("checkbox", name: "isSmsSend").value("false")				//onchange?
		clickButton($('div.ui-dialog'), 'Send')					//dont click
		waitfor { successSent.displayed==true }
	}
}


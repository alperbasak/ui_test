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
@Stepwise
class NewMessageSpec extends LocateSpec {
	
	def "Clicking cancel button closes new message dialog"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		popupInbox.click()
		waitFor { at InboxHomePage}
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }	
		waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and:"Click cancel button"
		$("td.buttons_class button.cancel").click()
		waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == false }
		waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == false }
	}
	
	def "Empty recipient input returns error"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens; Click send without recipient"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
        waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		$("td.buttons_class button.send").click()								
		
		and:"Error message is displayed"
		waitFor('fast') { errorMessage.displayed == true}						
		
		and:"Detailed dialog opens when clicked on error message; close message dialog"
		errorMessage.click()
		waitFor('fast')	{ errorMessageDetail.eq(0).displayed == true}
		$("td.buttons_class button.cancel").click()
	}
	
	def "Clicking X next to name, deletes the name"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
		waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }					
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and click X to delete"
		addressListItems.click()
		expect $("ul.token-input-list-facebook").children().size(), greaterThan(0)
		$("ul.token-input-list-facebook li span").click()
		waitFor("fast") {$("ul.token-input-list-facebook").children().size()==1}
	}
	
	def "Entering over 480 characters in subject area, opens up error dialog"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		addressListItems.click()
		subjectInput << params.get('newMessage.subjectInput')
		messageInput << params.get('newMessage.charLimit')
		$("td.buttons_class button.send").click()
		
		and:"Error message is displayed and error dialog opens when hovered and clicked on error message"
		waitFor('fast') { errorMessage.displayed == true}
		$("a.alert-charts").jquery.mouseover()										
		waitFor('fast') {  errorMessageDetail.eq(3).displayed ==true}
		errorMessage.click()
		waitFor('fast') {  errorMessageDetail.eq(2).displayed ==true}
		$("td.buttons_class button.cancel").click()
	}
	
	def "Clicking Send sends a new message"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
        waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }					
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		addressListItems.click()
		subjectInput << params.get('newMessage.subjectInput')
		messageInput << params.get('newMessage.messageInput')
		
		and:"Click send button"
		$("td.buttons_class button.send").click()
		expect successSent.displayed, is(true)
		waitFor {successSent.displayed==false}
	}
	

	def "Checking also SMS sends SMS to recipient"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Send') == true }
		waitFor('fast') {  hasButton($("form#sendMessage"), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }						
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		addressListItems.click()
		subjectInput << params.get('newMessage.subjectInput')
		messageInput << params.get('newMessage.messageInput')
	
		and:"Check 'send via SMS' box and send message"
		$("span.jqTransformCheckboxWrapper").click()			
		$("td.buttons_class button.send").click()
		expect successSent.displayed, is(true)
		waitFor {successSent.displayed==false}
	}
}


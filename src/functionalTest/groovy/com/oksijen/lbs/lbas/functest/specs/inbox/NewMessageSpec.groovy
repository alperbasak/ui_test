package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.*


/**
 *
 */

class NewMessageSpec extends LocateSpec {
	
	def "Clicking New message creates a new message"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "I click New Message"
		newMessage.click()
		
		then: "New message dialog opens"
		waitFor('fast') { hasButton($('div.ui-dialog'), 'Send') == true }
        waitFor('fast') { hasButton($('div.ui-dialog'), 'Cancel') == true }
		
		and: "I enter messageTo and autocomplete should be visible"
		toInput << params.get('newMessage.toInput')
		waitFor('fast') { addressList.displayed == true }
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		clickFirstItem(addressListItems)
		subjectInput << params.get('newMessage.subjectInput')
		messageInput << params.get('newMessage.messageInput')
		
	}
	
}


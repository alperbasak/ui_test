package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

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
class InboxSpec extends LocateSpec {
	@Ignore
	def "Clicking inbox tab takes me to messages page"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor { at InboxHomePage}
		
		when: "I click Inbox tab"
		inboxPanel.click()
		
		then: "Inbox page should render"
		waitFor {at InboxHomePage}
	}
	@Ignore
	def "Clicking Incoming Requests tab takes me to incoming requests page"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor { at InboxHomePage}
		
		when: "I click Incoming Requests tab"
		requestsInbox.click()
		
		then: "Incoming Requests page should render"
		expect requestsInbox.displayed, is(true)
	}
	@Unroll
	def "Clicking sent tab takes me to sent messages page"() {
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor {at InboxHomePage}
		
		when: "I click Sent tab"
		sentPanel.moveToElement()
		sentPanel.click()
		
		then:"Sent page should render"
		waitFor {at SentPage}
	}
	@Unroll
	def "Clicking trash tab takes me to deleted messages page"() {
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor { at InboxHomePage}
		
		when: "I click Deleted tab"
		trashPanel.click()
		
		then:"Trash page should render"
		waitFor {at TrashPage}
	}
	
	
}



package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.InboxHomePage


/**
 * 
 */
@Stepwise
class InboxSpec extends LocateSpec {
	
	def "Clicking inbox tab takes me to messages page" (){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click Inbox tab"
		inboxMenu.click()
		
		then: "Inbox page should render"
		waitFor {at InboxHomePage}
	}
	
	def "Clicking trash tab takes me to deleted messages page" () {
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click Sent tab"
		sentMenu.click()
		
		then:"Sent page should render"
		waitFor {at SentPage}
	}
	
	def "Clicking sent tab takes me to sent messages page" () {
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "I click Sent tab"
		sentMenu.click()
		
		then:"Trash page should render"
		waitFor {at TrashPage}
	}
	
}



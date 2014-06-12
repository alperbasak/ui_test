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

class InboxTableSpec extends LocateSpec {
	
	def "Checking if buttons activate on checkbox click"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "Check if buttons inactive & at least one message then select one message"
		expect markAsRead.hasClass('multi-user-button-inactive'), is(true)
		expect markAsUnread.hasClass('multi-user-button-inactive'), is(true)
		expect markDelete.hasClass('multi-user-button-inactive'), is(true)
		expect $("table#inboxTable tbody").hasClass('inboxTbTRRowCell'), is(true)
		$("table#inboxTable tbody tr td input").click()
		
		then: "Check if buttons active"
		expect markAsRead.hasClass('multi-user-button-inactive'), is(false)
		expect markAsUnread.hasClass('multi-user-button-inactive'), is(false)
		expect markDelete.hasClass('multi-user-button-inactive'), is(false)
	}

def "Clicking select all, selects all messages"(){
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "Check if buttons inactive & at least one message then select all messages"
	expect markAsRead.hasClass('multi-user-button-inactive'), is(true)
	expect markAsUnread.hasClass('multi-user-button-inactive'), is(true)
	expect markDelete.hasClass('multi-user-button-inactive'), is(true)
	expect $("table#inboxTable tbody").hasClass('inboxTbTRRowCell'), is(true)
	//$("table#inboxTable tbody tr td input").click()
	
	then: "Check if buttons active"
	expect markAsRead.hasClass('multi-user-button-inactive'), is(false)
	expect markAsUnread.hasClass('multi-user-button-inactive'), is(false)
	expect markDelete.hasClass('multi-user-button-inactive'), is(false)
	}
}
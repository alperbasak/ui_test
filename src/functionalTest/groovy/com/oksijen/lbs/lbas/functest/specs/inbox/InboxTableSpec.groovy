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
	@IgnoreRest
	def "Checking if buttons activate on checkbox click"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "Check if buttons inactive & at least one message then select one message"
		waitFor {markAsRead.parent()=="multi-user-button-inactive"}				//false
		waitFor {markAsUnread.parent()=='multi-user-button-inactive'}
		waitFor {markDelete.parent()=='multi-user-button-inactive'}
		waitFor {$("table#inboxTable tbody").children=='inboxTbTRRowCell'}
		//waitFor('fast') {markBtns.children()=="multi-user-button-inactive"}  		//false
		$("table#inboxTable tbody tr td input.selector").click()
		
		then: "Check if buttons active"
		expect markBtns.has('multi-user-button-inactive'), is(false)
//		expect markAsRead.has('multi-user-button-inactive'), is(false)
//		expect markAsUnread.has('multi-user-button-inactive'), is(false)
//		expect markDelete.has('multi-user-button-inactive'), is(false)
	
		}

	def "Clicking select all, selects all messages"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		
		when: "Check if buttons inactive & at least one message then select all messages"
//		waitFor { markAsRead.hasClass('multi-user-button-inactive')==true}
//		waitFor {markAsUnread.hasClass('multi-user-button-inactive')==true}
//		waitFor {markDelete.hasClass('multi-user-button-inactive')==true}
//		waitFor {$("table#inboxTable tbody").hasClass('inboxTbTRRowCell')==true}
		waitFor('fast') {markBtns.hasClass("multi-user-button-inactive")==true}  		//false
		$("input#messages-tab-select-all").click()
		
		then: "Check if buttons active"
		expect markAsRead.hasClass('multi-user-button-inactive'), is(false)
		expect markAsUnread.hasClass('multi-user-button-inactive'), is(false)
		expect markDelete.hasClass('multi-user-button-inactive'), is(false)
	}

//	def "Clicking Mark as Read, changes selected message status as read"(){
//		given: "We are at the InboxHomePage"
//		at WelcomePage
//		inboxMenu.click()
//		waitFor('slow') { at InboxHomePage}
//
//		when: "Check for at least one message and select one,"
//		//waitFor {$("table#inboxTable tbody").hasClass('inboxTbTRRowCell')==true}
//		$("table#inboxTable tbody tr td input.selector").click()
//		
//		
//}
	}
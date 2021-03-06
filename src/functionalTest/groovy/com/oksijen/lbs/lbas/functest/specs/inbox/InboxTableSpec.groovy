package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.*

import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*

/**
 *
 */
@Stepwise
class InboxTableSpec extends LocateSpec {
	

	@RetryOnFailure(times=3)
	def "Checking if buttons activate on checkbox click"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		popupInbox.jquery.mouseover()
		popupInbox.click()
		waitFor { at InboxHomePage}
		
		when: "Check if buttons inactive & at least one message then select one message"
		waitFor('fast') {markAsRead.hasClass('multi_user_button_inactive')==true}
		waitFor('fast') {markAsUnread.hasClass('multi_user_button_inactive')==true}
		waitFor('fast') {markDelete.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody").children().size() > 0}
		$("table#inboxTable tbody tr td input.selector").click()
		
		then: "Check if buttons active"
		expect markAsRead.hasClass('multi_user_button_inactive'), is(false)
		expect markAsUnread.hasClass('multi_user_button_inactive'), is(false)
		expect markDelete.hasClass('multi_user_button_inactive'), is(false)
		inboxPanel.click()
		}
	
	@RetryOnFailure(times=3)
	def "Clicking select all, selects all messages"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "Check if buttons inactive & at least one message then select all messages"
		waitFor('fast') {markAsRead.hasClass('multi_user_button_inactive')==true}
		waitFor('fast') {markAsUnread.hasClass('multi_user_button_inactive')==true}
		waitFor('fast') {markDelete.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody").children().size() > 0} 	
		$("input#messages-tab-select-all").click()
		
		then: "Check if buttons active"
		expect markAsRead.hasClass('multi_user_button_inactive'), is(false)
		expect markAsUnread.hasClass('multi_user_button_inactive'), is(false)
		expect markDelete.hasClass('multi_user_button_inactive'), is(false)
		
		and:
		markAsUnread.click()
		waitFor {markAsUnread.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody tr").first().find("div").hasClass('bold')==true}
		inboxPanel.click()
	}
	
	@RetryOnFailure(times=3)
	def "Clicking Delete, Deletes selected message"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
	
		when: "Check if buttons inactive & at least one message then select one message"
		waitFor('fast') {markDelete.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody").children().size() > 0}
		$("table#inboxTable tbody tr td input.selector").click()
				
		then: "Click Delete and the message will be deleted"
		waitFor {markDelete.hasClass('multi_user_button_inactive')==false}
		markDelete.click()
//		waitFor('fast') {successSent.displayed==true}
		waitFor {markDelete.hasClass('multi_user_button_inactive')==true}
		inboxPanel.click()
		}
	
	@RetryOnFailure(times=3)
	def "Clicking Mark As Read, changes selected message status as read"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "Check if buttons inactive & at least one message then select one unread message"
		waitFor('fast') {markAsRead.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody").children().size() > 0}
		waitFor {$("table#inboxTable tbody tr").first().find("div").hasClass('bold')==true}
		$("table#inboxTable tbody tr td input.selector").click()
				
		then: "Click Mark as Read and the message will be marked as read "
		waitFor {markAsRead.hasClass('multi_user_button_inactive')==false}
		markAsRead.click()
		waitFor {markAsRead.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody tr").first().find("div").hasClass('bold')==false}
		inboxPanel.click()
		}
	
	
	@RetryOnFailure(times=3)
	def "Clicking Mark As Unread, changes selected message status as unread"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when: "Check if buttons inactive & at least one message then select one read message"
		waitFor('fast') {markAsUnread.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody").children().size() > 0}
		waitFor {$("table#inboxTable tbody tr").first().find("div").hasClass('bold')==false}
		$("table#inboxTable tbody tr td input.selector").click()
				
		then: "Click Mark as Unread and the message will be marked as unread "
		waitFor {markAsUnread.hasClass('multi_user_button_inactive')==false}
		markAsUnread.click()
		waitFor {markAsUnread.hasClass('multi_user_button_inactive')==true}
		waitFor {$("table#inboxTable tbody tr").first().find("div").hasClass('bold')==true}
		inboxPanel.click()
		}
	
}
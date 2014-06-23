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
class DeleteSpec extends LocateSpec {

	def "Clicking Delete at Trash Page opens confirmation dialog; clicking cancel, cancels delete operation"() {
		given: "We are at the TrashPage"
		at WelcomePage
		inboxMenu.moveToElement()
		waitFor('slow') {at InboxPopupMenu}
		inboxMenu.click()
		waitFor('slow') { at InboxHomePage}
		trashPanel.click()
		waitFor {at TrashPage}
		
		when: "I select a message"
		waitFor {$("table#trashTable tbody").children().size() > 0}
//		def inputValue = { $("table#trashTable tbody tr td input").val()}				//check value algorithm needed
		$("table#trashTable tbody tr td input.selector").click()
		markDeleteTrash.click()
		waitFor('fast') {cancelTrash.displayed==true}
		
		then: "I click Cancel and selected message will not be permanently deleted"
		cancelTrash.click()
		waitFor {cancelTrash.displayed==false}
		trashPanel.click()
		
		}
	
	def "Clicking Delete at Trash Page opens confirmation dialog; clicking delete, deletes message permanently"() {
		given: "We are at the TrashPage"
		at TrashPage	
		
		when: "I select a message"
		waitFor {$("table#trashTable tbody").children().size() > 0}
		$("table#trashTable tbody tr td input.selector").click()
		markDeleteTrash.click()
		waitFor('fast') {deleteTrash.displayed==true}
		
		then: "I click Delete and selected message will be permanently deleted"
		deleteTrash.click()
		waitFor {deleteTrash.displayed==false}
		trashPanel.click()
		}
	
	def "Clicking Back to inbox at Trash Page returns the message back to inbox"() {
		given: "We are at the TrashPage"
		at TrashPage
		
		when: "I select a message"
		waitFor {$("table#trashTable tbody").children().size() > 0}
		waitFor('fast') {backToInbox.hasClass('multi_user_button_inactive')==true}
		$("table#trashTable tbody tr td input.selector").click()
		
		then: "I click Delete and selected message will be permanently deleted"
		backToInbox.click()
		waitFor('fast') {backToInbox.hasClass('multi_user_button_inactive')==true}
		trashPanel.click()
		}
	
	def "Selecting All and clicking Back to inbox at Trash Page returns all selected messages back to inbox"() {
		given: "We are at the TrashPage"
		at TrashPage
		
		when: "I select a message"
		waitFor {$("table#trashTable tbody").children().size() > 0}
		waitFor('fast') {backToInbox.hasClass('multi_user_button_inactive')==true}
		$("#tab-trash ul li input#messages-tab-select-all").click()

		then: "I click Delete and selected message will be permanently deleted"
		backToInbox.click()
		waitFor('fast') {backToInbox.hasClass('multi_user_button_inactive')==true}
		trashPanel.click()
		}
	
	
	def "Clicking Select all and Delete at Trash Page opens confirmation dialog; clicking delete, deletes all selected messages permanently"() {
		given: "We are at the TrashPage"
		at TrashPage
		
		when: "I select all messages"
		waitFor {$("table#trashTable tbody").children().size() > 0}
		$("#tab-trash ul li input#messages-tab-select-all").click()
		markDeleteTrash.click()
		waitFor('fast') {deleteTrash.displayed==true}
		
		then: "I click Delete and selected messages will be permanently deleted"
		deleteTrash.click()
		waitFor {deleteTrash.displayed==false}
		trashPanel.click()
	}
	
		
}



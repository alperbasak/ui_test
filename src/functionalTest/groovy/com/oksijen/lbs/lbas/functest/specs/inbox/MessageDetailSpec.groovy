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
class MessageDetailSpec extends LocateSpec {
	
	
def "Clicking on a message displays the message"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I go back to inboxPage"
	expect messageDetail.displayed, is(true)
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Reply, opens reply dialog; Cancel, closes dialog"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply"
	waitFor('fast') {messageDetail.displayed==true}
	replyBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I click Cancel and reply dialog closes and I go back to inboxPage"
	$("td.buttons_class button.cancel").click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == false }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == false }	
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Reply, opens reply dialog; Send, sends the message"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply"
	waitFor('fast') {messageDetail.displayed==true}
	replyBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I click Send and success dialog renders and I go back to inboxPage"
	messageInput << params.get('messageDetail.reply')
	$("td.buttons_class button.send").click()
	expect successSent.displayed, is(true)
	waitFor {successSent.displayed==false}
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Reply, opens reply dialog; via SMS is selected, also sends the message via SMS"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply"
	waitFor('fast') {messageDetail.displayed==true}
	replyBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I select Via SMS checkbox, click Send and success dialog renders and I go back to inboxPage"
	messageInput << params.get('messageDetail.reply')
	$("span.jqTransformCheckboxWrapper").click()
	$("td.buttons_class button.send").click()
	expect successSent.displayed, is(true)
	waitFor {successSent.displayed==false}
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Reply All, opens reply all dialog; Cancel, closes dialog"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply all"
	waitFor('fast') {messageDetail.displayed==true}
	replyAllBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I click Cancel and reply all dialog closes and I go back to inboxPage"
	$("td.buttons_class button.cancel").click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == false }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == false }
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Reply All, opens reply all dialog; Send, sends the message"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply All"
	waitFor('fast') {messageDetail.displayed==true}
	replyAllBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I check for multiple recipients; I click Send and success dialog renders"
	expect $("ul.token-input-list-facebook").children().size(), greaterThan(2)
	messageInput << params.get('messageDetail.replyAll')
	$("td.buttons_class button.send").click()
	expect successSent.displayed, is(true)
	waitFor {successSent.displayed==false}
	
	and:"I go back to inboxPage"
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Reply all, opens reply all dialog; via SMS is selected, also sends the message via SMS"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
		
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply All"
	waitFor('fast') {messageDetail.displayed==true}
	replyAllBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I select Via SMS checkbox, click Send and success dialog renders"
	expect $("ul.token-input-list-facebook").children().size(), greaterThan(2)
	messageInput << params.get('messageDetail.replyAll')
	$("span.jqTransformCheckboxWrapper").click()
	$("td.buttons_class button.send").click()
	expect successSent.displayed, is(true)
	waitFor {successSent.displayed==false}
	
	and:"I go back to inboxPage"
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}
def "Clicking on Forward, opens forward dialog; Cancel, closes dialog"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Reply all"
	waitFor('fast') {messageDetail.displayed==true}
	forwardBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I click Cancel and forward dialog closes and I go back to inboxPage"
	$("td.buttons_class button.cancel").click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == false }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == false }
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Forward, opens forward dialog; Send, sends the message"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
	
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Forward"
	waitFor('fast') {messageDetail.displayed==true}
	forwardBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I enter recipient, I click Send and success dialog renders"
	toInput << params.get('newMessage.toInput')
	waitFor { addressList.displayed == true }
	expect addressListItems.size(), greaterThan(0)
	addressListItems.click()
	messageInput << params.get('messageDetail.forward')
	$("td.buttons_class button.send").click()
	expect successSent.displayed, is(true)
	waitFor {successSent.displayed==false}
	
	and:"I go back to inboxPage"
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Forward, opens forward dialog; via SMS is selected, also sends the message via SMS"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
		
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Forward"
	waitFor('fast') {messageDetail.displayed==true}
	forwardBtn.click()
	waitFor('fast') { hasButton($("form#sendMessage"), 'Send') == true }
	waitFor('fast') { hasButton($("form#sendMessage"), 'Cancel') == true }
	
	and:"I select Via SMS checkbox, click Send and success dialog renders"
	toInput << params.get('newMessage.toInput')
	waitFor { addressList.displayed == true }
	expect addressListItems.size(), greaterThan(0)
	addressListItems.click()
	messageInput << params.get('messageDetail.forward')
	$("span.jqTransformCheckboxWrapper").click()
	$("td.buttons_class button.send").click()
	expect successSent.displayed, is(true)
	waitFor {successSent.displayed==false}
	
	and:"I go back to inboxPage"
	$("a.goBack").click()
	waitFor { at InboxHomePage}
}

def "Clicking on Delete, opens delete success dialog"() {
	given: "We are at the InboxHomePage"
	at WelcomePage
	inboxMenu.click()
	waitFor('slow') { at InboxHomePage}
		
	when: "I click on a message"
	waitFor {$("table#inboxTable tbody").children().size() > 0}
	$("table#inboxTable tbody tr.inboxTbTRRowCell").click()
	
	then:"Message should render and I click Delete"
	waitFor('fast') {messageDetail.displayed==true}
	deleteBtn.click()
//	waitFor('fast') {successSent.displayed==true}
//	waitFor('fast') {successSent.displayed==false}
	
}
}



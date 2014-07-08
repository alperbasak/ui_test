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
class MessageNavSpec extends LocateSpec {
	
	
	def "Clicking next arrow takes me to next messages page"(){
		given: "We are at the InboxHomePage"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		popupInbox.click()
		waitFor { at InboxHomePage}
		
		when:"I check for if I'm at the first page and click next arrow"
		waitFor('fast') {$("div#inboxPaging ul li", 0).children().hasClass('active')==true}
		$("div#inboxPaging a.inboxPagingLink", 0).click()
				
		then:"I check for if I'm at the second page"
		waitFor('fast') {$("div#inboxPaging ul li", 1).children().hasClass('active')==true}
		inboxPanel.click()
		}
	
	
	def "Clicking last arrow takes me to last messages page"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when:"I check for if I'm at the first page and click last arrow"
		waitFor('fast') {$("div#inboxPaging ul li", 0).children().hasClass('active')==true}
		$("div#inboxPaging a.inboxPagingLink", 1).click()
		
		then:"I check for if I'm at the last page"
		waitFor {$("div#inboxPaging ul li").last().children().hasClass('active')==true}
		inboxPanel.click()
	}
	
	def "Clicking prev arrow takes me to previous messages page"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when:"I check for if I'm at the last page and click prev arrow"
		$("div#inboxPaging a.inboxPagingLink", 1).click()
		waitFor('fast') {$("div#inboxPaging ul li").last().children().hasClass('active')==true}
		$("div#inboxPaging a.inboxPagingLink", 1).click()
		
		then:"I check for if I'm at the previous to last page"
		waitFor('fast') {$("div#inboxPaging ul li").last().previous().children().hasClass('active')==true}
		inboxPanel.click()
		}
	
	
	def "Clicking first arrow takes me to first messages page"(){
		given: "We are at the InboxHomePage"
		at InboxHomePage
		
		when:"I check for if I'm at the last page and click first arrow"
		$("div#inboxPaging a.inboxPagingLink", 1).click()
		waitFor('fast') {$("div#inboxPaging ul li").last().children().hasClass('active')==true}
		$("div#inboxPaging a.inboxPagingLink", 0).click()
		
		then:"I check for if I'm at the first page"
		waitFor('fast') {$("div#inboxPaging ul li", 0).children().hasClass('active')==true}
		inboxPanel.click()
		}
	
}


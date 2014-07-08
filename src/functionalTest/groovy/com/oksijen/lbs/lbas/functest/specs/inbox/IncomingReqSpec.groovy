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
class IncomingReqSpec extends LocateSpec {

	
	def "Rejecting Location Permission Request"(){
		given:"I am at Incoming Requests Page"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		popupTabs.$('li',1).click()
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select default visibility profile"
		waitFor {$("table#requestedList tbody").children().size() > 0}
		$("table#requestedList tbody").children().click()
		waitFor {$('ul.action').hasClass("buttons_class")==true}
		$('div.permanent span a').click()
		defaultVisibility.click()
		
		then:"I click Reject"
		locationReject.click()
		waitFor {$('div.ui-dialog').displayed==true}
		waitFor {$('body').hasClass('div.ui-dialog')==false}
		}
	
	def "Accepting Location Permission Request"(){				
		given:"I am at Incoming Requests Page"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor {at InboxPopupMenu}
		popupTabs.$('li',1).click()
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select default visibility profile"
		waitFor {$("table#requestedList tbody").children().size() > 0}
		$("table#requestedList tbody").children().click()
		waitFor {$('ul.action').hasClass("buttons_class")==true}
		$('div.permanent span a').click()
		defaultVisibility.click()
		
		then:"I click Accept"
		locationAccept.click()
		waitFor {$('div.ui-dialog').displayed==true}
		waitFor {$('body').hasClass('div.ui-dialog')==false}
		
		}
		
	}
		




package com.oksijen.lbs.lbas.functest.specs.inbox

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.inbox.*
import com.oksijen.lbs.lbas.functest.pages.availability.*

import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*


/**
 * 
 */
@Stepwise
class IncomingReqSpec extends LocateSpec {

	@RetryOnFailure
	def "Rejecting Location Permission Request"(){
		given:"I am at Incoming Requests Page"
		at WelcomePage
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		$('ul.tab-access').find('li',1).jquery.mouseover()
		$('ul.tab-access').find('li',1).click()
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		if($("table#requestedList tbody").children().size()==0){
		request('user4','alper')
		request('alper3','alper')
		$('#btn_logout').click()
		waitFor{at LoginPage}
		username << params.get('username')
		password << params.get('password')
		loginButton.click()}
		waitFor {at WelcomePage}
		inboxMenu.jquery.mouseover()
		waitFor{$('.menu-popup').displayed==true}
		$('ul.tab-access').find('li',1).jquery.mouseover()
		$('ul.tab-access').find('li',1).click()
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select default visibility profile"
		waitFor {$("table#requestedList tbody").children().size() > 0}
		$("table#requestedList tbody").children().click()
		waitFor {$('ul.action').hasClass("buttons_class")==true}
		if ($('div.permanent').size()>0){
		$('div.permanent span a').click()
		defaultVisibility.click()
		}
		
		then:"I click Reject"
		locationReject.click()
		waitFor {$('div.ui-dialog').displayed==true}
		waitFor {$('body').hasClass('div.ui-dialog')==false}
		}
	@RetryOnFailure
	def "Accepting Location Permission Request"(){				
		given:"I am at Incoming Requests Page"
		waitFor { requestsTab.hasClass('ui-state-active')==true}
		
		when: "I select default visibility profile and accept"
		if($("table#requestedList tbody").children().size() > 0){
		$("table#requestedList tbody").children().click()
		waitFor {$('ul.action').hasClass("buttons_class")==true}
		if ($('div.permanent').size()>0){
		$('div.permanent span a').click()
		defaultVisibility.click()
		}
		locationAccept.click()
		waitFor {$('div.ui-dialog').displayed==true}
		waitFor {$('body').hasClass('div.ui-dialog')==false}
		}
		then:"Delete permission"
		$('#btn_privacy').click()
		waitFor { at AvailabilityHomePage }
		locateMe.click()
		waitFor { at LocateMePage }
		
		$('tr.makeHover',username:'user4 user4').jquery.mouseover()
		waitFor {$('tr.makeHover',username:'user4 user4').find('a.delete').displayed==true}
		$('tr.makeHover',username:'user4 user4').find('a.delete').click()
		waitFor {$('#dialog').displayed==true}
		sendBtn.click()
		waitFor {$('#dialog').displayed==false}
		}
		
	}
		




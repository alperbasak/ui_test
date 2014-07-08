package com.oksijen.lbs.lbas.functest.specs.availability

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.availability.*


/**
 * 
 */
@Stepwise
class MyAvailabilitySpec extends LocateSpec {
    
	def "My availability page is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		
		when: "I click Availability tab"
		privacyMenu.click()
		
		then: "My Availability page should render"
		waitFor { at AvailabilityHomePage }
	}
	
	def "Updating visibility changes the visibility status"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I change visibility to Not visible"
		visibilityStatus.click()
		notVisible.click()
		
		then:"No one can view your location"
		waitFor {  $("div#plusBox div").text().startsWith("No one")}
				
	}
	
	def "When mouse is hovered over i, visibility profile is shown"(){
	given:"We are at the My availability page"
	at AvailabilityHomePage
	
	when:"I hover over info to see detailed profile"
	$('div#plusBox').click()
	$("a.info").jquery.mouseover()
	
	then:
	waitFor {$('div#calendarX').displayed==true}
	$('div#minusBox').jquery.mouseover()
	$('div#minusBox').click()
	}
	
	
	def "Cancel deleting locator permission"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I change visibility to Visible"
		visibilityStatus.click()
		visible.click()
		
		then:"Delete one locator"
		$('div#plusBox').click()
		waitFor {$('table#locating tbody').children().size() > 0}
		$('table#locating').find('a.removeLocation').click()
		
		and: "Click cancel"
		waitFor {$('div#dialog').displayed==true}
		$('#dialog').find('button.cancel').click()
		waitFor {$('table#locating tbody').children().size() > 0}
	}
	
	def "Deleting locator permission"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I change visibility to Visible"
		visibilityStatus.click()
		visible.click()
		
		then:"Delete one locator"
		waitFor {$('table#locating tbody').children().size() > 0}
		$('table#locating').find('a.removeLocation').click()
		
		and: "Click delete"
		waitFor {$('div#dialog').displayed==true}
		$('#dialog').find('button.send').click()
		waitFor {$('div.successMessageCheck').displayed==true}
		waitFor {$('div.successMessageCheck').displayed==false}
	}
	
	def "Cancel adding exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Add exception"
		$('a#addExcp').click()
		waitFor {$('#addExcp-form').displayed==true}
		$('input#name') << params.get('addExcp.excpTitle')
		
		then:"Click cancel"
		$('#dialog').find('button.cancel').click()
		waitFor {$('table#excp').displayed==false}
	
	}
	
	def "Add exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Add exception"
		$('a#addExcp').click()
		waitFor {$('#addExcp-form').displayed==true}
		$('input#name') << params.get('addExcp.excpTitle')
		
		then:"Click Add"
		$('#dialog').find('button.send').click()
		waitFor {$('div#activeException').displayed==true}
		waitFor {$("span.ui-selectmenu-status").text().startsWith("Not")}
		waitFor {$('table#excp').displayed==true}
	
	}
	
		def "Cancel Edit exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Edit exception"
		$('a#editExceptionRow').click()
		waitFor {$('#dialog').displayed==true}
		$('input#name') << params.get('editExcp.excpTitle')
		
		then:"Click Cancel"
		$('#dialog').find('button.cancel').click()
		waitFor {$('div#activeException').displayed==true}
		waitFor {$("span.ui-selectmenu-status").text().startsWith("Not")}
		waitFor {$('table#excp').displayed==true}
	
	}
	
	def "Entering a later start time than end time returns error while editing exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Edit exception"
		$('a#editExceptionRow').click()
		waitFor {$('#dialog').displayed==true}
		$('input#name') << params.get('editExcp.excpTitle')
				
		then:"Click calender for start time"
		$('img.ui-datepicker-trigger').click()
		waitFor {$('div#ui-datepicker-div').displayed==true}
		$('a.ui-datepicker-next').click()
		$('table.ui-datepicker-calendar tbody tr',2).children().click()
				
		and:"Clicking Edit will return error"
		$('#dialog').find('button.send').click()
		waitFor {$('span#ui-dialog-title-dialog').displayed==true}
		$('div.ui-dialog-buttonset button').click()
		$('#dialog').find('button.cancel').click()
		$('li#availabilityMe').click()
	}

	def "Edit exception to a later time"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Edit exception"
		$('a#editExceptionRow').click()
		waitFor {$('#dialog').displayed==true}
		$('input#name') << params.get('editExcp.excpTitle')
				
		then:"Edit start-end dates"
		$('img.ui-datepicker-trigger').click()
		waitFor {$('div#ui-datepicker-div').displayed==true}
		$('a.ui-datepicker-next').click()
		$('table.ui-datepicker-calendar tbody tr',2).children().click()
		$('img.ui-datepicker-trigger').last().click()
		waitFor {$('div#ui-datepicker-div').displayed==true}
		$('a.ui-datepicker-next').click()
		$('table.ui-datepicker-calendar tbody tr',2).children().click()
		waitFor {$('div#ui-datepicker-div').displayed==false}
		
		and:"Clicking Edit"
		$('#dialog').find('button.send').click()
		waitFor {$('div#activeException').displayed==false}
		waitFor {$('table#excp').displayed==true}
		waitFor {$('table#excp').find('td.first').text().contains("-Edited")}
	
	} 
	
	def "Cancel Delete exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Delete exception"
		$('a#removeExceptionRow').click()
		
		then:"I click cancel on delete dialog"
		waitFor {$('div#dialog').displayed==true}
		$('#dialog').find('button.cancel').click()
		waitFor {$('table#excp').displayed==true}
		
	}
	
	def "Delete exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Delete exception"
		$('a#removeExceptionRow').click()
		
		then:"I click delete on delete dialog"
		waitFor {$('div#dialog').displayed==true}
		$('#dialog').find('button.send').click()
		waitFor {$('table#excp').displayed==false}
		
	}
	
	def "Changing e-mail notifications"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I uncheck email notifications checkbox"
		$('input#notificationEmailCheck').click()
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==true}
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==false}
		
		then:"I select notification frequency"
		$('a#notificationEmailSelect-button').click()
		$("ul#notificationEmailSelect-menu li:not(.ui-selectmenu-item-selected)").click()
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==true}
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==false}
		
		
	}

		def "Changing sms notifications"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I uncheck sms notifications checkbox"
		$('input#notificationSmsCheck').click()
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==true}
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==false}
		
		then:"I select notification frequency"
		$('a#notificationSmsSelect-button').click()
		$("ul#notificationSmsSelect-menu li:not(.ui-selectmenu-item-selected)").click()
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==true}
		waitFor {$('div.ui-dialog').last().hasClass('success-dialog')==false}
	}
	
}



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
	plusBox.click()
	$("a.info").jquery.mouseover()
	
	then:
	waitFor {profileInfo.displayed==true}
	minusBox.jquery.mouseover()
	minusBox.click()
	}
	
	
	def "Cancel deleting locator permission"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I change visibility to Visible"
		visibilityStatus.click()
		visible.click()
		
		then:"Delete one locator"
		plusBox.click()
		waitFor {tableLocator.size() > 0}
		deleteLocator.click()
		
		and: "Click cancel"
		waitFor {$('div#dialog').displayed==true}
		cancelBtn.click()
		waitFor {tableLocator.size() > 0}
	}
	
	def "Deleting locator permission"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I change visibility to Visible"
		visibilityStatus.click()
		visible.click()
		
		then:"Delete one locator"
		waitFor {tableLocator.size() > 0}
		deleteLocator.click()
		
		and: "Click delete"
		waitFor {$('div#dialog').displayed==true}
		sendBtn.click()
		waitFor {successMsg.displayed==true}
		waitFor {successMsg.displayed==false}
	}
	
	def "Cancel adding exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Add exception"
		addExcp.click()
		waitFor {$('#addExcp-form').displayed==true}
		$('input#name') << params.get('addExcp.excpTitle')
		
		then:"Click cancel"
		cancelBtn.click()
		waitFor {excpTable.displayed==false}
	
	}
	
	def "Add exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Add exception"
		addExcp.click()
		waitFor {$('#addExcp-form').displayed==true}
		$('input#name') << params.get('addExcp.excpTitle')
		
		then:"Click Add"
		sendBtn.click()
		waitFor {activeExcp.displayed==true}
		waitFor {$("span.ui-selectmenu-status").text().startsWith("Not")}
		waitFor {excpTable.displayed==true}
	
	}
	
		def "Cancel Edit exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Edit exception"
		editExcp.click()
		waitFor {$('#dialog').displayed==true}
		$('input#name') << params.get('editExcp.excpTitle')
		
		then:"Click Cancel"
		cancelBtn.click()
		waitFor {activeExcp.displayed==true}
		waitFor {$("span.ui-selectmenu-status").text().startsWith("Not")}
		waitFor {excpTable.displayed==true}
	
	}
	
	def "Entering a later start time than end time returns error while editing exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Edit exception"
		editExcp.click()
		waitFor {$('#dialog').displayed==true}
		$('input#name') << params.get('editExcp.excpTitle')
				
		then:"Click calender for start time"
		datePicker.click()
		waitFor {calendarSelect.displayed==true}
		nextMonth.click()
		dateSelect.click()
				
		and:"Clicking Edit will return error"
		sendBtn.click()
		waitFor {$('span#ui-dialog-title-dialog').displayed==true}
		$('div.ui-dialog-buttonset button').click()
		cancelBtn.click()
		$('li#availabilityMe').click()
	}

	def "Edit exception to a later time"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Edit exception"
		editExcp.click()
		waitFor {$('#dialog').displayed==true}
		$('input#name') << params.get('editExcp.excpTitle')
				
		then:"Edit start-end dates"
		datePicker.click()
		waitFor {calendarSelect.displayed==true}
		nextMonth.click()
		dateSelect.click()
		datePicker.last().click()
		waitFor {calendarSelect.displayed==true}
		nextMonth.click()
		dateSelect.click()
		waitFor {calendarSelect.displayed==false}
		
		and:"Clicking Edit"
		sendBtn.click()
		waitFor {activeExcp.displayed==false}
		waitFor {excpTable.displayed==true}
		waitFor {excpTable.find('td.first').text().contains("-Edited")}
	
	} 
	
	def "Cancel Delete exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Delete exception"
		delExcp.click()
		
		then:"I click cancel on delete dialog"
		waitFor {$('div#dialog').displayed==true}
		cancelBtn.click()
		waitFor {excpTable.displayed==true}
		
	}
	
	def "Delete exception"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I click Delete exception"
		delExcp.click()
		
		then:"I click delete on delete dialog"
		waitFor {$('div#dialog').displayed==true}
		sendBtn.click()
		waitFor {excpTable.displayed==false}
		
	}
	
	def "Changing e-mail notifications"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I uncheck email notifications checkbox"
		$('input#notificationEmailCheck').click()
		waitFor {successDialog.hasClass('success-dialog')==true}
		waitFor {successDialog.hasClass('success-dialog')==false}
		
		then:"I select notification frequency"
		$('a#notificationEmailSelect-button').click()
		$("ul#notificationEmailSelect-menu li:not(.ui-selectmenu-item-selected)").click()
		waitFor {successDialog.hasClass('success-dialog')==true}
		waitFor {successDialog.hasClass('success-dialog')==false}
		
		
	}

		def "Changing sms notifications"(){
		given:"We are at the My availability page"
		at AvailabilityHomePage
		
		when:"I uncheck sms notifications checkbox"
		$('input#notificationSmsCheck').click()
		waitFor {successDialog.hasClass('success-dialog')==true}
		waitFor {successDialog.hasClass('success-dialog')==false}
		
		then:"I select notification frequency"
		$('a#notificationSmsSelect-button').click()
		$("ul#notificationSmsSelect-menu li:not(.ui-selectmenu-item-selected)").click()
		waitFor {successDialog.hasClass('success-dialog')==true}
		waitFor {successDialog.hasClass('success-dialog')==false}
	}
	
}



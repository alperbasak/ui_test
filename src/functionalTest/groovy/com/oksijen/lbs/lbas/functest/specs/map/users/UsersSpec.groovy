package com.oksijen.lbs.lbas.functest.specs.map.users

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@Stepwise
class UsersSpec extends LocateSpec {
	@RetryOnFailure(times=5)
	  def "Permission is required to locate other users"(){
		  given: "We are at the UsersPage"
		  at MapHomePage
		  usersTab.click()
		  waitFor {at UsersPage}
		  
		  when:"I open detail card"
		  groupOpenClose.click()
		  nolocatableUsers.find('.openClose').click()
		  
		  then:"Check if buttons are locked"
		  waitFor	{nolocatableUsers.find('.items_list_buttons li',0).hasClass('locked')==true}
		  waitFor	{nolocatableUsers.find('.items_list_buttons li',1).hasClass('locked')==true}
		  
	  }
	
	  @RetryOnFailure(times=5)
	  def "I locate the user after I use the search bar"() {
		given: "We are at the UsersPage"	
    	at UsersPage
		
		when:"I enter search text"
		searchInput<<params.get('users.Locate')
		waitFor {$('span.searchReset').displayed==true}
		
		then:"I click locate on a locatable user"
		locatableUserButton.click()
		
		and: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		
		and: "Tooltip should be closed when close button is clicked"
		expect tooltipClose.displayed, is(true)
		tooltipClose.click()
		expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
		resetInput.click()
    }
	@Ignore
	def "Make a locating request to another user"(){
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I enter search text"
		searchInput<<params.get('users.Request')
		waitFor {$('span.searchReset').displayed==true}
		
		then:"I click request on a user I want to send a request"
		requestUserButton.click()
		
		and:"Request permission dialog should open"
		waitFor {$('form#locationRequest').displayed==true}
		permissionInput<<params.get('users.RequestMessage')
		permissionSend.click()
		
		and:"Permission requested acknowledgement message displayed"
		waitFor {permissionDialog.displayed==true}
		$('button.ui-button').click()
			
		and:"Permission requested user should have pending status"
		searchInput<<Keys.chord(Keys.SPACE)
		waitFor {requestUserButton.hasClass('pendingRequestLeft')==true}
		resetInput.click()
		}
	@RetryOnFailure(times=5)
	def "Show only locatable users"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I select show only locatable from dropdown box"
		$('a#filter_all_tab-users-button').click()
		showLocatable.click()
		
		then:"Only locatable users will be shown"
		waitFor {nolocatableUsers.displayed==false}	
	}
	@RetryOnFailure(times=5)
	def "Sending a message to a user from detail card"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I click send message icon"
//		groupOpenClose.click()
		locatableUsers.find('img.openClose').click()
		waitFor {locatableUsers.find('ul.item_details').displayed==true}
		detailMessage.click()
		waitFor {$('form#sendMessage').displayed==true}
		
		then:"Click send button"
		$("td.buttons_class button.send").click()
		expect successSent.displayed, is(true)
		waitFor {successSent.displayed==false}
	}	
	@RetryOnFailure(times=5)
	def "Creating locating report of a user"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I click create report"
		detailReport.click()
//		waitFor {$('.locateSingleUser').find('.ui-dialog-buttonset button').displayed==true}
//		$('.locateSingleUser').find('.ui-dialog-buttonset button').click()
	
		then: "Report dialog opens and click create"
		waitFor {$('form#requestReportPermission').displayed==true}
		permissionSend.click()
		
		and: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)

		and:"Location report dialog should open and I click print"
		waitFor {locReportDiv.displayed==true}
		withNewWindow({printReport.click()},
			{
			waitFor {$('#printMainDiv').displayed==true}
//			$('input',value:"Print").click()
			})
		waitFor {at UsersPage}
		
		and:"I select the format I want the report to be generated"
		$('a#locationReportExportType-button').click()
		$('ul#locationReportExportType-menu li',4).click()
		downloadReport.click()
		
		when:"I click View Report to see latest location reports"
		viewReport.click()
		waitFor {$('div#locReportListDiv').displayed==true}
		
		then:"I open detailed report view"
		locReportUser.click()
		waitFor {locReportHist.displayed==true}
		locReportUser.click()
		
		and:"Close Report dialog"
		$('a.closeReport').click()
		waitFor {locReportDiv.displayed==false}
		$('#btn_map_clear').click()
		}
	@RetryOnFailure(times=5)
	def "Detailed user information dialog opens when I click User Details"(){
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I click create report"
		detailUser.click()
		
		then:"Detailed user information dialog opens"
		waitFor {$('.itemDetails').displayed==true}
		closeThick.click()
	}
	@RetryOnFailure(times=5)
	def "Locating user from detail card"() {
		given:"We are at the UsersPage"
		at UsersPage

		when:"I click Locate from detail card"
		detailLocate.click()

		then: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }

		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)

		and: "Tooltip should be closed when close button is clicked"
		expect tooltipClose.displayed, is(true)
		tooltipClose.click()
		expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
	}
	@RetryOnFailure(times=5)
	def "I share my location with a user"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I click Locate from detail card"
		detailShareLoc.click()

		then: "Tooltip should be shown on map"
		waitFor('fast') { $('.shareMyLocationBox').displayed == true }
		$(".ui-dialog-buttonset button").click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		}
	
		def "Show selected users location using locate button"(){
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I select show only locatable from dropdown box"
		$('a#filter_all_tab-users-button').click()
		showLocatable.click()
		waitFor {nolocatableUsers.displayed==false}
		
		then:"Select All locatable users"
		$('input#select_all_tab-users').click()
		$('a#btn_tab-users_showOnMap').click()
		if($('.dialogError').displayed==true){
			waitFor{$('.dialogError').find('.ui-dialog-buttonset button').displayed==true}
			$('.dialogError').find('.ui-dialog-buttonset button').click()
			waitFor{$('.dialogError').displayed==false}
			waitFor{$('.locateSingleUser').find('.ui-dialog-buttonset button').displayed==true}
			$('.locateSingleUser').find('.ui-dialog-buttonset button').click()
			waitFor{$('.locateSingleUser').displayed==false}
		}
		
		and: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		
		and: "Tooltip should be closed when close button is clicked"
		expect tooltipClose.displayed, is(true)
		tooltipClose.click()
		expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
		
		}
		@RetryOnFailure(times=5)
		def "Create location reports of selected users using create report button"(){
			given:"We are at the UsersPage"
			at UsersPage
			
			when:"I select show only locatable from dropdown box"
			$('a#filter_all_tab-users-button').click()
			showLocatable.click()
			waitFor {nolocatableUsers.displayed==false}
			
			then:"Select All locatable users"
			$('#btn_tab-users_createReport').click()
			
			and: "Report dialog opens and click create"
			waitFor {$('form#requestReportPermission').displayed==true}
			permissionSend.click()
			
			and: "Tooltip should be shown on map"
			waitFor('fast') { tooltip.displayed == true }
			
			and: "There must be links visible on tooltip"
			expect hasLink(tooltip, 'Show Nearest Users'), is(true)
			expect hasLink(tooltip, 'Save Place'), is(true)
			expect hasLink(tooltip, 'Get Directions'), is(true)
			expect hasLink(tooltip, 'Setup Meeting'), is(true)
			
			and:"Location report dialog should open and I click close"
			waitFor {locReportDiv.displayed==true}
			$('a.closeReport').click()
			waitFor {locReportDiv.displayed==false}
			expect tooltipClose.displayed, is(true)
			tooltipClose.click()
			expect tooltip.displayed, is(false)
			
	
			and:"Close Report dialog"
			$('#btn_map_clear').click()
			}
		@RetryOnFailure(times=5)
		def "Send a message to selected groups or users"(){
			given:"We are at the UsersPage"
			at UsersPage
			
			when:"I select show only locatable from dropdown box"
			$('a#filter_all_tab-users-button').click()
			showLocatable.click()
			waitFor {nolocatableUsers.displayed==false}
			
			then:"Select All locatable users"
			if($('input#select_all_tab-users',checked:"checked")){
			$('input#select_all_tab-users').click()
			}
			$('#btn_tab-users_sendMessage').click()
			
			and:"Send message dialog is opened"
			waitFor {$('.userSendMessage').displayed==true}
			$('button.cancel').click()
			waitFor {$('.userSendMessage').displayed==false}
		}
	}

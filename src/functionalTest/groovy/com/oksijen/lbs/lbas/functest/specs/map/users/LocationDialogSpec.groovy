package com.oksijen.lbs.lbas.functest.specs.map.users

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*

/**
 * 
 */
@Stepwise
class LocationDialogSpec extends LocateSpec {
	
	def "Showing nearby users of another user"(){
		given: "We are at the UsersPage"
		at MapHomePage
		usersTab.click()
		waitFor {at UsersPage}
		
		when:"I locate a user and select show nearest users"
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
		
		when: "I click Show nearest users"
		$('ul.toolTipRight li a',text:"Show Nearest Users").click()
		waitFor {$('.undefined').displayed==true}
		
		then: "I select largest radius"
		$('#select-radius-button').click()
		$('#select-radius-menu li').last().click()
		$('div.ui-dialog-buttonset').find('.show').click()
		
		and:"Nearby users will be shown"
		waitFor('fast') { tooltip.displayed == true }
		and: "Tooltip should be closed when close button is clicked"
		waitFor{$('.contents-list-grouped').displayed==true}
		$("span.toolTipRight a img").click()
		expect tooltip.displayed, is(false)
		$('#btn_map_clear').click()
		
	} 
	
	def "Save location to my places"(){
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I locate a user and select show nearest users"
		locatableUserButton.click()
		
		then: "Tooltip should be shown on map"
		waitFor('fast') { tooltip.displayed == true }
		
		and: "There must be links visible on tooltip"
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		
		when: "I click save place"
		$('a',text:"Save Place").click()
		waitFor {$('.openDetailsPlaces').displayed==true}
		$('#editPoiName') << "TestPOI"
		$('#addLocationButton').click()
		
		then:"Success Dialog is displayed"
		waitFor {$('.undefined').displayed==true}
		$('span',text:"OK").click()
		waitFor {at PlacesPage}
		
	}
	
	def "More info about location is displayed when more info is clicked"(){
		given:"We are at PlacesPage"
		at PlacesPage
		
		when:"I click More info"
		waitFor('fast') { tooltip.displayed == true }
		tooltip.find('span', text:"More info").click()
		
		then:"Extra info about place will be displayed"
		waitFor {$('li#extra').displayed==true}
		}
		
	def "I can share the location with another user"(){
		given:"We are at PlacesPage"
		at PlacesPage
		
		when:"I click share"
		waitFor('fast') { tooltip.displayed == true }
		tooltip.find('span', text:"Share").click()
		
		then:"Share dialog is opened"
		waitFor {$('.shareWithMailPhone').displayed==true}
		$('#emailOrSms') << params.get('username')
		$('span',key:"buttons.add").click()
		$('span',key:"buttons.share").click()
		waitFor {$('.shareWithMailPhone').displayed==false}
		}

	def "I can edit saved location details"(){
		given:"We are at PlacesPage"
		at PlacesPage
		$('#btn_map_clear').click()
		$('#tab-places-personal').find('#g-1').click()
		$('#btn_tab-places_showOnMap').click()
		
		when:"I click edit"
		waitFor('fast') { tooltip.displayed == true }
		tooltip.find('span',text:"Edit").click()
		
		then:"Edit dialog is opened"
		waitFor {$('.openDetailsPlaces').displayed==true}
		$('#editPoiName') << "-Edited"
		$('#updateLocationButton').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		and:"Delete saved location"
		$('#tab-places-personal').find('span.openClose').click()
		waitFor{$('#tab-places-personal').find('ul.places').displayed==true}
		$('#tab-places-personal').find('ul.places').find('input.place').click()
		$('#btn_tab-places_Delete').click()
		waitFor {$('.undefined').displayed==true}
		$('span',text:"OK").click()
		waitFor {$('.undefined').displayed==true}
		$('span',text:"OK").click()
		waitFor {$('.undefined').displayed==false}
		$('#btn_map_clear').click()
		
	}

	def "Get directions to the location of the user"() {
		given: "We are at the UsersPage"
		at PlacesPage
		$('#btn_tab-users').click()
		waitFor {at UsersPage}
		
		when:"I click locate"
		locatableUserButton.click()
		
		then:"I click get directions"
		waitFor('fast') { tooltip.displayed == true }
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		$('a',text:"Get Directions").click()
		
		and:"I am forwarded to RoutesPage"
		waitFor {at RoutesPage}
		$('#routePoint0') << params.get('placesSearch.targetInput')
		waitFor('fast') { autocompleteList.displayed == true }
		waitFor {autocompleteListItems.size()>0}
		autocompleteListItems.click()
		waitFor('fast') { autocompleteList.displayed == false }
		
		and:"Route will be displayed on map"
		waitFor {$('#idRouteResult').displayed==true}
		waitFor('fast') { tooltip.displayed == true }
		$('#btn_map_clear').click()
	}

	def "A meeting can be created at the location"() {
		given: "We are at the UsersPage"
		at RoutesPage
		$('#btn_tab-users').click()
		waitFor {at UsersPage}
		
		when:"I click locate"
		locatableUserButton.click()
		
		then:"I click get directions"
		waitFor('fast') { tooltip.displayed == true }
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		$('a',text:"Setup Meeting").click()
		
		and:"Event dialog is displayed"
		$('#meetingSubject')<<"TestEvent"
		$('#meetingCheckboxAllDay').click()
		$('#token-input-messageTo')<<params.get('newMessage.toInput')
		waitFor { addressList.displayed == true }
		expect addressListItems.size(), greaterThan(0)
		
		and:"Select first and enter other parameters"
		addressListItems.click()
		$('#inboxLocationRequestAcceptButton').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		$('#btn_map_clear').click()
	}
	
	def "New message dialog opens when send message is clicked"() {
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I click locate"
		locatableUserButton.click()
	
		then:"I click send message"
		waitFor('fast') { tooltip.displayed == true }
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		$('ul.toolTipBottom li',0).click()

		and:"New Message dialog opens"
		waitFor {$('.userSendMessage').displayed==true}
		
		and:"Click send button"
		$("button.send").click()
		waitFor {successSent.displayed==true}
		waitFor {successSent.displayed==false}
		$('#btn_map_clear').click()
		}
		
	def "Location history report will be displayed when view report is clicked"() {
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I click locate"
		locatableUserButton.click()
	
		then:"I click view report"
		waitFor('fast') { tooltip.displayed == true }
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		$('ul.toolTipBottom li',1).click()

		and:"Create report dialog opens"
		waitFor {$('.locationReportRequestPermission').displayed==true}
		$('div.ui-dialog-buttonset button',1).click()
		waitFor {$('#locReportTableDiv').displayed==true}
		
		and:"Click close button"
		$("a.closeReport").click()
		waitFor {$('#locReportTableDiv').displayed==false}
		$('#btn_map_clear').click()
		}
	
	def "User information is displayed when view details is clicked"() {
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I click locate"
		locatableUserButton.click()
	
		then:"I click view details"
		waitFor('fast') { tooltip.displayed == true }
		expect hasLink(tooltip, 'Show Nearest Users'), is(true)
		expect hasLink(tooltip, 'Save Place'), is(true)
		expect hasLink(tooltip, 'Get Directions'), is(true)
		expect hasLink(tooltip, 'Setup Meeting'), is(true)
		$('ul.toolTipBottom li',2).click()

		and:"User info dialog opens"
		waitFor {$('.dialogInfo').displayed==true}
			
		and:"Click close button"
		$("span.ui-icon",text:"Close").click()
		waitFor {$('.dialogInfo').displayed==false}
		$('#btn_map_clear').click()
		}
	
	
}
	
	

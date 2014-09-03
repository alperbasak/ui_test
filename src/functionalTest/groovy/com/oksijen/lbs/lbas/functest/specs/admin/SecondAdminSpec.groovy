package com.oksijen.lbs.lbas.functest.specs.admin

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.util.TestParams
import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.admin.*
import com.oksijen.lbs.lbas.functest.pages.map.*
import com.oksijen.lbs.lbas.functest.pages.calendar.*


import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@Stepwise
class SecondAdminSpec extends LocateSpec {
    @Ignore
def "Create a group, select multi admin"(){							
	given:"We are at Group Management Page"		
	adminBtn.click()
	at AdminHomePage
	groupMan.click()
	at AdminHomePage
	
	when:"I select Add Group from dropdown and click apply"
	selectMenu.click()
	addGroup.click()
	apply.click()
	
	then:"Add members"
	waitFor {groupDialog.displayed==true}
	groupName<<"SecondAdminGroup"
	groupMembersTab.click()
	waitFor{members.displayed==true}
	waitFor{allMembers.size()>0}
	$('select#selected option',text:'GroupAdmin1 Test').click()
	$('select#selected option',text:'GroupAdmin2 Test').click()
	$('select#selected option',text:'GroupAdmin3 Test').click()
	memberAdd.click()
	
	and:"New group dialog opens and I select only one admin"
	groupPermissionsTab.click()
	waitFor {permissions.displayed==true}
	
	def checkboxes= permissions.find('input',type:"checkbox")
	checkboxes[0].click()
	checkboxes[1].click()
	checkboxes[2].click()
	checkboxes[3].click()
	checkboxes[4].click()
	checkboxes[5].click()
	checkboxes[6].click()
	checkboxes[7].click()
	checkboxes[8].click()
	
	and: "Add more than one admin and save"
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	$('select#allAdminUsers option',text:'GroupAdmin1 Test').click()
	$('select#allAdminUsers option',text:'GroupAdmin2 Test').click()
	$('select#allAdminUsers option',text:'GroupAdmin3 Test').click()
	arrowAdd.click()
	
	waitFor('fast') {groupAdminUsers.size()>1}
	sendDialog.click()
	waitFor {$('.confirmation').displayed==true}
	$('span.ui-button-text',text:'OK').click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}
@RetryOnFailure
def "Logout and login with 2nd admin"(){
	given:"We are at Group Management Page"	
//	at AdminHomePage
	at WelcomePage
	$('a#btn_logout').click()
	waitFor {at LoginPage}
		
	when:"I login with 2nd admin"
	username='admin1'
	password=params.get('password')
	loginButton.click()
	waitFor {at WelcomePage}
	
	then:"Only the group with admin rights should be seen"
	waitFor {$('#tab-users').find('div.contents ul li').size()==1}

	}
@RetryOnFailure
def "Locate users with other admin"(){
	given:"We are at Users Page"
	at UsersPage
	
	when:"I select the group and click locate"
	$('input.groupId').click()
	$('a#btn_tab-users_showOnMap').click()
	
	then:"I see the location of the users on the map"
	waitFor{tooltip.displayed==true}
	tooltipClose.click()
	waitFor{tooltip.displayed==false}
	
	}
@RetryOnFailure
def "Create location reports with other admin"(){
	given:"We are at Users Page"
	at UsersPage
	
	when:"I select a group and click create report"
	if ($('#btn_tab-users_createReport').hasClass('multi_user_button_inactive')){
		$('input.groupId').click()
		}
	waitFor{$('#btn_tab-users_createReport').hasClass('multi_user_button')==true}
	$('#btn_tab-users_createReport').click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	permissionSend.click()
	
	then:"Tooltip opens and view report"
	waitFor{$('#reportTopCover').displayed==true}
	viewReport.click()
	waitFor{$('#locReportUserList div').size()>1}
	$('#locReportUserList div').last().click()
	waitFor{$('#locReportUserList ul').last().displayed==true}
	$('#btn_map_clear').click()
	waitFor{tooltip.displayed==false}
	}
@RetryOnFailure
def "Send a message to all group members"(){
	given:"We are at Users Page"
	at UsersPage
	
	when:"I select a group and click create report"
	if ($('#btn_tab-users_sendMessage').hasClass('multi_user_button_inactive')){
		$('input.groupId').click()
		}
	waitFor{$('#btn_tab-users_sendMessage').hasClass('multi_user_button')==true}
	$('#btn_tab-users_sendMessage').click()
	waitFor{$('.userSendMessage').displayed==true}
	
	then:"Send message to group"
	$('td.buttons_class button')[0].click()
	waitFor {successSent.displayed==true}
	waitFor {successSent.displayed==false}
	}
@RetryOnFailure
def "Other admin can create new user"(){
	given:"We are at Users Page"
	at UsersPage
	
	when:"I open actions and click create new user"
	$('#userActionList5-button').click()
	waitFor{$('#userActionList5-menu').displayed==true}
	$('#userActionList5-menu li.userActionList2').click()
	
	then:"Enter user details and create new user"
	waitFor{$('.addExcpPos').displayed==true}
	userName << params.get('newUser.name')
	userSurname << params.get('newUser.surname')
	userMail << params.get('newUser.email')
	$('a.send-button').click()
	
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}
@RetryOnFailure
def "Other Admin can edit User"(){
	given:
	at UsersPage
	
	when:"I select a user and edit"
	searchInput<<params.get('newUser.name')
	waitFor {$('span.searchReset').displayed==true}
	if ($('#userActionList2-button').displayed==false){
		$('input.user.check-box').click()
		}
	waitFor{$('#userActionList2-button').displayed==true}
	actionListClose2.click()
	editUser.click()
	
	then:"Editing dialog opens"
	waitFor {$('.addExcpPos').displayed==true}
	userName << params.get('editUser.name')
	$('a.send-button').click()
	
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	}
@RetryOnFailure
def "Create a new enterprise category"(){
	given: "We are at the PlacesPage"
	at UsersPage
	$('a#btn_tab-places').click()
	at PlacesPage
	$('#btn_tab-places-enterprise').click()
	waitFor {$('#tab-places-enterprise').hasClass('ui-tabs-hide')==false}
	
	when:"I click new category"
	$('#btn_tab-places_newCategory').click()
	waitFor {$('#tabs_in_enterpriseCategoryDialog').displayed==true}
	
	then:"I enter category details"
	$('#categoryDetailForm_categoryName') << params.get('newCategoryName')
	$('a#tab2').click()
	waitFor {$('#updateCatPermission').displayed==true}
	$('span.groupName',text:'SecondAdminGroup').parent().parent().find('input.groupIdCat')click()
	
	and:"Add permissions"
	$('#changePermissions0').click()
	$('#changePermissions1').click()
	$('#changePermissions2').click()
	$('#changePermissions3').click()
	$('#changePermissions4').click()
	$('#changePermissions5').click()
	$('#changePermissions6').click()
	$('span.ui-button-text',text:'Save').click()
	
	and:"Success dialog is shown"
	waitFor {$('.dialog.undefined').displayed==true}
	$('.undefined').find('.ui-dialog-buttonset button').click()
	if ($('#tabs_in_enterpriseCategoryDialog').displayed==true){
		$('span.ui-button-text',text:'Cancel').click()
	}
	}
@RetryOnFailure
def "Edit enterprise category"(){
	given: "We are at the PlacesPage"
	at PlacesPage
		
	when:"I select a category to edit"
	waitFor{$('.placeCategory').displayed==true}
	$('#tab-places-enterprise').find('input.placeId').click()
	$('#editCategoryGroupAction').click()
	waitFor {$('.newCategoryPopUp').displayed==true}
	$('#categoryDetailForm_categoryName') << params.get('editUser.name')
	
	then:"I add a new user with admin rights"
	$('a#tab3').click()
	waitFor {$('#CategoryAdmin').displayed==true}
	$('lm').last().click()
	waitFor {$('#changeCategoryAdmin').displayed==true}
	$('a.head').click()
	waitFor{$('ul.ui-accordion-content').displayed==true}
	$('ul.ui-accordion-content li a').click()
	$('#changeCategoryAdmin').find('lm',title:'Save').click()
	waitFor{$('#navigationCatAdmin').displayed==false}
	$('.newCategoryPopUp').find('span',text:'Update').click()
	
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	}
@RetryOnFailure
def "Show enterprise category on map"(){
	given: "We are at the PlacesPage"
	at PlacesPage
	
	when:"I add multiple places to an enterprise category"
	searchInput << 'Ankara'
	waitFor('fast') { autocompleteList.displayed == true }
	
	then:
	expect autocompleteListItems.size(), greaterThan(0)
	clickFirstItem(autocompleteListItems)
	waitFor('fast') { tooltip.displayed == true }
	expect hasLink(tooltip, 'Save Place'), is(true)
	$('a',text:'Save Place').click()
	waitFor {$('#edit_loc_dialog').displayed==true}
	$('#editPoiName')<<'Place1'
	$('#edit_loc_tab2_link').click()
	waitFor {$('#enterpriseOpen').displayed==true}
	$('#selectEntCategoriesCheckContainer ul li input').click()
	$('#addLocationButton').click()
	waitFor {$('.dialog.undefined').displayed==true}
	$('.undefined').find('.ui-dialog-buttonset button').click()
	
	when:
	searchInput << 'Istanbul'
	waitFor('fast') { autocompleteList2.displayed == true }
	
	then:
	expect autocompleteListItems2.size(), greaterThan(0)
	clickFirstItem(autocompleteListItems2)
	waitFor('fast') { tooltip.displayed == true }
	expect hasLink(tooltip, 'Save Place'), is(true)
	$('a',text:'Save Place').click()
	waitFor {$('#edit_loc_dialog').displayed==true}
	$('#editPoiName')<<'Place2'
	$('#edit_loc_tab2_link').click()
	waitFor {$('#enterpriseOpen').displayed==true}
	$('#selectEntCategoriesCheckContainer ul li input').click()
	$('#addLocationButton').click()
	waitFor {$('.dialog.undefined').displayed==true}
	$('.undefined').find('.ui-dialog-buttonset button').click()
	
	and:"I show the places on map"
	$('#tab-places-enterprise').find('input.placeId').click()
	waitFor {$('.placeCategory ul li').size()>1}
	$('#btn_tab-places_showOnMap').click()
	waitFor{$('canvas').size()>1}
	waitFor('fast') { tooltip.displayed == true }
		
	}
@RetryOnFailure
def "Setup a meeting at a place"(){
	given: "We are at the PlacesPage"
	at PlacesPage
	
	when:"I click Setup Meeting"
	waitFor('fast') { tooltip.displayed == true }
	waitFor{hasLink(tooltip, 'Save Place')==true}
	$('a',text:'Setup Meeting').click()
	
	then:"Meeting dialog opens"
	waitFor{$('.dialog.noClose').displayed==true}
	$('#meetingSubject')<<'TestMeeting'
	$('#meetingCheckboxAllDay').click()
	$('#inboxLocationRequestAcceptButton').click()
	
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Delete meeting"
	calendar.click()
	waitFor {at CalendarHomePage}
	$('span.fc-event-title',text:'TestMeeting').click()
	waitFor{$('.meeting-detail').displayed==true}
	$('.ui-dialog-buttonset button')[2].click()
	waitFor{$('.delete-text').displayed==true}
	$('.buttons_class').find('button.send').click()
	
	and:"Success dialog is shown"
	waitFor {$('.noCloseNoOk').displayed==true}
	waitFor {$('.noCloseNoOk').displayed==false}
		
	}
@RetryOnFailure
def "Delete enterprise category"(){
	given: "We are at the PlacesPage"
	at CalendarHomePage
	$('a#btn_map').click()
	waitFor {at MapHomePage}
	$('a#btn_tab-places').click()
	at PlacesPage
	$('#btn_tab-places-enterprise').click()
	waitFor {$('#tab-places-enterprise').hasClass('ui-tabs-hide')==false}
	
	when:"I select enterprise category and delete"
	if (enterprisePlace.value()==false){ 
	enterprisePlace.click()
	}
	$('a#btn_tab-places_Delete').click()
	waitFor{$('.dialog.undefined').displayed==true}
	
	then:"I accept and delete the place"
	$('div.ui-dialog-buttonset button').click()
	waitFor{$('#ui-dialog-title-dialog', text:'Delete').displayed==false}
	waitFor{$('#ui-dialog-title-dialog', text:'Success').displayed==true}
	waitFor{$('div.ui-dialog-buttonset button span',text:'OK').displayed==true}
//	$('div.ui-dialog-buttonset button span',text:'OK').click()
}
@RetryOnFailure
def "Delete User"(){
	given:"We are at the UsersPage"
	at PlacesPage	
	$('a#btn_logout').click()
	waitFor {at LoginPage}
		
	when:"I login with company admin"
	username=params.get('username')
	password=params.get('password')
	loginButton.click()
	waitFor {at UsersPage}

	then: "I select the edited user and delete"
	searchInput<<params.get('newUser.name')
	searchInput<<params.get('editUser.name')
	waitFor {$('span.searchReset').displayed==true}
	if ($('#userActionList2-button').displayed==false){
		$('input.user.check-box').click()
		}
	waitFor{$('#userActionList2-button').displayed==true}
	actionListClose2.click()
	deleteUser.click()
	
	and:"Delete confimation dialog opens"
	waitFor {$('#dialog').displayed==true}
	$('div.ui-dialog-buttonset button',0).click()
	
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

		}
	
}

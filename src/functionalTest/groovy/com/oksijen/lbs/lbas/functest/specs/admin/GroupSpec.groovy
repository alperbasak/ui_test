package com.oksijen.lbs.lbas.functest.specs.admin

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

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
class GroupSpec extends LocateSpec {
	@RetryOnFailure(times=5)
def "Group Management Page is displayed"(){
	given: "We are at the WelcomePage"
	at WelcomePage
		
	when: "I click Admin tab"
	adminBtn.click()
	waitFor { at AdminHomePage }
		
	then: "Click Group Management and page should render"
	groupMan.click()
	waitFor { rightPanel.find('h1').text().contains('Gr') }
	if($('td.name',text:'NewGroup1').displayed==true){
	$('td.name',text:'NewGroup1').parent().find('input').click()
	if($('td.name',text:'NewGroup2').displayed==true){
	$('td.name',text:'NewGroup2').parent().find('input').click()
	if($('td.name',text:'NewAssetGroup1').displayed==true){
	$('td.name',text:'NewAssetGroup1').parent().find('input').click()
	if($('td.name',text:'NewAssetGroup2').displayed==true){
	$('td.name',text:'NewAssetGroup2').parent().find('input').click()}}}
	selectMenu[1].click()
	delete.click()
	apply.click()
	
	waitFor{$('.confirmation').displayed==true}
	$('#dialog').find('span',text:'OK').click()
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	}
	
	}
@RetryOnFailure(times=5)
def "Create a group, select one admin"(){
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I select Add Group from dropdown and click apply"
	selectMenu.click()
	addGroup.click()
	apply.click()
	
	then:"New group dialog opens and I select only one admin"
	waitFor {groupDialog.displayed==true}
	groupName<<"NewGroup1"
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
	
	and: "Check for only one admin and save"
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==1
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}
@RetryOnFailure(times=5)
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
	
	then:"New group dialog opens and I select only one admin"
	waitFor {groupDialog.displayed==true}
	groupName<<"NewGroup2"
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
	assert allAdmins.size()>4
	allAdmins[0].click()
	allAdmins[1].click()
	allAdmins[2].click()
	allAdmins[3].click()
	arrowAdd.click()
	
	waitFor('fast') {groupAdminUsers.size()==5}
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}

@RetryOnFailure(times=5)
def "Edit group, remove one admin from group admins"(){									
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I edit multi admin group"
	$('td.name',text:'NewGroup2').parent().find('a.btn-update').click()
	
	then:"Dialog opens and I remove one admin"
	waitFor {groupDialog.displayed==true}
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==5
	groupAdminUsers[0].click()
	arrowRemove.click()
	waitFor('fast')	{groupAdminUsers.size()==4}
	
	and:"Save"
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}

@RetryOnFailure(times=5)
def "Edit group, add one admin to group admins"(){									
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I edit multi admin group"
	$('td.name',text:'NewGroup2').parent().find('a.btn-update').click()
	
	then:"Dialog opens and I add one admin"
	waitFor {groupDialog.displayed==true}
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==4
	allAdmins[0].click()
	arrowAdd.click()
	waitFor('fast')	{groupAdminUsers.size()==5}
	
	and:"Save"
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}
@RetryOnFailure(times=5)
def "Create an asset group, select one admin"(){
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I select Add Asset Group from dropdown and click apply"
	selectMenu.click()
	addAssetGroup.click()
	apply.click()
	
	then:"New group dialog opens and I select only one admin"
	waitFor {groupDialog.displayed==true}
	groupName<<"NewAssetGroup1"

	and: "Check for only one admin and save"									
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==1
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}
@RetryOnFailure(times=5)
def "Create an asset group, select multi admin"(){							
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I select Add Asset Group from dropdown and click apply"
	selectMenu.click()
	addAssetGroup.click()
	apply.click()
	
	then:"New asset group dialog opens and I select only one admin"
	waitFor {groupDialog.displayed==true}
	groupName<<"NewAssetGroup2"
	
	and: "Add more than one admin and save"
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert allAdmins.size()>4
	allAdmins[0].click()
	allAdmins[1].click()
	allAdmins[2].click()
	allAdmins[3].click()
	arrowAdd.click()
	
	waitFor('fast') {groupAdminUsers.size()==5}
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}

	}

@RetryOnFailure(times=5)
def "Edit asset group, remove one admin from group admins"(){									
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I edit multi admin asset group"
	$('td.name',text:'NewAssetGroup2').parent().find('a.btn-update').click()
	
	then:"Dialog opens and I remove one admin"
	waitFor {groupDialog.displayed==true}
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==5
	groupAdminUsers[0].click()
	arrowRemove.click()
	waitFor('fast')	{groupAdminUsers.size()==4}
	
	and:"Save"
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}

@RetryOnFailure(times=5)
def "Edit asset group, add one admin to group admins"(){									
	given:"We are at Group Management Page"
	at AdminHomePage
	
	when:"I edit multi admin asset group"
	$('td.name',text:'NewAssetGroup2').parent().find('a.btn-update').click()
	
	then:"Dialog opens and I add one admin"
	waitFor {groupDialog.displayed==true}
	groupAdminTab.click()
	waitFor {admin.displayed==true}
	assert groupAdminUsers.size()==4
	allAdmins[0].click()
	arrowAdd.click()
	waitFor('fast')	{groupAdminUsers.size()==5}
	
	and:"Save"
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
		
	}
@RetryOnFailure(times=5)
def "Create a new enterprise category"(){
	given: "We are at the PlacesPage"
	$('#btn_map').click()
	$('a#btn_tab-places').click()
	at PlacesPage
	$('#btn_tab-places-enterprise').click()
	waitFor {$('#tab-places-enterprise').hasClass('ui-tabs-hide')==false}
	
	when:"I click new category"
	if($('span.groupName',text:'aa').displayed==true){
		enterprisePlace.click()
		$('a#btn_tab-places_Delete').click()
		waitFor{$('.dialog.undefined').displayed==true}
		$('div.ui-dialog-buttonset button').click()
		waitFor{$('#ui-dialog-title-dialog', text:'Delete').displayed==false}
		waitFor{$('#ui-dialog-title-dialog', text:'Success').displayed==true}
		waitFor{$('div.ui-dialog-buttonset button span',text:'OK').displayed==true}
		$('#btn_tab-places-recents').click()
		$('#btn_tab-places-enterprise').click()
	}
	$('#btn_tab-places_newCategory').click()
	waitFor {$('#tabs_in_enterpriseCategoryDialog').displayed==true}
	
	then:"I enter category details"
	$('#categoryDetailForm_categoryName') << params.get('newCategoryName')
	$('a#tab2').click()
	waitFor {$('#updateCatPermission').displayed==true}
	$('#permissionSearchInput') << params.get('users.Request')
	waitFor {$('div#wp_autocomplete').displayed==true}
	$('div#wp_autocomplete').find('li.ui-menu-item a').click()

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
@RetryOnFailure(times=5)
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
@RetryOnFailure(times=5)
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
@RetryOnFailure(times=5)
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
@RetryOnFailure(times=5)
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
@RetryOnFailure(times=5)
def "Delete Groups"(){
	given:"We are at Group Management Page"		
	adminBtn.click()
	at AdminHomePage
	groupMan.click()
	at AdminHomePage
	
	when:"I select all added groups"
	$('td.name',text:'NewGroup1').parent().find('input').click()
	$('td.name',text:'NewGroup2').parent().find('input').click()
	$('td.name',text:'NewAssetGroup1').parent().find('input').click()
	$('td.name',text:'NewAssetGroup2').parent().find('input').click()
	
	then:"I select Delete from dropdown menu"
	selectMenu[1].click()
	delete.click()
	apply.click()
	
	waitFor{$('.confirmation').displayed==true}
	$('#dialog').find('span',text:'OK').click()
	and:"Success dialog is shown"
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	}
}
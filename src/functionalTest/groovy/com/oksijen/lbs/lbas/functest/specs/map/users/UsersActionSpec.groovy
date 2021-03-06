package com.oksijen.lbs.lbas.functest.specs.map.users

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import com.oksijen.lbs.lbas.functest.pages.availability.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@Stepwise
class UsersActionSpec extends LocateSpec {
	@RetryOnFailure(times=5)
	def "Creating a new user, empty input returns error"() {
		given:"We are at the UsersPage"
		at MapHomePage
		  usersTab.click()
		  waitFor {at UsersPage}
		
		when: "I click create a new user from actions menu"
		actionListClose.click()
		createNewUser.click()
		
		then:"New user dialog opens"
		waitFor {$('.addExcpPos').displayed==true}
		$('a.send-button').click()
		
		and:"Error is displayed"
		waitFor {$('div#error-view-user').displayed==true}
		$('a.cancel-button').click()
		actionListClose.click()
		$('#userActionList-menu li a').click()
	}
	@RetryOnFailure(times=5)
	def "Creating a new user, successful create"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when: "I click create a new user from actions menu"
		actionListClose.click()
		createNewUser.click()	
	
		then:"New user dialog opens"
		waitFor {$('.addExcpPos').displayed==true}
		userName << params.get('newUser.name')
		userSurname << params.get('newUser.surname')
		userMail << params.get('newUser.email')
		$('a.send-button').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
			
	}			
	
	@RetryOnFailure(times=5)
	def "Edit User"(){
	at UsersPage
	
	when:"I select a user and edit"
	searchInput<<params.get('newUser.name')
	waitFor {$('span.searchReset').displayed==true}
	$('input.user.check-box').click()
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
	@RetryOnFailure(times=5)
	def "Move a user to another group"(){
		given:"We are at the UsersPage"
		at UsersPage
	
		when: "I select a user and move to another group"
		searchInput<< " "
		waitFor {$('ul.users').displayed==true}	
		if ($("#tab-users_count").text()=="0 selected"){
			$('input.user.check-box').click()
		}
		waitFor {actionListClose2.displayed==true}
		actionListClose2.click()
		moveToNewGroup.click()
	
		then:"New dialog opens and I select group"
		waitFor {$('.addExcpPos').displayed==true}
		$('#dialog').find('span.ui-selectmenu-status').click()
		$('ul.usersMoveToGroupDropdown li a',text:'ToBeDeleted').click()
		$('a.apply-button').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		}
	@RetryOnFailure(times=5)
	def "Delete multiple Users"(){
		given:"We are at the UsersPage"
		at UsersPage
		
		when: "I select a group of users and delete"
		$('span.groupName',text:'ToBeDeleted').parent().parent().find('input').click()
		actionListClose2.click()
		deleteUser.click()
		
		then:"Delete confimation dialog opens"
		waitFor {$('#dialog').displayed==true}
		$('div.ui-dialog-buttonset button',0).click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
	
			}
	@Ignore
	def "Create a new group"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when: "I click create a new group from actions menu"
		actionListClose2.click()
		createNewGroup.click()
		
		then:"New group dialog opens"
		waitFor {$('.addExcpPos').displayed==true}
		groupName << params.get('newGroup.name')
		groupDesc << params.get('newGroup.desc')
		
		and:"Select group members"
		groupMembers.click()
		$('input#groupMemberUserSearch') << params.get('newUser.name')
		$('select#selected option').click()
		$('a.action-arrow.add').click()
		waitFor {$('select#available option').size()>0}
		
		and:"Enter group permissions"
		groupPermissions.click()
		$('input', name:"lbasGroup.create_edit_users").click()
		$('input', name:"lbasGroup.create_edit_groups").click()
		
		and:"Edit group admins"
		groupAdmin.click()
		waitFor {$('#groupAdminUsers').displayed==true}
		$('a.send-button').click()
		if($('.confirmation').displayed==true){
			$('.confirmation').find('.ui-dialog-buttonset button',1).click()
			waitFor {$('.confirmation').displayed==false}
		}
		if($('.dialogError').displayed==true){
			$('.dialogError').find('.ui-dialog-buttonset button').click()
			waitFor {$('.dialogError').displayed==false}
			$('a.cancel-button').click()
		}
		waitFor{at UsersPage}
	}
	@Ignore
	def "Edit group"(){
		given:"We are at the UsersPage"
		at UsersPage
		
		when: "I select a group and edit"
		$('input.groupId.check-box').click()
		actionListClose2.click()
		editGroup.click()
		
		then:"Editing dialog opens"
		waitFor {$('.addExcpPos').displayed==true}
		groupName << params.get('editUser.name')
		
		and:"Select group members"
		groupMembers.click()

		$('select#selected option').click()
		$('a.action-arrow.add').click()
		$('a.action-arrow.remove').click()
		waitFor {$('select#selected option').size()>0}
		
		and:"Enter group permissions"
		groupPermissions.click()

		and:"Edit group admins"
		groupAdmin.click()
		waitFor {$('#groupAdminUsers').displayed==true}
		$('a.send-button').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
	
			}
	@RetryOnFailure(times=5)
	def "Requesting permission to locate user"(){
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I select a user and make a request"
		searchInput="cagdas"
		waitFor {$('ul.users').displayed==true}
		if (nolocatableUsers.find('a.globalSearchButton').hasClass('pendingRequestLeft')){
			decline()
		}
		nolocatableUsers.find('input.user').click()
		actionListClose2.click()
		requestPermission.click()
		
		then:"Request permission dialog should open"
		waitFor {$('form#locationRequest').displayed==true}
		permissionInput<<params.get('users.RequestMessage')
		permissionSend.click()
		
		and:"Permission requested acknowledgement message displayed"
		waitFor {permissionDialog.displayed==true}
		$('button.ui-button').click()
		decline()
		
		}
	
	@RetryOnFailure(times=5)
	def "Share my location with a user"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I select a user and share my location"
		searchInput="cagdas"
		waitFor {$('ul.users').displayed==true}
		if (nolocatableUsers.find('a.globalSearchButton').hasClass('pendingRequestLeft')){
			decline()
		}
		nolocatableUsers.find('input.user').click()
		actionListClose2.click()
		shareMyLocation.click()

		then: "Tooltip should be shown"
		waitFor('fast') { $('.shareMyLocationBox').displayed == true }
		$(".ui-dialog-buttonset button").click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		privacyMenu.click()
		waitFor { at AvailabilityHomePage }
		locateMe.click()
		waitFor { at LocateMePage }
		cagPermission.jquery.mouseover()
		waitFor {cagPermission.find('a.delete').displayed==true}
		cagPermission.find('a.delete').click()
		waitFor {$('#dialog').displayed==true}
		sendBtn.click()
		waitFor {$('#dialog').displayed==false}
		
		}

}
		

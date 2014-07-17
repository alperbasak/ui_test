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
class UsersActionSpec extends LocateSpec {
	
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
		$('li.generalActions a').click()
	}
	
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
	

	def "Edit User"(){
	at UsersPage
	
	when:"I select a user and edit"
	groupOpenClose.click()
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
	
	def "Move a user to another group"(){
		given:"We are at the UsersPage"
		at UsersPage
	
		when: "I select a user and move to another group"
		groupOpenClose.click()
		$('input.user.check-box').click()
		actionListClose2.click()
		moveToNewGroup.click()
	
		then:"New dialog opens and I select group"
		waitFor {$('.addExcpPos').displayed==true}
		$('a.usersMoveToGroupDropdown').click()
		$('ul.usersMoveToGroupDropdown li').last().click()
		$('a.apply-button').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		}
	
	def "Delete multiple Users"(){
		given:"We are at the UsersPage"
		at UsersPage
		
		when: "I select a group of users and delete"
		$('input.groupId.check-box').click()
		actionListClose2.click()
		deleteUser.click()
		
		then:"Delete confimation dialog opens"
		waitFor {$('#dialog').displayed==true}
		$('div.ui-dialog-buttonset button',0).click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
	
			}
	
	def "Create a new group"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when: "I click create a new group from actions menu"
		actionListClose.click()
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
		$('a#groupAdminChangeLink').click()
		waitFor {$('#groupAdminChangeCombo').displayed==true}
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
		$('a.send-button').click()
		
		and:"Success dialog is shown"
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
	
			}
		
	def "Requesting permission to locate user"(){
		given: "We are at the UsersPage"
		at UsersPage
		
		when:"I select a user and make a request"
		groupOpenClose.click()
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

		}
	
	def "Share my location with a user"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I select a user and share my location"
		groupOpenClose.click()
		$('input.user.check-box').click()
		actionListClose2.click()
		shareMyLocation.click()

		then: "Tooltip should be shown"
		waitFor('fast') { $('.shareMyLocationBox').displayed == true }
		$(".ui-dialog-buttonset button").click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		}
	
	
}
		

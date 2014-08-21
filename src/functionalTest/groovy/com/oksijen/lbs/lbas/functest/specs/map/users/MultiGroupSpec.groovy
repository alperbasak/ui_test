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
class MultiGroupSpec extends LocateSpec {
	  
	def "Show only locatable users"() {
		given:"We are at the UsersPage"
		at UsersPage
		
		when:"I select show only locatable from dropdown box"
		$('a#filter_all_tab-users-button').click()
		showLocatable.click()
		
		then:"Only locatable users will be shown"
		waitFor {nolocatableUsers.displayed==false}	
	}
	
	def "Select two groups and locate"(){
		given:"at Users page"
		at UsersPage
		
		when:"I select first two locatable groups"
		locatableGroup[4].find('input.groupId').click()
		locatableGroup[3].find('input.groupId').click()
			
		then:"Show on map"
		$('#btn_tab-users_showOnMap').click()
		if($('.dialogError').displayed==true){
			waitFor{$('.dialogError').find('.ui-dialog-buttonset button').displayed==true}
			$('.dialogError').find('.ui-dialog-buttonset button').click()
			waitFor{$('.dialogError').displayed==false}
			waitFor{$('.locateSingleUser').find('.ui-dialog-buttonset button').displayed==true}
			$('.locateSingleUser').find('.ui-dialog-buttonset button').click()
			waitFor{$('.locateSingleUser').displayed==false}
		}
		waitFor{tooltip.displayed == true }
		$('#btn_map_clear').click()
		waitFor{tooltip.displayed == false }
		
			}
		
	def "Select three groups and locate"(){
		given:"at Users page"
		at UsersPage
		waitFor {nolocatableUsers.displayed==false}
		
		when:"I select first three locatable groups"
		locatableGroup[2].find('input.groupId').click()
		if(locatableGroup[4].find('input.groupId').value()==false){
		locatableGroup[4].find('input.groupId').click()
		}
		if(locatableGroup[3].find('input.groupId').value()==false){
		locatableGroup[3].find('input.groupId').click()
		}
				
		then:"Show on map"
		$('#btn_tab-users_showOnMap').click()
		if($('.dialogError').displayed==true){
			waitFor{$('.dialogError').find('.ui-dialog-buttonset button').displayed==true}
			$('.dialogError').find('.ui-dialog-buttonset button').click()
			waitFor{$('.dialogError').displayed==false}
			waitFor{$('.locateSingleUser').find('.ui-dialog-buttonset button').displayed==true}
			$('.locateSingleUser').find('.ui-dialog-buttonset button').click()
			waitFor{$('.locateSingleUser').displayed==false}
		}

		waitFor{tooltip.displayed == true }
		$('#btn_map_clear').click()
		waitFor{tooltip.displayed == false }
		
		}
	
	def "Select five groups and locate"(){
		given:"at Users page"
		at UsersPage
		waitFor {nolocatableUsers.displayed==false}
		
		when:"I select first five locatable groups"
		locatableGroup[1].find('input.groupId').click()
		locatableGroup[0].find('input.groupId').click()
			
		if(locatableGroup[4].find('input.groupId').value()==false){
		locatableGroup[4].find('input.groupId').click()
		}
		if(locatableGroup[3].find('input.groupId').value()==false){
		locatableGroup[3].find('input.groupId').click()
		}
		if(locatableGroup[2].find('input.groupId').value()==false){
		locatableGroup[2].find('input.groupId').click()
		}
				
		then:"Show on map"
		$('#btn_tab-users_showOnMap').click()
		if($('.dialogError').displayed==true){
			waitFor{$('.dialogError').find('.ui-dialog-buttonset button').displayed==true}
			$('.dialogError').find('.ui-dialog-buttonset button').click()
			waitFor{$('.dialogError').displayed==false}
			waitFor{$('.locateSingleUser').find('.ui-dialog-buttonset button').displayed==true}
			$('.locateSingleUser').find('.ui-dialog-buttonset button').click()
			waitFor{$('.locateSingleUser').displayed==false}
		}

		waitFor{tooltip.displayed == true }
		$('#btn_map_clear').click()
		waitFor{tooltip.displayed == false }
		
		}
	
	}


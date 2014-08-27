package com.oksijen.lbs.lbas.functest.specs.map.geofencing

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.GeofencingPage
import com.oksijen.lbs.lbas.functest.pages.map.geofencing.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
@Stepwise
class AlarmsSpec extends LocateSpec {
	@RetryOnFailure(times=5)
	def "Create a new alarm"(){
		given:"We are at the AlarmsPage"
		at MapHomePage
		geofencingTab.click()
		waitFor {at GeofencingPage}
		
		when:"I select create a new alarm from action list"
		selectOption('alarmActionList','1')
		waitFor {$('.editAlarmDialog').displayed==true}
		
		then:"I enter relevant info"
		waitFor {$('#alarmDetailsForm').displayed==true}
		$('input#alarmName') << "AlarmTest"
		$('input#startTime') << "08:00"
		$('input#stopTime') << "17:00"
		$('textarea#alarmNotes') << "ForTest"
		
		and:
		$('ul.tabs.clearfix li',1).click()
		waitFor {$('#alarmGeofencesForm').displayed==true}
		$('select#allGeofences option',1).click()
		$('a.action-arrow.add').click()
		
		and:
		$('ul.tabs.clearfix li',2).click()
		waitFor {$('div#alarmTargetsList').displayed==true}
		$('#alarmTargetsSearchInput') << params.get('users.Request')
		waitFor {$('div#eventtarget_autocomplete ul li').size()>0}
		$('li.ui-menu-item a').click()
	
		and:
		$('ul.tabs.clearfix li',3).click()
		waitFor {$('#alarmNotificationForm').displayed==true}
		$('#notifyByEmail').click()
		$('#notificationTargetsSearchInput') << params.get('users.Request')
		waitFor {$('#notiftarget_autocomplete ul li').size()>0}
		$('#notiftarget_autocomplete ul li',1).find('a').click()
			
		and:"I click Create"
		$('ul.tabs.clearfix li',0).click()
		$('a.send-button').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
	}
	@RetryOnFailure(times=5)
	def "Detailed alarm info dialog is displayed when clicked on the name"(){
		given:"We are at the AlarmsPage"
		at GeofencingPage
	
		when:"I click name for alarm detail"
		waitFor {$('ul#alarmList li').size()>0}
		$('a.btn-show-link').click()
		
		then:"Alarm info dialog is opened"
		waitFor {$('.editAlarmDialog').displayed==true}
		
		and:"Scroll through the tabs"
		waitFor {$('#alarmDetailsForm').displayed==true}
		$('ul.tabs.clearfix li',1).click()
		waitFor {$('#alarmGeofencesForm').displayed==true}
		$('ul.tabs.clearfix li',2).click()
		waitFor {$('div#alarmTargetsList').displayed==true}
		$('ul.tabs.clearfix li',3).click()
		waitFor {$('#alarmNotificationForm').displayed==true}
		
		and:"I click cancel to close the dialog"
		$('a.cancel-button').click()
		waitFor {$('.editAlarmDialog').displayed==false}
		}
	@RetryOnFailure(times=5)
	def "Editing alarm will open editing dialog"(){
		given:"We are at the AlarmsPage"
		at GeofencingPage
		
		when: "I click edit an alarm"
		$('a.btn-update').click()
		waitFor {$('.editAlarmDialog').displayed==true}
		waitFor {$('#alarmDetailsForm').displayed==true}
		$('input#alarmName') << "-Edited"	
		$('ul.tabs.clearfix li',2).click()
		waitFor {$('div#alarmTargetsList').displayed==true}
		$('ul.tabs.clearfix li',3).click()
		waitFor {$('#alarmNotificationForm').displayed==true}
		$('ul.tabs.clearfix li',1).click()
		waitFor {$('#alarmGeofencesForm').displayed==true}
		
		then: "I click save and all edits will be saved"
		$('a.send-button').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
	}
	@RetryOnFailure(times=5)
	def "Deleting alarm"(){
		given:"We are at the AlarmsPage"
		at GeofencingPage
		
		when: "I click delete an alarm"
		$('a.btn-delete').click()
		
		then: "Confirmation dialog will be opened"
		waitFor {$('.confirmation').displayed==true}
		$('.confirmation').find('.right').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		}				
}

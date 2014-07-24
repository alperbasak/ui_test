package com.oksijen.lbs.lbas.functest.specs.account

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.account.*


/**
 * 
 */
@Stepwise
class AccountSpec extends LocateSpec {
    
	def "Personal details displayed when account page is clicked"() {
		given: "We are at the WelcomePage"
		at WelcomePage
		
		when: "I click My Account"
		accountBtn.click()
		
		then: "My Availability page should render"
		waitFor { at AccountHomePage }
	}
	
	def "Password can be changed via password tab"() {
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click Password tab"
		password.click()
		
		then:"Password update will render"
		waitFor {$('input#newPassword').displayed==true}
		waitFor {$('input#retypePassword').displayed==true}
		waitFor {$('#updatePasswordBtn').displayed==true}
		}
		
	def "Group info can be updated via Groups tab"(){
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click Groups tab"
		groups.click()
		
		then:"Groups update will render"
		waitFor {$('#accountGroupDropdown-button').displayed==true}
		waitFor {$('#updateGroupBtn').displayed==true}
		}
	
	def "Time zone info can be updated via Time zone tab"() {
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click TimeZone tab"
		timeZone.click()
		
		then:"TimeZone update will render"
		waitFor {$('#accountTimeZoneDropdown-button').displayed==true}
		waitFor {$('#updateTimeZoneBtn').displayed==true}
		}
	
	def "Length unit info can be updated via Units tab"() {
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click Units tab"
		units.click()
		
		then:"Units update will render"
		waitFor {$('#accountUnitsDropdown-button').displayed==true}
		waitFor {$('#updateUnitsBtn').displayed==true}
		}
	
	def "Temperature info can be updated via temperature tab"() {
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click Temperature tab"
		temp.click()
		
		then:"Temperature update will render"
		waitFor {$('#accountTemperatureDropdown-button').displayed==true}
		waitFor {$('#updateTemperatureBtn').displayed==true}
		}
	
	def "Work address info can be updated via work address tab"() {
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click work address tab"
		workAddress.click()
		
		then:"Work address update will render"
		waitFor {$('#accountWorkAddressText').displayed==true}
		waitFor {$('#updateWorkAddressBtn').displayed==true}
		}
	
	def "Language preferances can be updated via Language tab"() {
		given: "We are at the AccountPage"
		at AccountHomePage
		
		when:"I click Language tab"
		lang.click()
		
		then:"Language update update will render"
		waitFor {$('#accountLanguageDropdown-button').displayed==true}
		waitFor {$('#updateLanguageBtn').displayed==true}
		}
		
	}
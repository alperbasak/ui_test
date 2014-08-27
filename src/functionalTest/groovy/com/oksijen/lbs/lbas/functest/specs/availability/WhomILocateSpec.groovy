package com.oksijen.lbs.lbas.functest.specs.availability

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.availability.*

import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@Stepwise
class WhomILocateSpec extends LocateSpec {
	@RetryOnFailure(times=5)
def "Whom I can locate page is displayed"(){
	given: "We are at the WelcomePage"
	at WelcomePage
		
	when: "I click Availability tab"
	privacyMenu.click()
	waitFor { at AvailabilityHomePage }
		
	then: "Click I can locate and page should render"
	locateOthers.click()
	waitFor { at WhomILocatePage }
	}
@RetryOnFailure(times=5)
def "Filter by kind of request option allows me to show only the permanent permissions"(){

	given:"We are at whom I can locate page"
	at WhomILocatePage
	
	when:"I select Filter by Permanent"
	assert allPermission.size()>0
	filterBy.click()
	permFilter.click()
	
	then:"Permanent permissions will be shown"
	if (permPermission.size()>0){
	waitFor {permPermission.displayed==true}
	}
	
	when:"I select Filter by Temporary"
	filterBy.click()
	tempFilter.click()
		
	then:"Temporary permissions will be shown"
	if (tempPermission.size()>0){
	waitFor {tempPermission.displayed==true}
	}
	filterBy.click()
	allFilter.click()
	}
@RetryOnFailure(times=5)
def "When mouse is hovered over i, visibility profile is shown"(){
	given:"We are at who can locate me page"
	at LocateMePage
	
	when:"I hover over info to see detailed profile"
	$("a.info").jquery.mouseover()
	
	then:
	waitFor {profileInfo.displayed==true}
	$('li.locateOthers').click()
	waitFor {profileInfo.displayed==false}
	}

}
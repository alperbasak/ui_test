package com.oksijen.lbs.lbas.functest.specs.availability

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.availability.*


/**
 * 
 */
@Stepwise
class LocateMeSpec extends LocateSpec {
    
def "who can locate me page is displayed"(){
	given: "We are at the WelcomePage"
	at WelcomePage
		
	when: "I click Availability tab"
	privacyMenu.click()
	waitFor { at AvailabilityHomePage }
		
	then: "Click who can locate me and page should render"
	locateMe.click()
	waitFor { at LocateMePage }
	}

def "Filter by kind of request option allows me to show only the permanent permissions"(){

	given:"We are at who can locate me page"
	at LocateMePage
	
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

def "Clicking View will open location requests history"(){

	given:"We are at who can locate me page"
	at LocateMePage
	
	when:"I click View"
	assert allPermission.size()>0
	$('a.viewPos').click()
	
	then:"Location requests history dialog will open"
	waitFor {$('#dialog').displayed==true}
	$('#dialog a#before').click()
	$('#dialog a#after').click()
	waitFor {$('#dialog div#tableLoc').displayed==true}
	
	and:"I click close"
	closeView.click()
	waitFor {$('#dialog').displayed==false}
}

def "When mouse is hovered over i, visibility profile is shown"(){
	given:"We are at who can locate me page"
	at LocateMePage
	
	when:"I hover over info to see detailed profile"
	$("a.info").jquery.mouseover()
	
	then:
	waitFor {profileInfo.displayed==true}
	$('li.locateMe').click()
	waitFor {profileInfo.displayed==false}
	}

def "Changing location update notifications"(){
	given:"We are at who can locate me page"
	at LocateMePage
	
	when:"I select notification frequency"
	$('a#whoLocatedMeNotifySelect-button').click()
	$("ul#whoLocatedMeNotifySelect-menu li:not(.ui-selectmenu-item-selected)").click()
	waitFor {successDialog.hasClass('success-dialog')==true}
	waitFor {successDialog.hasClass('success-dialog')==false}
	
	then:"I uncheck the notification checkbox"
	$('input#whoLocatedMeNotifyCheck').click()
	waitFor {successDialog.hasClass('success-dialog')==true}
	waitFor {successDialog.hasClass('success-dialog')==false}
	
}

def "Editing permissions"(){
	given:"We are at who can locate me page"
	at LocateMePage
	
	when:"I click edit"
	allPermission.jquery.mouseover()
	waitFor {allPermission.find('a.edit').displayed==true}
	allPermission.find('a.edit').click()
	waitFor {$('#dialog').displayed==true}
	
	then:"I change visibility profile"
	waitFor{$('a#visPro-button').displayed==true}
	$('a#visPro-button').click()
	waitFor{$('.ui-selectmenu-open').displayed==true}
	$('ul#visPro-menu li').last().click()
	$('div #dialog').find('input#allow_report').click()
	sendBtn.click()

	and:"Check if profile and reporting is changed"
	waitFor {allPermission.find('td.seventh div').hasClass('iconCheck')==false}
	waitFor {$('table#whoLocateMe').find('p.textt').text().contains('default')}
	
}

def "Deleting permissions"(){
	given:"We are at who can locate me page"
	at LocateMePage
	
	when:"I click delete"
	allPermission.jquery.mouseover()
	waitFor {allPermission.find('a.delete').displayed==true}
	allPermission.find('a.delete').click()
	waitFor {$('#dialog').displayed==true}
	
	then:"I click delete"
	sendBtn.click()
	waitFor {$('#dialog').displayed==false}
	
	and:
	acceptRequest('alper2', 'alper')	
}





}
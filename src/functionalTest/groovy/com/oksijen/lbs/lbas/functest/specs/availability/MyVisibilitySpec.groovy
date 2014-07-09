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
class MyVisibilitySpec extends LocateSpec {
    
def "My visibility profiles page is displayed"(){
	given: "We are at the WelcomePage"
	at WelcomePage
		
	when: "I click Availability tab"
	privacyMenu.click()
	waitFor { at AvailabilityHomePage }
		
	then: "Click visibility profiles and page should render"
	myVisibility.click()
	waitFor { at VisibilityPage }
	}

def "Switching to full day view enlarges the agenda table"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when:"I click full day to switch the view"
	waitFor {workingTime.hasClass('selected')==true}
	assert agendaTable.size()==20
	fullTime.click()
	
	then:"Agenda table will be enlarged"
	waitFor {fullTime.hasClass('selected')==true}
	assert agendaTable.size()==48
	}

def "Null visibility profile name returns error"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click create new profile"
	createNew.click()
	waitFor {$('#dialog').displayed==true}
	saveBtn.click()
		
	then: "Error will be returned"
	waitFor {errorName.displayed==true}
	cancelBtn.click()
	
	}

def "Create new visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click create new profile"
	createNew.click()
	waitFor {$('#dialog').displayed==true}
	newVisName << params.get('visibility.newProfile')
	saveBtn.click() 
	
	then: "New profile will be created in a new tab"
	profileName.click()
	assert profileName.find('a span').text()==params.get('visibility.newProfile')
	
	}

def "Same visibility profile name will return error"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click create new profile"
	createNew.click()
	waitFor {$('#dialog').displayed==true}
	newVisName << params.get('visibility.newProfile')
	saveBtn.click()
	
	then: "Error will be returned"
	waitFor {errorName.displayed==true}
	cancelBtn.click()
	
	}

def "Adding hours to location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click on an hour interval"
	$('tr.fc-slot0 td').click()
	$('a#calSave').click() 	
	
	then:
	waitFor {$('div.successMessageCheck').displayed==true}
	waitFor {$('div.successMessageCheck').displayed==false}
	waitFor {$('div.fc-event').hasClass('fc-event-skin')==true}
}


def "Changing hour interval in location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I drag to next hour interval"
	interact{
	dragAndDrop($('div.fc-event'),$('tr.fc-slot1 td'))
	}
	$('a#calSave').click()
	
	then:
	waitFor {$('div.successMessageCheck').displayed==true}
	waitFor {$('div.successMessageCheck').displayed==false}
	assert	 $("div.fc-event").height==19
	waitFor {$('div.fc-event').hasClass('fc-event-skin')==true}
}

def "Resizing to change hour interval in location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click on an hour interval"
	$('div.fc-event').jquery.mouseover()
	waitFor {$('div.ui-resizable-handle').displayed==true}
	interact{
	dragAndDrop($('div.ui-resizable-handle.ui-resizable-s'),$('tr.fc-slot8 td'))
	}
	$('a#calSave').click()
	
	then:
	waitFor {$('div.successMessageCheck').displayed==true}
	waitFor {$('div.successMessageCheck').displayed==false}
	assert	 $('div.fc-event').height==145
	waitFor {$('div.fc-event').hasClass('fc-event-skin')==true}
}

def "Moving the interval to another day in location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I drag and drop an hour interval"
	interact{
	dragAndDrop($('div.fc-event'),$('td.fc-mon'))
	}
	$('a#calSave').click()
	
	then:
	waitFor {$('div.successMessageCheck').displayed==true}
	waitFor {$('div.successMessageCheck').displayed==false}
	assert	 $('div.fc-event').height==145
	waitFor {$('div.fc-event').hasClass('fc-event-skin')==true}
}

def "Canceling a change will not be saved to the visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click on an hour interval and delete but don't save"
	$('div.fc-event').click()
	$("a.closeTray").click()
	$('a#calClear').click()
	
	then:
	waitFor {$('div.fc-view').size()>0}
}

def "Deleting an interval in a location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "Delete and save"
	$('div.fc-event').click()
	$("a.closeTray").click()
	$('a#calSave').click()
	
	then:
	waitFor {$('div.successMessageCheck').displayed==true}
	waitFor {$('div.successMessageCheck').displayed==false}
	waitFor {$('div.fc-event').size()==0}
}

def "Deleting a visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "Delete Profile and accept"
	$('span.closeBT').last().click()
	waitFor {$('div#dialog').displayed==true}
	$('#dialog button.send').click()
	
	then:
	waitFor {$('div#dialog').displayed==false}
	assert profileName.find('a span').text()!=params.get('visibility.newProfile')
}

}
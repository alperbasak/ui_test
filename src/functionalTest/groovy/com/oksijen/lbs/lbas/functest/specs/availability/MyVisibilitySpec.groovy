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
class MyVisibilitySpec extends LocateSpec {
	@RetryOnFailure(times=5)
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
@RetryOnFailure(times=5)
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
@RetryOnFailure(times=5)
def "Null visibility profile name returns error"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click create new profile"
	createNew.click()
	waitFor {$('#dialog').displayed==true}
	$('div#dialog').find('button.send').click()
		
	then: "Error will be returned"
	waitFor {errorName.displayed==true}
	$('div#dialog').find('button.cancel').click()
	
	}
@RetryOnFailure(times=5)
def "Create new visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click create new profile"
	createNew.click()
	waitFor {$('#dialog').displayed==true}
	newVisName << params.get('visibility.newProfile')
	$('div#dialog').find('button.send').click() 
	
	then: "New profile will be created in a new tab"
	profileName.click()
	assert profileName.find('a span').text()==params.get('visibility.newProfile')
	
	}
@RetryOnFailure(times=5)
def "Same visibility profile name will return error"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click create new profile"
	createNew.click()
	waitFor {$('#dialog').displayed==true}
	newVisName << params.get('visibility.newProfile')
	$('div#dialog').find('button.send').click()
	
	then: "Error will be returned"
	waitFor {errorName.displayed==true}
	$('div#dialog').find('button.cancel').click()
	
	}
@RetryOnFailure(times=5)
def "Adding hours to location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click on an hour interval"
	$('tr.fc-slot0 td').click()
	saveBtn.click() 	
	
	then:
	waitFor {successMessage.displayed==true}
	waitFor {successMessage.displayed==false}
	waitFor {visInterval.hasClass('fc-event-skin')==true}
}

@RetryOnFailure(times=5)
def "Changing hour interval in location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I drag to next hour interval"
	interact{
	dragAndDrop(visInterval,$('tr.fc-slot1 td'))
	}
	saveBtn.click()
	
	then:
	waitFor {successMessage.displayed==true}
	waitFor {successMessage.displayed==false}
	assert	 visInterval.height==19
	waitFor {visInterval.hasClass('fc-event-skin')==true}
}
@RetryOnFailure(times=5)
def "Resizing to change hour interval in location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click on an hour interval"
	visInterval.jquery.mouseover()
	waitFor {$('div.ui-resizable-handle').displayed==true}
	interact{
	dragAndDrop($('div.ui-resizable-handle.ui-resizable-s'),$('tr.fc-slot8 td'))
	}
	saveBtn.click()
	
	then:
	waitFor {successMessage.displayed==true}
	waitFor {successMessage.displayed==false}
	assert	 visInterval.height==145
	waitFor {visInterval.hasClass('fc-event-skin')==true}
}
@RetryOnFailure(times=5)
def "Moving the interval to another day in location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I drag and drop an hour interval"
	interact{
	dragAndDrop(visInterval,$('td.fc-mon'))
	}
	saveBtn.click()
	
	then:
	waitFor {successMessage.displayed==true}
	waitFor {successMessage.displayed==false}
	assert	 visInterval.height==145
	waitFor {visInterval.hasClass('fc-event-skin')==true}
}
@RetryOnFailure(times=5)
def "Canceling a change will not be saved to the visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "I click on an hour interval and delete but don't save"
	visInterval.click()
	deletePrf.click()
	$('a#calClear').click()
	
	then:
	waitFor {$('div.fc-view').size()>0}
}
@RetryOnFailure(times=5)
def "Deleting an interval in a location visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "Delete and save"
	visInterval.click()
	deletePrf.click()
	saveBtn.click()
	
	then:
	waitFor {successMessage.displayed==true}
	waitFor {successMessage.displayed==false}
	waitFor {visInterval.size()==0}
}
@RetryOnFailure(times=5)
def "Deleting a visibility profile"() {
	given:"We are at Visibility profile page"
	at VisibilityPage
	
	when: "Delete Profile and accept"
	$('span.closeBT').last().click()
	waitFor {$('div#dialog').displayed==true}
	$('div#dialog').find('button.send').click()
	
	then:
	waitFor {$('div#dialog').displayed==false}
	assert profileName.find('a span').text()!=params.get('visibility.newProfile')
}

}
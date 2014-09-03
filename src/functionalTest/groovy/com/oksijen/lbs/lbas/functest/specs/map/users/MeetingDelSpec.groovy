package com.oksijen.lbs.lbas.functest.specs.map.users

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import com.oksijen.lbs.lbas.functest.pages.calendar.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*

/**
 * 
 */
@Stepwise
class MeetingDelSpec extends LocateSpec {
	@RetryOnFailure
	def "Delete TestEvent"() {
		when: "We are at the UsersPage"
		at WelcomePage
		calendarMenu.click()
		waitFor {at CalendarHomePage}
		
		then:
		if($('span.fc-event-title',text:'TestEvent').displayed==true){
		$('span.fc-event-title',text:'TestEvent').click()
		waitFor{$('.meeting-detail').displayed==true}
		$('.ui-dialog-buttonset button')[2].click()
		waitFor{$('.delete-text').displayed==true}
		$('.buttons_class').find('button.send').click()
		waitFor {$('.noCloseNoOk').displayed==true}
		waitFor {$('.noCloseNoOk').displayed==false}
	}
	}
}
	
	

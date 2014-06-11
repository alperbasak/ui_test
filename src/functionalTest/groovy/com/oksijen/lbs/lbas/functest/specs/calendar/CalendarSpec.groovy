package com.oksijen.lbs.lbas.functest.specs.calendar

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.calendar.CalendarHomePage


/**
 * 
 */
@Stepwise
class CalendarSpec extends LocateSpec {
	@Unroll
	def "Clicking calendar tab takes me to calendar page"(){
		given: "We are at the WelcomePage"
		at WelcomePage
				
		when: "I click Calendar tab"
		calendarMenu.click()
				
		then: "Calendar page should render"
		waitFor {at CalendarHomePage}
	}
}



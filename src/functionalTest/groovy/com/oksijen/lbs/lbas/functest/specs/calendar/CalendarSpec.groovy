package com.oksijen.lbs.lbas.functest.specs.calendar

import geb.spock.GebSpec

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.calendar.*


/**
 * 
 */
@Stepwise
class CalendarSpec extends LocateSpec {
	@Unroll
	def "Month view is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()
						
		when: "Calendar page is displayed"
		waitFor {at CalendarHomePage}
				
		then: "Calendar page should render"
		expect currentDate.displayed, is(true)
		
	}
	
	def "Today is highlighted"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()
						
		when: "Calendar page is displayed"
		waitFor {at CalendarHomePage}
				
		then: "Calendar page should render"
		expect highlightToday.displayed, is(true)
		
	}
	
}



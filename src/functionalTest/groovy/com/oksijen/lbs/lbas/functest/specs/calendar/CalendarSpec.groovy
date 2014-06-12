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

	def "Weekly view is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Week is clicked"
		waitFor {at CalendarHomePage}

		weekView.click()

		then: "Calendar weekly view page should render"
		waitFor {at CalendarWeekPage}
	}

	def "Date interval displayed on weekly view"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Week is clicked"
		waitFor {at CalendarHomePage}

		weekView.click()

		then: "Calendar weekly view page should render"
		waitFor {at CalendarWeekPage}

		expect dateWeek.displayed, is(true)
	}
	@IgnoreRest
	def "Daily view is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Daily is clicked"
		waitFor {at CalendarHomePage}

		dayView.click()

		then: "Calendar daily view page should render"
		waitFor {at CalendarDayPage}
	}
}



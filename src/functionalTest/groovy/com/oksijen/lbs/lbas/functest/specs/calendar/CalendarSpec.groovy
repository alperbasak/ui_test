package com.oksijen.lbs.lbas.functest.specs.calendar

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.calendar.*

import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * 
 */
@RetryOnFailure(times=5)
@Stepwise
class CalendarSpec extends LocateSpec {
    
	def "Month view is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Calendar page is displayed"
		waitFor('slow') { at CalendarHomePage }

		then: "Calendar page should render"
		expect currentDate.displayed, is(true)
	}
	
	def "Today is highlighted"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Calendar page is displayed"
		waitFor { at CalendarHomePage }

		then: "Calendar page should render"
		expect highlightToday.displayed, is(true)
	}
	
	def "Weekly view is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Week is clicked"
		waitFor('slow') { at CalendarHomePage }

		weekView.click()

		then: "Calendar weekly view page should render"
		waitFor { at CalendarWeekPage }
	}
	
	def "Date interval displayed on weekly view"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Week is clicked"
		waitFor('slow') { at CalendarHomePage }

		weekView.click()

		then: "Calendar weekly view page should render"
		waitFor { at CalendarWeekPage }

		expect dateWeek.displayed, is(true)
	}
	
	def "Daily view is displayed"(){
		given: "We are at the WelcomePage"
		at WelcomePage
		calendarMenu.click()

		when: "Daily is clicked"
		waitFor('slow') {at CalendarHomePage}

		dayView.click()

		then: "Calendar daily view page should render"
		waitFor { at CalendarDayPage }
		expect dateDay.displayed, is(true)
				
	}
	
	def "A new meeting window is opened on monthly view"(){
		given:"at CalendarPage"
		at CalendarDayPage
		
		when: "a cell is clicked"
		monthView.click()
		waitFor {at CalendarHomePage}
		monthPointer.click()
		
		then: "New event dialog should render"
		expect meetingmonthTitle.displayed, is(true)
		
		and: "click cancel buton"
		cancelMeetingButton.click() 
		
	}
	
	def "A new meeting window is opened on weekly view"(){
		given:"at CalendarPage"
		at CalendarHomePage

		when: "a cell is clicked"
		weekView.click()
		waitFor('slow') {at CalendarWeekPage}
				
		weekPointer.click()

		then: "New event dialog should render"
		waitFor { at CalendarWeekPage }
		waitFor {meetingTitle.displayed==true}
		
		and: "click cancel buton"
		cancelMeetingButton.click()
		
	}

	def "A new meeting window is opened on daily view"(){
		given:"at CalendarPage"
		at CalendarWeekPage
		
		when: "a cell is clicked"
		dayView.click()
		waitFor('slow') {at CalendarDayPage}
		dayPointer.click()

		then: "New event dialog should render"
		waitFor { at CalendarDayPage }
		expect meetingdayTitle.displayed, is(true)
		
		and: "click cancel buton"
		cancelMeetingButton.click()
		calendarMenu.click()
	}
}



package com.oksijen.lbs.lbas.functest.pages.calendar

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by cpekyaman on 3/25/2014.
 */
class CalendarHomePage extends WelcomePage {
	static url = "welcome"
	static at = { mainDiv.displayed == true }

	static content = {
		mainDiv(required: true) 	{ $("div#calendar") }
		leftPanel(required: true) 	{ $("div.left") }
		rightPanel(required: true) 	{ $("div.right") }
		calendarMenu(required: false)  { $("a#btn_calendar")}
		currentDate(required: false) { $("div.fc-day-number")}
		highlightToday(required: false) { $("td.fc-today")}
		weekView(required: false) { $("span.fc-button.fc-button-agendaWeek.fc-state-default")}
		dayView(required:false) { $("span.fc-button.fc-button-agendaDay")}
		meetingSubject(required: false)  { $("span#meetingSubjectVld")}
		meetingmonthTitle(required: false) { $("span#ui-dialog-title-dialog")}
		monthPointer(required: false) { $("td.fc-mon.fc-widget-content.fc-day0.fc-first.fc-other-month")}
		cancelMeetingButton(required: false) { $("button#inboxLocationRequestRejectButton")}
	}
}

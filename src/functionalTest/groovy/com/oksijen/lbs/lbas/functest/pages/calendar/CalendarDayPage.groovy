package com.oksijen.lbs.lbas.functest.pages.calendar

import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.calendar.CalendarHomePage

/**
 * Created by cturkdogan on 6/12/2014.
 */
class CalendarDayPage extends CalendarHomePage {
	static url = "welcome"
	static at = { mainDiv.displayed == true }

	static content = {
		mainDiv(required: true) 	{ $("div#calendar") }
		leftPane(required: true) 	{ $("div.fc-view.fc-view-agendaDay.fc-agenda") }
		dateDay(required: true)	{ $("span.fc-header-title")}
		dayPointer(required: false) { $("div.fc-view-agendaDay").find("tr.fc-slot13 td")}
		meetingdayTitle(required: false) { $(".noClose")}
		cancelMeetingButton(required: false) { $("button#inboxLocationRequestRejectButton")}
		monthView(required: false) {$('span.fc-button-month')}
	}
}
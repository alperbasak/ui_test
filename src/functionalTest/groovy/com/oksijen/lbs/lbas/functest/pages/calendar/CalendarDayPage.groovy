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
		dayPointer(required: false) { $("tr.fc-slot13.fc-minor")}
		meetingdayTitle(required: false) { $("div#dialog.ui-dialog-content.ui-widget-content")}
		cancelMeetingButton(required: false) { $("button#inboxLocationRequestRejectButton")}
	}
}
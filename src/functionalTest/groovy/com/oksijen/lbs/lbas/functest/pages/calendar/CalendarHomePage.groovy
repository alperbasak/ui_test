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
    }
}

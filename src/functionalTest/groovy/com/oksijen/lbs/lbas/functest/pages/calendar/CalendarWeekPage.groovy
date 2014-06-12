package com.oksijen.lbs.lbas.functest.pages.calendar

import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.calendar.CalendarHomePage

/**
 * Created by cturkdogan on 6/12/2014.
 */
class CalendarWeekPage extends CalendarHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#calendar") }
        timePanel(required: true) 	{ $("th.fc-agenda-axis.fc-widget-header") }
    		
    }
}
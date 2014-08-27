package com.oksijen.lbs.lbas.functest.pages.availability

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class AvailabilityHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 			{ $("div#privacy") }
        leftPanel(required: true) 			{ $("div#visibility") }
        rightPanel(required: true) 			{ $("div#availability-container") }
		myAvailability(required:true)		{$('li#availabilityMe')}
		myVisibility(required: true)		{$("li.visibilityMe")}
		locateMe(required:true)				{$("li.locateMe")}
		locateOthers(required:true)			{$('li.locateOthers')}
		
		visibilityStatus(required: false)	{ $('a#visibilityStatus-button')}
		notVisible(required: false)			{$('ul#visibilityStatus-menu li',1)}
		visible(required: false)			{$('ul#visibilityStatus-menu li',0)}
		
		plusBox	(required: false)			{$('div#plusBox')}
		minusBox(required: false)			{$('div#minusBox')}
		tableLocator(required: false)		{$('table#locating tbody').children()}
		deleteLocator(required: false)		{$('table#locating').find('a.removeLocation')}
		
		cancelBtn(required: false)			{$('#dialog').find('button.cancel')}
		sendBtn(required: false)			{$('#dialog').find('button.send')}
		
		profileInfo(required:false)			{$('div#calendarX')}
		
		successMsg(required: false)			{$('div.successMessageCheck')}
		addExcp(required: false)			{$('a#addExcp')}
			
		activeExcp(required: false)			{$('div#activeException')}
		excpTable(required: false) 			{$('table#excp')}
		editExcp(required: false) 			{$('a#editExceptionRow')}
		delExcp(required: false)			{$('a#removeExceptionRow')}
		
		calendarSelect(required: false)		{$('div#ui-datepicker-div')}
		datePicker(required: false)			{$('img.ui-datepicker-trigger')}
		nextMonth(required: false)			{$('a.ui-datepicker-next')}
		dateSelect(required: false)			{$('table.ui-datepicker-calendar tbody tr',2).children()}
		successDialog(required: false)		{$('div.ui-dialog').last()}
		
		
		    }
}

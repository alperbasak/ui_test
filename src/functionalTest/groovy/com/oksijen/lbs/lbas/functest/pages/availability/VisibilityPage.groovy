package com.oksijen.lbs.lbas.functest.pages.availability

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class VisibilityPage extends AvailabilityHomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 			{$("div#privacy") }
        leftPanel(required: true) 			{$("div#visibility") }
        rightPanel(required: true) 			{$("div#visibilityProfiles-container") }
		agendaTable(required: false)		{$('table.fc-agenda-slots tbody tr')}
		workingTime(required: false)		{$('a#workingTime')}
		fullTime(required:false)			{$('a#fullTime')}
		createNew(required:false)			{$("a#tabVis_0")}
		newVisName(required:false)			{$('#dialog').find('input#tab_title')}
		saveBtn(required:false)				{$('a#calSave')}
		profileName(required:false)			{$('li.tab-profiles').last().previous()}
		errorName(required:false)			{$('#dialog').find('.errorsDisplay')}
		visInterval(required:false)			{$('div.fc-event')}
		deletePrf(required:false)			{$("a.closeTray")}
		successMessage(required:false)		{$('div.successMessageCheck')}
	    }
}


package com.oksijen.lbs.lbas.functest.pages.map

/**
 * Created by cpekyaman on 3/25/2014.
 */
class AssetsPage extends MapHomePage {
    static url = "welcome"
    static at = { searchInput.displayed == true }

    static content = {
    	link(required: true) 		{ $('a#btn_tab-assets') }
        searchInput(required: true) { $("input#search_assets") }
        contents(required: true) 	{ $("div.contents") }
		
		tooltip(required: false, cache: false) { $("div.toolTipContainer") }
		tooltipClose(required: false, cache: false) { _slink('Close', tooltip.find('div.toolTipHeader')) }
		permissionSend(required:false)			{$('div.ui-dialog-buttonset button',1)}
		
		viewReport(required: false) 		{$("div.buttonsForReport a").last()}
		locReportDiv(required: false) 		{$('div#locReportTableDiv')}
		locReportHist(required: false)		{$('div#locReportUserList ul')}
		locReportUser(required: false)		{ $('div#locReportUserList div')}
		adminBtn(required:false)		{$('a#btn_admin')}
    }
}

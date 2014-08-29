package com.oksijen.lbs.lbas.functest.pages.map

/**
 * Created by cpekyaman on 3/25/2014.
 */
class UsersPage extends MapHomePage {
    static url = "welcome"
    static at = { searchInput.displayed == true }

    static content = {
    	link(required: true) 		{ $('a#btn_tab-users') }
        searchInput(required: true) { $("input#search_users") }
        contents(required: true) 	{ $("div.contents") }
		resetInput(required:false)	{$('span.searchReset')}
		
		locatableUserButton(required:false) {$('li.locatableItems').find('a.globalSearchButton')}
		requestUserButton(required:false) {$('li.noLocatableItems').find('a.globalSearchButton')}
		nolocatableUsers(required:false)	{$('ul.users li.noLocatableItems')}
		locatableUsers(required:false)		{$('ul.users li.locatableItems')}
		
		tooltip(required: false, cache: false) { $("div.toolTipContainer") }
		tooltipClose(required: false, cache: false) { _slink('Close', tooltip.find('div.toolTipHeader')) }
		
		showLocatable(required:false)	{$('ul#filter_all_tab-users-menu li',1)}
		
		groupOpenClose(required:false)		{$('span.groupName',text:'VF').parent().parent().find('img.openClose')}
		detailLocate(required:false)	{locatableUsers.find('.items_list_buttons li',0)}
		detailReport(required:false)	{locatableUsers.find('.items_list_buttons li',1)}
		detailMessage(required:false)	{locatableUsers.find('.items_list_buttons li',2)}
		detailUser(required:false)		{locatableUsers.find('.items_list_buttons li',3)}
		detailShareLoc(required:false)	{locatableUsers.find('.items_list_buttons li',4)}
		
		permissionDialog(required:false)		 {$('#dialog').last()}
		permissionInput(required:false)  		{$('#sendSpecialMesTextarea')}
		permissionSend(required:false)			{$('div.ui-dialog-buttonset button',1)}
		successSent(required: false) 		{ $("div.successMessageCheck") }
		
		printReport(required: false) 		{$("div.buttonsForReport a",0)}
		downloadReport(required: false) 		{$("div.buttonsForReport a",1)}
		viewReport(required: false) 		{$("div.buttonsForReport a").last()}
		locReportDiv(required: false) 		{$('div#locReportTableDiv')}
		locReportHist(required: false)		{$('div#locReportUserList ul')}
		locReportUser(required: false)		{ $('div#locReportUserList div')}
		
		closeThick(required:false)		{$('span.ui-icon-closethick')}
		successDialog(required:false)	{$('.success-dialog')}
		
		actionListClose(required:false)		{$("a#userActionList-button")}
		actionListClose2(required:false)	{$('a#userActionList2-button')}
		createNewUser(required:false)		{$("ul#userActionList-menu").find("li.userActionList2 a")}
		deleteUser(required:false)		{$("ul#userActionList2-menu").find("li.userActionList11",0).children()}
		editUser(required:false)		{$("ul#userActionList2-menu").find("li.userActionList11",1).children()}
		moveToNewGroup(required:false)		{$("ul#userActionList2-menu").find("li.userActionList10 a")}
		createNewGroup(required:false)		{$("ul#userActionList2-menu").find("li.userActionList4 a")}
		editGroup(required:false)		{$("ul#userActionList2-menu").find("li.userActionList5 a")}
		requestPermission(required:false)		{$("ul#userActionList2-menu").find("li.userActionList1 a")}
		shareMyLocation(required:false)		{$("ul#userActionList2-menu").find("li.userActionList11",2).children()}
				
		userName(required:false)			{$('#userDetailForm').find('input',3)}
		userSurname(required:false)			{$('#userDetailForm').find('input',4)}
		userMail(required:false)			{$('#userDetailForm').find('input',5)}
		groupName(required:false)		{$('#updateGroupName').find('input',2)}
		groupDesc(required:false)		{$('#updateGroupName').find('textarea')}
		groupMembers(required:false)	{$('ul.tabs.clearfix li',1)}
		groupPermissions(required:false)	{$('ul.tabs.clearfix li',2)}
		groupAdmin(required:false)	{$('ul.tabs.clearfix li',3)}
		
		addressList(required: false)		{ $('div.token-input-dropdown-facebook ul') }
		addressListItems(required: false)	{ addressList.children()}
		successSent(required: false) 		{ $("div.successMessageCheck") }
		
		locatableGroup(required:false)		{$('div#tab-users div.contents ul li').not(".noLocatableItems")}
		calendar(required:true)		{$('#btn_calendar')}
    }
}

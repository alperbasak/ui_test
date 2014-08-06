package com.oksijen.lbs.lbas.functest.pages.admin

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * 
 */
class AdminHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mainDiv.displayed == true }

    static content = {
        mainDiv(required: true) 	{ $("div#admin") }
        leftPanel(required: true) 	{ $("div#adminNav") }
        rightPanel(required: true) 	{ $("div#adminContent") }
		
		groupMan(required: true) 	{$('ul.adminMenu li',3)}
		selectMenu(required:false)	{$('a.ui-selectmenu')}
		addGroup(required:false)	{$('a',text:"Add group")}
		apply(required:false)		{$('.toolbar-apply')}
		
		groupDialog(required:false)	{$('.newGroupOvl')}
		groupName(required:false)	{$('input',id:startsWith('lbasGroup'))}
		groupDetailsTab(required:false)		{$('ul.tabs.clearfix li',0)}
		groupMembersTab(required:false)		{$('ul.tabs.clearfix li',1)}
		groupPermissionsTab(required:false)	{$('ul.tabs.clearfix li',2)}
		groupAdminTab(required:false)		{$('ul.tabs.clearfix li',3)}

		permissions(required:false)		{$('form#updateGroupPermissions')}
		allAdmins(required:false)		{$('select#allAdminUsers option')}
		admin(required:false)			{$('form#groupAdmin')}
		groupAdminUsers(required:false)	{$('select#groupAdminUsers option')}
		
		arrowAdd(required:false)		{$('a.action-arrow.add')}
		arrowRemove(required:false)		{$('a.action-arrow.remove')}
		
		sendDialog(required:false)		{$('a.send-button')}
		successDialog(required:false)	{$('.success-dialog')}
    }
}

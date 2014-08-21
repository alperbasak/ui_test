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
		companyMan(required: true) 	{$('ul.adminMenu li',0)}
		selectMenu(required:false)	{$('a.ui-selectmenu')}
		addGroup(required:false)	{$('li a',text:"Add group")}
		addAsset(required:false)	{$('li a',text:"Add asset")}
		addAssetGroup(required:false){$('li a',text:"Add asset group")}
		apply(required:false)		{$('.toolbar-apply')}
		delete(required:false)		{$('li a',text:"Delete")}
		
		groupDialog(required:false)	{$('.addExcpPos')}
		groupName(required:false)	{$('input',id:startsWith('lbasGroup'))}
		groupDetailsTab(required:false)		{$('ul.tabs.clearfix li',0)}
		groupMembersTab(required:false)		{$('ul.tabs.clearfix li',1)}
		groupPermissionsTab(required:false)	{$('ul.tabs.clearfix li',2)}
		groupAdminTab(required:false)		{$('ul.tabs.clearfix li',3)}

		permissions(required:false)		{$('form#updateGroupPermissions')}
		members(required:false)			{$('form#updateGroupMembers')}
		allMembers(required:false)		{$('select#selected option')}
		allAdmins(required:false)		{$('select#allAdminUsers option')}
		admin(required:false)			{$('form#groupAdmin')}
		groupAdminUsers(required:false)	{$('select#groupAdminUsers option')}
		
		memberAdd(required:false)		{$('#updateGroupMembers').find('a.action-arrow.add')}
		arrowAdd(required:false)		{$('#groupAdmin').find('a.action-arrow.add')}
		arrowRemove(required:false)		{$('#groupAdmin').find('a.action-arrow.remove')}
		
		sendDialog(required:false)		{$('a.send-button')}
		successDialog(required:false)	{$('.success-dialog')}
		
		assetMan(required: true) 	{$('ul.adminMenu li',2)}
		assetName(required:false)	{$('input',id:'lbasAsset.name')}
		assetModel(required:false)	{$('input',id:'lbasAsset.surname')}
		assetAllocated(required:false)	{$('#allocatedToFullName')}
		saveGroup(required:false)		{$('#adminEditGroup').find('a.send-button')}
		assetDialog(required:false)	{$('.editAssetDialog')}
		assetGroupMenu(required:false)	{$('#saveAsset_selectedGroup-button')}
		assetGroup(required:false)		{$('#saveAsset_selectedGroup-menu').find('a',text:'AA')}
		
		assetProfile(required:false)	{$('#assetVisibilityProfile table tbody tr')}
		companyWorkingHours(required:false)	{$('div.text',text:contains('Company working'))}
		companyVisibility(required:false)	{$('#defaultVisibilityProfile').find('tr')}
    }
}

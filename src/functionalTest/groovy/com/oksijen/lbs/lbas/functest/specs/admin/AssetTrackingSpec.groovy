package com.oksijen.lbs.lbas.functest.specs.admin

import geb.spock.GebSpec
import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*
import org.openqa.selenium.Keys

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.LoginPage
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.admin.*
import com.oksijen.lbs.lbas.functest.pages.map.*

/**
 * 
 */
@Stepwise
class AssetTrackingSpec extends LocateSpec {
    
def "Asset Management Page is displayed"(){
	given: "We are at the WelcomePage"
	at WelcomePage
		
	when: "I click Admin tab"
	adminBtn.click()
	waitFor { at AdminHomePage }
		
	then: "Click Group Management and page should render"
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	}

def "Edit asset in the group"(){
	given:"We are the Asset Tab"
	at AdminHomePage

	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
//	when:"I click Add asset"
//	selectMenu.click()
//	addAsset.click()
//	apply.click()
//	waitFor {$('.editAssetDialog').displayed==true}
//		
//	then:"I enter necessary info and create a new asset group"
//	assetName<<"AssetTrackingTest"
//	assetModel<<"Model"
//	assetAllocated<<"Alper"
//	waitFor{$('ul.ui-autocomplete li').size()>0}
//	$('a.ui-corner-all')[0].click()
//	$('a.link-new').click() 
//	waitFor{$('.newGroupOvl').displayed==true}
//	groupName<<"GroupTrackingTest"
//	saveGroup.click()
//	waitFor {successDialog.displayed==true}
//	waitFor {successDialog.displayed==false}
	
	then:"Tracking Settings PPT:off VC:0"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	if ($('#permanentPeriodicTracking',value:'true').size()>0){
		$('#permanentPeriodicTracking').click()
	}
	waitFor{$('#permanentPeriodicTracking').value()==false}
	$('#assetVisibilityType',value:'none').click()
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset is unlocatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor{$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	}

def "Tracking Settings PPT:on VC:0"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	waitFor{$('#permanentPeriodicTracking').value()==false}
	$('#permanentPeriodicTracking').click()
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset locatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor{$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
	$('a',name:'btn_item_locate').click()
	waitFor{tooltip.displayed==true}
//	if($('button.ui-button-text-only').displayed){
//		$('button.ui-button-text-only').click()
//		waitFor{$('.locateSingleUser').displayed==false}
//		}
	
	and:"Create Report"
	$('.toolTipBottom li',1).click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	$('span.ui-button-text',text:"Create").click()
	waitFor {$('div#reportTopCover').displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	
	viewReport.click()
	waitFor {$('div#locReportListDiv').displayed==true}
	locReportUser.click()
	waitFor {locReportHist.displayed==true}
	locReportUser.click()

	and:"Go back to Asset Management page"
	$('#btn_map_clear').click()
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	}

def "Tracking Settings PPT:off VC:Company"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I change periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#permanentPeriodicTracking').value()=='true'}
	$('#permanentPeriodicTracking').click()
	$('#assetVisibilityType',value:'company').click()
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset is unlocatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor{$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}

def "Tracking Settings PPT:on VC:Company"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}

	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	waitFor{$('#permanentPeriodicTracking').value()==false}
	$('#permanentPeriodicTracking').click()
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset locatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
	$('a',name:'btn_item_locate').click()
	waitFor{tooltip.displayed==true}
//	if($('button.ui-button-text-only').displayed){
//		$('button.ui-button-text-only').click()
//		waitFor{$('.locateSingleUser').displayed==false}
//		}
	
	and:"Create Report"
	$('.toolTipBottom li',1).click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	waitFor{$('form#requestReportPermission').displayed==true}
	$('div.ui-dialog-buttonset button')[1].click()
	waitFor {$('div#reportTopCover').displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	$('div.buttonsForReport a.multi_user_button',1).click()
	waitFor{$('#locReportUserList').displayed==true}
	$('#locReportUserList').click()
	waitFor{$('#locReportUserList ul').displayed==true}
	
	and:"Go back to Asset Management page"
	$('#btn_map_clear').click()
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	}

def "Tracking Settings PPT:off VC:Custom"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I change periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#permanentPeriodicTracking').value()=='true'}
	$('#permanentPeriodicTracking').click()
	$('#assetVisibilityType',value:'permanent').click()
	waitFor{$('div#permanent').displayed==true}
	
	assetProfile[0].find('input.checkBox').click()
	assetProfile[0].find('.fromTime div input.inputText.time.hours')<<"08"
	assetProfile[0].find('.fromTime div input.inputText.time.mins')<<"00"
	assetProfile[0].find('.toTime div input.inputText.time.hours')<<"17"
	assetProfile[0].find('.toTime div input.inputText.time.mins')<<"00"
	
	assetProfile[1].find('input.checkBox').click()
	assetProfile[1].find('.fromTime div input.inputText.time.hours')<<"08"
	assetProfile[1].find('.fromTime div input.inputText.time.mins')<<"00"
	assetProfile[1].find('.toTime div input.inputText.time.hours')<<"17"
	assetProfile[1].find('.toTime div input.inputText.time.mins')<<"00"
	
	assetProfile[2].find('input.checkBox').click()
	assetProfile[2].find('.fromTime div input.inputText.time.hours')<<"08"
	assetProfile[2].find('.fromTime div input.inputText.time.mins')<<"00"
	assetProfile[2].find('.toTime div input.inputText.time.hours')<<"17"
	assetProfile[2].find('.toTime div input.inputText.time.mins')<<"00"
	
	assetProfile[3].find('input.checkBox').click()
	assetProfile[3].find('.fromTime div input.inputText.time.hours')<<"08"
	assetProfile[3].find('.fromTime div input.inputText.time.mins')<<"00"
	assetProfile[3].find('.toTime div input.inputText.time.hours')<<"17"
	assetProfile[3].find('.toTime div input.inputText.time.mins')<<"00"
	
	assetProfile[4].find('input.checkBox').click()
	assetProfile[4].find('.fromTime div input.inputText.time.hours')<<"08"
	assetProfile[4].find('.fromTime div input.inputText.time.mins')<<"00"
	assetProfile[4].find('.toTime div input.inputText.time.hours')<<"17"
	assetProfile[4].find('.toTime div input.inputText.time.mins')<<"00"
	
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset is unlocatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor{$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}

def "Tracking Settings PPT:on VC:Custom"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	waitFor{$('#permanentPeriodicTracking').value()==false}
	$('#permanentPeriodicTracking').click()
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset locatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
	$('a',name:'btn_item_locate').click()
	waitFor{tooltip.displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	
	and:"Create Report"
	$('.toolTipBottom li',1).click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	waitFor{$('form#requestReportPermission').displayed==true}
	$('span.ui-button-text',text:"Create").click()
	waitFor {$('div#reportTopCover').displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	$('div.buttonsForReport a.multi_user_button',1).click()
	waitFor{$('#locReportUserList').displayed==true}
	$('#locReportUserList').click()
	waitFor{$('#locReportUserList ul').displayed==true}
	
	and:"Go back to Asset Management page"
	$('#btn_map_clear').click()
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}

def "Tracking Settings PPT:on VC:Unlocatable Custom"(){
	given:"We are the Asset Tab"
	at AdminHomePage

	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	waitFor{$('#permanentPeriodicTracking').value()=='true'}
	
	assetProfile[0].find('input.checkBox').click()
	assetProfile[0].find('input.checkBox').click()
	assetProfile[0].find('.fromTime div input.inputText.time.hours')<<"01"
	assetProfile[0].find('.fromTime div input.inputText.time.mins')<<"00"
	assetProfile[0].find('.toTime div input.inputText.time.hours')<<"02"
	assetProfile[0].find('.toTime div input.inputText.time.mins')<<"00"
	assetProfile[1].find('input.checkBox').click()
	assetProfile[2].find('input.checkBox').click()
	assetProfile[3].find('input.checkBox').click()
	assetProfile[4].find('input.checkBox').click()
	
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset is unlocatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}
	
def "Tracking Settings PPT:off VC:Specific"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I change periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#permanentPeriodicTracking').value()=='true'}
	$('#permanentPeriodicTracking').click()
	$('#assetVisibilityType',value:'specific').click()
	waitFor{$('div#specific').displayed==true}

	$('#startTime')<<'08:00'
	$('#stopTime')<<'17:00'
	
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset is unlocatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}

def "Tracking Settings PPT:on VC:Specific"(){
	given:"We are the Asset Tab"
	at AdminHomePage

	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	waitFor{$('#permanentPeriodicTracking').value()==false}
	$('#permanentPeriodicTracking').click()
	sendDialog.click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Check if asset locatable"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'GroupTrackingTest').displayed==true}
	$('span.groupName',text:'GroupTrackingTest').click()
	$('div.container span',text:'AssetTrackingTest Model').click()
	waitFor{$('span',text:'AssetTrackingTest Model').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
	$('a',name:'btn_item_locate').click()
	waitFor{tooltip.displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}

	and:"Create Report"
	$('.toolTipBottom li',1).click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	waitFor{$('form#requestReportPermission').displayed==true}
	$('div.ui-dialog-buttonset button')[1].click()
	waitFor {$('div#reportTopCover').displayed==true}
	if($('span.ui-button-text').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	$('div.buttonsForReport a.multi_user_button',1).click()
	waitFor{$('#locReportUserList').displayed==true}
	$('#locReportUserList').click()
	waitFor{$('#locReportUserList ul').displayed==true}
	
	and:"Go back to Asset Management page"
	$('#btn_map_clear').click()
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}
@Ignore
def "Delete asset and group"(){
	given:"We are the Asset Tab"
	at AdminHomePage
	
	when:"I delete asset"
	$('td.name',text:'AssetTrackingTest').parent().find('a.btn_delete').click()

	then:"Delete dialog opens"
	waitFor{$('.noClose').displayed==true}
	$('#dialog').find('a.graphicBtn.violet').click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	and:"Delete Asset Group"
	groupMan.click()
	$('td.name',text:'GroupTrackingTest').parent().find('a.btn_delete').click()
	waitFor{$('.noClose').displayed==true}
	$('#dialog').find('a.graphicBtn.violet').click()
	waitFor {successDialog.displayed==true}
	waitFor {successDialog.displayed==false}
	
	}
}
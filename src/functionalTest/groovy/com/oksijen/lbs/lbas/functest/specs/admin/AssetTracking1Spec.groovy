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

import spock.lang.Specification

import com.oksijen.lbs.spock.extensions.retry.*

/**
 * 
 */
@Stepwise
class AssetTracking1Spec extends LocateSpec {
	@RetryOnFailure
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
@RetryOnFailure
def "Edit asset in the group"(){
	given:"We are the Asset Tab"
	if($('#admin').displayed==false){
		at WelcomePage
		adminBtn.click()
		waitFor { at AdminHomePage }
		assetMan.click()
		}
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
	if ($('#permanentPeriodicTracking').value()){
		$('#permanentPeriodicTracking').click()
	}
	waitFor{$('#permanentPeriodicTrackingFrequency-button').displayed==false}
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
	waitFor{$('.groupElementExpanded').find('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	}
@RetryOnFailure
def "Tracking Settings PPT:on VC:0"(){
	given:"We are the Asset Tab"
	if($('#admin').displayed==false){
		at WelcomePage
		adminBtn.click()
		waitFor { at AdminHomePage }
		assetMan.click()
		}
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	if ($('#permanentPeriodicTracking').value()==false){
		$('#permanentPeriodicTracking').click()
	}
	waitFor{$('#permanentPeriodicTrackingFrequency-button').displayed==true}
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
	waitFor{$('.groupElementExpanded').find('li.icon.locked.first').displayed==false}
	$('.groupElementExpanded').find('a',name:'btn_item_locate').click()
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
@RetryOnFailure
def "Tracking Settings PPT:off VC:Company"(){
	given:"We are the Asset Tab"
	if($('#admin').displayed==false){
		at WelcomePage
		adminBtn.click()
		waitFor { at AdminHomePage }
		assetMan.click()
		}
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}
	
	then:"I change periodic tracking"
	$('ul.tabs li')[1].click()
	if ($('#permanentPeriodicTracking').value()){
		$('#permanentPeriodicTracking').click()
	}
	waitFor{$('#permanentPeriodicTrackingFrequency-button').displayed==false}
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
	waitFor{$('.groupElementExpanded').find('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}

@RetryOnFailure
def "Tracking Settings PPT:on VC:Company"(){
	given:"We are the Asset Tab"
	if($('#admin').displayed==false){
		$('#btn_map_clear').click()
		at WelcomePage
		adminBtn.click()
		waitFor { at AdminHomePage }
		assetMan.click()
		}
	at AdminHomePage
	
	when:"I click Edit asset"
	$('tr').has('td.name',text:'AssetTrackingTest').find('a.btn-update').click()
	waitFor {$('.editAssetDialog').displayed==true}

	then:"I enable permanent periodic tracking"
	$('ul.tabs li')[1].click()
	waitFor{$('#assetTracking').displayed==true}
	if ($('#permanentPeriodicTracking').value()==false){
		$('#permanentPeriodicTracking').click()
	}
	waitFor{$('#permanentPeriodicTrackingFrequency-button').displayed==true}
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
	waitFor{$('.groupElementExpanded').find('li.icon.locked.first').displayed==false}
	$('.groupElementExpanded').find('a',name:'btn_item_locate').click()
	waitFor{tooltip.displayed==true}
//	if($('button.ui-button-text-only').displayed){
//		$('button.ui-button-text-only').click()
//		waitFor{$('.locateSingleUser').displayed==false}
//		}
	
	and:"Create Report"
	$('.toolTipBottom li',1).click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	waitFor{$('form#requestReportPermission').displayed==true}
	$('.locationReportRequestPermission').find('div.ui-dialog-buttonset button')[1].click()
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
}
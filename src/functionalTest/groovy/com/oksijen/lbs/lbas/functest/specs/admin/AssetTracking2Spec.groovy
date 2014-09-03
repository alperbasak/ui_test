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
class AssetTracking2Spec extends LocateSpec {

@RetryOnFailure
def "Tracking Settings PPT:off VC:Custom"(){
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
	
	then:"I change periodic tracking"
	$('ul.tabs li')[1].click()
	if ($('#permanentPeriodicTracking').value()){
		$('#permanentPeriodicTracking').click()
	}
	waitFor{$('#permanentPeriodicTrackingFrequency-button').displayed==false}
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
	waitFor{$('.groupElementExpanded').find('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}
@RetryOnFailure
def "Tracking Settings PPT:on VC:Custom"(){
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
@RetryOnFailure
def "Tracking Settings PPT:on VC:Unlocatable Custom"(){
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
	waitFor{$('.groupElementExpanded').find('li.icon.locked.first').displayed==true}
	
	and:"Go back to Asset Management page"
	adminBtn.click()
	waitFor { at AdminHomePage }
	assetMan.click()
	waitFor { rightPanel.find('h1').text().contains('Asset') }
	
	}
}
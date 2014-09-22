package com.oksijen.lbs.lbas.functest.specs.map.assets

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.*
import com.oksijen.lbs.lbas.functest.pages.admin.*
import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*

@Stepwise
class AssetTempSpec extends LocateSpec {

@RetryOnFailure
def "Create an asset and get location report within temporary hours"(){
	given:"We are at asset management page"
		at MapHomePage
		$('a#btn_admin').click()
		waitFor {at AdminHomePage}
		assetMan.click()
		at AdminHomePage
		
	when:"Add asset to a group"
		selectMenu.click()
		addAsset.click()
		apply.click()
		waitFor{assetDialog.displayed==true}
		assetName<<"TempAsset"
		assetModel<<"Test"
		assetAllocated<<"Alper"
		waitFor{$('ul.ui-autocomplete li').size()>0}
		$('li.ui-menu-item a.ui-corner-all')[0].click()
		$('#id_agps_enabled').click()		
		assetGroupMenu.click()
		assetGroup.click()
		
	then:"I enable permanent periodic tracking"
		$('ul.tabs li')[1].click()
		waitFor{$('#assetTracking').displayed==true}
		waitFor{$('#permanentPeriodicTracking').value()==false}
		$('#permanentPeriodicTracking').click()
		$('#assetVisibilityType',value:'specific').click()
		waitFor{$('#specific').displayed==true}
		$('#startTime')<<'08:30'
		$('#stopTime')<<'17:00'
		sendDialog.click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
	and:"Create report inside temporary hours"
	$('#btn_map').click()
	waitFor {at MapHomePage}
	$('#btn_tab-assets').click()
	$('#refreshAssets').click()
	waitFor {at AssetsPage}
	waitFor {$('span.groupName',text:'AA').displayed==true}
	$('span.groupName',text:'AA').click()
	$('div.container span',text:'TempAsset Test').click()
	waitFor{$('span',text:'TempAsset Test').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
	$('span.itemNameExpanded',text:'TempAsset Test').parent().find('.items_list_buttons li.first').click()
	waitFor{tooltip.displayed==true}
	$('span.itemNameExpanded',text:'TempAsset Test').parent().find('.items_list_buttons li.second').click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	waitFor{$('form#requestReportPermission').displayed==true}	
	$('div.ui-dialog-buttonset button')[1].click()
	waitFor {$('div#reportTopCover').displayed==true}
	if($('span.ui-button-text',text:'Cancel').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	$('div.buttonsForReport a.multi_user_button',1).click()
	waitFor{$('#locReportUserList').displayed==true}
	$('#locReportUserList').click()
	waitFor{$('#locReportUserList ul').displayed==true}
	$('#btn_map_clear').click()
	}
@RetryOnFailure
def "Get location report outside temporary hours"(){
	given:"We are at AssetPage"
		$('#refreshAssets').click()
		waitFor {at AssetsPage}
				
	when:"I create report"
	waitFor {$('span.groupName',text:'AA').displayed==true}
	$('span.groupName',text:'AA').click()
	$('div.container span',text:'TempAsset Test').click()
	waitFor{$('span',text:'TempAsset Test').parent().find('ul.item_details').displayed==true}
	waitFor{$('li.icon.locked.first').displayed==false}
		
	then:"Out of bounds hours will not create report"
	$('span.itemNameExpanded',text:'TempAsset Test').parent().find('.items_list_buttons li.first').click()
	waitFor{tooltip.displayed==true}
	$('span.itemNameExpanded',text:'TempAsset Test').parent().find('.items_list_buttons li.second').click()
	waitFor{$('.locationReportRequestPermission').displayed==true}
	waitFor{$('form#requestReportPermission').displayed==true}
	$('.part1 img.ui-datepicker-trigger').click()
	waitFor{$('#ui-datepicker-div').displayed==true}
	$('a.ui-datepicker-next').click()
	$('table.ui-datepicker-calendar tbody tr')[2].find('td a').click()
	$('.part2 img.ui-datepicker-trigger').click()
	waitFor{$('#ui-datepicker-div').displayed==true}
	$('a.ui-datepicker-next').click()
	$('table.ui-datepicker-calendar tbody tr')[2].find('td a').click()
	waitFor{$('#ui-datepicker-div').displayed==false}
	waitFor{$('#dialog').displayed==true}
	$('div.ui-dialog-buttonset button')[1].click()
	waitFor {$('div#reportTopCover').displayed==true}
	if($('span.ui-button-text',text:'Cancel').displayed){
		$('button.ui-button-text-only').click()
		waitFor{$('.locateSingleUser').displayed==false}
		}
	$('div.buttonsForReport a.multi_user_button',1).click()
	
	waitFor{$('.dialogError').displayed==true}
	$('.ui-button-text').click()
	waitFor{$('.dialogError').displayed==false}
	}
@RetryOnFailure
def "Delete asset"(){
		given:"We are at asset management page"
		$('a#btn_admin').click()
		waitFor {at AdminHomePage}
				
		when:"I edit working hours and delete asset"
		assetMan.click()
		at AdminHomePage
		$('td.name',text:'TempAsset').parent().find('a.btn-delete').click()
		
		then:"Delete dialog opens"
		waitFor{$('.noClose').displayed==true}
		$('#dialog').find('a.graphicBtn.violet').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		}	
	}

package com.oksijen.lbs.lbas.functest.specs.map.geofencing

import java.awt.Dialog;

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.gargoylesoftware.htmlunit.javascript.host.css.StyleAttributes;
import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.GeofencingPage
import com.oksijen.lbs.lbas.functest.pages.map.geofencing.*

@Stepwise
class GeofencesSpec extends LocateSpec {
	@Ignore
    def "Geofence drawing should be visible on map when I click on create"() {
    	given: "We are at the GeofencesPage"    	
    	at MapHomePage
    	geofencingTab.click()
    	waitFor {at GeofencingPage}
    	geofencesBtn.click()
    	waitFor {at GeofencesPage}
    	    	
        when: "I click 'Create New'"
        selectOption('geofenceActionList', '1')

        then: "Geofence drawing toolbar should be visible on map"
        waitFor('fast') { hasSLink($('div.gm-style'), 'Save Geofence') == true }
        waitFor('fast') { hasSLink($('div.gm-style'), 'Cancel') == true }

		and:"I click on rectangle and create a geofence"
		drawRect.click()
		interact{					///////////////NULL
			clickAndHold(canvas)
			moveByOffset(100,100)
			release()
		}		
		saveGeo.click()
		
		and:"New Geofence name dialog will open"
		waitFor {$('.addExcpPos').displayed==true}
		$('input#geofenceName') << params.get('geofenceName')
		$('a.send-button').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
    }
	
	def "I can show a geofence by clicking on the name"(){
		given: "We are at the GeofencesPage"
		at MapHomePage
		geofencingTab.click()
		waitFor {at GeofencingPage}
		geofencesBtn.click()
		waitFor {at GeofencesPage}
	
		when:"I click on a geofence name"
		$('ul#geofenceList li div a').click()
		
		then:"Geofence is displayed on the map"
		waitFor {canvas.find('canvas').displayed==true}
		waitFor {canvas.find('a.map_button').displayed==true}
		
		and:"When I click close, geofence is closed"
		canvas.find('a.map_button').click()
		waitFor {canvas.find('canvas').displayed==false}
		}

	@Ignore
	def "I can edit a geofence by clicking on a name"(){
		given: "We are at the GeofencesPage"
		at GeofencesPage
		
		when:"I click edit, geofence will be displayed on the map"
		$('ul#geofenceList').find('a.btn-update').click()
		waitFor {canvas.find('canvas').displayed==true}
		
		then:"I select an edge and change its location"
		interact{
		clickAndHold($("div[style~='width: 11px']").eq(0).children())			///////PROVIDE A LOCATION
		moveToElement($("div[style~='width: 11px']").eq(1).children())
		release()
		}
		saveGeo.click()
		
		and:"New Geofence name dialog will open"
		waitFor {$('.addExcpPos').displayed==true}
		$('input#geofenceName') << "-Edited"
		$('a.send-button').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		
		}
	@Ignore
	def "Delete a geofence"(){
		given: "We are at the GeofencesPage"
		at GeofencesPage
		
		when:"I click delete, geofence will be deleted from the map"
		$('ul#geofenceList li',3).find('a.btn-delete').click()
		
		then:"Confirmation dialog opens"
		waitFor {$('.confirmation ').displayed==true}
		$('#dialog div a').click()
		waitFor {successDialog.displayed==true}
		waitFor {successDialog.displayed==false}
		}
	
	def "Display multiple geofences on map"(){
		given: "We are at the GeofencesPage"
		at GeofencesPage
		
		when:"I select multiple geofences and click show on map from actionlist"
		waitFor {$('ul#geofenceList li').size()>3}
		$('ul#geofenceList li',0).find('input.check-box').click()
		$('ul#geofenceList li',1).find('input.check-box').click()
		$('ul#geofenceList li',2).find('input.check-box').click()
		selectOption('geofenceActionList', '3')
		
		then:"Geofence is displayed on the map"
		waitFor {canvas.find('canvas').displayed==true}
		waitFor {canvas.find('a.map_button').displayed==true}
		
		and:"When I click close, geofence is closed"
		canvas.find('a.map_button').click()
		waitFor {canvas.find('canvas').displayed==false}
	
		}
	}

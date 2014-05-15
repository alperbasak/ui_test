package com.oksijen.lbs.lbas.functest.specs.map.places

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec

import com.oksijen.lbs.lbas.functest.pages.map.MapHomePage
import com.oksijen.lbs.lbas.functest.pages.map.GeofencingPage
import com.oksijen.lbs.lbas.functest.pages.map.geofencing.*

@Stepwise
class GeofencesSpec extends LocateSpec {
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
        //expect hasButton($('div.gm-style'), 'Cancel'), is(true)
        //expect hasButton($('div.gm-style'), 'Save Geofence'), is(true)
    }
}

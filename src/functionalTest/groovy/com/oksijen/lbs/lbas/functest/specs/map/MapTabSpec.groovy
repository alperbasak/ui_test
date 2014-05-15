package com.oksijen.lbs.lbas.functest.specs.map

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*

/**
 * Created by cpekyaman on 3/25/2014.
 */
@Stepwise
class MapTabSpec extends LocateSpec {
    def "Assets should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click assets link"
        assetsTab.click()

        then: "Assets tab should be rendered"
        waitFor {at AssetsPage}
    }
    
    def "Places should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click places link"
        placesTab.click()

        then: "Assets tab should be rendered"
        waitFor {at PlacesPage}
    }
    
    def "Users should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click users link"
        usersTab.click()

        then: "Users tab should be rendered"
        waitFor {at UsersPage}
    }
    
    def "Routes should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click routes link"
        routesTab.click()

        then: "Routes tab should be rendered"
        waitFor {at RoutesPage}
    }
    
    def "Geofencing should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click geofencing link"
        geofencingTab.click()

        then: "Geofencing tab should be rendered"
        waitFor {at GeofencingPage}
    }
}

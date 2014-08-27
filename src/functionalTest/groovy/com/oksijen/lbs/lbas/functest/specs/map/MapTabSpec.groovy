package com.oksijen.lbs.lbas.functest.specs.map

import spock.lang.*
import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import com.oksijen.lbs.lbas.functest.specs.LocateSpec
import com.oksijen.lbs.lbas.functest.pages.WelcomePage
import com.oksijen.lbs.lbas.functest.pages.map.*

import spock.lang.Specification
import com.oksijen.lbs.spock.extensions.retry.*
/**
 * Created by cpekyaman on 3/25/2014.
 */
@Stepwise
class MapTabSpec extends LocateSpec {
	@RetryOnFailure(times=5)
    def "Assets should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click assets link"
        assetsTab.click()

        then: "Assets tab should be rendered"
        waitFor {at AssetsPage}
    }
	@RetryOnFailure(times=5)
    def "Places should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click places link"
        placesTab.click()

        then: "Places tab should be rendered"
        waitFor {at PlacesPage}
    }
	@RetryOnFailure(times=5)
    def "Users should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click users link"
        usersTab.click()

        then: "Users tab should be rendered"
        waitFor {at UsersPage}
    }
	@RetryOnFailure(times=5)
    def "Routes should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click routes link"
        routesTab.click()

        then: "Routes tab should be rendered"
        waitFor {at RoutesPage}
    }
	@RetryOnFailure(times=5)
    def "Geofencing should be rendered when tab is clicked"() {
    	given: "We are at the MapHomePage"
    	at MapHomePage
    	
        when: "I click geofencing link"
        geofencingTab.click()

        then: "Geofencing tab should be rendered"
        waitFor {at GeofencingPage}
    }
}

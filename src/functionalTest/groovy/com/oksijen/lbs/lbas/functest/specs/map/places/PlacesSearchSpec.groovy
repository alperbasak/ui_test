package com.oksijen.lbs.lbas.functest.specs.map.places

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
class PlacesSearchSpec extends LocateSpec {
    def "I should be able to search with autocomplete search input"() {
    	given: "We are at the PlacesPage"    	
    	at MapHomePage
    	placesTab.click()
    	waitFor {at PlacesPage}
    	    	
        when: "I enter searchtext"
        searchInput << params.get('placesSearch.searchInput')

        then: "Autocomplete list should be visible"    
        waitFor('fast') { autocompleteList.displayed == true }
        expect autocompleteListItems.size(), greaterThan(0)
        
        and: "Tooltip should be shown on map"
        clickFirstItem(autocompleteListItems)
        waitFor('fast') { tooltip.displayed == true }
        
        and: "There must be links visible on tooltip"
        expect hasLink(tooltip, 'Show Nearest Users'), is(true)
        expect hasLink(tooltip, 'Save Place'), is(true)
        expect hasLink(tooltip, 'Get Directions'), is(true)
        expect hasLink(tooltip, 'Setup Meeting'), is(true)
        
        and: "Tooltip should be closed when close button is clicked"
        expect tooltipClose.displayed, is(true)
        tooltipClose.click()
        expect tooltip.displayed, is(false)
    }
}

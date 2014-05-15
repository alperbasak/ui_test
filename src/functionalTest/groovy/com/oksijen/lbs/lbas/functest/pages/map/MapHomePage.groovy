package com.oksijen.lbs.lbas.functest.pages.map

import com.oksijen.lbs.lbas.functest.pages.WelcomePage

/**
 * Created by cpekyaman on 3/25/2014.
 */
class MapHomePage extends WelcomePage {
    static url = "welcome"
    static at = { mapDiv.displayed == true }

    static content = {
        mapDiv(required: true) 		{ $("div#map") }
        leftPanel(required: true) 	{ $("div#left") }
        rightPanel(required: true) 	{ $("div#right") }
        
        assetsTab(required: true) 		{ $('a#btn_tab-assets') }
        usersTab(required: true) 		{ $('a#btn_tab-users') }
        placesTab(required: true) 		{ $('a#btn_tab-places') }
        routesTab(required: true) 		{ $('a#btn_tab-routes') }
        geofencingTab(required: true) 	{ $('a#btn_tab-geofencing') }
        
        locationBtn(required: true)	{ $('a#btn_map_myLocation') }
        clearBtn(required: true)	{ $('a#btn_map_clear') }
    }
}

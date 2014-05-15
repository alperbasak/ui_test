package com.oksijen.lbs.lbas.functest.pages.map

/**
 * Created by cpekyaman on 3/25/2014.
 */
class GeofencingPage extends MapHomePage {
    static url = "welcome"
    static at = { searchInput.displayed == true }

    static content = {
    	link(required: true) 			{ $('a#btn_tab-geofencing') }
        searchInput(required: true) 	{ $("input#search_alarms") }
        
        header(required: true)			{ $("div.subtabsCover") }
        contents(required: true)		{ $("div.subtabs") }
        
        alarmsBtn(required: true)		{ $("a#btn_tab-geofencing-alarms") }
        alarms(required: true)			{ $("div#tab-geofencing-alarms") }        
        geofencesBtn(required: true) 	{ $("a#btn_tab-geofencing-geofences") }
        geofences(required: true) 		{ $("div#tab-geofencing-geofences") }
    }
}

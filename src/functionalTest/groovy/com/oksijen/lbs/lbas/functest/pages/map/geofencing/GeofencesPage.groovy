package com.oksijen.lbs.lbas.functest.pages.map.geofencing

import com.oksijen.lbs.lbas.functest.pages.map.GeofencingPage

class GeofencesPage extends GeofencingPage {
    static url = "welcome"
    static at = { geofenceActionsBtn.displayed == true }

    static content = {        
        geofenceActions(required: true) 	{ $("select#geofenceActionList") }
        geofenceActionsBtn(required: true) 	{ $("a#geofenceActionList-button") }
        geofenceActionsMenu(required: true) { $("ul#geofenceActionList-menu") }
    }
}

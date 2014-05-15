package com.oksijen.lbs.lbas.functest.pages.map.geofencing

import com.oksijen.lbs.lbas.functest.pages.map.GeofencingPage

class AlarmsPage extends GeofencingPage {
    static url = "welcome"
    static at = { alarmActionsBtn.displayed == true }

    static content = {        
        alarmActions(required: true) 		{ $("select#alarmActionList") }
        alarmActionsBtn(required: true)		{ $("a#alarmActionList-button") }
        alarmActionsMenu(required: true) 	{ $("ul#geofenceActionList-menu") }
    }
}

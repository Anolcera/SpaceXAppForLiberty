package com.whichonespink.spacex.presentation.navigation

sealed class SpaceXScreens(val route: String){
    object SlideShowScreen: SpaceXScreens(route = "slide_show_screen")
    object MissionsScreen: SpaceXScreens(route = "missions_screen/" +
            "{$MISSIONS_NAV_ARG}")
    {
        fun passMissionsAsArgs(
            missions: String
        ) : String {
            return "missions_screen/$missions"
        }
    }
}

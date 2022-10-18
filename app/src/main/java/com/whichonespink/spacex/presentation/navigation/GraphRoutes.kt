package com.whichonespink.spacex.presentation.navigation

sealed class GraphRoutes(val route: String){
    object RootGraphRoute: GraphRoutes(route = "root_graph")
}

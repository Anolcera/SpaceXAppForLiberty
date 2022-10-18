package com.whichonespink.spacex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.whichonespink.spacex.presentation.missions_view.MissionsScreen
import com.whichonespink.spacex.presentation.slide_show.ShipsSlideShowScreen

@Composable
fun SpaceXNavGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        route = GraphRoutes.RootGraphRoute.route,
        startDestination = SpaceXScreens.SlideShowScreen.route
    ){
        composable(
            route = SpaceXScreens.SlideShowScreen.route
        ){
            ShipsSlideShowScreen(navController = navHostController)
        }

        composable(
            route = SpaceXScreens.MissionsScreen.route,
            arguments = listOf(
                navArgument(MISSIONS_NAV_ARG){
                    type = NavType.StringType
                }
            )
        ){
            MissionsScreen(navController = navHostController)
        }
    }
}
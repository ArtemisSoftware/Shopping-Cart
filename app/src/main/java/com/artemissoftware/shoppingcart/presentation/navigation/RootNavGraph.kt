package com.artemissoftware.shoppingcart.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.shoppingcart.presentation.cart.CartScreen

private const val ROOT_GRAPH = "root_graph"

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH,
        startDestination = startDestination,
    ) {

        composable(route = Route.Cart.getFullRoute()) {
            CartScreen(
                navigateToSearchProduct = {

                }
            )
        }

    }
}

sealed class Route(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
) : BaseDestination(
    route = route,
    arguments = arguments,
) {
    object Cart : Route(route = "cart")

//    data object Image : RandomImageRoute(
//        route = "random_image",
//        arguments = listOf(
//            navArgument(
//                name = NavArguments.RANDOM_IMAGE,
//            ) {
//                type = ImageNavType()
//            },
//        ),
//    )
}
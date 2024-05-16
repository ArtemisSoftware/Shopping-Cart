package com.artemissoftware.shoppingcart.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artemissoftware.shoppingcart.presentation.addproduct.AddProductScreen
import com.artemissoftware.shoppingcart.presentation.cart.CartScreen
import com.artemissoftware.shoppingcart.presentation.details.DetailsScreen
import com.artemissoftware.shoppingcart.presentation.searchproduct.SearchProductScreen

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
                    navController.navigate(Route.Search.getFullRoute())
                },
                navigateToDetail = {
                    navController.navigate(Route.Detail.withCustomArgs(it))
                }
            )
        }

        composable(route = Route.Search.getFullRoute()) {
            SearchProductScreen(
                onPopBackStack = {
                    navController.popBackStack()
                },
                navigateToAddProduct = {
                    navController.navigate(Route.AddProduct.withCustomArgs(it))
                }
            )
        }

        composable(
            route = Route.AddProduct.getFullRoute(),
            arguments = Route.AddProduct.arguments,
        ) {
            AddProductScreen(
                onPopBackStack = {
                    navController.popBackStack()
                },
                navigateToSearchProduct = {
                    navController.navigate(Route.Search.getFullRoute())
                }
            )
        }

        composable(
            route = Route.Detail.getFullRoute(),
            arguments = Route.Detail.arguments,
        ) {
            DetailsScreen(
                onPopBackStack = {
                    navController.popBackStack()
                },
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

    object Search : Route(route = "search")

    object AddProduct : Route(
        route = "add_product",
        arguments = listOf(
            navArgument(
                name = NavArguments.PRODUCT_ID,
            ) {
                nullable = false
                type = NavType.IntType
            }
        )
    )

    object Detail : Route(
        route = "detail",
        arguments = listOf(
            navArgument(
                name = NavArguments.PRODUCT_ID,
            ) {
                nullable = false
                type = NavType.IntType
            }
        )
    )
}
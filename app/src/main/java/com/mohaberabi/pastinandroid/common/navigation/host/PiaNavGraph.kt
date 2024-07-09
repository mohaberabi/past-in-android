package com.mohaberabi.pastinandroid.common.navigation.host

import androidx.annotation.IdRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mohaberabi.pastinandroid.common.navigation.routes.PiaRoutes
import com.mohaberabi.pastinandroid.foryou.presentation.screen.ForYouScreenRoot
import com.mohaberabi.pastinandroid.presentation.interest_details.screen.InterestDetailScreenRoot
import com.mohaberabi.pastinandroid.presentation.interest_details.viewmodel.InterestNavConst
import com.mohaberabi.pastinandroid.presentation.interest_listing.screen.InterestScreenRoot
import com.mohaberabi.pastinandroid.presentation.screen.SavedNewsScreenRoot
import com.mohaberabi.pastinandroid.search.presentation.screen.SearchScreenRoot


@Composable
fun PiaNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onShowSnackBar: (String) -> Unit,

    ) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PiaRoutes.FOR_YOU_LAYOUT
    ) {


        composable(PiaRoutes.FOR_YOU_LAYOUT) {
            ForYouScreenRoot(
                onShowSnackBar = onShowSnackBar,
            )
        }
        composable(PiaRoutes.SAVED_LAYOUT) {
            SavedNewsScreenRoot()
        }

        composable(PiaRoutes.SEARCH_LAYOUT) {
            SearchScreenRoot(
                onBackClick = {
                    navController.popBackStack()
                },
                onGoInterestDetails = {
                    navController.navigateToDetails(it)

                },
            )

        }

        composable(
            route = "${InterestNavConst.DETAIL_ROUTE}/{${InterestNavConst.INTEREST_ID_ARG}}",
            arguments = listOf(
                navArgument(InterestNavConst.INTEREST_ID_ARG) {
                    type = NavType.StringType
                }
            )
        ) {
            InterestDetailScreenRoot(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
        composable(PiaRoutes.INTERESTS_LAYOUT) {

            InterestScreenRoot {
                navController.navigateToDetails(it)
            }
        }


    }

}


internal fun NavHostController.navigateToDetails(id: String) =
    navigate("${InterestNavConst.DETAIL_ROUTE}/${id}")

internal fun NavHostController.navigateAndPopUpTo(
    restoreState: Boolean = true,
    inclusive: Boolean = true,
    saveState: Boolean = true,
    to: String,
    upTo: String,
) {
    navigate(to) {
        popUpTo(upTo) {
            this.inclusive = inclusive
            this.saveState = saveState
        }
        this.restoreState = restoreState
    }

}
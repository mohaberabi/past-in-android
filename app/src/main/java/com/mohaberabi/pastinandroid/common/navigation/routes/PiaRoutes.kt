package com.mohaberabi.pastinandroid.common.navigation.routes


sealed class PiaRoutes(val name: String) {
    data object SearchRoute : PiaRoutes("search")


    companion object {
        const val FOR_YOU_LAYOUT = "forYouLayout"
        const val SAVED_LAYOUT = "savedLayout"
        const val INTERESTS_LAYOUT = "intersetsLayout"
        const val SEARCH_LAYOUT = "searchLayout"
    }

}


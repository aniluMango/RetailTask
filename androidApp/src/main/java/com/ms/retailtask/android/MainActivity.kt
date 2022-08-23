
package com.ms.retailtask.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ms.retailtask.android.ui.theme.RetailTaskTheme
import com.ms.retailtask.android.ui.theme.backWindowD

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Update the system bars to be translucent
            val systemUiController = rememberSystemUiController()
            val useDarkIcons: Boolean
            val color = if (isSystemInDarkTheme()) {
                useDarkIcons = false
                backWindowD
            } else {
                useDarkIcons = true
                Color.White
            }

            SideEffect {
                systemUiController.setSystemBarsColor(color, darkIcons = useDarkIcons)
            }
            val navController = rememberNavController()


            RetailTaskTheme {
                NavHost(navController = navController, startDestination = "Main") {
                    composable("Main") {     ShowTabs(navController) }
                    composable("Details/{modelID}") {  backStackEntry ->
                        DetailsUI(navController, backStackEntry.arguments?.getString("modelID")) }
                    /*...*/
                }


            }
        }
    }

}




@Composable
fun ShowProgressBar() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colors.secondary
        )
    }

}



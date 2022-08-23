package com.ms.retailtask.android

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ms.retailtask.Constant
import com.ms.retailtask.android.ui.theme.*
import kotlinx.coroutines.launch
import model.RetailTaskModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowTaskItems(index: Int, model: RetailTaskModel, navHostController: NavHostController?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        backgroundColor = if (isSystemInDarkTheme()) {
            cardDark
        } else {
            Color.White
        },
        elevation = 2.dp,
        onClick = { navHostController?.navigate("Details/${model.id}")  }
    ) {
        val color = when (model.priority) {
            Constant.urgent -> {
                Color.Red
            }
            Constant.critical -> {
                Orange
            }
            else -> {
                Skye
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .background(color = color)
                    .fillMaxHeight()
                    .width(5.dp)
            )

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopStart)
            ) {

                Row {


                    Text(
                        text = model.priority,

                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .background(color.copy(0.10f), RoundedCornerShape(10.dp))
                            .padding(vertical = 1.dp, horizontal = 4.dp),
                        color = color,
                        fontSize = 12.sp

                    )

                    Text(
                        text = model.name,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 5.dp),
                        color = MaterialTheme.colors.secondaryVariant,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Row {
                    //
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        tint = Color.Gray,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "Mon,Jul 12 - Sun, Jul 18 · Due: Jul 19",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .align(Alignment.CenterVertically),
                        color = Color.Gray,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }

                Row {
                    //
                    val annotatedString = buildAnnotatedString {
                        append("5 Items")
                        append(" · ")
                        withStyle(style = SpanStyle(Skye)) {
                            append(" Marketing ")
                        }
                        append(" · ")
                        append("T100$index")
                    }
                    Text(
                        text = annotatedString,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 5.dp)
                            .align(Alignment.CenterVertically),
                        color = Color.Gray,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                }

            }
            if (index % 2 == 0) {
                Text(
                    text = "new".uppercase(),
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 5.dp)
                        .background(Skye, RoundedCornerShape(3.dp))
                        .padding(vertical = 1.dp, horizontal = 2.dp)
                        .align(Alignment.TopEnd),
                    fontSize = 8.sp
                )
            }

        }
    }
}

@Composable
fun ShowList(navHostController: NavHostController) {
    val viewModel: TaskViewModel = viewModel()
    val model = remember {
        viewModel
    }
    val value = model.uiState.collectAsState()


    when (value.value) {
        is TaskState.Process -> {
            ShowProgressBar()
        }
        is TaskState.Success -> {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item {
                    Text(
                        text = "Today's Priorities: Sunday Jun 4th ",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondaryVariant
                        ),
                        modifier = Modifier.padding(10.dp, 15.dp),

                        )

                }

                itemsIndexed((value.value as TaskState.Success).list) { index, model ->
                    ShowTaskItems(index, model,navHostController)
                }

                item {
                    Text(
                        text = "OverDue (10)",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Red
                        ),
                        modifier = Modifier.padding(10.dp, 15.dp)
                    )
                }

                itemsIndexed((value.value as TaskState.Success).list) { index, model ->
                    ShowTaskItems(index, model, navHostController)
                }
            }

        }
        else -> {
            Text(
                text = "Empty View ",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShowTabs(navController: NavHostController) {
    val list = listOf(TabItem.MyTask, TabItem.MyStoreTask)
    val pagerState = rememberPagerState(initialPage = 0)
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Operation",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = MaterialTheme.colors.secondaryVariant
                        ),

                        )

                }

            }, actions = {
                val current = LocalContext.current
                Icon(
                    imageVector = Icons.Rounded.Search, "",
                    modifier = Modifier
                        .padding(10.dp)
                        .background(
                            MaterialTheme.colors.secondaryVariant.copy(0.1f),
                            CircleShape
                        )
                        .padding(5.dp)
                        .clickable {
                            Toast.makeText(current,"Search", Toast.LENGTH_SHORT).show()

                        }
                    ,
                    tint = MaterialTheme.colors.secondaryVariant,
                )
            }
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Tabs(tabs = list, pagerState = pagerState)
                TabContent(tabs = list, pagerState = pagerState,navController)
            }

        },
        backgroundColor = if (isSystemInDarkTheme()) {
            backWindowD
        } else {
            backWindow
        }

    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.secondary
    ) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(tabItem.title) },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = Color.Gray,
                enabled = true,
            )

        }


    }


}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screens(navController)
    }
}


typealias ComposableFun = @Composable (navController: NavHostController) -> Unit

sealed class TabItem(val title: String, val screens: ComposableFun) {

    object MyTask : TabItem(title = "My Task", screens = { ShowList(it) })
    object MyStoreTask : TabItem(title = "My Store Task", screens = { ShowList(it) })


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetailTaskTheme {
        ShowTaskItems(
            1,
            RetailTaskModel("1", "Task Retails Title", 20, Constant.urgent),
            null

        )
    }
}
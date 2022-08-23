package com.ms.retailtask.android

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ms.retailtask.Constant
import com.ms.retailtask.RetailsTaskRepo
import com.ms.retailtask.android.ui.theme.Orange
import com.ms.retailtask.android.ui.theme.Skye
import com.ms.retailtask.android.ui.theme.backWindow
import com.ms.retailtask.android.ui.theme.backWindowD
import model.RetailTaskModel

@Composable
fun DetailsUI(navController: NavHostController?, string: String?) {
    val model = RetailsTaskRepo.getTaskByID(string)
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "T000${model.id}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.secondaryVariant
                        ),
                    )

                }

            }, actions = {
                val current = LocalContext.current
                Row {
                    Text(
                        text = "3",
                        color = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier.align(Alignment.CenterVertically),

                        )
                    Icon(
                        imageVector = Icons.Rounded.List, "",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                Toast
                                    .makeText(current, "Search", Toast.LENGTH_SHORT)
                                    .show()

                            },
                        tint = MaterialTheme.colors.secondaryVariant,
                    )
                }

            },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack, "",
                        tint = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                navController?.popBackStack()
                            }
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            DetailsView(padding, model, scrollState)
        },
        backgroundColor = if (isSystemInDarkTheme()) {
            backWindowD
        } else {
            backWindow
        }
    )

}

@Composable
fun DetailsView(padding: PaddingValues, model: RetailTaskModel, scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 10.dp)
            .verticalScroll(state = scrollState),
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
        Spacer(modifier = Modifier.height(10.dp))

        Card(
            elevation = 2.dp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                Text(
                    text = model.name,
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    color = MaterialTheme.colors.secondaryVariant,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontWeight = FontWeight.SemiBold
                )
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
                    val annotatedString = buildAnnotatedString {

                        append(" · ")
                        withStyle(style = SpanStyle(Skye)) {
                            append(" Marketing ")
                        }
                        append(" · ")
                        append("Jul 12 - Jul 19")
                        append(" · ")
                        append("Due Jul 23")
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
        }

        Card(
            elevation = 2.dp, modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight()

        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    text = "As per reports, with the new update, WhatsApp users will let people view their contacts' statuses through the chat list directly, as opposed to going to the separate Status — this can be done by just tapping on the contact's profile photo, a report suggested.\n" +
                            "As per the report, the feature is in beta testing only on the Android platform and will roll out the final version in a phased manner.\n" +
                            "Earlier, Facebook announced that it would allow users to create Reels from Stories that they have already shared. Further, Meta introduced ‘Add Yours’, an interactive feature for Reels on Instagram and Facebook, to enhance engagement."
                )
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://static.fibre2fashion.com/newsresource/images/275/shutterstock-498932230_286830.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )

                Text(
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    text = "As per reports, with the new update, WhatsApp users will let people view their contacts' statuses through the chat list directly, as opposed to going to the separate Status — this can be done by just tapping on the contact's profile photo, a report suggested.\n" +
                            "As per the report, the feature is in beta testing only on the Android platform and will roll out the final version in a phased manner.\n"
                )

                CartList()

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    text = "Once you have completed all the tasks in the brif please talk to manager about the next steps. this is the end of the message.\n"
                )


            }

        }

        AttachmentUI()


        ItemsList()
    }
}

@Composable
fun ItemsList() {
    Card(
        elevation = 2.dp, modifier = Modifier

            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colors.secondaryVariant,
                text = "Items (3)",
                fontWeight = FontWeight.SemiBold
            )
            Divider(color = Color.Gray.copy(0.6f))

            Row {
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .background(Color.Gray.copy(0.4f), shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 5.dp),
                    color = MaterialTheme.colors.secondaryVariant,
                    text = "1",
                    fontWeight = FontWeight.SemiBold,

                    )
                Text(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.8f),
                    text = "Validated Barcodes",
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                text = "Received new bar code. if the barcode do not look like the image below, contact store support immediate"
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://www.kindpng.com/picc/m/77-771331_barcode-transparent-png-ticket-barcode-transparent-png-download.png")
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

            Divider(color = Color.Gray.copy(0.6f))
            Row {
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .background(Color.Gray.copy(0.4f), shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 5.dp),
                    color = MaterialTheme.colors.secondaryVariant,
                    text = "2",
                    fontWeight = FontWeight.SemiBold,

                    )
                Text(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.8f),
                    text = "Execute Re-pricing",
                    fontWeight = FontWeight.SemiBold
                )

            }
            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                text = "· Received new bar code. "
            )
            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                text = "· if the barcode do not look like the image below"
            )
            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                text = "· Contact store support immediate "
            )

            Button(
                onClick = {},
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Skye),
                modifier = Modifier.padding(top = 5.dp)

            ) {

                Text("Mark as Completed", color = Color.White)
                    
            }
        }
    }

}

@Composable
fun AttachmentUI() {

    Card(
        elevation = 2.dp, modifier = Modifier

            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.8f),
                text = "Attachment",
                fontWeight = FontWeight.SemiBold
            )
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    tint = Skye
                )
                Text(text = "Barcode Specific.pdf", color = Skye)
            }
            Row {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "",
                    tint = Skye
                )
                Text(text = "Latest Edittion.pdf", color = Skye)
            }

            Spacer(modifier = Modifier.height(10.dp))
        }


    }

}

@Composable
private fun CartList() {
    LazyRow(contentPadding = PaddingValues(horizontal = 10.dp)) {
        item {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 5.dp)
                    .width(120.dp)
                    .height(150.dp)
                    .border(1.dp, color = Color.Gray)
            ) {
                Text(
                    text = "#001",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(5.dp)
                )
                AsyncImage(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://the-collective.imgix.net/img/app/product/6/661233-6759026.jpg?w=1600&auto=format")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "A45ACE1132",
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(5.dp),
                    fontSize = 12.sp
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width(120.dp)
                    .height(150.dp)
                    .border(1.dp, color = Color.Gray)
            ) {
                Text(
                    text = "#001",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(5.dp)
                )
                AsyncImage(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://5.imimg.com/data5/UV/UD/MY-52691782/mens-cotton-pant-500x500.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "A45ACE1132",
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(5.dp),
                    fontSize = 12.sp
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width(120.dp)
                    .height(150.dp)
                    .border(1.dp, color = Color.Gray)
            ) {
                Text(
                    text = "#001",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(5.dp)
                )
                AsyncImage(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://contents.mediadecathlon.com/p1484240/ab565f3675dbdd7e3c486175e2c16583/p1484240.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "A45ACE1132",
                    color = MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(5.dp),
                    fontSize = 12.sp
                )

                AsyncImage(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://similarpng.com/barcode-scan-bar-label-qr-code-and-industrial-barcode-on-transparent-background-png/")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Preview(heightDp = 2000)
@Composable
fun Preview() {
    DetailsUI(null, "2")
}
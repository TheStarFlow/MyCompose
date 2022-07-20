package com.zzs.mycompose

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.zzs.mycompose.provider.MsgCardObj
import com.zzs.mycompose.ui.theme.MyComposeTheme

class BasicComponnetActivity : ComponentActivity() {

    private val data = MsgCard("罗贯中", "三国演义")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                Column {
                    MessageCard(data)
                    ListCard(MsgCardObj.msgs)
                    MyAlertDialog()
                }
            }
        }
    }


    @Composable
    fun MessageCard(card: MsgCard) {
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) Color(0XFFCCCCCC) else MaterialTheme.colors.surface
        )
        MyComposeTheme {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 5.dp,
                modifier = Modifier.padding(8.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    },
                color = surfaceColor
            ) {
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painterResource(R.mipmap.zhouzi),
                        contentDescription = "zhouzi",
                        modifier = Modifier.size(50.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondary, shape = CircleShape)
                    )
                    Spacer(Modifier.padding(horizontal = 8.dp))
                    Column {
                        Text(
                            "${card.author}",
                            color = MaterialTheme.colors.primaryVariant,
                            style = MaterialTheme.typography.subtitle2,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                            modifier = Modifier.animateContentSize()
                        )
                        Spacer(Modifier.padding(4.dp))
                        Text(
                            "${card.name}", color = Color.Gray,
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }
                }
            }
        }
        if (isExpanded) {
            MyDialog()
        }
    }

    @Preview
    @Composable
    fun PreviewMyCard() {
        MessageCard(data)
    }

    data class MsgCard(val name: String, val author: String)


    // @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewDarkMode() {
        MyComposeTheme {
            MessageCard(data)
        }
    }

    @Composable
    fun ListCard(msgs: List<MsgCard>) {
        LazyColumn {
            items(msgs) { messsage ->
                MessageCard(card = messsage)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewListCard() {
        ListCard(MsgCardObj.msgs)
    }

    @Preview
    @Composable
    fun PreviewMyDialog() {
        MyAlertDialog()
    }

    @Composable
    fun MyAlertDialog() {
        var open by remember { mutableStateOf(true) }
        if (open) {
            AlertDialog(
                onDismissRequest = {
                    open = false
                },
                title = {
                    Text(
                        "开启位置服务",
                        fontWeight = FontWeight.W600,
                        style = MaterialTheme.typography.h6,
                        fontSize = 24f.sp
                    )
                },
                text = {
                    Text(
                        "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息",
                        fontSize = 16.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            open = false
                        }
                    ) {
                        Text(
                            "确认",
                            fontWeight = FontWeight.W700,
                            style = MaterialTheme.typography.button
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            open = false
                        }
                    ) {
                        Text(
                            "取消",
                            fontWeight = FontWeight.W700,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            )
        }
    }

    @Composable
    fun MyDialog() {
        val open = remember { mutableStateOf(true) }
        if (open.value) {
            Dialog(
                onDismissRequest = {
                    open.value = false
                },
                content = {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(0.dp)
                            .width(320.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.LightGray),
                        elevation = 16.dp,
                        contentColor = Color.Black
                    ) {
                        Image(
                            painter = painterResource(R.mipmap.wallpaper),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(53.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0X80FFFFFF))
                                    .clickable {
                                        startActivity(
                                            Intent(
                                                this@BasicComponnetActivity,
                                                LayoutComponentsActivity::class.java
                                            )
                                        )
                                    },//backgrounk 在clip前面的话会切不到圆角
                            ) {
                                Text(
                                    "这是一个Card", modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                tint = Color.LightGray,
                                modifier = Modifier.clickable {
                                    open.value = false
                                }
                                    .size(32.dp)
                                    .align(Alignment.TopEnd)
                            )
                        }
                    }
                },
                properties = DialogProperties(
                    dismissOnClickOutside = false
                )
            )
        }
    }

    data class ButtonState(var text: String, var textColor: Color, var buttonColor: Color)

    @Composable
    fun ScaffoldDemo() {
        MyComposeTheme {
            Scaffold {

            }
        }
    }


}
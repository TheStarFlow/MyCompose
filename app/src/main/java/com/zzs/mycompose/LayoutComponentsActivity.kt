package com.zzs.mycompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzs.mycompose.ui.theme.MyComposeTheme

class LayoutComponentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScaffold()
        }
    }

    @Composable
    fun MyScaffold() {
        MyComposeTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    finish()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        },
                        title = {
                            Text("布局组件界面")
                        },
                    )
                },
                modifier = Modifier.padding(top = 12.dp)
            ) {
               Body()
            }
        }
    }

    @Preview
    @Composable
    fun Body(){
        Text(
            "布局组件界面body",
            modifier = Modifier.padding(top = 8.dp)
        )
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.padding(top = 48.dp).fillMaxWidth()
        ) {
            Button(
                onClick = {

                }
            ){
                Text("点击唤起分享弹框")
            }
        }
    }

    override fun onBackPressed() {

    }
}
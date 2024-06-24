package com.dhruv.datarendering

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: ExampleDataViewModel,
) {
    val currentName by vm.currentName.collectAsState()

    Scaffold {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                currentName?.let { name ->
                    TextRender(
                        text = name.name,
                        onNextClick = { vm.nextName() },
                        onPreviousClick = { vm.previousName() }
                    )
                }
            }
        }
    }
}

@Composable
fun TextRender(
    modifier: Modifier = Modifier,
    text: String,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text, fontSize = 20.sp
            )
            Button(onClick = onNextClick) {
                Text(text = "NextName")
            }
            Button(onClick = onPreviousClick) {
                Text(text = "PreviousName")
            }
        }
    }
}
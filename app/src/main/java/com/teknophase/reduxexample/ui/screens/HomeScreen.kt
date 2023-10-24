package com.teknophase.reduxexample.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.teknophase.redux.map
import com.teknophase.reduxexample.ui.components.ListItem
import com.teknophase.reduxexample.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center) {
        val coroutineScope = rememberCoroutineScope()
        val error = viewModel.store.stateFlow.map(coroutineScope) {
            it.isError
        }.collectAsState()
        val isLoading = viewModel.store.stateFlow.map(coroutineScope) {
            it.isLoading
        }.collectAsState()
        val photos = viewModel.store.stateFlow.map(coroutineScope) {
            it.photos
        }.collectAsState()

        if(isLoading.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Center)
                )
            }
        } else {
            if(error.value){
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Error",
                        modifier = Modifier.align(Center)
                    )
                }
            }

            LazyColumn {
                items(photos.value.size) {index ->
                    ListItem(photos.value.get(index))
                    Divider()
                }
            }
        }
    }
}
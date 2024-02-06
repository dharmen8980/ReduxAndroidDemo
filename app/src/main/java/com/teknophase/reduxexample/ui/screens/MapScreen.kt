package com.teknophase.reduxexample.ui.screens

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.model.LatLng

@Composable
fun MapScreen () {
    Surface {
        Text(text = "Hello from Map")
    }
}

@Composable
fun MapExample() {
    val mumbai = LatLng(19.0760, 72.8777)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mumbai, 11f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
}
@Preview(showBackground = true)
@Composable
fun MapPreview () {
    MapScreen()
}
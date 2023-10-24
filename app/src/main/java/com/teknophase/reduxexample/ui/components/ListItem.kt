package com.teknophase.reduxexample.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.teknophase.reduxexample.model.ListItemModel

@Composable
fun ListItem(photoModel : ListItemModel) {
    Row(
       modifier = Modifier
           .fillMaxWidth()
           .height(80.dp)
           .background(Color.White)
    ) {
        AsyncImage(
            model = photoModel.url,
            modifier = Modifier
                .size(width = 80.dp, height = 70.dp)
                .align(CenterVertically)
                .padding(horizontal = 5.dp),
            contentDescription = ""
        )

        Column(modifier = Modifier.fillMaxSize().padding(5.dp)) {
            Text(
                text = photoModel.title,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )

            Text(
                text = photoModel.thumbnailUrl,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }
    }
}
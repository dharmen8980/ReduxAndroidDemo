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
import coil.compose.AsyncImage
import com.teknophase.reduxexample.model.ListItemModel
import com.teknophase.reduxexample.ui.constants.font_12
import com.teknophase.reduxexample.ui.constants.font_14
import com.teknophase.reduxexample.ui.constants.size_10
import com.teknophase.reduxexample.ui.constants.size_5
import com.teknophase.reduxexample.ui.constants.size_70
import com.teknophase.reduxexample.ui.constants.size_80

@Composable
fun ListItem(photoModel: ListItemModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(size_80)
            .background(Color.White)
    ) {
        AsyncImage(
            model = photoModel.url,
            modifier = Modifier
                .size(width = size_80, height = size_70)
                .align(CenterVertically)
                .padding(horizontal = size_5),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(size_5)
        ) {
            Text(
                text = photoModel.title,
                modifier = Modifier.padding(top = size_10),
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = font_14
                )
            )

            Text(
                text = photoModel.thumbnailUrl,
                modifier = Modifier.padding(top = size_10),
                color = Color.Black,
                style = TextStyle(
                    fontSize = font_12
                )
            )
        }
    }
}
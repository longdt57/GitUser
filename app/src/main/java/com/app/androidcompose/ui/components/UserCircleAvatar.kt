package com.app.androidcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.app.androidcompose.R
import com.app.androidcompose.ui.theme.ComposeTheme
import com.app.androidcompose.ui.theme.GreySoft950

@Composable
fun UserCircleAvatar(modifier: Modifier = Modifier, avatarUrl: String?) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(GreySoft950),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .crossfade(true) // Optional fade animation
                .build(),
            contentDescription = avatarUrl,
            placeholder = rememberAsyncImagePainter(model = R.drawable.im_avatar_placeholder),
            error = rememberAsyncImagePainter(model = R.drawable.im_avatar_placeholder),
            contentScale = ContentScale.Fit,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserCircleAvatarPreview() {
    ComposeTheme {
        UserCircleAvatar(
            modifier = Modifier.size(40.dp),
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }
}

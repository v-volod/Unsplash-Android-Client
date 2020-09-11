package com.volodymyrvoiko.screen.photos

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.volodymyrvoiko.R
import com.volodymyrvoiko.api.Photo
import com.volodymyrvoiko.api.aspect
import com.volodymyrvoiko.extensions.Modifiers
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade

@Composable
fun PhotosScreen(
) {
    val viewModel: PhotosViewModel = viewModel()
    val state = viewModel.photos.collectAsState(initial = emptyList())

    PhotosList(state.value)
}

@Composable
fun PhotosList(
    photos: List<Photo>
) {
    LazyColumnFor(
        items = photos,
    ) { item ->
        PhotoItem(
            photoUrl = item.urls.raw,
            photoAspectRatio = item.aspect,
            description = item.description,
            userName = item.user.name,
            userProfileImageUrl = item.user.profileImage.medium
        )
    }
}

@Composable
fun PhotoItem(
    photoUrl: String,
    photoAspectRatio: Float,
    description: String?,
    userName: String,
    userProfileImageUrl: String
) {
    val context = ContextAmbient.current
    val chooserTitle = stringResource(R.string.view_url_title)

    Column {
        Surface(
            modifier = Modifier.aspectRatio(photoAspectRatio),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            CoilImageWithCrossfade(
                data = photoUrl
            )
        }

        Row(
            modifier = Modifier.padding(16.dp),
            verticalGravity = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.preferredSize(48.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                CoilImageWithCrossfade(
                    data = userProfileImageUrl,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.preferredWidth(16.dp))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = description ?: stringResource(R.string.no_description),
                    style = MaterialTheme.typography.body2
                )
            }

            Spacer(modifier = Modifier.preferredWidth(16.dp))

            Image(
                asset = Icons.Default.SaveAlt,
                modifier = Modifiers(
                    Modifier.size(48.dp),
                    Modifier.clickable(onClick = {
                        context.startActivity(
                            Intent.createChooser(
                                Intent(Intent.ACTION_VIEW).setData(Uri.parse(photoUrl)),
                                chooserTitle
                            )
                        )
                    })
                ),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant),
            )
        }
    }
}
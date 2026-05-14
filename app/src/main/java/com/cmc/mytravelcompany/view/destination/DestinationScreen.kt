package com.cmc.mytravelcompany.view.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.domain.entity.DestinationEntity
import com.example.ui.theme.GothamFamily
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationScreen(
    destinationViewModel: DestinationViewModel = hiltViewModel(), onPressBackArrow: () -> Unit
) {
    val uiState by destinationViewModel.uiState.collectAsStateWithLifecycle()
    val deepGoldStart = MaterialTheme.colorScheme.primary
    val deepGoldEnd = MaterialTheme.colorScheme.background

    Scaffold(
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(deepGoldStart, deepGoldEnd)
                        )
                    )
            )

            Column() {
                when (val state = uiState) {
                    is DestinationUiState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is DestinationUiState.Error -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = state.message, color = MaterialTheme.colorScheme.error)
                        }
                    }
                    is DestinationUiState.Success -> {
                        FirstInformation(state.destination)
                        Spacer(modifier = Modifier.size(30.dp))
                        SecondInformation(state.destination)

                    }
                }
            }
        }
    }
}



@Composable
fun FirstInformation(destination : DestinationEntity){
    val shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .clip(shape)
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(destination.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        // --- NUEVA ETIQUETA DE PRECIO EN LA ESQUINA INFERIOR DERECHA ---
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Desde ${destination.priceApprox.toInt()}€",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }


        // Texto principal
        Text(
            text = destination.name.uppercase(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary ,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(15f, 15f),
                    blurRadius = 8f
                )
            ), textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SecondInformation(destination: DestinationEntity) {
    Text(
            textAlign = TextAlign.Center,
    text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = GothamFamily,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            append("VACACIONES EN " + destination.name.uppercase())
        }
    },
    fontSize = 28.sp, modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
}

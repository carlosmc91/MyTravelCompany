package com.cmc.mytravelcompany.view.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.view.core.components.CmcButtonBorderMain
import com.example.ui.theme.GothamFamily
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onOpenDrawer: () -> Unit,
) {
    val uiState by mainViewModel.mainUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyTopAppBar {
                onOpenDrawer()
            }
        }, containerColor = Color.Transparent, content = { _ ->
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    if (uiState.isLoadingBanners) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    } else if (uiState.banners.isNotEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            BannerPager(
                                banners = uiState.banners, modifier = Modifier.fillMaxSize()
                            )

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = GothamFamily,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.White
                                            )
                                        ) {
                                            append("VIAJES ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = GothamFamily,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFF9E899)
                                            )
                                        ) {
                                            append("PERSONALIZADOS")
                                        }
                                    },
                                    fontSize = 28.sp,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha = 0.95f),
                                            offset = Offset(0f, 2f),
                                            blurRadius = 15f
                                        )
                                    )
                                )

                                Spacer(modifier = Modifier.size(35.dp))

                                Text(
                                    textAlign = TextAlign.Center,
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = GothamFamily,
                                                fontWeight = FontWeight.Normal,
                                                color = Color.White
                                            )
                                        ) {
                                            append("ESPECIALISTAS EN VIAJES PERSONALIZADOS ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = GothamFamily,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                        ) {
                                            append("Y A MEDIDA QUE CONVIERTEN TUS SUEÑOS EN REALIDAD")
                                        }
                                    },
                                    fontSize = 17.sp,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = Color.Black.copy(alpha = 0.85f),
                                            offset = Offset(0f, 1f),
                                            blurRadius = 12f
                                        )
                                    )
                                )
                            }

                            Box(
                                modifier = Modifier.fillMaxSize().padding(bottom = 40.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                CmcButtonBorderMain(
                                    text = "CUÉNTANOS TU SUEÑO",
                                    color = MaterialTheme.colorScheme.primary
                                ) {
                                    // Acción del botón
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(text = "No hay promociones disponibles", color = Color.White)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun BannerPager(banners: List<BannerEntity>, modifier: Modifier = Modifier) {
    if (banners.size <= 1) {
        val pagerState = rememberPagerState(pageCount = { banners.size })
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { index ->
            BannerItem(banners[index])
        }
        return
    }

    val displayBanners = remember(banners) {
        listOf(banners.last()) + banners + listOf(banners.first())
    }

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { displayBanners.size }
    )

    LaunchedEffect(pagerState.settledPage) {
        when (pagerState.settledPage) {
            0 -> pagerState.scrollToPage(banners.size)
            displayBanners.size - 1 -> pagerState.scrollToPage(1)
        }
    }

    LaunchedEffect(pagerState.settledPage) {
        delay(10000L)
        if (!pagerState.isScrollInProgress) {
            val nextPage = pagerState.currentPage + 1
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        beyondViewportPageCount = 1
    ) { page ->
        BannerItem(displayBanners[page])
    }
}

@Composable
fun BannerItem(banner: BannerEntity) {
    var isImageLoading by remember { mutableStateOf(true) }

    val imageData = remember(banner.localPath, banner.imageUrl) {
        val file = banner.localPath?.let { File(it) }
        if (file != null && file.exists()) file else banner.imageUrl
    }

    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = androidx.compose.ui.graphics.RectangleShape 
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isImageLoading) {
                Box(modifier = Modifier.fillMaxSize())
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageData)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onState = { state ->
                    isImageLoading = state is AsyncImagePainter.State.Loading
                })

            Surface(
                color = Color(0x904A4D3B),
                shape = CircleShape,
                border = BorderStroke(1.dp, Color(0xFF9EA388)),
                modifier = Modifier
                    .align(BiasAlignment(horizontalBias = -0.9f, verticalBias = -0.7f))
                    .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(35.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = banner.title,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = banner.subtitle,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }
    }
}

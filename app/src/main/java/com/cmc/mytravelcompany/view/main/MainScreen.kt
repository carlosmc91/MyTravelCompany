package com.cmc.mytravelcompany.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cmc.mytravelcompany.domain.entity.BannerEntity
import com.cmc.mytravelcompany.view.core.components.CmcButtonBorder
import kotlinx.coroutines.launch


@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), onPressDiscover: () -> Unit) {
    val uiState by mainViewModel.mainUiState.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val deepGoldStart = MaterialTheme.colorScheme.primary
    val deepGoldEnd = MaterialTheme.colorScheme.background

    MyNavigationDrawer(drawerState, uiState) {
        Scaffold(
            topBar = {
                MyTopAppBar {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }, containerColor = Color.Transparent
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(deepGoldStart, deepGoldEnd)
                            )
                        )
                )

                Column() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f)
                            .padding(top = padding.calculateTopPadding())

                    ) {
                        if (uiState.isLoadingBanners) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.White)
                            }
                        } else if (uiState.banners.isNotEmpty()) {
                            BannerPager(
                                banners = uiState.banners, modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "No hay promociones disponibles", color = Color.White)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TransitoursHeader()

                        CmcButtonBorder(
                            "Descúbrelo",
                            onClick = { onPressDiscover() },
                            paddingValues = PaddingValues(
                                5.dp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun BannerPager(banners: List<BannerEntity>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { banners.size })

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 5.dp),
        pageSpacing = 5.dp
    ) { page ->
        BannerItem(banners[page])
    }
}

@Composable
fun BannerItem(banner: BannerEntity) {
    var titleSize by remember(banner.title) { mutableStateOf(20.sp) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banner.imageUrl)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = 300f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = banner.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleSize,
                    maxLines = 1,
                    softWrap = false,
                    onTextLayout = { textLayoutResult ->
                        if (textLayoutResult.didOverflowWidth) {
                            titleSize *= 0.9f
                        }
                    })
                Text(
                    text = banner.subtitle,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }


        }
    }
}

@Composable
fun TransitoursHeader() {
    var bigFontSize by remember { mutableStateOf(80.sp) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Transitours", style = TextStyle(
                fontSize = bigFontSize,
                fontWeight = FontWeight.Black,
                color = Color.LightGray.copy(alpha = 0.3f),
                letterSpacing = (-2).sp
            ), maxLines = 1, softWrap = false, onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowWidth) {
                    bigFontSize *= 0.9f
                }
            })

        Text(
            text = "¿Por qué viajar con nosotros?",
            style = TextStyle(
                fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 20.dp)
        )


    }
}

package org.akshar.quantum_scan

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.unit.dp
import org.akshar.quantum_scan.bottomNavigationWidgets.Circle
import org.akshar.quantum_scan.bottomNavigationWidgets.CustomBottomNavigation
import org.akshar.quantum_scan.bottomNavigationWidgets.FabGroup
import org.akshar.quantum_scan.bottomNavigationWidgets.getPlatformRenderEffect
import org.akshar.quantum_scan.ui.theme.QuantumScanTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    QuantumScanTheme {
        MainContent()
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ), label = "mainScreenFabAnimation"
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        ), label = "mainScreenClickAnimation"
    )

    val renderEffect = remember {
        getPlatformRenderEffect() as? RenderEffect
    }


    MainScreenContent(
        modifier = modifier,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress,
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }
}
@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    renderEffect: RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { }
) {
    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            CustomBottomNavigation()
            Circle(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                animationProgress = 0.5f
            )

            FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress)
            FabGroup(
                renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation,
                onCapturePressed = {
                }
            )
            Circle(
                color = Color.White,
                animationProgress = clickAnimationProgress
            )
        }
    }
}
package com.volodymyrvoiko.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifiers(vararg modifier: Modifier) = modifier.asList().reduce(Modifier::then)
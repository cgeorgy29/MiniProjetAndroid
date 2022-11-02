package com.example.appliprofil.compo

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.appliprofil.R


@Composable
fun ExpendableSearchButton (
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    expandedInitially: Boolean = false,
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }
    Crossfade(targetState = expanded) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpendedButton(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
            )

            false -> CollapsedButton(
                onExpandedChanged = onExpandedChanged)
        }
    }
}

@Composable
fun CollapsedButton(
    onExpandedChanged: (Boolean) -> Unit
){
        FloatingActionButton(onClick = {onExpandedChanged(true)}) {
            Icon(
                painterResource(id = R.drawable.chercher),
                contentDescription = "search icon",
                Modifier.width(25.dp)
            )
        }
    }

@Composable
fun ExpendedButton(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit
){
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        Modifier.width(300.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
            ExtendedFloatingActionButton(
                text = {
                    TextField(
                        value = textFieldValue,
                        onValueChange = {
                            textFieldValue = it
                            onSearchDisplayChanged(it.text)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(textFieldFocusRequester)
                        ,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "fermeture")
                },
                onClick = {
                    onExpandedChanged(false)
                    onSearchDisplayClosed()
                    textFieldFocusRequester.freeFocus()
                    textFieldValue = TextFieldValue("")


                })
    }
}


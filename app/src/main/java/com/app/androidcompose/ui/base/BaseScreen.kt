package com.app.androidcompose.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    content: @Composable () -> Unit,
) {
    content()
    LoadingView(viewModel)
    ErrorView(viewModel)
}

@Composable
private fun LoadingView(viewModel: BaseViewModel) {
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    LoadingView(loading = loading)
}

@Composable
private fun LoadingView(loading: LoadingState) {
    when (loading) {
        is LoadingState.Loading -> {
            LoadingProgress(loading)
        }

        else -> {}
    }
}

@Composable
private fun ErrorView(viewModel: BaseViewModel) {
    val error by viewModel.error.collectAsStateWithLifecycle()
    ErrorView(
        error = error,
        onErrorConfirmation = { viewModel.onErrorConfirmation() },
        onErrorDismissRequest = { viewModel.onErrorDismissClick() }
    )
}

@Composable
private fun ErrorView(
    error: ErrorState,
    onErrorConfirmation: (ErrorState) -> Unit = {},
    onErrorDismissRequest: (ErrorState) -> Unit = {},
) {
    when (error) {
        is ErrorState.MessageError -> {
            AlertDialogView(
                dialogTitle = error.titleRes?.let { stringResource(id = it) },
                dialogText = error.messageRes?.let { stringResource(id = it) },
                confirmText = error.primaryRes?.let { stringResource(id = it) },
                dismissText = error.secondaryRes?.let { stringResource(id = it) },
                onConfirmation = { onErrorConfirmation(error) },
                onDismissRequest = { onErrorDismissRequest(error) })
        }

        is ErrorState.None -> {}
    }
}

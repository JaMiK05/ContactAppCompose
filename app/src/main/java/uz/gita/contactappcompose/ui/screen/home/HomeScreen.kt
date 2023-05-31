package uz.gita.contactappcompose.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.KeyEventDispatcher
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.lifecycle.rememberScreenLifecycleOwner
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.runBlocking
import uz.gita.contactappcompose.data.model.ContactData
import uz.gita.contactappcompose.ui.component.ContactItem
import uz.gita.contactappcompose.ui.screen.addcontact.AddContactScreen

class HomeScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeContract.ViewModel = getViewModel<HomeViewModelImpl>()
        viewModel.onEventDispatcher(HomeContract.Intent.UpdateContacts)
        val uiState = viewModel.uiState.collectAsState().value
        HomeScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContent(
    uiState: HomeContract.UiState,
    onEventDispatcher: (HomeContract.Intent) -> Unit,
) {
    val navigator = LocalNavigator.currentOrThrow

    if (uiState.editOrAddContactState) {
        navigator.push(AddContactScreen(uiState.contact))
        onEventDispatcher(HomeContract.Intent.CloseEditOrAddContact)
    }


    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text("Contacts")
        })
    }, floatingActionButton = {
        Image(
            modifier = Modifier.clickable {
                onEventDispatcher(HomeContract.Intent.OpenEditOrAddContact(null))
            }, imageVector = Icons.Default.Add, contentDescription = ""
        )
    }) { contentPadding ->
        // Screen content
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            items(uiState.contacs) {
                ContactItem(
                    modifier = Modifier.run {
                        combinedClickable(onClick = {
                            onEventDispatcher(HomeContract.Intent.OpenEditOrAddContact(it))
                        }, onLongClick = {
                            onEventDispatcher(HomeContract.Intent.Delete(it))
                        })
                    },
                    name = it.firstName + " " + it.lastName,
                    number = it.phone,
                )
            }
        }
    }

}



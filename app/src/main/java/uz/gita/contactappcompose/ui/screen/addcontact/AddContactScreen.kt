package uz.gita.contactappcompose.ui.screen.addcontact

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.contactappcompose.data.model.ContactData
import uz.gita.contactappcompose.ui.component.MyTextField
import uz.gita.contactappcompose.ui.screen.addcontact.viewmodel.AddContactViewModelImpl
import uz.gita.contactappcompose.ui.screen.addcontact.viewmodel.AddContract
import uz.gita.contactappcompose.ui.theme.ContactAppComposeTheme

class AddContactScreen(data: ContactData?) : AndroidScreen() {

    private val data1 = data

    @Composable
    override fun Content() {

        val viewModel: AddContract.ViewModel = getViewModel<AddContactViewModelImpl>()
        val uiState = viewModel.uiState.collectAsState().value
        AddContactScreenContent(uiState, viewModel::onEventDispatcher, data1 = data1)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreenContent(
    uiState: AddContract.UiState,
    onEvenDispatcher: (AddContract.Intent) -> Unit,
    data1: ContactData?,
) {

    val navigator = LocalNavigator.currentOrThrow
    var fname by remember {
        mutableStateOf(data1?.firstName ?: "")
    }

    var lname by remember {
        mutableStateOf(data1?.lastName ?: "")
    }

    var phone by remember {
        mutableStateOf(data1?.phone ?: "+998")
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text("Add Contact")
            })
        },

        ) { contentPadding ->
        // Screen content
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "First name")
            MyTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
                value = fname,
                onValueChange = { fname = it.trim() })
            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Last name")
            MyTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
                value = lname,
                onValueChange = { lname = it.trim() })
            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Phone number")
            MyTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
                value = phone,
                keyboard = KeyboardType.Phone,
                onValueChange = { phone = it.trim() })

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
                onClick = {
                    if (data1 == null) {
                        onEvenDispatcher(
                            AddContract.Intent.CloseEditOrAddContact(
                                ContactData(
                                    firstName = fname, lastName = lname, phone = phone
                                ), null
                            )
                        )
                    } else {
                        val send = ContactData(
                            id = data1.id, firstName = fname, lastName = lname, phone = phone
                        )
                        onEvenDispatcher(
                            AddContract.Intent.CloseEditOrAddContact(send, "update")
                        )
                    }
                }) {
                Text(text = "Add Contact")
            }

        }
    }
}


@Preview
@Composable
fun AddContactScreenPreview() {
    ContactAppComposeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//            AddContactScreenContent()
        }
    }
}
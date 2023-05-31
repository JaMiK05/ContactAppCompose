package uz.gita.contactappcompose.ui.screen.addcontact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.contactappcompose.data.model.ContactData
import uz.gita.contactappcompose.domain.repository.crud.AppRepository
import uz.gita.contactappcompose.ui.screen.addcontact.direction.AddScreenDirection
import uz.gita.contactappcompose.ui.screen.home.HomeContract
import javax.inject.Inject


@HiltViewModel
class AddContactViewModelImpl @Inject constructor(
    private val repository: AppRepository,
    private val direction: AddScreenDirection,
) : ViewModel(), AddContract.ViewModel {

    override val uiState = MutableStateFlow(AddContract.UiState())

    override fun onEventDispatcher(intent: AddContract.Intent) {
        when (intent) {
            is AddContract.Intent.CloseEditOrAddContact -> {
                if (intent.str == null) {
                    addContact(intent.data)
                } else {
                    updateContact(intent.data)
                }
                viewModelScope.launch {
                    direction.navigateToHomeScreen()
                }
            }
        }
    }

    private fun addContact(data: ContactData) {
        viewModelScope.launch {
            repository.add(data)
        }
    }

    private fun updateContact(data: ContactData) {
        viewModelScope.launch {
            repository.update(data)
        }
    }

}

//    override fun addContact(contactData: ContactData) {
//        viewModelScope.launch {
//            repository.add(contactData)
//        }
//    }
//
//    override fun upDateContact(contactData: ContactData) {
//        viewModelScope.launch {
//            repository.update(contactData)
//        }
//    }

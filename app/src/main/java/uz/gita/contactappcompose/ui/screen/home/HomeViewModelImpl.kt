package uz.gita.contactappcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import uz.gita.contactappcompose.data.model.ContactData
import uz.gita.contactappcompose.domain.repository.crud.AppRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepository,
) : ViewModel(), HomeContract.ViewModel {


    init {
        updateContacts()
    }

    fun updateContacts() {
        repository.retrieveAllContacts()
            .onEach { contacts -> uiState.update { it.copy(contacs = contacts) } }
            .launchIn(viewModelScope)
    }


    override val uiState: MutableStateFlow<HomeContract.UiState> =
        MutableStateFlow(HomeContract.UiState())

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.OpenEditOrAddContact -> uiState.update {
                it.copy(contact = intent.data, editOrAddContactState = true)
            }

            is HomeContract.Intent.Delete -> {
                viewModelScope.launch {
                    repository.delete(intent.data)
                }
            }

            is HomeContract.Intent.CloseEditOrAddContact -> uiState.update {
                it.copy(
                    editOrAddContactState = false
                )
            }

            is HomeContract.Intent.UpdateContacts -> {
                updateContacts()
            }
        }
    }

}

//    override val contactsLiveData: LiveData<List<ContactData>>
//        get() = repository.retrieveAllContacts().asLiveData()
//
//    override fun deleteContact(contactData: ContactData) {
//        viewModelScope.launch {
//            repository.delete(contactData)
//        }
//    }

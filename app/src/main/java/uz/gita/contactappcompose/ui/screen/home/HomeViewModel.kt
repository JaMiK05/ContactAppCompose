package uz.gita.contactappcompose.ui.screen.home

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.contactappcompose.data.model.ContactData

interface HomeViewModel {
    val contactsLiveData: LiveData<List<ContactData>>

    fun deleteContact(contactData: ContactData)

}


interface HomeContract {


    sealed interface Intent {

        class OpenEditOrAddContact(val data: ContactData?) : Intent
        class Delete(val data: ContactData) : Intent
        object UpdateContacts: Intent
        object CloseEditOrAddContact : Intent
    }

    data class UiState(
        val contacs: List<ContactData> = listOf(),
        val editOrAddContactState: Boolean = false,
        val contact: ContactData? = null,
    )

    interface ViewModel {

        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)

    }

}

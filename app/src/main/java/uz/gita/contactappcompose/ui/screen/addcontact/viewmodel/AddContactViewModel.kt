package uz.gita.contactappcompose.ui.screen.addcontact.viewmodel

import kotlinx.coroutines.flow.StateFlow
import uz.gita.contactappcompose.data.model.ContactData

interface AddContract {

    sealed interface Intent {
        class CloseEditOrAddContact(val data: ContactData, val str: String?) : Intent
    }

    data class UiState(
        val bool: Boolean = true,
    )

    interface ViewModel {

        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)

    }


}

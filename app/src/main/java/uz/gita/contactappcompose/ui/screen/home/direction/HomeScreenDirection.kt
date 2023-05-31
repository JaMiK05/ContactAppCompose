package uz.gita.contactappcompose.ui.screen.home.direction

import uz.gita.contactappcompose.data.model.ContactData

interface HomeScreenDirection {

    suspend fun navigateToAddScreen(data: ContactData?)

}
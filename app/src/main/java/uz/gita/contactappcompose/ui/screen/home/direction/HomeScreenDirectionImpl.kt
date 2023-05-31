package uz.gita.contactappcompose.ui.screen.home.direction

import uz.gita.contactappcompose.data.model.ContactData
import uz.gita.contactappcompose.navigation.AppNavigator
import uz.gita.contactappcompose.ui.screen.addcontact.AddContactScreen
import javax.inject.Inject

class HomeScreenDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : HomeScreenDirection {

    override suspend fun navigateToAddScreen(data: ContactData?) {
        navigator.navigateTo(AddContactScreen(data))
    }


}
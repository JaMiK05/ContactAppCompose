package uz.gita.contactappcompose.ui.screen.addcontact.direction

import uz.gita.contactappcompose.navigation.AppNavigator
import uz.gita.contactappcompose.ui.screen.home.HomeScreen
import javax.inject.Inject

class AddScreeenDirectionImpl @Inject constructor(
    private val navigator: AppNavigator,
) : AddScreenDirection {

    override suspend fun navigateToHomeScreen() {
        navigator.navigateTo(HomeScreen())
    }


}
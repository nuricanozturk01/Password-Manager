package nuricanozturk.dev.passwordmanager.Util;

public enum SceneNames
{
    LOGIN_SCREEN("login_screen.fxml"),
    SIGN_UP_SCREEN("sign_up_screen.fxml"),
    MAIN_SCREEN("main_screen.fxml"),
    LOG_SCREEN("log_screen.fxml"),
    ADD_SCREEN("add_screen.fxml"),
    UPDATE_SCREEN("update_screen.fxml"),
    PASSWORD_CHANGE_SCREEN("change_password_screen.fxml"),
    EMAIL_VERIFICATION_SCREEN("email_verfication_screen.fxml"),
    PASSWORD_AUTHENTICATION_SCREEN("password_authentication_screen.fxml");
    private final String sceneName;
    SceneNames(String sceneName)
    {
        this.sceneName = sceneName;
    }
    public String getSceneName()
    {
        return sceneName;
    }
}

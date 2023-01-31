package nuricanozturk.dev.passwordmanager.decrypt.passwordmanagerdecrypt.Util;

public enum SceneNames
{
    MAIN_SCREEN("main.fxml");
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

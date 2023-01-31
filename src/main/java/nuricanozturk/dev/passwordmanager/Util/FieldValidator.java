package nuricanozturk.dev.passwordmanager.Util;

import java.util.Arrays;

public final class FieldValidator
{
    public static boolean isValidText(String ... args)
    {
        for (String arg : args)
            if (arg.isEmpty() || arg.isBlank())
                return false;
        return true;    }

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }
}

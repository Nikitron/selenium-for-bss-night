package ru.tsystems.phoenixqa.selenium.atlas;

import io.qameta.atlas.core.api.MethodExtension;
import io.qameta.atlas.core.internal.Configuration;
import io.qameta.atlas.core.util.MethodInfo;
import lombok.val;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CheckMethodExtension implements MethodExtension {
    public static ThreadLocal<List<String>> errorList = ThreadLocal.withInitial(ArrayList::new);

    @Override
    public boolean test(Method method) {
        return method.getName().equals("check");
    }

    @Override
    public Object invoke(Object proxy, MethodInfo methodInfo, Configuration config) {
        val reason = methodInfo.getParameter(String.class)
                .orElse("Soft check is failed for element " + proxy.toString());
        val matcher = methodInfo.getParameter(Matcher.class).orElseThrow();

        if (!matcher.matches(proxy)) {
            val description = new StringDescription();
            description.appendText(reason)
                    .appendText("\nExpected: ")
                    .appendDescriptionOf(matcher)
                    .appendText("\n     but: ");
            matcher.describeMismatch(proxy, description);
            val errorMessage = description.toString();
            System.out.println(errorMessage);

            errorList.get().add(errorMessage);
        }
        return proxy;
    }
}

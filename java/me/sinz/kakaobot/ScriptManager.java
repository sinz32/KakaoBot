package me.sinz.kakaobot;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;

import me.sinz.kakaobot.api.Event;
import me.sinz.library.PrimitiveWrapFactory;

public class ScriptManager {

    private static ScriptableObject scope;

    public static void callResponse(final Object[] args) {
        ThreadManager.getInstance().execute(()->callScriptMethod(Event.MESSAGE, args));
    }

    public static void callScriptMethod(String event, Object[] args) {
        if (scope == null) return;
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        rhino.setLanguageVersion(Context.VERSION_ES6);
        rhino.setWrapFactory(new PrimitiveWrapFactory());
        try {
            Function func = (Function) scope.get(event, scope);
            if (func == null) return;
            func.call(rhino, scope, scope, args);
        } catch (ClassCastException e) {
            Context.exit();
        }
        Context.exit();
    }

}

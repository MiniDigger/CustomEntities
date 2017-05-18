package me.minidigger.customentities.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by Yamakaja on 18.05.17.
 */
public class CustomEntitiesAgent {

    public static void premain(String agentArgument, Instrumentation instrumentation) {

        instrumentation.addTransformer(new EntityClassFileTransformer());

    }

}

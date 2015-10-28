package de.thberger.camelcam;

import io.rhiot.utils.process.DefaultProcessManager;

import java.util.List;

public class CustomProcessManager extends DefaultProcessManager {
    @Override
    public List<String> executeAndJoinOutput(String... commands) {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].startsWith("modprobe")) {
                commands[i] = "sudo " + commands[i];
            }
        }
        return super.executeAndJoinOutput(commands);
    }
}
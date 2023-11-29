package me.aussied;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxPlatform {
    
    public static boolean isLinux() {
        return System.getProperty("os.name").equals("Linux");
    }

    public static String getKernel() {
        return System.getProperty("os.version");
    }

    public static String getPrettyName() {
        return getReleaseInfoFromTag("pretty_name");
    }

    public static String getName() {
        return getReleaseInfoFromTag("name");
    }

    public static String getLogo() {
        return getReleaseInfoFromTag("logo");
    }

    public static String getBuildId() {
        return getReleaseInfoFromTag("build_id");
    }

    public static String getIdLike() {
        return getReleaseInfoFromTag("id_like");
    }

    public static String getId() {
        return getReleaseInfoFromTag("id");
    }

    private static String getReleaseInfoFromTag(String tag) {
        String upperTag = tag.toUpperCase() + "=";
        String command = "awk '/^" + upperTag + "/' /etc/os-release";

        return ConsoleUtil.getCommandOutput(command).replace(upperTag, "");
    }

    public static String getCommandOutput(String exec) {
        String result = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(exec).getInputStream()));

            String input;
            while ((input = reader.readLine()) != null) {
                result += "\n" + input;
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("Failed to execute command '" + exec + "': " + e.getCause());
        }
        return result;
    }

}

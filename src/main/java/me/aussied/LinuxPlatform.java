package me.aussied;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxPlatform {

    /** yes this literally just uses java io to check what's in the os-release file. */
    private static final String command = "cat /etc/os-release";

    /** Checks if the user is running Linux */
    public static boolean isLinux() {
        return System.getProperty("os.name").equals("Linux");
    }

    /** pretty name (basically just the name but more pretty ig). */
    public static String getPrettyName() {
        return Console.getTagFromCommand("PRETTY_NAME=");
    }

    /** Distribution name. */
    public static String getName() {
        return Console.getTagFromCommand("NAME=");
    }

    /** Logo name I think. */
    public static String getLogo() {
        return Console.getTagFromCommand("LOGO=");
    }

    /** Distribution Build/Version. */
    public static String getBuildId() {
        return Console.getTagFromCommand("BUILD_ID=");
    }

    /** Current Kernel. */
    public static String getKernel() {
        return System.getProperty("os.version");
    }

    /**  Identification Like (like what it's based on, e.g. Arch or Debian/Ubuntu). */
    public static String getIdLike() {
        return Console.getTagFromCommand("ID_LIKE=");
    }

    /** Identification. */
    public static String getId() {
        return Console.getTagFromCommand("ID=");
    }

    /** I did this for absolutely no reason :). */
    public static class Console {

        /** getLineFromCommand but removes the specified tag (eg. ID=6.90.420 to 6.90.420) */
        private static String getTagFromCommand(String startsWith) {
            return getLineFromCommand(LinuxPlatform.command, startsWith).replace(startsWith, "");
        }

        /** Gets a line from what it starts with, im too lazy to make it with arrays */
        private static String getLineFromCommand(String exec, String startsWith) {
            String result = null;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(exec).getInputStream()));

                String input;
                while ((input = reader.readLine()) != null)
                    if (input.startsWith(startsWith))
                        result = input;

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            assert result != null;
            return result.replace(startsWith, "");
        }

        /** Returns to the output of the specified command */
        public static String getCommand(String exec) {
            String result = null;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(exec).getInputStream()));

                String input;
                while ((input = reader.readLine()) != null)
                    result += "\n" + input;

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

}

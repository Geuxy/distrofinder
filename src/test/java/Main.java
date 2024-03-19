import static me.aussied.LinuxInfo.*;

public class Main {

    /** I am aware static imports are bad, but it's just an example. */
    public static void main(String[] args) {

        System.out.println(getName() + " " + getBuildId());
        System.out.println("Running " + getKernel() + " Kernel.");

        /** Example for getCommand which prints the output */
        System.out.println();
        System.out.println(Console.getCommand("cat /etc/os-release"));
    }

}

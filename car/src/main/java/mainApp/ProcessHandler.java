package mainApp;

import org.apache.commons.lang3.SystemUtils;
import java.io.IOException;
import java.util.List;

public class ProcessHandler {

    public static void main(String []args){}

    public static void portForward(String service, String env, String port) throws IOException {

        Process process = Runtime.getRuntime().exec(
                "kubectl port-forward services/" + service + " 8080:" + port + " -n dxl-" + env.split("-")[1] + "-gr");
        System.out.println("Service " + service + " has been port forwarded to port " + port + ".\n");
    }

    public static void killProcesses() {
        try {
            String [] processNames = {"kubectl"};
            for (String process : processNames) {
                if (SystemUtils.IS_OS_LINUX) {
                    String[] cmd= {"/bin/sh",
                            "-c", "ps -ef | grep -w "+
                            process +" | grep -v grep | awk '/[0-9]/{print $2}' | xargs kill -9 "};

                    StringBuffer output = new StringBuffer();
                    try {
                        Process p = Runtime.getRuntime().exec(cmd);
                        List<String> results = (List<String>) p.getInputStream();
                        for (String line : results) {
                            System.out.println(line);
                            output.append(line);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else if (SystemUtils.IS_OS_WINDOWS) {
                    Runtime.getRuntime().exec("taskkill /F /IM "+
                            process +".exe");
                    System.out.println("DEBUG: Killing processes. \n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

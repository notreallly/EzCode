package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Yijer
 */
public class Execute {

    private static final Pattern PATH_REGEX = Pattern.compile("(C:\\\\code\\\\\\w+\\\\)(.*)");

    private static List<String> printOutput(String name, InputStream input) throws IOException {
        String token = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        List<String> output = new ArrayList<>();
        Matcher matcher;

        while ((token = in.readLine()) != null) {
            matcher = PATH_REGEX.matcher(token);
            if (matcher.find()) {
                output.add(name + matcher.group(2));
            } else {
                output.add(name + " " + token);
            }
        }
        return output;
    }

    public static List<String> runProcess(String command) throws IOException, InterruptedException {

        Process process = Runtime.getRuntime().exec(command);

        ArrayList<String> output = new ArrayList<>();
        output.addAll(printOutput("stdout:", process.getInputStream()));
        output.addAll(printOutput("errout:", process.getErrorStream()));
        if (!process.waitFor(10, TimeUnit.SECONDS)) {
            output.add("timeout");
            process.destroy();
        }
        output.add(0, Integer.toString(process.exitValue()));

        return output;
    }
}

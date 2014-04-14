/*
 * Copyright (c) 2013 Schrodinger, Inc.  All Rights Reserved.
 */
package lilleswing.gcj.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public abstract class Problem<T> {
    public abstract String solve(T t);
    public abstract List<T> parse(final String data);
    public abstract void preCompute();

    public void display(final List<String> results, final String filename) {
        final String base = filename.substring(0, filename.lastIndexOf('.'));
        final String outFileName = base + ".out";

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(outFileName)));
            int case_num = 1;
            for(String result: results) {
                final String answer = String.format("Case #%d: %s", case_num, result);
                System.out.println(answer);
                writer.write(answer + "\n");
                case_num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public void main(String filename) {
        preCompute();
        final String data = readLines(filename);
        final List<T> cases = parse(data);
        final List<String> results = Lists.transform(cases, new Function<T, String>() {
            @Override
            public String apply(T t) {
                return solve(t);
            }
        });
        display(results, filename);
    }

    private String readLines(final String filename) {
        try {
            final String content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
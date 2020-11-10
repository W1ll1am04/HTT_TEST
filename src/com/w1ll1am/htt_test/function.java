package com.w1ll1am.htt_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;

public class function {
    public static void DisplayHelp() {
        System.out.println("Retrieve http status codes through console / terminal.");
        System.out.println("Arguments & usage, and an explanation: ");
        System.out.println("Usage: HTT_TEST [URL] [URL] [-h] [-d item] [-s count] [-p protocol] [-g premission]");
        System.out.println("                [-S] [-H header] [-t count] [-r file]");
        System.out.println();
        System.out.println("-h, --help                           Display this message.");
        System.out.println("-d, --disable  [OPTION]              Disable a part of the default output.");
        System.out.println("-s, --sleepfor [COUNT]               Sleep for [count] secounds for every request.");
        System.out.println("-p, --protocol [PROTOCOL]            Add entered protcol to entered urls. Ex 'http://'");
        System.out.println("-g, --getpremission [PREMISSION]     Returns a SocketPermission representing the permission necessary to connect to the destination host and port.");
        System.out.println("-t, --times [COUNT]                  Run request(s) [COUNT] times.");
        System.out.println("-r, --readlist [FILE]                Read request url(s) from a simple file and loop thru them.");
        System.out.println("-S, --statusCodeOnly                 Output http status-code only.");
        System.out.println("-H, --header [HEADER]                Get specified request header(s).");
        System.out.println("\n'--disable' valid arguments:");
        System.out.println("    SECTIONS:");
        System.out.println("        Disable the topbar of '--getpremission' argument. Example disable 'PREMISSIONS: ' output.");
        System.out.println("    RETURNMESSAGE:");
        System.out.println("        Disable the default return message. Example disable ' 'yoururl' returned 200 OK' output.");
        System.out.println("    URLSECTIONS:");
        System.out.println("        Disable sections for this run. Example disable '---yoururl---' output.");
        System.out.println("\n'--getpremission' valid arguments:");
        System.out.println("    GETACTIONS:");
        System.out.println("        Get premission 'getactions' from connection.");
        System.out.println("    GETNAME:");
        System.out.println("        Get premission 'GETNAME' from connection.");
        pause(false);
        System.exit(0);
    }

    public static void Custom_help(String iarg, String parent, boolean dtop, boolean extran) {
        if (dtop) System.out.println("HTT_TEST: [URL]... [OPTION]...");
        if (isNullOrEmpty(parent)) {
            System.out.println("Invalid option - " + iarg);
        } else {
            System.out.println(parent + ": Invalid option - '" + iarg + "'");
        }
        if (extran) System.out.println("");
        System.out.print("Try `HTT_TEST --help' for more options.");
        pause(false);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean check_protocol(String url) {
        String[] list = url.split("://");
        return list.length > 1;
    }

    public static boolean isBoolean(String value) {
        return "true".equals(value.toLowerCase()) || "false".equals(value.toLowerCase());
    }

    private static void pause(boolean readline) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (readline) {
                br.readLine();
            } else {
                br.read();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static ArrayList<String> readlist(String file) {
        class exFormatter {
            public ArrayList<String> format(String ex_m) {
                ArrayList<String> ex = new ArrayList<>();
                ex.add(null);
                ex.add(ex_m);
                ex.add(file);
                return ex;
            }
        };

        exFormatter ex_h = new exFormatter();
        try {
            File f = new File(file);
            if (f.exists() && !f.isDirectory()) {
                ArrayList<String> lines = new ArrayList<>();
                Scanner scanner = new Scanner(new File(file));

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    lines.add(line);
                }
                return lines;
            } else {
                return ex_h.format("Cannot find the file: 'un.txt'.");
            }
        } catch (FileNotFoundException e) {
            return ex_h.format(e.getMessage());
        }
    }

    public static String SorrundWithChar(String chars, String str) {
        String[] nchars = chars.split("-");
        if (nchars.length > 1) {
            return new String(nchars[0] + str + nchars[1]);
        } else {
            return str;
        }
    }

    boolean isInt(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
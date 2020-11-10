package com.w1ll1am.htt_test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static function func = new function();
    static String[] headers = null;
    static ArrayList<String> getpremissions = new ArrayList<>();
    static ArrayList<String> urls = new ArrayList<>();

    static String url = "";
    static String prt;
    static String url_flnme;

    static boolean multi_url;
    static int times = 1;
    static int sleeptime = 0;

    static boolean disable_rmes = false;
    static boolean debug = true;
    static boolean disablesections = false;
    static boolean disableurlsections = false;
    static boolean allheaders = false;
    static boolean c_prt = false;
    static boolean get_h = false;
    static boolean mpl_t = false;
    static boolean getprm = false;
    static boolean getallprm = false;
    static boolean statusonly = false;

    public static void main(String[] args) {
        try {
            logger.setupLogger("htt_test.log");
            String arg;
            if (args.length != 0) {
                if (debug)
                    for (int i = 0; i < args.length; i++) {
                        if (!args[i].startsWith("-") && !args[i].startsWith("--")) {
                            urls.add(args[i]);
                        } else {
                            switch (args[i]) {
                                case "-D":
                                case "-debug":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        if (function.isBoolean(args[i])) { debug = Boolean.parseBoolean(args[i]); }
                                        else { System.out.println("-D: Value '" + args[i] + "', is not boolean value. ignoring.");}
                                    } else {
                                        System.out.println(arg+" requires an argument, ignoring..");
                                        logger.logger.warning(" [Args] "+arg+" requires an argument, ignoring.");
                                    }
                                    break;
                                case "-d":
                                case "--disable":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        switch (args[i].toUpperCase()) {
                                            case "SECTIONS":
                                                disablesections = true;
                                                break;
                                            case "RETURNMESSAGE":
                                                disable_rmes = true;
                                                break;
                                            case "URLSECTIONS":
                                                disableurlsections = true;
                                                break;
                                        }
                                    } else {
                                        System.out.println(arg+" requires an argument, ignoring..");
                                        logger.logger.warning(" [Args] "+arg+" requires an argument, ignoring.");
                                    }
                                    break;
                                case "-S":
                                case "--statusCodeOnly":
                                    statusonly = true;
                                    break;
                                case "-p":
                                case "--protocol":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        prt = args[i];
                                        c_prt = true;
                                    } else {
                                        System.out.println(arg+" requires an argument, using default protocol.");
                                        logger.logger.warning(" [Args]"+arg+" requires an argument, using default protocol.");
                                    }
                                    break;
                                case "-H":
                                case "--header":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        if (!args[i].trim().equals("") && args[i] != null) {
                                            get_h = true;
                                            if (args[i].contains(",")) {
                                                headers = args[i].split(",");
                                            } else if (args[i].toUpperCase().equals("-ALL")) {
                                                allheaders = true;
                                            } else {
                                                headers = new String[]{args[i]};
                                            }
                                        }
                                    } else {
                                        System.out.println(arg+" requires an argument, ignoring.");
                                        logger.logger.warning(" [Args] "+arg+" requires an argument, ignoring.");
                                    }
                                    break;
                                case "-t":
                                case "--times":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        mpl_t = true;

                                        if (func.isInt(args[i])) {
                                            times = Integer.parseInt(args[i]);
                                        } else {
                                            System.out.println("Error: '" + args[i] + "' Must be int.");
                                            logger.logger.warning(" [Args] Error: '" + args[i] + "' Must be int.");
                                        }
                                    } else {
                                        System.out.println("invalid option -- '" + args[i] + "'");
                                        logger.logger.warning(" [Args] invalid option -- '" + args[i] + "'");
                                    }
                                    break;
                                case "-r":
                                case "--readlist":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        url_flnme = args[i];

                                        urls.addAll(function.readlist(args[i]));
                                        multi_url = true;
                                    }
                                    break;
                                case "-h":
                                case "--help":
                                    function.DisplayHelp();
                                    break;
                                case "-s":
                                case "--sleepfor":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;

                                        if (func.isInt(args[i])) {
                                            sleeptime = Integer.parseInt(args[i]);
                                        } else {
                                            System.out.println("Error: '" + args[i] + "' Must be int.");
                                            logger.logger.warning(" [Args] Error: '" + args[i] + "' Must be int.");
                                        }
                                    } else {
                                        System.out.println("invalid option -- '" + args[i] + "'");
                                        logger.logger.warning(" [Args] invalid option -- '" + args[i] + "'");
                                    }
                                    break;
                                case "-g":
                                case "--getpremission":
                                    arg = args[i];
                                    if (args.length > i + 1) {
                                        i++;
                                        getprm = true;
                                        String[] prems = args[i].split(",");
                                        for (String prem : prems) {
                                            switch (prem.toUpperCase()) {
                                                case "GETACTIONS": // Add to getpremissions if it matches avaliable premissons.
                                                case "GETNAME":
                                                    getpremissions.add(prem.toUpperCase());
                                                    break;
                                                case "-ALL":
                                                    getallprm = true;
                                                    break;
                                                default:
                                                    function.Custom_help(prem, "--getpremission", false, false);
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    function.Custom_help(args[i], "", true, true);
                                    break;
                            }
                        }
                    }
                args_start();
            } else {
                Default_start();
            }
        }
        catch (Exception e) {
            System.out.println("General Args Exception on , Class: " + e.getClass() + ", Exception: " + e.getMessage());
            logger.logger.severe("main(String[] args): " + "General Args Exception on, Class: " + e.getClass() + ", Exception: " + e.getMessage());
        }
    }

    public static void args_start() {
        if (multi_url)
            System.out.println("Getting status(es) of '" + url_flnme + "'..");
        for (int x = 0; x < times; x++) {
            try {
                TimeUnit.SECONDS.sleep(sleeptime);
            } catch (InterruptedException e) {
                System.out.println("The sleep was interrupted: " + e.getMessage());
                logger.logger.severe("The sleep was interrupted: " + e.getMessage());
            }
            if (!urls.isEmpty()) {
                if (urls.get(0) != null) {
                    for (String s : urls) {
                        if (c_prt) {
                            connector.get_status(prt + s);
                        } else {
                            handle_protch(s);
                        }
                    }
                }
                else {
                    if (debug) { for (String s : urls) System.out.println(s); }
                    else { System.out.println(urls.get(1)); }
                }
            }
            else {
                System.out.println("HTT_TEST: Missing url(s), unable to continue.");
                System.out.println("\nTry `HTT_TEST --help' for help.");
            }
        }
    }

    public static void handle_protch(String url_) {
        if (function.check_protocol(url_)) {
            connector.get_status(url_);
        } else {
            connector.get_status("https://" + url_);
        }
    }


    public static void Default_start() {
        Scanner input = new Scanner(System.in);

        System.out.print("URL: ");
        url = input.nextLine();
        if (function.isNullOrEmpty(url)) {
            System.out.println("Entered URL is null or empty.");
            System.exit(0);
        } else {
            if (url.contains(",")) {
                String[] urls = url.split(",");
                for (String s : urls) {
                    handle_protch(s.trim());
                }
            }
            else {
                handle_protch(url);
            }
        }
    }
}
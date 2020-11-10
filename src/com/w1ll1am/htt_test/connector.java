package com.w1ll1am.htt_test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class connector {

    private static HttpURLConnection connection;
    private static String requestMethod = "GET";
    private static String sectionspace = "    ";

    private static void check_premissions() {
        try {
            if (Main.disablesections) { sectionspace = ""; }
            for (String getpremission : Main.getpremissions) {
                switch (getpremission.toUpperCase()) {
                    case "GETACTIONS":
                        System.out.println(sectionspace + connection.getPermission().getActions());
                        break;
                    case "GETNAME":
                        if (Main.c_prt) {
                            System.out.println(sectionspace + Main.prt + connection.getPermission().getName());
                        } else {
                            if (function.check_protocol(connection.getPermission().getName())) {
                                System.out.println(sectionspace + connection.getPermission().getName());
                            } else {
                                System.out.println(sectionspace + "https://" + connection.getPermission().getName());
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid premission: '" + getpremission + "'.");
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void get_status(String uri) {
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection(); // Add connection section like ---w1ll1am04.github.io---

            if (!Main.disableurlsections) {
                System.out.println("\n---------- " + uri + " ----------");
            }

            logger.logger.info(" get_status: Attempting to open a connection to '" + uri + "'.");
            connection.setRequestMethod(requestMethod);
            logger.logger.info(" get_status: using requestmethod: " + requestMethod);
            connection.connect();

            if (Main.get_h) {
                if (Main.allheaders) {
                    System.out.println(connection.getHeaderFields());
                } else {
                    for (String header : Main.headers) {
                        System.out.println(function.SorrundWithChar("[-]", connection.getHeaderField(header)));
                    }
                }
                System.out.println();
            }

            if (Main.getprm) {
                if (!Main.disablesections) {
                    System.out.println("PREMISSIONS:");
                }
                if (Main.getallprm) {
                    System.out.println(connection.getPermission() + "\n");
                } else {
                    check_premissions();
                }
            }


            if (!Main.disable_rmes) {
                if (!Main.statusonly) {
                    System.out.println("'" + uri + "' returned " + connection.getResponseCode() + " " + connection.getResponseMessage());
                } else {
                    System.out.println(connection.getResponseCode());
                }
            }

            connection.disconnect();
        } catch (ProtocolException e) {
            System.out.println("An protocol exception has occured on URI: "+uri+". Exception: " + e.getMessage());
            logger.logger.severe(" get_status: An protocol exception has occured. Exception: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception. URI: " + uri + ", Exception: " + e.getMessage());
            logger.logger.severe(" get_status: " + "Malformed URL Exception. URI: " + uri + ", Exception: " + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("ClassCAST Exception! URI: " + uri + ", Exception: " + e.getMessage());
            logger.logger.severe(" get_status: " + "ClassCAST Exception! URI: " + uri + ", Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General URL Exception on " + uri + ", Class: " + e.getClass() + ", Exception: " + e.getMessage());
            logger.logger.severe(" get_status: " + "General URL Exception on " + uri + ", Class: " + e.getClass() + ", Exception: " + e.getMessage());
        }
    }
}

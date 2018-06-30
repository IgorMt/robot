package com.im.robot;

import com.im.robot.services.PlayService;

import java.util.Scanner;

/**
 * @author imaltsev
 * @since 30/06/18
 * <p>
 * Entry class with the main method
 * The main method reads commands and transfers them to service, then output results are returned
 * to console.
 */
public class RobotApplication {

    public static void main(String[] args) {

        System.out.println("Start Robot Play.");
        PlayService ps = new PlayService(4, 4);
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.print("Your first command: ");
            System.out.print(">");
            while (true) {
                String cmd = scanner.nextLine();
                if (cmd.equalsIgnoreCase("exit")) {
                    System.out.println("Robot Stopped.");
                    break;
                }
                String resp = ps.runCommand(cmd);
                if (!"".equals(resp)) {
                    System.out.println(resp);
                }
                System.out.print(">");
            }
        }
    }
}

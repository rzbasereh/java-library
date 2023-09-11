package org.basereh;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CLI cli = new CLI(scanner);
            cli.mainLoop();
        }
    }
}
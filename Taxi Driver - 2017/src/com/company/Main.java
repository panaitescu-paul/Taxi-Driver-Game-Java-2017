/*
*---------------------------------------------
*   Paul Panaitescu
*   Taxi Driver - Mandatory Assignment - SWC
*   [17.SEP.2017]
*---------------------------------------------
*/


package com.company;
import java.util.Scanner;

public class Main {

    //console colors
    public static String reset = "\u001B[0m";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";
    public static String cyan = "\u001B[36m";
    public static String yellow = "\u001B[33m";

    //taxi 1
    public static long taxi1Start = 0;
    public static long taxi1Stop = 0;
    public static long taxi1Pause = 0;
    public static boolean taxi1Active = false;
    public static boolean taxi1Unpose = false;
    public static double taxi1Time = 0;

    //taxi 2
    public static long taxi2Start = 0;
    public static long taxi2Stop = 0;
    public static long taxi2Pause = 0;
    public static boolean taxi2Active = false;
    public static boolean taxi2Unpose = false;
    public static double taxi2Time = 0;

    //taxi 3
    public static long taxi3Start = 0;
    public static long taxi3Stop = 0;
    public static long taxi3Pause = 0;
    public static boolean taxi3Active = false;
    public static boolean taxi3Unpose = false;
    public static double taxi3Time = 0;

    //taxi 4
    public static long taxi4Start = 0;
    public static long taxi4Stop = 0;
    public static long taxi4Pause = 0;
    public static boolean taxi4Active = false;
    public static boolean taxi4Unpose = false;
    public static double taxi4Time = 0;

    //taxi 5
    public static long taxi5Start = 0;
    public static long taxi5Stop = 0;
    public static long taxi5Pause = 0;
    public static boolean taxi5Active = false;
    public static boolean taxi5Unpose = false;
    public static double taxi5Time = 0;

    //taxi 6
    public static long taxi6Start = 0;
    public static long taxi6Stop = 0;
    public static long taxi6Pause = 0;
    public static boolean taxi6Active = false;
    public static boolean taxi6Unpose = false;
    public static double taxi6Time = 0;

    //taxi 7
    public static long taxi7Start = 0;
    public static long taxi7Stop = 0;
    public static long taxi7Pause = 0;
    public static boolean taxi7Active = false;
    public static boolean taxi7Unpose = false;
    public static double taxi7Time = 0;

    //taxi 8
    public static long taxi8Start = 0;
    public static long taxi8Stop = 0;
    public static long taxi8Pause = 0;
    public static boolean taxi8Active = false;
    public static boolean taxi8Unpose = false;
    public static double taxi8Time = 0;

    //taxi 9
    public static long taxi9Start = 0;
    public static long taxi9Stop = 0;
    public static long taxi9Pause = 0;
    public static boolean taxi9Active = false;
    public static boolean taxi9Unpose = false;
    public static double taxi9Time = 0;

    //taxi 10
    public static long taxi10Start = 0;
    public static long taxi10Stop = 0;
    public static long taxi10Pause = 0;
    public static boolean taxi10Active = false;
    public static boolean taxi10Unpose = false;
    public static double taxi10Time = 0;

    public static void main(String[] args) {

        showMenu();
        taxi();

    }

    public static void taxi() {
        int input, index;
        double driveTime;

        while (true) {
            input = chooseMenu();

            if (input == 1) { //Start
                if (checkTaxisMoving()) { //Start (1 taxi at a time)
                    showMenu();
                } else {

                    showTaxis();
                    index = chooseTaxi();

                    if (getTaxiActive(index) == false && getTaxiPause(index) > 0) {// UNPAUSE
                        setTaxiStart(index, System.currentTimeMillis());
                        setTaxiPause(index, 0);
                        setTaxiActive(index, true);
                        setTaxiUnpose(index, true);
                        System.out.println(cyan + "Taxi " + index +": Unpause " + reset);

                    } else if (getTaxiActive(index) == false) { // Start
                        setTaxiStart(index, System.currentTimeMillis());
                        setTaxiActive(index, true);
                        System.out.println(cyan + "Taxi " + index + " STARTED" + reset);
                        showMenu();

                    } else {
                        System.out.println(red + "Taxi " + index + " is already Started! Stop Drive before Starting another!" + reset);
                        showMenu();
                    }
                }

            } else if (input == 2) { //Pause

                showTaxis();
                index = chooseTaxi();
                if (getTaxiActive(index)) {
                    setTaxiPause(index, System.currentTimeMillis());
                    System.out.println(cyan + "Taxi " + index + " PAUSED" + reset);

                    //midway time
                    driveTime = (getTaxiPause(index) - getTaxiStart(index))/ 1000.0; // in seconds (as a double)
                    driveTime = Math.round(driveTime * 100d) / 100d; //round up to 2 digits

                    System.out.println("Time 1 : " + getTaxiTime(index) );
                    System.out.println("Time 2: " + driveTime );
                    setTaxiTime(index, getTaxiTime(index) + driveTime); //midway + current drive time
                    System.out.println("Total time: " + getTaxiTime(index));

                    setTaxiActive(index, false);
                    driveTime = 0;
                    showMenu();

                } else {
                    System.out.println(red + "The choosen Taxi is not Started yet." + reset);
                    showMenu();
                }

            } else if (input == 3) { //Stop

                showTaxis();
                index = chooseTaxi();

                if (getTaxiTime(index) > 0 && getTaxiUnpose(index) && getTaxiPause(index) > 0) { // Start -> Pause -> Unpose -> Pause -> Stop

                    System.out.println("Start -> Pause -> Unpose -> Pause -> Stop ");
                    System.out.println("Total time: " + getTaxiTime(index));

                    setTaxiStop(index, System.currentTimeMillis());
                    setTaxiActive(index, false);
                    System.out.println(cyan + "Taxi " + index + " STOPPED" + reset);
                    showBill(getTaxiTime(index));
                    setTaxiReset(index);
                    showMenu();

                } else if (getTaxiTime(index) > 0 && getTaxiUnpose(index)) { // Start -> Pause -> Unpose -> Stop

                    System.out.println("Start -> Pause -> Unpose -> Stop ");
                    setTaxiStop(index, System.currentTimeMillis());

                    driveTime = (getTaxiStop(index) - getTaxiStart(index))/ 1000.0; // in seconds (as a double)
                    driveTime = Math.round(driveTime * 100d) / 100d; //round up to 2 digits

                    System.out.println("Time 1 : " + getTaxiTime(index) );
                    System.out.println("Time 2: " + driveTime );
                    setTaxiTime(index, getTaxiTime(index) + driveTime); //midway + current drive time
                    System.out.println("Total time: " + getTaxiTime(index));
                    driveTime = 0;

                    setTaxiActive(index, false);
                    System.out.println(cyan + "Taxi " + index + " STOPPED" + reset);
                    showBill(getTaxiTime(index));
                    setTaxiReset(index);
                    showMenu();

                } else if (getTaxiTime(index) > 0) { // Start -> Pause -> Stop

                    System.out.println("Start -> Pause -> Stop ");
                    driveTime = (getTaxiPause(index) - getTaxiStart(index))/ 1000.0; // in seconds (as a double)
                    driveTime = Math.round(driveTime * 100d) / 100d; //round up to 2 digits

                    System.out.println("Time 1 : " + getTaxiTime(index) );
                    setTaxiTime(index, driveTime);
                    System.out.println("Total time: " + getTaxiTime(index));
                    setTaxiActive(index, false);
                    System.out.println(cyan + "Taxi " + index + " STOPPED" + reset);
                    showBill(getTaxiTime(index));
                    setTaxiReset(index);
                    showMenu();

                } else if (getTaxiActive(index)) { // Start -> Stop
                    System.out.println("Start -> Stop ");
                    setTaxiStop(index, System.currentTimeMillis());

                    driveTime = (getTaxiStop(index) - getTaxiStart(index))/ 1000.0; // in seconds (as a double)
                    driveTime = Math.round(driveTime * 100d) / 100d; //round up to 2 digits

                    System.out.println("Time 1 : " + getTaxiTime(index) );
                    System.out.println("Time 2: " + driveTime );
                    setTaxiTime(index, getTaxiTime(index) + driveTime); //midway + current drive time
                    System.out.println("Total time: " + getTaxiTime(index));

                    setTaxiActive(index, false);
                    System.out.println(cyan + "Taxi " + index + " STOPPED" + reset);
                    driveTime = 0;
                    showBill(getTaxiTime(index));
                    setTaxiReset(index);
                    showMenu();

                } else {

                    System.out.println(red + "The choosen Taxi is not Started yet. Stop a driving or Paused taxi." + reset);
                    showMenu();
                }

            } else if (input == 4) { //Bill
                showTaxis();
                index = chooseTaxi();
                showBill(getTaxiTime(index));

            } else if (input == 5) { //Free Ride
                showTaxis();
                index = chooseTaxi();
                showBillFree(getTaxiTime(index));
            }
        }
    }

    // Getters

    public static long getTaxiStart(int taxi) {
        switch (taxi) {
            case 1:
                return taxi1Start;
            case 2:
                return taxi2Start;
            case 3:
                return taxi3Start;
            case 4:
                return taxi4Start;
            case 5:
                return taxi5Start;
            case 6:
                return taxi6Start;
            case 7:
                return taxi7Start;
            case 8:
                return taxi8Start;
            case 9:
                return taxi9Start;
            case 10:
                return taxi10Start;
        }
        return -1;
    }

    public static long getTaxiStop(int taxi) {
        switch (taxi) {
            case 1:
                return taxi1Stop;
            case 2:
                return taxi2Stop;
            case 3:
                return taxi3Stop;
            case 4:
                return taxi4Stop;
            case 5:
                return taxi5Stop;
            case 6:
                return taxi6Stop;
            case 7:
                return taxi7Stop;
            case 8:
                return taxi8Stop;
            case 9:
                return taxi9Stop;
            case 10:
                return taxi10Stop;
        }
        return -1;
    }

    public static long getTaxiPause(int taxi) {
        switch (taxi) {
            case 1:
                return taxi1Pause;
            case 2:
                return taxi2Pause;
            case 3:
                return taxi3Pause;
            case 4:
                return taxi4Pause;
            case 5:
                return taxi5Pause;
            case 6:
                return taxi6Pause;
            case 7:
                return taxi7Pause;
            case 8:
                return taxi8Pause;
            case 9:
                return taxi9Pause;
            case 10:
                return taxi10Pause;
        }
        return -1;
    }

    public static boolean getTaxiActive(int taxi) {
        switch (taxi) {
            case 1:
                return taxi1Active;
            case 2:
                return taxi2Active;
            case 3:
                return taxi3Active;
            case 4:
                return taxi4Active;
            case 5:
                return taxi5Active;
            case 6:
                return taxi6Active;
            case 7:
                return taxi7Active;
            case 8:
                return taxi8Active;
            case 9:
                return taxi9Active;
            case 10:
                return taxi10Active;
        }
        return false;
    }

    public static boolean getTaxiUnpose(int taxi) {
        switch (taxi) {
            case 1:
                return taxi1Unpose;
            case 2:
                return taxi2Unpose;
            case 3:
                return taxi3Unpose;
            case 4:
                return taxi4Unpose;
            case 5:
                return taxi5Unpose;
            case 6:
                return taxi6Unpose;
            case 7:
                return taxi7Unpose;
            case 8:
                return taxi8Unpose;
            case 9:
                return taxi9Unpose;
            case 10:
                return taxi10Unpose;
        }
        return false;
    }


    public static double getTaxiTime(int taxi) {
        switch (taxi) {
            case 1:
                return taxi1Time;
            case 2:
                return taxi2Time;
            case 3:
                return taxi3Time;
            case 4:
                return taxi4Time;
            case 5:
                return taxi5Time;
            case 6:
                return taxi6Time;
            case 7:
                return taxi7Time;
            case 8:
                return taxi8Time;
            case 9:
                return taxi9Time;
            case 10:
                return taxi10Time;
        }
        return -1;
    }

    ///End Getters

    //Setters

    public static void setTaxiStart(int taxi, long time) {
        switch (taxi) {
            case 1:
                taxi1Start = time;
                break;
            case 2:
                taxi2Start = time;
                break;
            case 3:
                taxi3Start = time;
                break;
            case 4:
                taxi4Start = time;
                break;
            case 5:
                taxi5Start = time;
                break;
            case 6:
                taxi6Start = time;
                break;
            case 7:
                taxi7Start = time;
                break;
            case 8:
                taxi8Start = time;
                break;
            case 9:
                taxi9Start = time;
                break;
            case 10:
                taxi10Start = time;
                break;
        }
    }

    public static void setTaxiStop(int taxi, long time) {
        switch (taxi) {
            case 1:
                taxi1Stop = time;
                break;
            case 2:
                taxi2Stop = time;
                break;
            case 3:
                taxi3Stop = time;
                break;
            case 4:
                taxi4Stop = time;
                break;
            case 5:
                taxi5Stop = time;
                break;
            case 6:
                taxi6Stop = time;
                break;
            case 7:
                taxi7Stop = time;
                break;
            case 8:
                taxi8Stop = time;
                break;
            case 9:
                taxi9Stop = time;
                break;
            case 10:
                taxi10Stop = time;
                break;
        }
    }

    public static void setTaxiPause(int taxi, long time) {
        switch (taxi) {
            case 1:
                taxi1Pause = time;
                break;
            case 2:
                taxi2Pause = time;
                break;
            case 3:
                taxi3Pause = time;
                break;
            case 4:
                taxi4Pause = time;
                break;
            case 5:
                taxi5Pause = time;
                break;
            case 6:
                taxi6Pause = time;
                break;
            case 7:
                taxi7Pause = time;
                break;
            case 8:
                taxi8Pause = time;
                break;
            case 9:
                taxi9Pause = time;
                break;
            case 10:
                taxi10Pause = time;
                break;
        }
    }

    public static void setTaxiActive(int taxi, boolean value) {
        switch (taxi) {
            case 1:
                taxi1Active = value;
                break;
            case 2:
                taxi2Active = value;
                break;
            case 3:
                taxi3Active = value;
                break;
            case 4:
                taxi4Active = value;
                break;
            case 5:
                taxi5Active = value;
                break;
            case 6:
                taxi6Active = value;
                break;
            case 7:
                taxi7Active = value;
                break;
            case 8:
                taxi8Active = value;
                break;
            case 9:
                taxi9Active = value;
                break;
            case 10:
                taxi10Active = value;
                break;
        }
    }

    public static void setTaxiUnpose(int taxi, boolean value) {
        switch (taxi) {
            case 1:
                taxi1Unpose = value;
                break;
            case 2:
                taxi2Unpose = value;
                break;
            case 3:
                taxi3Unpose = value;
                break;
            case 4:
                taxi4Unpose = value;
                break;
            case 5:
                taxi5Unpose = value;
                break;
            case 6:
                taxi6Unpose = value;
                break;
            case 7:
                taxi7Unpose = value;
                break;
            case 8:
                taxi8Unpose = value;
                break;
            case 9:
                taxi9Unpose = value;
                break;
            case 10:
                taxi10Unpose = value;
                break;
        }
    }

    public static void setTaxiTime(int taxi, double time) {
        switch (taxi) {
            case 1:
                taxi1Time = time;
                break;
            case 2:
                taxi2Time = time;
                break;
            case 3:
                taxi3Time = time;
                break;
            case 4:
                taxi4Time = time;
                break;
            case 5:
                taxi5Time = time;
                break;
            case 6:
                taxi6Time = time;
                break;
            case 7:
                taxi7Time = time;
                break;
            case 8:
                taxi8Time = time;
                break;
            case 9:
                taxi9Time = time;
                break;
            case 10:
                taxi10Time = time;
                break;
        }
    }
    // End Setters

    // Reset
    public static void setTaxiReset(int taxi) {
        switch (taxi) {
            case 1:
                taxi1Start = 0;
                taxi1Stop = 0;
                taxi1Pause = 0;
                taxi1Active = false;
                taxi1Unpose = false;
                taxi1Time = 0;
                break;
            case 2:
                taxi2Start = 0;
                taxi2Stop = 0;
                taxi2Pause = 0;
                taxi2Active = false;
                taxi2Unpose = false;
                taxi2Time = 0;
                break;
            case 3:
                taxi3Start = 0;
                taxi3Stop = 0;
                taxi3Pause = 0;
                taxi3Active = false;
                taxi3Unpose = false;
                taxi3Time = 0;
                break;
            case 4:
                taxi4Start = 0;
                taxi4Stop = 0;
                taxi4Pause = 0;
                taxi4Active = false;
                taxi4Unpose = false;
                taxi4Time = 0;
                break;
            case 5:
                taxi5Start = 0;
                taxi5Stop = 0;
                taxi5Pause = 0;
                taxi5Active = false;
                taxi5Unpose = false;
                taxi5Time = 0;
                break;
            case 6:
                taxi6Start = 0;
                taxi6Stop = 0;
                taxi6Pause = 0;
                taxi6Active = false;
                taxi6Unpose = false;
                taxi6Time = 0;
                break;
            case 7:
                taxi7Start = 0;
                taxi7Stop = 0;
                taxi7Pause = 0;
                taxi7Active = false;
                taxi7Unpose = false;
                taxi7Time = 0;
                break;
            case 8:
                taxi8Start = 0;
                taxi8Stop = 0;
                taxi8Pause = 0;
                taxi8Active = false;
                taxi8Unpose = false;
                taxi8Time = 0;
                break;
            case 9:
                taxi9Start = 0;
                taxi9Stop = 0;
                taxi9Pause = 0;
                taxi9Active = false;
                taxi9Unpose = false;
                taxi9Time = 0;
                break;
            case 10:
                taxi10Start = 0;
                taxi10Stop = 0;
                taxi10Pause = 0;
                taxi10Active = false;
                taxi10Unpose = false;
                taxi10Time = 0;
                break;
        }
    }

    public static boolean checkTaxisMoving() {
        for (int i=1;i<=10;i++) {
            if (getTaxiActive(i)) {
                System.out.println(red + "taxi: " + i + " is moving. Pause or stop a taxi." + reset);
                return true;
            }
        }
        return false;
    }

    public static int chooseTaxi() {
        int taxiNumber = input();
        // check for valid input
        if (taxiNumber == 1 || taxiNumber == 2 || taxiNumber == 3 || taxiNumber == 4 || taxiNumber == 5 || taxiNumber == 6 || taxiNumber == 7 || taxiNumber == 8 || taxiNumber == 9 || taxiNumber == 10) { //Taxi 1, 2, 3, ... , 10
            return taxiNumber;
        }
        return chooseTaxi();
    }

    public static int chooseMenu() {
        int number = input();
        // check for valid input
        if (number == 1 || number == 2 || number == 3 || number == 4 || number == 5 ) {
            return number;
        }
        return chooseMenu();
    }

    public static void showTaxis() {
        System.out.println("----------------------------------------");
        System.out.print("Pick a Taxi: ");

        for (int i=1;i<=10;i++) {
            if (getTaxiActive(i)) {
                System.out.print(green + i + " " + reset); //Driving taxis are green
            } else if (getTaxiTime(i) > 0) {
                System.out.print(yellow + i + " " + reset); //Stopped taxis are yellow
            } else if (getTaxiActive(i)== false){
                System.out.print(i + " "); //Stationary taxis are black
            }
        }
        System.out.println("\n----------------------------------------");
    }

    public static int input() {
        Scanner scanner = new Scanner(System.in);
        int number;

        //get valid inputs
        do {
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("%s is not a valid number.\n", input);
            }
            number = scanner.nextInt();
        } while (number < 0);
        return number;
    }

    public static void showMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to TAXI DRIVER! Choose option and hit Enter: ");
        System.out.println("[1] Start Taxi");
        System.out.println("[2] Pause Taxi");
        System.out.println("[3] Stop Taxi");
        System.out.println("[4] Ask for Price");
        System.out.println("[5] Free Ride");
        System.out.println("----------------------------------------");
    }

    public static void showBill(double time) {
        double price = time*2.25;
        price = Math.round(price * 100d) / 100d; //round up to 2 digits
        System.out.println("----------------------------------------");
        System.out.println("BILL: ");
        System.out.println("Time: " + green + time + reset);
        System.out.println("Price per secound: " + green + "$2.25" + reset);
        System.out.println("Total Price: " + green + "$" + price + reset);
        System.out.println("----------------------------------------");
    }

    public static void showBillFree(double time) {
        System.out.println("----------------------------------------");
        System.out.println("FREE RIDE BILL: ");
        System.out.println("Time: " + green + time + reset);
        System.out.println("Price per secound: " + green + "$0" + reset);
        System.out.println("Total Price: " + green + "$" + 0 + reset);
        System.out.println("----------------------------------------");
    }

}
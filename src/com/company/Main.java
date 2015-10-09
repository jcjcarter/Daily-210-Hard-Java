package com.company;



public class Main {

    public static void main(String[] args) {
        int[] loc = new int[] {0, 0, 90}; //x, y, heading in degrees
        final int MAX_CYCLES = 1_000_000;
      //  System.out.println(">>>>>>>>>>> "+MAX_CYCLES);
        int cycles = 0;
        outer:
        while (cycles < MAX_CYCLES) {
            for (int i = 0; i < args[0].length(); i++) {
                loc = executeCommand(args[0].charAt(i), loc);
                //System.out.printf("%c: (%d, %d) Heading: %d%n", args[0].charAt(i), loc[0], loc[1], loc[2]);
                if (loc[0] == 0 && loc[1] == 0 && loc[2] == 90 && i == args[0].length() - 1) {
                    cycles++;
                    break outer;
                }
            }
            cycles++;
        }
        if (cycles < MAX_CYCLES)
            System.out.printf("Loop detected! %s cycle(s) to complete loop.", cycles);
        else
            System.out.printf("No loop detected after %d cycles!", MAX_CYCLES);
    }

    public static int[] executeCommand(char command, int[] loc) {
        switch (command) {
            case 'R': loc[2] = loc[2] - 90 >= 0 ? loc[2] - 90 : 270; break;
            case 'L': loc[2] = loc[2] + 90 < 360 ? loc[2] + 90 : 0; break;
            case 'S':
                switch (loc[2]) {
                    case 90:  loc[1] = loc[1] + 1; break;
                    case 0:   loc[0] = loc[0] + 1; break;
                    case 270: loc[1] = loc[1] - 1; break;
                    case 180: loc[0] = loc[0] - 1; break;
                }
        }
        return loc;
    }
}

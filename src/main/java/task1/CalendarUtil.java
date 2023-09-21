package task1;


import java.util.Scanner;

public class CalendarUtil {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the year:");
        int year = scanner.nextInt();

        System.out.println("Enter the month (1-12):");
        int month = scanner.nextInt();



        printCalendar(year, month);
    }

    private static void printCalendar(int year, int month) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        String[] days = {"S", "M", "T", "W", "T", "F", "S"};

        int dayOfWeek = zeller(year, month, 1);

        int daysInMonth = getDaysInMonth(year, month);

        System.out.println(months[month - 1] + " " + year);
        for (String day : days) {
            System.out.print(day + "  ");
        }
        System.out.println();

        int i;
        for (i = 0; i < dayOfWeek; i++) {
            System.out.print("   ");
        }

        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%2d ", day);
            if ((i + day) % 7 == 0) {
                System.out.println();
            }
        }
    }

    private static int zeller(int year, int month, int day) {
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int h = (day + (13 * (month + 1)) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
        return (h + 5) % 7;
    }

    private static int getDaysInMonth(int year, int month) {
        if (month == 2) {
            return (isLeapYear(year) ? 29 : 28);
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }
}

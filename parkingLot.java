import java.util.Scanner;
import java.util.Scanner.*;
import java.math.*;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;
import java.math.*;
import java.util.InputMismatchException;

//Carpark Program: Team C
//Group Members: Akib Kazi, Elyse Luckenbach, Kennette James Maddela, Andres Gene, Ashif Shaik
class parkingLot {
    static void newLot( Slots arr[], int totalSlots) {
        Scanner sc = new Scanner(System.in);

        //Request Car's Plate #
        System.out.print("Enter the Plate Number of the Car: ");
        String num = sc.nextLine();
        System.out.println("");
        //Request Car's Make
        System.out.print("Enter Make of the Car: ");
        String make = sc.nextLine();
        System.out.println("\nSuccessfully Added Car");

        //Fill random vacant slot, assigns int s to a random integer between 0 and the total amount of slots. The do while loop checks if the array slot is filled and if it is not it assigns s to that slot.
        int s = (int)Math.floor(Math.random() * totalSlots);
        do{
            s = (int)Math.floor(Math.random() * totalSlots);
        }while(arr[s].filled != false);

        //passing variables
        arr[s].filled = true;
        arr[s].number = num;
        arr[s].makeCar = make;

    }

    static void emptyLot(Slots arr[], int num){

        // emptying occupied lot, if vacant then its already empty
        if(arr[num-1].filled == true){
            arr[num-1].filled = false;
            arr[num-1].number = "";
            System.out.println("\nSlot "+ num + " is emptied successfully\n");
        }else {
            System.out.println("\nSlot "+ num + " is already empty\n");
        }

    }

    static void available(Slots arr[]){
        int vacant = 0, occupied = 0;

        //print out vacant and occupied slots including their plate # and make
        for(int i = 0; i<arr.length; i++){
            if(arr[i].filled == false){
                System.out.println("Lot - "+ (i+1)+":\n");
                vacant += 1;
            }
            if(arr[i].filled == true){
                System.out.println("Lot - " + (i+1) + ": " + arr[i].number + ", " + arr[i].makeCar + "\n");
                occupied += 1;
            }
        }

        //print out number of occupied and vacant slots
        System.out.println("Number of Vacant Slots: " + vacant);
        System.out.println("Number of Occupied Slots: " + occupied + "\n");
    }

    public static void main(String[] args) {
        System.out.println("Greetings, Welcome to the Carpark Program!");
        Scanner sc = new Scanner(System.in);

        //request total number of slots from the user
        int totalSlots = 0;

        Boolean condition = false;
        String num0;
        do {
            try {
                System.out.print("Enter the Total # Parking Spaces: ");
                num0 = sc.nextLine();
                if(num0 != null) {
                    int num = Integer.valueOf(num0);
                    totalSlots = num;
                    condition = true;}

            } catch (Exception e) {
                System.out.println("Invalid Input");

            }
        } while (condition == false || totalSlots <= 0);


        //array with totalSlots as the number of index
        Slots[] arr = new Slots[totalSlots];
        for(int i = 0; i < totalSlots; i++){
            arr[i] = new Slots(false,"");
        }

        int sel = 1;
        do{
            //Main Menu
            System.out.println("\nEnter 1 to add new car to the lot" +"\n"+ "Enter 2 to remove the car to the lot" +"\n"+ "Enter 3 to show available slots" +"\n"+ "Enter 4 to Exit Program");
            sel = sc.nextInt();
            System.out.println("");

            //Ensures userinput is correct
            if((sel < 1) || (sel > 4)) {
                do {
                    System.out.println("Invalid Command, Please Try Again.");
                    System.out.print("Enter a Command: ");
                    sel = sc.nextInt();
                }
                while ((sel < 1) || (sel > 4));
            }

            switch (sel) {
                case 1:
                    //calls add car method: option #1
                    boolean allFilled = true;
                    for(int i = 0; i < totalSlots; i++){
                        if(arr[i].filled == false){
                            allFilled = false; // checks if all the slots are filled,
                        }
                    }
                    if(allFilled == false){
                        newLot(arr, totalSlots); // add a car to the slot
                    }else {
                        System.out.println("All slots are filled");
                    }
                    break;
                case 2:
                    //calls remove care method: option #2
                    int lotNum = 0;
                    for(int i = 0; i<arr.length; i++){
                        if(arr[i].filled == false){
                            System.out.println("Lot - "+ (i+1)+"\n");
                        }
                        if(arr[i].filled == true){
                            System.out.println("Lot - " + (i+1) + ": " + arr[i].number + ", " + arr[i].makeCar + "\n");

                        }
                    }
                    //ensures lot number is valid to be emptied
                    do {
                        System.out.println("Enter lot number to empty");
                        lotNum = sc.nextInt();
                        if ((lotNum < 1) || (lotNum > totalSlots )){
                            System.out.println("Invalid lot number, Please Try again.");
                        }
                    }
                    while ((lotNum < 1) || (lotNum > totalSlots ));
                    emptyLot(arr, lotNum);
                    break;
                case 3:
                    //calls the show available slots method: option 3
                    available(arr);
                    break;

            }
            //Exit program
            if (sel == 4){
                System.out.println("Carpark Program is now Exiting...");
                System.exit(0);
            }

            //do-while loop reiterates as long as it satisfies requirements
        }while ( sel > 0 && sel < 4);
    }
}

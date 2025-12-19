import java.util.Scanner;

import java.util.InputMismatchException;


// INTERFACES


// base interface for all vehicles
interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}


// interface specific to Cars
interface CarVehicle {
    void setNumDoors(int doors);
    int getNumDoors();
    void setFuelType(String fuelType); // e.g Petrol, Diesel, electric
    String getFuelType();
}

// interface specific to motorcycles
interface MotorVehicle {
    void setNumWheels(int wheels);
    int getNumWheels();
    void setBikeType(String type); // e.g. Sport, Cruiser, Off-road
    String getBikeType();
}

// interface specific to Trucks
interface TruckVehicle {
    void setCargoCapacity(double tons);
    double getCargoCapacity();
    void setTransmissionType(String type); // e.g. Manual, Automatic
    String getTransmissionType();
}


// CLASSES (Implementation)


class Car implements Vehicle, CarVehicle {
    private String make, model, fuelType;
    private int year, numDoors;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }


    // implementing Vehicle methods
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    
    // implementing CarVehicle methods
    public void setNumDoors(int doors) { this.numDoors = doors; }
    public int getNumDoors() { return numDoors; }

    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public String getFuelType() { return fuelType; }

    public String toString() {
        return "CAR: " + year + " " + make + " " + model + 
               " | Doors: " + numDoors + " | Fuel: " + fuelType;
    }
}


class Motorcycle implements Vehicle, MotorVehicle {
    private String make, model, bikeType;
    private int year, numWheels;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    // Implementing MotorVehicle methods
    public void setNumWheels(int wheels) { this.numWheels = wheels; }
    public int getNumWheels() { return numWheels; }
    public void setBikeType(String type) { this.bikeType = type; }
    public String getBikeType() { return bikeType; }

    public String toString() {
        return "MOTORCYCLE: " + year + " " + make + " " + model + 
               " | Wheels: " + numWheels + " | Type: " + bikeType;
    }
}


class Truck implements Vehicle, TruckVehicle {
    private String make, model, transmissionType;
    private int year;
    private double cargoCapacity;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    // implementing TruckVehicle method
    public void setCargoCapacity(double tons) { this.cargoCapacity = tons; }
    public double getCargoCapacity() { return cargoCapacity; }
    public void setTransmissionType(String type) { this.transmissionType = type; }
    public String getTransmissionType() { return transmissionType; }

    public String toString() {
        return "TRUCK: " + year + " " + make + " " + model + 
               " | Cargo: " + cargoCapacity + " tons | Trans: " + transmissionType;
    }
}


// MAIN PROGRAM (Administrator Interface)


public class VehicleRentalAgency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("=======================================");
        System.out.println("Vehicle Rental Agency Management System");
        System.out.println("=======================================");


        while (choice != 4) {
            System.out.println("\n--- Select Vehicle Type to Register ---");
            System.out.println("1. Add Car");
            System.out.println("2. Add Motorcycle");
            System.out.println("3. Add Truck");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            // Error Handling: input validation for menu choice
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // clear newline character
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
                continue;
            }

            if (choice == 4) {
                System.out.println("Exiting system...");
                break;
            }

            // Common inputs for all vehicles
            System.out.print("Enter Make: ");
            String make = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            int year = 0;
            

            // Error Handling: loop until valid year is entered
            boolean validYear = false;
            while (!validYear) {
                try {
                    System.out.print("Enter Year: ");
                    year = scanner.nextInt();
                    scanner.nextLine(); // clear newline
                    validYear = true;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Year must be a number.");
                    scanner.nextLine();
                }
            }
            // branch based on vehicle type
            switch (choice) {
                case 1: // CAR
                    Car car = new Car(make, model, year);
                    
                    System.out.print("Enter Number of Door: ");
                    if (scanner.hasNextInt()) {
                        car.setNumDoors(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        System.out.println("Invalid input. Defaulting to 4.");
                        car.setNumDoors(4);
                        scanner.nextLine();
                    }

                    System.out.print("Enter Fuel Type (Petrol/Diesel/Electric): ");
                    car.setFuelType(scanner.nextLine());
                    
                    System.out.println("\n>>> Vehicle Registered Successfully!");
                    System.out.println(car.toString());
                    break;

                case 2: // MOTORCYCLE
                    Motorcycle moto = new Motorcycle(make, model, year);
                    
                    System.out.print("Enter Number of wheels: ");
                    if (scanner.hasNextInt()) {
                        moto.setNumWheels(scanner.nextInt());
                        scanner.nextLine();
                    } else {
                        moto.setNumWheels(2); // Default
                        scanner.nextLine();
                    }

                    System.out.print("Enter Type (Sport/Cruiser/Off-road): ");
                    moto.setBikeType(scanner.nextLine());

                    System.out.println("\n>>> Vehicle Registered Successfully!");
                    System.out.println(moto.toString());
                    break;

                case 3: // TRUCK
                    Truck truck = new Truck(make, model, year);

                    System.out.print("Enter Cargo Capacity (tons): ");
                    if (scanner.hasNextDouble()) {
                        truck.setCargoCapacity(scanner.nextDouble());
                        scanner.nextLine();
                    } else {
                        truck.setCargoCapacity(1.0); // Default
                        scanner.nextLine();
                    }

                    System.out.print("Enter Transmission (Manual/Automatic): ");
                    truck.setTransmissionType(scanner.nextLine());

                    System.out.println("\n>>> Vehicle Registered Successfully!");
                    System.out.println(truck.toString());
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }
        scanner.close();
    }
}
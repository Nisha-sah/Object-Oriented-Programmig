package studentmanagement;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Parent class representing a person in the hospital system.
 */
class Person {
    protected int personID;
    protected String name;
    protected int age;
    protected String gender;
    protected String address;
    protected String contactDetails;

    public Person(int personID, String name, int age, String gender, String address, String contactDetails) {
        this.personID = personID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.contactDetails = contactDetails;
    }

    /**
     * Displays personal details of the person.
     */
    public void viewPersonalDetails() {
        System.out.println("ID: " + personID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Address: " + address);
        System.out.println("Contact: " + contactDetails);
    }
}

/**
 * Patient class representing a hospital patient.
 */
class Patient extends Person {
    private int patientID;

    public Patient(int personID, String name, int age, String gender, String address, String contactDetails, int patientID) {
        super(personID, name, age, gender, address, contactDetails);
        this.patientID = patientID;
    }

    /**
     * Books an appointment for this patient.
     */
    public void bookAppointment(Appointment appointment) {
        System.out.println(name + " booked an appointment on " + appointment.getAppointmentDate() + " at " + appointment.getAppointmentTime());
    }

    public int getPatientID() {
        return patientID;
    }
}

/**
 * Doctor class representing a doctor in the hospital.
 */
class Doctor extends Person {
    private int doctorID;
    private String department;

    public Doctor(int personID, String name, int age, String gender, String address, String contactDetails, int doctorID, String department) {
        super(personID, name, age, gender, address, contactDetails);
        this.doctorID = doctorID;
        this.department = department;
    }

    /**
     * Views details of a patient.
     */
    public void seePatientDetails(Patient patient) {
        System.out.println("Doctor " + name + " is viewing patient details:");
        patient.viewPersonalDetails();
    }

    /**
     * Prescribes a treatment for a patient.
     */
    public void prescribeTreatment(Treatment treatment, Patient patient) {
        System.out.println("Doctor " + name + " prescribed " + treatment.getType() + " to " + patient.name);
    }
}

/**
 * Appointment class representing an appointment between patient and doctor.
 */
class Appointment {
    private int appointmentID;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Patient patient;

    public Appointment(int appointmentID, LocalDate appointmentDate, LocalTime appointmentTime, Patient patient) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * Reschedules the appointment to a new date and time.
     */
    public void rescheduleAppointment(LocalDate newDate, LocalTime newTime) {
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        System.out.println("Appointment rescheduled to " + newDate + " at " + newTime);
    }

    /**
     * Cancels the appointment.
     */
    public void cancelAppointment() {
        System.out.println("Appointment canceled for " + patient.name);
    }

    /**
     * Views appointment details.
     */
    public void viewAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
        System.out.println("Patient: " + patient.name);
    }
}

/**
 * Treatment class representing a treatment prescribed by a doctor.
 */
class Treatment {
    private int treatmentID;
    private String type;
    private String description;

    public Treatment(int treatmentID, String type, String description) {
        this.treatmentID = treatmentID;
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    /**
     * Updates treatment description.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
        System.out.println("Treatment description updated.");
    }

    /**
     * Views treatment details.
     */
    public void viewTreatmentDetails() {
        System.out.println("Treatment ID: " + treatmentID);
        System.out.println("Type: " + type);
        System.out.println("Description: " + description);
    }
}

/**
 * Main class to run the Hospital Management System.
 */
public class HospitalManagementSystem {
    public static void main(String[] args) {
        // Create patient
        Patient patient1 = new Patient(101, "John Doe", 30, "Male", "123 Street", "555-1234", 1001);

        // Create doctor
        Doctor doctor1 = new Doctor(201, "Dr. Smith", 45, "Female", "456 Avenue", "555-5678", 2001, "Cardiology");

        // Create treatment
        Treatment treatment1 = new Treatment(301, "Medication", "Blood pressure medication");

        // Create appointment
        LocalDate appointmentDate = LocalDate.of(2026, 1, 1);
        LocalTime appointmentTime = LocalTime.of(10, 30);
        Appointment appointment1 = new Appointment(401, appointmentDate, appointmentTime, patient1);

        // Patient books appointment
        patient1.bookAppointment(appointment1);

        // View appointment details
        appointment1.viewAppointmentDetails();

        // Doctor sees patient details
        doctor1.seePatientDetails(patient1);

        // Doctor prescribes treatment
        doctor1.prescribeTreatment(treatment1, patient1);

        // View treatment details
        treatment1.viewTreatmentDetails();

        // Reschedule the appointment
        LocalDate newDate = LocalDate.of(2026, 1, 2);
        LocalTime newTime = LocalTime.of(11, 0);
        appointment1.rescheduleAppointment(newDate, newTime);
        appointment1.viewAppointmentDetails();

        // Cancel appointment
        appointment1.cancelAppointment();
    }
}

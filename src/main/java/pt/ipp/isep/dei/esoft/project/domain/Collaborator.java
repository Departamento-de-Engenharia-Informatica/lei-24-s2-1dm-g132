package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Represents a collaborator in the system.
 */
public class Collaborator {

    /**
     * The name of the collaborator.
     */
    private String name;

    /**
     * The birthdate of the collaborator.
     */
    private Calendar birthdate;

    /**
     * The admission date of the collaborator.
     */
    private Calendar admissionDate;

    /**
     * The address of the collaborator.
     */
    private String address;

    /**
     * The phone number of the collaborator.
     */
    private int phoneNumber;

    /**
     * The email of the collaborator.
     */
    private String email;

    /**
     * The taxpayer number of the collaborator.
     */
    private int taxpayerNumber;

    /**
     * The identification document number of the collaborator.
     */
    private String identificationDocumentNumber;

    /**
     * The identification document type of the collaborator.
     */
    private DocType identificationDocumentType;

    /**
     * The job of the collaborator.
     */
    private Job job;

    /**
     * The skills assigned to the collaborator.
     */
    private List<Skill> skills;

    /**
     * Enumeration representing different types of identification documents.
     */
    private static enum DocType{
        CC { @Override public String toString() { return "CC"; } },
        BI { @Override public String toString() { return "BI"; } },
        PASSPORT { @Override public String toString() { return "Passport"; } };

        /**
         * Validates the identification number based on the document type.
         *
         * @param type   The type of identification document.
         * @param number The identification number to validate.
         * @return True if the number is valid for the given document type, false otherwise.
         */
        public static boolean isValidNumber(DocType type, String number) {
            switch (type) {
                case CC:
                    String ccPattern = "\\d{8} \\d [A-Z]{2}\\d";
                    return number.trim().matches(ccPattern);
                case BI:
                    String biPattern = "\\d{8} \\d";
                    return number.trim().matches(biPattern);
                case PASSPORT:
                    String passportPattern = "[A-Z]{2}\\d{6}";
                    return number.trim().matches(passportPattern);
                default:
                    return false;
            }
        }
    }

    /**
     * Constructs a collaborator with the provided details.
     *
     * @param name                        The name of the collaborator.
     * @param birthdate                   The birthdate of the collaborator (format: year/month/day).
     * @param admissionDate               The admission date of the collaborator (format: year/month/day).
     * @param address                     The address of the collaborator (format: street, zipcode, city).
     * @param phoneNumber                 The phone number of the collaborator.
     * @param email                       The email of the collaborator.
     * @param taxpayerNumber              The taxpayer number of the collaborator.
     * @param identificationDocumentType  The type of identification document of the collaborator.
     * @param identificationDocumentNumber The identification document number of the collaborator.
     * @param job                         The job of the collaborator.
     */
    public Collaborator(String name, String birthdate, String admissionDate, String address, int phoneNumber, String email,
                        int taxpayerNumber, String identificationDocumentType, String identificationDocumentNumber, Job job){
        setName(name);
        setBirthdate(birthdate);
        setAdmissionDate(admissionDate);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setTaxpayerNumber(taxpayerNumber);
        setIdentificationDocumentType(identificationDocumentType);
        setIdentificationDocumentNumber(identificationDocumentNumber);
        this.job = job;
        this.skills = new ArrayList<>();
    }

    /**
     * Constructs a new collaborator with the provided details.
     * @param name The name of the collaborator.
     * @param birthdate The birthdate of the collaborator.
     * @param admissionDate The admission date of the collaborator.
     * @param address The address of the collaborator.
     * @param phoneNumber The phone number of the collaborator.
     * @param email The email address of the collaborator.
     * @param taxpayerNumber The taxpayer number of the collaborator.
     * @param identificationDocumentType The type of identification document.
     * @param identificationDocumentNumber The identification document number.
     * @param job The job of the collaborator.
     * @param skills The skills of the collaborator.
     */
    public Collaborator(String name, Calendar birthdate, Calendar admissionDate, String address, int phoneNumber, String email,
                        int taxpayerNumber, DocType identificationDocumentType, String identificationDocumentNumber, Job job, List<Skill> skills){
        setName(name);
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setTaxpayerNumber(taxpayerNumber);
        this.identificationDocumentType = identificationDocumentType;
        setIdentificationDocumentNumber(identificationDocumentNumber);
        this.job = job;
        this.skills = skills;
    }

    /**
     * Gets the name of the collaborator.
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the identification document number of the collaborator.
     * @return The identification document number.
     */
    public String getIdentificationDocumentNumber() {
        return identificationDocumentNumber;
    }

    /**
     * Gets the skills of the collaborator.
     * @return The list of skills of the collaborator.
     */
    public List<Skill> getSkills() {
        return List.copyOf(skills);
    }

    /**
     * Sets the name of the collaborator.
     * @param name The name to set.
     * @throws IllegalArgumentException If the name contains numeric characters,
     * or if it has more than 6 words, or if it's not correctly filled.
     */
    private void setName(String name) {

        if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name cannot contain numbers.");
        }

        String trimmedName = name.trim();

        int wordCount = trimmedName.isEmpty() ? 0 : trimmedName.split("\\s+").length;

        if (wordCount <= 6 && wordCount > 0) {
            this.name = name;
        }
        else if (wordCount == 0)
        {
            throw new IllegalArgumentException("Name wasn't correctly filled.");
        }
        else {
            throw new IllegalArgumentException("Name cannot have more than 6 words.");
        }
    }

    /**
     * Sets the birthdate of the collaborator.
     * @param birthdate The birthdate to set (in the format "year/month/day").
     * @throws IllegalArgumentException If the birthdate format is invalid,
     * or if it contains non-numeric values,
     * or if the collaborator is not at least 18 years old.
     */
    private void setBirthdate(String birthdate) {
        String[] parts = birthdate.trim().split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid birthdate format. Use year/month/day.");
        }

        for (String part : parts) {
            if (!part.matches("\\d+")) {
                throw new IllegalArgumentException("Non-numeric value found in birthdate.");
            }
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Calendar currentDate = Calendar.getInstance();

        currentDate.add(Calendar.YEAR, -18);

        if(!cal.before(currentDate))
        {
            throw new IllegalArgumentException("Collaborator must be at least 18 years old.");
        }

        this.birthdate = cal;
    }

    /**
     * Sets the admission date of the collaborator.
     * @param admissionDate The admission date to set (in the format "year/month/day").
     * @throws IllegalArgumentException If the admission date format is invalid,
     * or if it contains non-numeric values,
     * or if the collaborator is not at least 18 years old at the time of admission.
     */
    private void setAdmissionDate(String admissionDate) {
        String[] parts = admissionDate.trim().split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid admission date format. Use year/month/day.");
        }

        for (String part : parts) {
            if (!part.matches("\\d+")) {
                throw new IllegalArgumentException("Non-numeric value found in admission date.");
            }
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Calendar valDate = (Calendar) this.birthdate.clone();

        valDate.add(Calendar.YEAR, +18);

        if(cal.before(valDate))
        {
            throw new IllegalArgumentException("Collaborator must be at least 18 years old at the time of admission.");
        }

        this.admissionDate = cal;
    }

    /**
     * Sets the address of the collaborator.
     * @param address The address to set (formatted as "street, zipcode, city").
     * @throws IllegalArgumentException If the address format is invalid,
     * or if the street, zipcode, or city are empty,
     * or if the zipcode format is invalid.
     */
    private void setAddress(String address) {
        String[] components = address.trim().split(",");

        if (components.length != 3) {
            throw new IllegalArgumentException("Invalid address format. Use street, zipcode, city.");
        }

        String street = components[0].trim();
        String zipcode = components[1].trim();
        String city = components[2].trim();


        if (street.isEmpty()) {
            throw new IllegalArgumentException("Street cannot be empty.");
        }

        if (zipcode.isEmpty() || !zipcode.matches("\\d{4}-\\d{3}")) {
            throw new IllegalArgumentException("Zipcode must have the format '0000-000'.");
        }

        if (city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        }

        this.address = address;
    }

    /**
     * Sets the phone number of the collaborator.
     * @param phoneNumber The phone number to set.
     * @throws IllegalArgumentException If the phone number format is invalid.
     */
    private void setPhoneNumber(int phoneNumber) {
        String phoneNumberStr = String.valueOf(phoneNumber);

        if (!phoneNumberStr.matches("(91|92|93|95|96)\\d{7}")) {
            throw new IllegalArgumentException("Invalid phone number format. Phone number must start with 91, 92, 93, 95, or 96 and have 9 digits.");
        }

        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the taxpayer number of the collaborator.
     * @param taxpayerNumber The taxpayer number to set.
     * @throws IllegalArgumentException If the taxpayer number format is invalid.
     */
    private void setTaxpayerNumber(int taxpayerNumber) {
        String taxpayerNumberStr = String.valueOf(taxpayerNumber);

        if (!taxpayerNumberStr.matches("\\d{9}")) {
            throw new IllegalArgumentException("Invalid taxpayer number. Taxpayer number must have 9 digits.");
        }

        this.taxpayerNumber = taxpayerNumber;
    }

    /**
     * Sets the email address of the collaborator.
     * @param email The email address to set.
     * @throws IllegalArgumentException If the email address format is invalid.
     */
    private void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email address format.");
        }

        this.email = email;
    }

    /**
     * Sets the identification document type of the collaborator.
     * @param identificationDocumentType The identification document type to set.
     * @throws IllegalArgumentException If the identification document type is invalid.
     */
    private void setIdentificationDocumentType(String identificationDocumentType) {
        if(identificationDocumentType.equalsIgnoreCase(DocType.CC.toString()))
            this.identificationDocumentType = DocType.CC;
        else if(identificationDocumentType.equalsIgnoreCase(DocType.BI.toString()))
            this.identificationDocumentType = DocType.BI;
        else if(identificationDocumentType.equalsIgnoreCase(DocType.PASSPORT.toString()))
            this.identificationDocumentType = DocType.PASSPORT;
        else throw new IllegalArgumentException("Invalid document type.");
    }

    /**
     * Sets the identification document number of the collaborator.
     * @param identificationDocumentNumber The identification document number to set.
     * @throws IllegalArgumentException If the identification document number is invalid
     * for the provided type.
     */
    private void setIdentificationDocumentNumber(String identificationDocumentNumber) {
        if(DocType.isValidNumber(identificationDocumentType, identificationDocumentNumber))
            this.identificationDocumentNumber = identificationDocumentNumber;
        else
            throw new IllegalArgumentException("Invalid identification document number for the provided type.");
    }

    /**
     * Assigns skills to the collaborator.
     * @param selectedSkillsList The list of skills to assign.
     * @return The updated collaborator.
     * @throws IllegalArgumentException If all the provided skills are already assigned to the collaborator.
     */
    public Collaborator assignSkill(List<Skill> selectedSkillsList){
        boolean updated = false;
        for(Skill skill: selectedSkillsList)
        {
            if(!this.skills.contains(skill))
            {
                this.skills.add(skill);
                updated = true;
            }
        }
        if(!updated)
        {
            throw new IllegalArgumentException("Collaborator already had all the skills introduced");
        }
        return this;
    }

    /**
     * Indicates whether some other object is "equal to" this collaborator.
     * @param o The reference object with which to compare.
     * @return true if this collaborator is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Collaborator collaborator = (Collaborator) o;
        return identificationDocumentNumber.equalsIgnoreCase(collaborator.identificationDocumentNumber);
    }

    /**
     * Checks if the collaborator has the same identification number as the provided one.
     * @param collaboratorIdNumber The identification number to compare.
     * @return True if the collaborator has the same identification number, false otherwise.
     */
    public boolean sameIdNumber(String collaboratorIdNumber){
        return this.identificationDocumentNumber.equalsIgnoreCase(collaboratorIdNumber);
    }

    /**
     * Returns a new object that is a copy of this collaborator.
     * @return A new Collaborator object that is a copy of this collaborator.
     */
    @Override
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthdate, this.admissionDate, this.address, this.phoneNumber, this.email,
                this.taxpayerNumber, this.identificationDocumentType, this.identificationDocumentNumber, this.job, new ArrayList<>(this.skills));
    }

    /**
     * Returns a string representation of the collaborator suitable for displaying in a team proposal context.
     * @return The string representation of the collaborator for team proposal display.
     */
    public String toStringTeam() {
        return String.format("Collaborator: %s\n" +
                        "Skills: %s", this.name, this.skills);
    }

    /**
     * Returns a string representation of the Collaborator object.
     * @return A string representation of the Collaborator object.
     */
    @Override
    public String toString() {
        return String.format("Collaborator %s Data:\n" +
                "Birthdate: %d/%d/%d\n" +
                "Admission Date: %d/%d/%d\n" +
                "Address: %s\n" +
                "Phone Number: %d\n" +
                "Email: %s\n" +
                "Taxpayer number: %d\n" +
                "Identification Document Type: %s\n" +
                "Identification Document Number: %s\n" +
                "Job: %s\n" +
                "Skills: %s", this.name, this.birthdate.get(Calendar.YEAR), this.birthdate.get(Calendar.MONTH)+1, this.birthdate.get(Calendar.DAY_OF_MONTH),
                                                    this.admissionDate.get(Calendar.YEAR), this.admissionDate.get(Calendar.MONTH)+1, this.admissionDate.get(Calendar.DAY_OF_MONTH),
                                                    this.address, this.phoneNumber, this.email, this.taxpayerNumber, this.identificationDocumentType.toString(), this.identificationDocumentNumber,
                                                    this.job, this.skills);
    }
}

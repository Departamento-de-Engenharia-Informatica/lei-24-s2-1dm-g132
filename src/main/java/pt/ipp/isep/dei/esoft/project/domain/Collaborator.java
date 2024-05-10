package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Collaborator {
    private String name;
    private Calendar birthdate;
    private Calendar admissionDate;
    private String address;
    private int phoneNumber;
    private String email;
    private int taxpayerNumber;
    private String identificationDocumentNumber;

    private DocType identificationDocumentType;

    private Job job;

    private List<Skill> skills;

    private static enum DocType{
        CC { @Override public String toString() { return "CC"; } },
        BI { @Override public String toString() { return "BI"; } },
        PASSPORT { @Override public String toString() { return "Passport"; } };

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

    public String getName() {
        return name;
    }

    public String getIdentificationDocumentNumber() {
        return identificationDocumentNumber;
    }

    public List<Skill> getSkills() {
        return List.copyOf(skills);
    }

    private void setName(String name) {

        if (name.matches(".*\\d.*")) {
            // If the input name contains numeric characters, throw an exception or handle it accordingly
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

    private void setPhoneNumber(int phoneNumber) {
        String phoneNumberStr = String.valueOf(phoneNumber);

        if (!phoneNumberStr.matches("(91|92|93|95|96)\\d{7}")) {
            throw new IllegalArgumentException("Invalid phone number format. Phone number must start with 91, 92, 93, 95, or 96 and have 9 digits.");
        }

        this.phoneNumber = phoneNumber;
    }

    private void setTaxpayerNumber(int taxpayerNumber) {
        String taxpayerNumberStr = String.valueOf(taxpayerNumber);

        if (!taxpayerNumberStr.matches("\\d{9}")) {
            throw new IllegalArgumentException("Invalid taxpayer number. Taxpayer number must have 9 digits.");
        }

        this.taxpayerNumber = taxpayerNumber;
    }

    private void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email address format.");
        }

        this.email = email;
    }

    private void setIdentificationDocumentType(String identificationDocumentType) {
        if(identificationDocumentType.equalsIgnoreCase(DocType.CC.toString()))
            this.identificationDocumentType = DocType.CC;
        else if(identificationDocumentType.equalsIgnoreCase(DocType.BI.toString()))
            this.identificationDocumentType = DocType.BI;
        else if(identificationDocumentType.equalsIgnoreCase(DocType.PASSPORT.toString()))
            this.identificationDocumentType = DocType.PASSPORT;
        else throw new IllegalArgumentException("Invalid document type.");
    }

    private void setIdentificationDocumentNumber(String identificationDocumentNumber) {
        if(DocType.isValidNumber(identificationDocumentType, identificationDocumentNumber))
            this.identificationDocumentNumber = identificationDocumentNumber;
        else
            throw new IllegalArgumentException("Invalid identification document number for the provided type.");
    }

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

    public boolean sameIdNumber(String collaboratorIdNumber){
        return this.identificationDocumentNumber.equalsIgnoreCase(collaboratorIdNumber);
    }

    @Override
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthdate, this.admissionDate, this.address, this.phoneNumber, this.email,
                this.taxpayerNumber, this.identificationDocumentType, this.identificationDocumentNumber, this.job, new ArrayList<>(this.skills));
    }


    public String toStringTeam() {
        return String.format("Collaborator: %s\n" +
                        "Skills: %s", this.name, this.skills);
    }

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

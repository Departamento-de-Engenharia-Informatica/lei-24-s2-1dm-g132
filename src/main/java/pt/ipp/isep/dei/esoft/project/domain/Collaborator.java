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
    private int identificationDocumentNumber;

    private DocType identificationDocumentType;

    private Job job;

    private List<Skill> listSkills = new ArrayList<>();

    private static enum DocType{
        CC { @Override public String toString() { return "CC"; } },
        BI { @Override public String toString() { return "BI"; } },
        PASSPORT { @Override public String toString() { return "Passport"; } };
    }

    public Collaborator(String name, String birthdate, String admissionDate, String address, int phoneNumber, String email,
                        int taxpayerNumber, String identificationDocumentType, int identificationDocumentNumber, Job job){
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
    }

    public Collaborator(String name, Calendar birthdate, Calendar admissionDate, String address, int phoneNumber, String email,
                        int taxpayerNumber, DocType identificationDocumentType, int identificationDocumentNumber, Job job){
        setName(name);
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setTaxpayerNumber(taxpayerNumber);
        setIdentificationDocumentType(identificationDocumentType);
        setIdentificationDocumentNumber(identificationDocumentNumber);
        this.job = job;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setBirthdate(String birthdate) {
        //this.birthdate = birthdate;
        this.birthdate = Calendar.getInstance();
    }

    private void setAdmissionDate(String admissionDate) {
        //this.admissionDate = admissionDate;
        this.admissionDate = Calendar.getInstance();
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setTaxpayerNumber(int taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setIdentificationDocumentType(String identificationDocumentType) {
        if(identificationDocumentType.equalsIgnoreCase(DocType.CC.toString()))
            this.identificationDocumentType = DocType.CC;
        else if(identificationDocumentType.equalsIgnoreCase(DocType.BI.toString()))
            this.identificationDocumentType = DocType.BI;
        else if(identificationDocumentType.equalsIgnoreCase(DocType.PASSPORT.toString()))
            this.identificationDocumentType = DocType.PASSPORT;
        //Outro else e mandar erro
    }

    private void setIdentificationDocumentType(DocType identificationDocumentType) {
        this.identificationDocumentType = identificationDocumentType;
    }

    private void setIdentificationDocumentNumber(int identificationDocumentNumber) {
        this.identificationDocumentNumber = identificationDocumentNumber;
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
        return identificationDocumentNumber == collaborator.identificationDocumentNumber;
    }

    @Override
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthdate, this.admissionDate, this.address, this.phoneNumber, this.email,
                this.taxpayerNumber, this.identificationDocumentType, this.identificationDocumentNumber, this.job);
    }

}

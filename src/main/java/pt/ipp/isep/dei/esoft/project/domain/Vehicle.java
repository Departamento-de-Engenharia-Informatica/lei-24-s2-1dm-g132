package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public class Vehicle {
    public String Plate;
    public String Model;
    public String Brand;
    public String Type;
    public int Tare;
    public int GrossWeight;
    int CurrentKm;
    public Date AcquisitionDate;
    public Date RegistredDate;
    public int MaintenanceCheckupFrequency;

    public Vehicle(String Plate, String Model, String Brand, String Type, int Tare, int GrossWeight, int CurrentKm, Date AcquisitionDate, Date RegistredDate, int MaintenanceCheckupFrequency) {
        this.Plate = Plate;
        this.Model = Model;
        this.Brand = Brand;
        this.Type = Type;
        this.Tare = Tare;
        this.GrossWeight = GrossWeight;
        this.CurrentKm = CurrentKm;
        this.AcquisitionDate = AcquisitionDate;
        this.RegistredDate = RegistredDate;
        this.MaintenanceCheckupFrequency = MaintenanceCheckupFrequency;
    }

    public Date getAcquisitionDate() {
        return AcquisitionDate;
    }

    public int getCurrentKm() {
        return CurrentKm;
    }

    public int getGrossWeight() {
        return GrossWeight;
    }

    public Date getRegistredDate() {
        return RegistredDate;
    }

    public int getTare() {
        return Tare;
    }

    public String getBrand() {
        return Brand;
    }

    public String getModel() {
        return Model;
    }

    public String getPlate() {
        return Plate;
    }

    public String getType() {
        return Type;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        AcquisitionDate = acquisitionDate;
    }

    public int getMaintenanceCheckupFrequency() {
        return MaintenanceCheckupFrequency;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setCurrentKm(int currentKm) {
        CurrentKm = currentKm;
    }

    public void setGrossWeight(int grossWeight) {
        GrossWeight = grossWeight;
    }

    public void setMaintenanceCheckupFrequency(int maintenanceCheckupFrequency) {
        MaintenanceCheckupFrequency = maintenanceCheckupFrequency;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setPlate(String plate) {
        Plate = plate;
    }

    public void setRegistredDate(Date registredDate) {
        RegistredDate = registredDate;
    }

    public void setTare(int tare) {
        Tare = tare;
    }

    public void setType(String type) {
        Type = type;
    }

    public Vehicle clone() {
        return new Vehicle(this.Plate, this.Model, this.Brand, this.Type, this.Tare, this.GrossWeight, this.CurrentKm, this.AcquisitionDate, this.RegistredDate, this.MaintenanceCheckupFrequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Plate == vehicle.Plate;
    }

}

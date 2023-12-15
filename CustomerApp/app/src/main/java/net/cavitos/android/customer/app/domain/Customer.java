package net.cavitos.android.customer.app.domain;

public class Customer {

    private int id;
    private final String name;
    private final String country;
    private final String company;

    private final String photoPath;

    public Customer(int id, String name, String country, String company, String photoPath) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.company = company;
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCompany() {
        return company;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}

package com.example.riley.willyoupleasehiremeapp;

/**
 * Created by Riley on 1/15/2017.
 */

public class Job {
    private String CompanyName;
    private String ContactEmail;
    private String ContactLink;
    private String ContactName;
    private String Latitude;
    private String Longitude;
    private String Description;
    private String PositionTitle;

    public Job()
    {

    }

    public Job(String coName, String coEmail, String coLink, String contName, String lat,
               String lng, String des, String title)
    {
        CompanyName = coName;
        ContactEmail = coEmail;
        ContactLink = coLink;
        ContactName = contName;
        Latitude = lat;
        Longitude = lng;
        Description = des;
        PositionTitle = title;
    }

    public String getCompanyName()
    {
        return CompanyName;
    }

    public String getContactEmail()
    {
        return ContactEmail;
    }

    public String getContactLink()
    {
        return ContactLink;
    }

    public String getContactName()
    {
        return ContactName;
    }

    public String getLatitude()
    {
        return Latitude;
    }

    public String getLongitude()
    {
        return Longitude;
    }

    public String getDescription()
    {
        return Description;
    }

    public String getPositionTitle()
    {
        return PositionTitle;
    }


    public void setCompanyName(String companyName) {
        this.CompanyName = companyName;
    }

    public void setContactEmail(String contactEmail) {
        this.ContactEmail = contactEmail;
    }

    public void setContactLink(String contactLink) {
        this.ContactLink = contactLink;
    }

    public void setContactName(String contactName) {
        this.ContactName = contactName;
    }

    public void setLatitude(String latitude) {
        this.Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.Longitude = longitude;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setTitle(String title) {
        this.PositionTitle = title;
    }
}

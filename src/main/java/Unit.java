import java.io.Serializable;

public class Unit implements Serializable {
    @Save
    private String vendorCode;
    @Save
    private String unitName;
    @Save
    private String unitBrand;
    private String manufacturerCountry;
    private double price;

    public Unit(String vendorCode, String unitName, String unitBrand, String manufacturerCountry, double price) {
        this.vendorCode = vendorCode;
        this.unitName = unitName;
        this.unitBrand = unitBrand;
        this.manufacturerCountry = manufacturerCountry;
        this.price = price;
    }

    public Unit() {
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitBrand() {
        return unitBrand;
    }

    public void setUnitBrand(String unitBrand) {
        this.unitBrand = unitBrand;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "vendorCode='" + vendorCode + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitBrand='" + unitBrand + '\'' +
                ", manufacturerCountry='" + manufacturerCountry + '\'' +
                ", price=" + price +
                '}';
    }
}

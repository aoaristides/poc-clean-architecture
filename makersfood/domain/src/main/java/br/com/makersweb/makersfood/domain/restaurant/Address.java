package br.com.makersweb.makersfood.domain.restaurant;

import br.com.makersweb.makersfood.domain.ValueObject;
import br.com.makersweb.makersfood.domain.city.CityID;

/**
 * @author aaristides
 */
public class Address extends ValueObject {

    private String zipCode;
    private String street;
    private String streeNumber;
    private String complement;
    private String district;
    private CityID city;

    private Address(
            final String zipCode,
            final String street,
            final String streeNumber,
            final String complement,
            final String district,
            final CityID city
    ) {
        this.zipCode = zipCode;
        this.street = street;
        this.streeNumber = streeNumber;
        this.complement = complement;
        this.district = district;
        this.city = city;
    }

    public static Address newAddress(
            final String zipCode,
            final String street,
            final String streeNumber,
            final String complement,
            final String district
    ) {
        return new Address(zipCode, street, streeNumber, complement, district, null);
    }

    public static Address with(
            final String zipCode,
            final String street,
            final String streeNumber,
            final String complement,
            final String district,
            final CityID city
    ) {
        return new Address(zipCode, street, streeNumber, complement, district, city);
    }

    public static Address with(final Address address) {
        return with(address.zipCode, address.street, address.streeNumber, address.complement, address.district, address.city);
    }

    public Address addCity(final CityID cityID) {
        if (cityID == null) {
            return this;
        }
        this.city = cityID;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getStreeNumber() {
        return streeNumber;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public CityID getCity() {
        return city;
    }
}

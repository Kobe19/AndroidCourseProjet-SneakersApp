package danielguirol.project.sneakersapp;

public class JSONData {

    /* ************************************************************************************************************************************************************************************************************
     *          VARIABLES TO RECEIVE DATA                                                                                                                                       *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    private String id;
    private String brand;
    private String colorway;
    private String name;
    private String shoe;
    private String title;
    private String year;
    private String url;
    private String price;


    /* ************************************************************************************************************************************************************************************************************
     *          GETTERS AND SETTERS TO RETRIEVE AND SET THE DATA                                                                                                                                                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    /* ************************************************************************************************************************************************************************************************************
     *          TO STRING  METHOD THAT RETURN THE BRAND NAME FOR EACH REGISTER                                                                                                                                    *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    public String toString() {
        return brand;
    }
}



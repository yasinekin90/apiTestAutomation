
package POJO;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Bookingdates {

    @SerializedName("checkin")
    @Expose
    private String checkin;
    @SerializedName("checkout")
    @Expose
    private String checkout;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bookingdates() {
    }

    /**
     * 
     * @param checkin
     * @param checkout
     */
    public Bookingdates(String checkin, String checkout) {
        super();
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public Bookingdates withCheckin(String checkin) {
        this.checkin = checkin;
        return this;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Bookingdates withCheckout(String checkout) {
        this.checkout = checkout;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Bookingdates.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("checkin");
        sb.append('=');
        sb.append(((this.checkin == null)?"<null>":this.checkin));
        sb.append(',');
        sb.append("checkout");
        sb.append('=');
        sb.append(((this.checkout == null)?"<null>":this.checkout));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

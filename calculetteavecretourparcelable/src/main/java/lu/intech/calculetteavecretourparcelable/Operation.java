package lu.intech.calculetteavecretourparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class Operation implements Parcelable {

    private double ope1;
    private double ope2;
    private char operator;

    public Operation(double ope1, double ope2, char operator) {
        this.ope1 = ope1;
        this.ope2 = ope2;
        this.operator = operator;
    }

    protected Operation(Parcel in) {
        ope1 = in.readDouble();
        ope2 = in.readDouble();
        char[] t = new char[1];
        in.readCharArray(t);
        operator = t[0];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(ope1);
        dest.writeDouble(ope2);
        dest.writeCharArray(new char[]{operator});
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Parcelable.Creator<Operation> CREATOR = new Parcelable.Creator<Operation>() {

        @Override
        public Operation createFromParcel(Parcel in) {
            return new Operation(in);
        }

        @Override
        public Operation[] newArray(int size) {
            return new Operation[size];
        }
    };

    public double getOpe1() {
        return ope1;
    }

    public double getOpe2() {
        return ope2;
    }

    public char getOperator() {
        return operator;
    }
}

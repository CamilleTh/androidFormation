package metier;

import android.os.Parcel;
import android.os.Parcelable;

public class Operation implements Parcelable {
    private double operande1;
    private double operande2;
    private String operateur;

    public double getOperande1() {
        return operande1;
    }

    public double getOperande2() {
        return operande2;
    }

    public String getOperateur(){
        return operateur;
    }

    public Operation(double operande1, double operande2, String operateur) {
        this.operande1 = operande1;
        this.operande2 = operande2;
        this.operateur = operateur;
    }

    public Operation(Parcel parcel) {
        operande1 = parcel.readDouble();
        operande2 = parcel.readDouble();
        operateur = parcel.readString();
    }

    public static final Creator<Operation> CREATOR = new Creator<Operation>(){
        @Override
        public Operation createFromParcel(Parcel parcel) {
            return new Operation(parcel);
        }

        @Override
        public Operation[] newArray(int i) {
            return new Operation[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(operande1);
        parcel.writeDouble(operande2);
        parcel.writeString(operateur);
    }
}

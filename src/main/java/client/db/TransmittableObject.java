package client.db;

import java.util.Arrays;
import java.util.Base64;

public abstract class TransmittableObject {

    // use hashmap instead of array?
    private Attr<?>[] attributes = new Attr<?>[0];

    // should be called to register all attributes that should be included in
    // generated String
    protected void registerAttributes(Attr<?>... attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return Arrays.toString(attributes);

    }

    public String transmittableString() {
        Attr<?>[] filteredAttributes = Arrays.stream(attributes).filter(Attr::isSet).toArray(Attr<?>[]::new);

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < filteredAttributes.length - 1; i++) {


            s.append(filteredAttributes[i].transmittableString()).append(" ");
        }
        s.append(filteredAttributes[filteredAttributes.length - 1].transmittableString());

        return s.toString();
    }

    public static String toBase64String(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] fromBase64String(String data) {
        return Base64.getDecoder().decode((data).getBytes());
    }

    // borrowed from Arrays.toString(Object[] o)
    public static String toString(TransmittableObject[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i].transmittableString());
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

}
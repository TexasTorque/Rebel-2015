package org.texastorque.texastorque20155.constants;

import com.sun.squawk.microedition.io.FileConnection;
import java.io.InputStream;
import javax.microedition.io.Connector;
import org.texastorque.torquelib.util.TorqueUtil;

public class Params {

    public static double LEFT_DRIVE_P = 0.0;
    public static double LEFT_DRIVE_V = 0.0;
    public static double RIGHT_DRIVE_P = 0.0;
    public static double RIGHT_DRIVE_V = 0.0;

    public static void init() {
        //read
        String path = "";
        String content = "";
        try {
            InputStream is = null;
            FileConnection fc = null;
            try {
                fc = (FileConnection) Connector.open(path, Connector.READ);

                if (fc.exists()) {
                    int size = (int) fc.fileSize();
                    is = fc.openInputStream();
                    byte bytes[] = new byte[size];
                    is.read(bytes, 0, size);
                    content = new String(bytes, 0, size);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                    if (null != fc) {
                        fc.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //parse
        String[] lines = TorqueUtil.split(content, "\n");
    }
}

import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;
import jssc.SerialPort;

// 
// Decompiled by Procyon v0.5.36
// 

public class SimpleSerial
{
    Thread reader;
    Thread writer;
    String[] commPorts;
    String[] commPortsOwner;
    String cportName;
    String hoopPort;
    int[] cdata;
    static String lastData;
    static SerialPort serialPort;
    
    static {
        SimpleSerial.lastData = "";
    }
    
    public SimpleSerial() {
        this.reader = null;
        this.writer = null;
        this.hoopPort = "HOOP NOT FOUND";
    }
    
    public void getPorts() {
        System.out.println("Getting Ports with Simple Serial");
        final String[] portNames = SerialPortList.getPortNames();
        this.commPorts = new String[portNames.length];
        for (int i = 0; i < portNames.length; ++i) {
            System.out.println(portNames[i]);
            this.commPorts[i] = portNames[i];
            SimpleSerial.serialPort = new SerialPort(portNames[i]);
            try {
                SimpleSerial.serialPort.openPort();
                SimpleSerial.serialPort.setParams(2400, 8, 1, 0);
                final int mask = 25;
                SimpleSerial.serialPort.setEventsMask(mask);
                SimpleSerial.serialPort.addEventListener((SerialPortEventListener)new SerialPortReader());
                System.out.println("Listening to port");
                boolean gotit = false;
                int loops = 1;
                while (!gotit) {
                    if (SimpleSerial.lastData.startsWith("evoke")) {
                        gotit = true;
                        this.hoopPort = portNames[i];
                        System.out.println("got it");
                        System.out.print("hoopPort set to: ");
                        System.out.println(this.hoopPort);
                    }
                    if (++loops > 10) {
                        gotit = true;
                    }
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SimpleSerial.serialPort.closePort();
                System.out.print("Port Closed");
            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
    
    void connect(final String port, final int[] data) throws Exception {
        this.hoopPort = port;
        (this.writer = new Thread(new SerialWriter(data))).start();
    }
    
    static class SerialPortReader implements SerialPortEventListener
    {
        public void serialEvent(final SerialPortEvent event) {
            System.out.println("Got Serial Event");
            if (event.isRXCHAR()) {
                if (event.getEventValue() == 5) {
                    try {
                        SimpleSerial.lastData = SimpleSerial.serialPort.readString();
                        System.out.println(SimpleSerial.lastData);
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                }
            }
            else if (event.isCTS()) {
                if (event.getEventValue() == 1) {
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if (event.isDSR()) {
                if (event.getEventValue() == 1) {
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }
    
    public class SerialWriter implements Runnable
    {
        int[] data;
        
        public SerialWriter(final int[] data) {
            this.data = data;
        }
        
        @Override
        public void run() {
            Boolean portOpen = false;
            System.out.println("Opening port: ");
            System.out.print(SimpleSerial.this.hoopPort);
            SimpleSerial.serialPort = new SerialPort(SimpleSerial.this.hoopPort);
            try {
                SimpleSerial.serialPort.openPort();
                SimpleSerial.serialPort.setParams(2400, 8, 1, 0);
                portOpen = true;
            }
            catch (SerialPortException e) {
                e.printStackTrace();
            }
            if (portOpen) {
                final int dataLen = this.data.length;
                for (int i = 0; i < 50; ++i) {
                    if (i < dataLen) {
                        try {
                            SimpleSerial.serialPort.writeInt(this.data[i]);
                        }
                        catch (SerialPortException e2) {
                            e2.printStackTrace();
                        }
                        System.out.print(this.data[i]);
                        System.out.print(",");
                    }
                    else {
                        try {
                            SimpleSerial.serialPort.writeInt(0);
                        }
                        catch (SerialPortException e2) {
                            e2.printStackTrace();
                        }
                        System.out.print("0,");
                    }
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ie) {
                        System.out.println("sleep exception");
                    }
                }
                try {
                    SimpleSerial.serialPort.closePort();
                }
                catch (SerialPortException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
}

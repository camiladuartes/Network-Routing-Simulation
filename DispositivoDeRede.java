public abstract class DispositivoDeRede {
    protected String IP;
    protected String MAC;

    public DispositivoDeRede(){};

    public DispositivoDeRede(String MAC){
        this.MAC = MAC;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String mAC) {
        MAC = mAC;
    }
}
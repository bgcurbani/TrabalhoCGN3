public class Camera2D {
    
    private double minX, minY, maxX, maxY;
    private static Camera2D instance;
    
    private final double TAMANHOX = 30;
    private final double TAMANHOY = 30;

    private Camera2D() {
        setMaxX(-TAMANHOX);
        setMinX(TAMANHOX);
        
        setMaxY(-TAMANHOY);
        setMinY(TAMANHOY);
    }
    
    public static Camera2D getInstance(){
        if(instance == null){
            instance = new Camera2D();
        }
        return instance;
    }
    

    public void zoomIn() {
        if (!(maxX == -10 || maxY == -10)) {
            setMaxX(getMaxX() + 10);
            setMinX(getMinX() - 10);
            setMaxY(getMaxY() + 10);
            setMinY(getMinY() - 10);
        }
        else
            System.out.println("Limite de zoom-in");
    }
    
    public void zoomOut() {
        setMaxX(getMaxX() - 10);
        setMinX(getMinX() + 10);
        setMaxY(getMaxY() - 10);
        setMinY(getMinY() + 10);
    }
    
    public void panCima() {
        setMaxY(getMaxY() - 10);
        setMinY(getMinY() - 10);
    }
    
    public void panBaixo() {
        setMaxY(getMaxY() + 10);
        setMinY(getMinY() + 10);
    }
    
    public void panEsquerda() {
        setMaxX(getMaxX() + 10);
        setMinX(getMinX() + 10);
    }
    
    public void panDireita() {
        setMaxX(getMaxX() - 10);
        setMinX(getMinX() - 10);
    }
    
    private void setMaxX(double maxX) {
        this.maxX = maxX;
    }
    
    private void setMinX(double minX) {
        this.minX = minX;
    }
    
    private void setMaxY(double maxY) {
        this.maxY = maxY;
    }
    
    private void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxX() {
        return maxX;
    }
    
    public double getMinX() {
        return minX;
    }
    
    public double getMaxY() {
        return maxY;
    }
    
    public double getMinY() {
        return minY;
    }


}

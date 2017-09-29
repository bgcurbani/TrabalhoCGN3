public class Camera2D {
    
    private double minX, minY, maxX, maxY;
    private static Camera2D instance;
    
    //por garantia, usar multiplos de 5 no zoom
    private final double TAMANHO_ZOOM = 10;
    private double TAMANHOX = 400;
    private double TAMANHOY = 400;

    private Camera2D() {
        setMaxX(-TAMANHOX);
        setMinX(TAMANHOX);
        
        setMaxY(TAMANHOY);
        setMinY(-TAMANHOY);
    }
    
    public static Camera2D getInstance(){
        if(instance == null){
            instance = new Camera2D();
        }
        return instance;
    }
    

    public void zoomIn() {
        if (!(maxX == -10 || maxY == -10)) {
            setMaxX(getMaxX() + TAMANHO_ZOOM);
            setMinX(getMinX() - TAMANHO_ZOOM);
            setMaxY(getMaxY() - TAMANHO_ZOOM);
            setMinY(getMinY() + TAMANHO_ZOOM);
        }
        else
            System.out.println("Limite de zoom-in");
    }
    
    public void zoomOut() {
        setMaxX(getMaxX() - TAMANHO_ZOOM);
        setMinX(getMinX() + TAMANHO_ZOOM);
        setMaxY(getMaxY() + TAMANHO_ZOOM);
        setMinY(getMinY() - TAMANHO_ZOOM);
    }
    
    public void panCima() {
        setMaxY(getMaxY() - TAMANHO_ZOOM);
        setMinY(getMinY() - TAMANHO_ZOOM);
    }
    
    public void panBaixo() {
        setMaxY(getMaxY() + TAMANHO_ZOOM);
        setMinY(getMinY() + TAMANHO_ZOOM);
    }
    
    public void panEsquerda() {
        setMaxX(getMaxX() + TAMANHO_ZOOM);
        setMinX(getMinX() + TAMANHO_ZOOM);
    }
    
    public void panDireita() {
        setMaxX(getMaxX() - TAMANHO_ZOOM);
        setMinX(getMinX() - TAMANHO_ZOOM);
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
    
    public void setTAMANHOX(double TAMANHOX) {
        this.TAMANHOX = TAMANHOX;
    }

    public void setTAMANHOY(double TAMANHOY) {
        this.TAMANHOY = TAMANHOY;
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

    public double getTAMANHOX() {
        return TAMANHOX;
    }

    public double getTAMANHOY() {
        return TAMANHOY;
    }

}

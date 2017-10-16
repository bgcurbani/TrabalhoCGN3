public class Camera2D {
    
    private double minX, minY, maxX, maxY;
    private static Camera2D instance;
    
    //por garantia, usar multiplos de 5 no zoom
    private final double TAMANHO_ZOOM = 10;
    private double TAMANHOX = 400;
    private double TAMANHOY = 400;

    /**
     * Construtor privado da classe Camera2D que inicia os tamanhos máximos
     * e minímos dos eixos X e Y.
     */
    private Camera2D() {
        setMaxX(-TAMANHOX);
        setMinX(TAMANHOX);
        
        setMaxY(TAMANHOY);
        setMinY(-TAMANHOY);
    }
    
    /**
     * Retorna uma instância da classe Camera2D, criando ela se necessário.
     * 
     * @return Instância única da classe Camera2D (Singleton).
     */
    public static Camera2D getInstance(){
        if(instance == null){
            instance = new Camera2D();
        }
        return instance;
    }
    
    /**
     * Aproxima o câmera.
     */
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
    
    /**
     * Afasta a câmera.
     */
    public void zoomOut() {
        setMaxX(getMaxX() - TAMANHO_ZOOM);
        setMinX(getMinX() + TAMANHO_ZOOM);
        setMaxY(getMaxY() + TAMANHO_ZOOM);
        setMinY(getMinY() - TAMANHO_ZOOM);
    }
    
    /**
     * Movimenta a câmera para cima.
     */
    public void panCima() {
        setMaxY(getMaxY() - TAMANHO_ZOOM);
        setMinY(getMinY() - TAMANHO_ZOOM);
    }
    
    /**
     * Movimenta a câmera para baixo.
     */
    public void panBaixo() {
        setMaxY(getMaxY() + TAMANHO_ZOOM);
        setMinY(getMinY() + TAMANHO_ZOOM);
    }
    
    /**
     * Movimenta a câmera para esquerda.
     */
    public void panEsquerda() {
        setMaxX(getMaxX() + TAMANHO_ZOOM);
        setMinX(getMinX() + TAMANHO_ZOOM);
    }
    
    /**
     * Movimenta a câmera para direita.
     */
    public void panDireita() {
        setMaxX(getMaxX() - TAMANHO_ZOOM);
        setMinX(getMinX() - TAMANHO_ZOOM);
    }
    
    /**
     * Define o eixo X máximo.
     * 
     * @param maxX Limite do eixo X.
     */
    private void setMaxX(double maxX) {
        this.maxX = maxX;
    }
    
    /**
     * Define o eixo X mínimo.
     * 
     * @param minX Mínimo do eixo X.
     */
    private void setMinX(double minX) {
        this.minX = minX;
    }
    
    /**
     * Define o eixo Y máximo.
     * 
     * @param maxY Máximo do eixo Y.
     */
    private void setMaxY(double maxY) {
        this.maxY = maxY;
    }
    
    /**
     * Define o eixo Y mínimo.
     * 
     * @param minY Mínimo do eixo Y.
     */
    private void setMinY(double minY) {
        this.minY = minY;
    }
    
    /**
     * Define o tamanho de X.
     * 
     * @param TAMANHOX Tamanho de X.
     */
    public void setTAMANHOX(double TAMANHOX) {
        this.TAMANHOX = TAMANHOX;
    }

    /**
     * Define o tamanho de Y.
     * 
     * @param TAMANHOY Tamanho de Y.
     */
    public void setTAMANHOY(double TAMANHOY) {
        this.TAMANHOY = TAMANHOY;
    }

    /**
     * Retorna o tamanho máximo de X.
     * 
     * @return Tamanho máximo de X em double.
     */
    public double getMaxX() {
        return maxX;
    }
    
    /**
     * Retorna o tamanho mínimo de X.
     * 
     * @return Tamanho mínimo de X em double.
     */
    public double getMinX() {
        return minX;
    }
    
    /**
     * Retorna o tamanho máximo de Y.
     * 
     * @return Tamanho máximo de Y em double.
     */
    public double getMaxY() {
        return maxY;
    }
    
    /**
     * Retorna o tamanho mínimo de Y.
     * 
     * @return Tamanho mínimo de Y em double.
     */
    public double getMinY() {
        return minY;
    }

    /**
     * Retorna o tamanho de X.
     * 
     * @return Tamanho de X em double.
     */
    public double getTAMANHOX() {
        return TAMANHOX;
    }

    /**
     * Retorna o tamanho de Y.
     * 
     * @return Tamanho de Y em double.
     */
    public double getTAMANHOY() {
        return TAMANHOY;
    }

}

public class Camera2D 
{
    private int minX, minY, maxX, maxY;
    private static Camera2D instance;

    private Camera2D() {
    }
    
    public static Camera2D getInstance(){
        if(instance == null){
            instance = new Camera2D();
        }
        return instance;
    }
    

    public void zoomIn() {
        if (!(maxX == -10 || maxY == -10)) {
            maxX += 10;
            minX -= 10;
            maxY += 10;
            minY -= 10;
        }
        else
            System.out.println("Limite de zoom-in");
    }
    
    public void zoomOut() {
        maxX -= 10;
        minX += 10;
        maxY -= 10;
        minY += 10;
    }
    
    public void panCima() {
        maxY -= 10;
        minY -= 10;
    }
    
    public void panBaixo() {
        maxY += 10;
        minY += 10;
    }
    
    public void panEsquerda() {
        maxX += 10;
        minX += 10;
    }
    
    public void panDireita() {
        maxX -= 10;
        maxX -= 10;
    }
    
    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
    
    
}

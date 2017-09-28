
import java.util.LinkedList;

public class Mundo 
{
    private static Mundo instance;
    private Camera2D camera;
    private LinkedList<ObjetoGrafico> listaObjGrafico;
    
    private Mundo() 
    {
        this.camera = Camera2D.getInstance();
        listaObjGrafico = new LinkedList<>();
    }
    
    public static Mundo getInstance()
    {
        if (instance == null)
            instance = new Mundo();
        
        return instance;
    }

    public Camera2D getCamera() {
        return this.camera;
    }

    public LinkedList<ObjetoGrafico> getListaObjGrafico() {
        return listaObjGrafico;
    }

    public void setListaObjGrafico(LinkedList<ObjetoGrafico> listaObjGrafico) {
        this.listaObjGrafico = listaObjGrafico;
    }
}

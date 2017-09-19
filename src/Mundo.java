
import java.util.LinkedList;

public class Mundo 
{
    private static Mundo mundo;
    private Camera camera;
    private LinkedList<ObjetoGrafico> listaObjGrafico;
    
    private Mundo() 
    {
        this.camera = new Camera();
    }
    
    public static Mundo getInstance()
    {
        if (mundo == null)
            mundo = new Mundo();
        
        return mundo;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public LinkedList<ObjetoGrafico> getListaObjGrafico() {
        return listaObjGrafico;
    }

    public void setListaObjGrafico(LinkedList<ObjetoGrafico> listaObjGrafico) {
        this.listaObjGrafico = listaObjGrafico;
    }
}

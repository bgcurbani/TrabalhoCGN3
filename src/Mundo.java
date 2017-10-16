import java.util.LinkedList;

public class Mundo {
    private static Mundo instance;
    private Camera2D camera;
    private LinkedList<ObjetoGrafico> listaObjGrafico;
    
    /**
     * Construtor privado da classe Mundo,
     * onde é criada uma lista de objetos gráficos e uma instancia da câmera.
     */
    private Mundo() {
        this.camera = Camera2D.getInstance();
        listaObjGrafico = new LinkedList<>();
    }
    
    /**
     * Retorna uma instância da classe mundo, criando ela se necessário.
     * 
     * @return Instância única da classe Mundo (Singleton).
     */
    public static Mundo getInstance() {
        if (instance == null)
            instance = new Mundo();
        
        return instance;
    }

    /**
     * Retorna uma instância da classe Camera2D.
     * 
     * @return Instância da classe Camera2D.
     */
    public Camera2D getCamera() {
        return this.camera;
    }

    /**
     * Retorna a lista de objetos gráficos contidos no mundo.
     * 
     * @return LinkedList contendo os objetos gráficos do mundo.
     */
    public LinkedList<ObjetoGrafico> getListaObjGrafico() {
        return listaObjGrafico;
    }

    /**
     * Método para atribuir uma lista de objetos gráficos ao mundo.
     * 
     * @param listaObjGrafico LinkedList de objetos gráficos.
     */
    public void setListaObjGrafico(LinkedList<ObjetoGrafico> listaObjGrafico) {
        this.listaObjGrafico = listaObjGrafico;
    }
}

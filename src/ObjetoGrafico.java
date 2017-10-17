import java.util.LinkedList;
import javax.media.opengl.GL;

public final class ObjetoGrafico {
    GL gl;
    private float tamanho = 2.0f;

    private int primitiva = GL.GL_LINE_STRIP;
    private LinkedList<Ponto4D> vertices;
    private BoundingBox bBox = null;
    private boolean ehLineLoop;
    private boolean isPronto=false;
    private float red = 0;
    private float green = 0;
    private float blue = 0;
//    private ObjetoGrafico objFilho = null;
    private LinkedList<ObjetoGrafico> objsFilhos = null;


//	private int primitiva = GL.GL_POINTS;
//	private Ponto4D[] vertices = { new Ponto4D(10.0, 10.0, 0.0, 1.0) };	

    private Transformacao4D matrizObjeto = new Transformacao4D();

    /// Matrizes temporarias que sempre sao inicializadas com matriz Identidade entao podem ser "static".
    private static Transformacao4D matrizTmpTranslacao = new Transformacao4D();
    private static Transformacao4D matrizTmpTranslacaoInversa = new Transformacao4D();
    private static Transformacao4D matrizTmpEscala = new Transformacao4D();		
//	private static Transformacao4D matrizTmpRotacaoZ = new Transformacao4D();
    private static Transformacao4D matrizGlobal = new Transformacao4D();
//	private double anguloGlobal = 0.0;

    /**
     * Construtor da classe ObjetoGrafico que cria uma LinkedList de vértices.
     */
    public ObjetoGrafico() {
        vertices = new LinkedList();
    }

    /**
     * Atribui a instância do GL. 
     * 
     * @param gl instância do GL
     */
    public void atribuirGL(GL gl) {
        this.gl = gl;
    }

    /**
     * Retorna o tamanho do vértice.
     * 
     * @return Tamanho do vértice em double.
     */
    public double obterTamanho() {
        return tamanho;
    }

    /**
     * Retorna a primitiva atual.
     * 
     * @return Primitiva atualmente selecionada.
     */
    public double obterPrimitava() {
        return primitiva;
    }

    /**
     * Desenha o vértice.
     */
    public void desenha() {
        gl.glColor3f(red, green, blue);
        gl.glLineWidth(tamanho);
        gl.glPointSize(tamanho);

        gl.glPushMatrix();
        gl.glMultMatrixd(matrizObjeto.GetDate(), 0);
        gl.glBegin(primitiva);
        for (byte i = 0; i < vertices.size(); i++) {
            gl.glVertex2d(vertices.get(i).obterX(), vertices.get(i).obterY());
        }
        gl.glEnd();

        //////////// ATENCAO: chamar desenho dos filhos... 
        if (objsFilhos != null) {
            for (int i = 0; i < objsFilhos.size(); i++) {
                if (objsFilhos.get(i).getVertices().size() > 0) {
                    objsFilhos.get(i).desenha();
                }
            }
        }
        
        gl.glPopMatrix();
    }

    /**
     * Cria um objeto gráfico filho.
     */
    public ObjetoGrafico criaFilho() {
        if(objsFilhos == null){
            objsFilhos = new LinkedList();
        }
        ObjetoGrafico objFilho = new ObjetoGrafico();
        objFilho.atribuirGL(gl);
        objsFilhos.add(objFilho);
        return objFilho;
    }
    
    /**
     * Retona a lista de filhos do objeto atual.
     * 
     * @return LinkedList<ObjetoGrafico> objsFilhos.
     */
    public LinkedList<ObjetoGrafico> getListaFilhos(){
        return objsFilhos;
    }
        
    /**
     * Define a cor do objeto gráfico.
     * 
     * @param r Cor vermelha.
     * @param g Cor verde.
     * @param b Cor azul.
     */
    public void setColor(float r, float g, float b){
        this.red = r;
        this.green = g;
        this.blue = b;
        
    }
    
    /**
     * Define a translação do objeto gráfico.
     * 
     * @param tx Translação no eixo X.
     * @param ty Translação no eixo Y.
     * @param tz Translação no eixo Z.
     */
    public void translacaoXYZ(double tx, double ty, double tz) {
        Transformacao4D matrizTranslate = new Transformacao4D();
        matrizTranslate.atribuirTranslacao(tx,ty,tz);
        matrizObjeto = matrizTranslate.transformMatrix(matrizObjeto);		
    }

    /**
     * Define a escala do objeto gráfico.
     * 
     * @param Sx Escala no eixo X.
     * @param Sy Escala no eixo Y.
     */
    public void escalaXYZ(double Sx,double Sy) {
        Transformacao4D matrizScale = new Transformacao4D();		
        matrizScale.atribuirEscala(Sx,Sy,1.0);
        matrizObjeto = matrizScale.transformMatrix(matrizObjeto);
    }

    ///TODO: erro na rotacao
    public void rotacaoZ(double angulo) {
        //anguloGlobal += 10.0; // rotacao em 10 graus
        //Transformacao4D matrizRotacaoZ = new Transformacao4D();		
        //matrizRotacaoZ.atribuirRotacaoZ(Transformacao4D.DEG_TO_RAD * angulo);
        //matrizObjeto = matrizRotacaoZ.transformMatrix(matrizObjeto);
    }

    /**
     * Atribui a identidade ao objeto gráfico.
     */
    public void atribuirIdentidade() {
//		anguloGlobal = 0.0;
            matrizObjeto.atribuirIdentidade();
    }

    /**
     * Define a escala do objeto gráfico baseado em um ponto fixo.
     * 
     * @param escala Tamanho da escala.
     * @param ptoFixo Ponto fixo usado como base para a escala.
     */
    public void escalaXYZPtoFixo(double escala, Ponto4D ptoFixo) {
        matrizGlobal.atribuirIdentidade();

        //Adicionando essa troca de sinal fez funcionar(?)
        ptoFixo.inverterSinal(ptoFixo);
        matrizTmpTranslacao.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

        matrizTmpEscala.atribuirEscala(escala, escala, 1.0);
        matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

        ptoFixo.inverterSinal(ptoFixo);
        matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);
        
        matrizObjeto = matrizObjeto.transformMatrix(matrizGlobal);
    }

    /**
     * Rotaciona o objeto gráfico baseado em um ponto fixo.
     * 
     * @param angulo Ângulo da rotação.
     * @param ptoFixo Ponto fixo usado como base para a rotação.
     */
    public void rotacaoZPtoFixo(double angulo, Ponto4D ptoFixo) {
        matrizGlobal.atribuirIdentidade();

        ptoFixo.inverterSinal(ptoFixo);
        matrizTmpTranslacao.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

        matrizTmpEscala.atribuirRotacaoZ(Transformacao4D.DEG_TO_RAD * angulo);
        matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

        ptoFixo.inverterSinal(ptoFixo);
        matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

        matrizObjeto = matrizObjeto.transformMatrix(matrizGlobal);
    }

    /**
     * Exibe a matriz de objetos.
     */
    public void exibeMatriz() {
        matrizObjeto.exibeMatriz();
    }

    /**
     * Exibe os vértices de um objeto gráfico.
     */
    public void exibeVertices() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("P"+i+"[" + vertices.get(i).obterX() + ",\t" + vertices.get(i).obterY() + ",\t" + vertices.get(i).obterZ() + ",\t" + vertices.get(i).obterW() + "]");
        }
//        System.out.println("P0[" + vertices[0].obterX() + "," + vertices[0].obterY() + "," + vertices[0].obterZ() + "," + vertices[0].obterW() + "]");
//        System.out.println("P1[" + vertices[1].obterX() + "," + vertices[1].obterY() + "," + vertices[1].obterZ() + "," + vertices[1].obterW() + "]");
//        System.out.println("P2[" + vertices[2].obterX() + "," + vertices[2].obterY() + "," + vertices[2].obterZ() + "," + vertices[2].obterW() + "]");
//        System.out.println("P3[" + vertices[3].obterX() + "," + vertices[3].obterY() + "," + vertices[3].obterZ() + "," + vertices[3].obterW() + "]");
//        System.out.println("anguloGlobal:" + anguloGlobal);
    }

    /**
     * Adiciona um ponto ao objeto gráfico.
     * 
     * @param x Posição do ponto no eixo X.
     * @param y Posição do ponto no eixo Y.
     */
    public void AdicionaPonto(double x, double y) {
        Ponto4D ponto = new Ponto4D();
        Ponto4D ponto2 = new Ponto4D();
        ponto.atribuirX(x);
        ponto.atribuirY(y);
        
        ponto2.atribuirX(x);
        ponto2.atribuirY(y);
        

        if (vertices.isEmpty()) {
            vertices.add(ponto);
            vertices.add(ponto2);
        } else {
            vertices.set(vertices.size() - 1, ponto);
            vertices.add(ponto2);
          }
    }

    /**
     * Troca a primitiva do objeto gráfico.
     * 
     * @param desenhaLoop Se é ou não para finalizar o loop do objeto.
     * @param fecha Se é o último ponto adicionado ao objeto gráfico.
     */
    public void TrocaPrimitiva(boolean desenhaLoop, boolean fecha) {
        ehLineLoop = desenhaLoop;
        
        if (fecha && desenhaLoop)
            this.primitiva = GL.GL_LINE_LOOP;
        else
            this.primitiva = GL.GL_LINE_STRIP;
    }

    /**
     * Finaliza o objeto gráfico.
     */
    public void setPronto() {
        if(vertices.size() >= 3 && this.ehLineLoop){
            TrocaPrimitiva(ehLineLoop, true);
        }
        isPronto = true;
        criaBBox();
    }

    /**
     * Retorna a lista de vértices do objeto gráfico.
     * 
     * @return LinkedList contendo os vértices do objeto gráfico.
     */
    public LinkedList<Ponto4D> getVertices() {
        return vertices;
    }

    /**
     * Retorna a primitiva atual do objeto gráfico.
     * 
     * @return boolean dizendo se é LineLoop(1) ou LineStrip(0). 
     */
    public boolean getPrimitiva(){
        return ehLineLoop;
    }
    
    /**
     * Retorna se true se o objeto grafico é um ponto.
     * 
     * @return true se o objeto é apenas um ponto, false caso tenha mais vértices.
     */
    public boolean isPronto(){
        return isPronto;
    }
    
    /**
     * Retorna a BoundingBox do objeto gráfico.
     * 
     * @return BoundingBox do objeto gráfico.
     */
    public BoundingBox getBbox(){
        return bBox;
    }
    
    /**
     * Criar a BoundingBox do objeto gráfico.
     */
    private void criaBBox() {
        double menorX, maiorX;
        double menorY, maiorY;
        double menorZ, maiorZ;
        Ponto4D pontoAtual = vertices.get(0);
        menorX = maiorX = pontoAtual.obterX();
        menorY = maiorY = pontoAtual.obterY();
        menorZ = maiorZ = pontoAtual.obterZ();
        
        for (int i = 1; i < vertices.size(); i++) {
            pontoAtual = vertices.get(i);
            
            if (menorX > pontoAtual.obterX()) {
                menorX = pontoAtual.obterX();
            } else {
                if (maiorX < pontoAtual.obterX()) {
                    maiorX = pontoAtual.obterX();
                }
            }
            
            if (menorY > pontoAtual.obterY()) {
                menorY = pontoAtual.obterY();
            } else {
                if (maiorY < pontoAtual.obterY()) {
                    maiorY = pontoAtual.obterY();
                }
            }
            
            if (menorZ > pontoAtual.obterZ()) {
                menorZ = pontoAtual.obterZ();
            } else {
                if (maiorZ < pontoAtual.obterZ()) {
                    maiorZ = pontoAtual.obterZ();
                }
            }
            
        }
        
            bBox = new BoundingBox(menorX, menorY, menorZ, maiorX, maiorY, maiorZ);
            bBox.processarCentroBBox();

    }

}

import javax.media.opengl.GL;

public final class BoundingBox {
    private double menorX;
    private double menorY;
    private double menorZ;
    private double maiorX;
    private double maiorY;
    private double maiorZ;
    private Ponto4D centro;
//    private Color color;


    /**
     * Construtor da BoundingBox que inicializa todos os pontos com 0 e cria um novo Ponto4D.
     */
    public BoundingBox() {
        this(0, 0, 0, 0, 0, 0);
        this.centro = new Ponto4D();
    }

    /**
     * Construtor da BoundingBox que recebe os tamanhos por parametro.
     * 
     * @param smallerX Menor X.
     * @param smallerY Menor Y.
     * @param smallerZ Menor Z.
     * @param greaterX Maior X.
     * @param greaterY Maior Y.
     * @param greaterZ Maior Z.
     */
    public BoundingBox(double smallerX, double smallerY, double smallerZ, double greaterX, double greaterY, double greaterZ) {
        this.menorX = smallerX;
        this.menorY = smallerY;
        this.menorZ = smallerZ;
        this.maiorX = greaterX;
        this.maiorY = greaterY;
        this.maiorZ = greaterZ;
        this.centro = new Ponto4D();
    }

    /**
     * Atribui todos os atributos de tamanho da BoundingBox e processa o centro da mesma.
     * 
     * @param smallerX Menor X.
     * @param smallerY Menor Y.
     * @param smallerZ Menor Z.
     * @param greaterX Maior X.
     * @param greaterY Maior Y.
     * @param greaterZ Maior Z.
     */
    public void atribuirBoundingBox(double smallerX, double smallerY, double smallerZ, double greaterX, double greaterY, double greaterZ) {
        this.menorX = smallerX;
        this.menorY = smallerY;
        this.menorZ = smallerZ;
        this.maiorX = greaterX;
        this.maiorY = greaterY;
        this.maiorZ = greaterZ;
        this.centro = new Ponto4D();
        processarCentroBBox();
    }
		
    /**
     * Atualiza a BoundingBox baseado em ponto.
     * 
     * @param point Ponto utilizado para atualizar as margens da BoundingBox.
     */
    public void atualizarBBox(Ponto4D point) {
        atualizarBBox(point.obterX(), point.obterY(), point.obterZ());
    }

    /**
     * Atualiza os mínimos e máximos de uma BoundingBox caso necessário.
     * 
     * @param x Valor do eixo X.
     * @param y Valor do eixo Y.
     * @param z Valor do eixo Z.
     */
    public void atualizarBBox(double x, double y, double z) {
        if (x < menorX)
            menorX = x;
        else {
            if (x > maiorX) maiorX = x;
        }
        if (y < menorY)
            menorY = y;
        else {
            if (y > maiorY) maiorY = y;
        }
        if (z < menorZ)
            menorZ = z;
        else {
            if (z > maiorZ) maiorZ = z;
        }
    }
	
    /**
     * Define o centro de uma BoundingBox.
     */
    public void processarCentroBBox() {
        centro.atribuirX((maiorX + menorX)/2);
        centro.atribuirY((maiorY + menorY)/2);
        centro.atribuirZ((maiorZ + menorZ)/2);
    }

    /**
     * Desenha a BoundingBox na tela.
     * 
     * @param gl OpenGL utilizado para densenhar a BoundingBox.
     */
    public void desenharOpenGLBBox(GL gl) {
        gl.glColor3f(1.0f, 0.0f, 0.0f);

        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3d(menorX, maiorY, menorZ);
            gl.glVertex3d(maiorX, maiorY, menorZ);
            gl.glVertex3d(maiorX, menorY, menorZ);
            gl.glVertex3d(menorX, menorY, menorZ);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3d(menorX, menorY, menorZ);
            gl.glVertex3d(menorX, menorY, maiorZ);
            gl.glVertex3d(menorX, maiorY, maiorZ);
            gl.glVertex3d(menorX, maiorY, menorZ);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3d(maiorX, maiorY, maiorZ);
            gl.glVertex3d(menorX, maiorY, maiorZ);
            gl.glVertex3d(menorX, menorY, maiorZ);
            gl.glVertex3d(maiorX, menorY, maiorZ);
        gl.glEnd();
        
        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3d(maiorX, menorY, menorZ);
            gl.glVertex3d(maiorX, maiorY, menorZ);
            gl.glVertex3d(maiorX, maiorY, maiorZ);
            gl.glVertex3d(maiorX, menorY, maiorZ);
        gl.glEnd();
    }

    /**
     * Obter menor valor X da BBox.
     * 
     * @return menor valor X da BBox.
     */
    public double obterMenorX() {
        return menorX;
    }

    /**
     * Obter menor valor Y da BBox.
     * 
     * @return menor valor Y da BBox.
     */
    public double obterMenorY() {
        return menorY;
    }

    /**
     * Obter menor valor Z da BBox.
     * 
     * @return menor valor Z da BBox.
     */
    public double obterMenorZ() {
        return menorZ;
    }

    /**
     * Obter maior valor X da BBox.
     * 
     * @return maior valor X da BBox.
     */
    public double obterMaiorX() {
        return maiorX;
    }

    /**
     * Obter maior valor Y da BBox.
     * 
     * @return maior valor Y da BBox.
     */
    public double obterMaiorY() {
        return maiorY;
    }

    /**
     * Obter maior valor Z da BBox.
     * 
     * @return maior valor Z da BBox.
     */
    public double obterMaiorZ() {
        return maiorZ;
    }

    /**
     * Obter ponto do centro da BBox.
     * 
     * @return ponto do centro da BBox.
     */
    public Ponto4D obterCentro() {
        return centro;
    }

}


package tonmat.geneticalg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class GeneticAlg extends ApplicationAdapter {
    private static final int POPULATION = 100;
    private int genes;
    private Population population;
    private ShapeRenderer shapeRenderer;

    private int geneW;
    private float geneSize;
    private boolean autoNextGeneration = false;

    private void print() {
        System.out.printf("gen %3d, fittest: %4.2f\n", population.generation, population.fittest.fitness);
    }

    private void createPopulation(int genes) {
        System.out.printf("[population created, %d gene(s)]\n", genes);
        population = new Population(POPULATION, genes);
        print();

        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();
        final float area = width * height;
        geneSize = (float) Math.sqrt(area / genes);
        final var geneH = (height / geneSize);
        geneW = MathUtils.ceil((float) genes / geneH);
        geneSize = width / geneW;
    }

    private void nextGeneration() {
        if (population.fittest.fitness < 1f) {
            population.nextGeneration();
            print();
        }
    }

    @Override
    public void create() {
        createPopulation(genes = 1);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            createPopulation(genes = 1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.N))
            createPopulation(++genes);
        if (Gdx.input.isKeyJustPressed(Input.Keys.G))
            autoNextGeneration = !autoNextGeneration;
        if (autoNextGeneration || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            nextGeneration();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        for (var i = 0; i < genes; i++) {
            final var x = geneSize * (i % geneW);
            final var y = geneSize * (i / geneW);
            shapeRenderer.rect(x, y, geneSize, geneSize);
        }
        for (var i = 0; i < genes; i++) {
            if (population.fittest.genes[i] == 0)
                shapeRenderer.setColor(Color.WHITE);
            else
                shapeRenderer.setColor(Color.BLUE);
            var x = geneSize * (i % geneW);
            var y = geneSize * (i / geneW);
            shapeRenderer.rect(x + 2, y + 2, geneSize - 4, geneSize - 4);
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}

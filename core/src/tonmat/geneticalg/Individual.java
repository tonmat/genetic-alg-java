package tonmat.geneticalg;

import com.badlogic.gdx.math.MathUtils;

public class Individual implements Comparable<Individual> {
    public final int[] genes;
    public float fitness;

    public Individual(int genes) {
        this.genes = new int[genes];
        randomizeGenes();
    }

    private void randomizeGenes() {
        for (var i = 0; i < genes.length; i++)
            genes[i] = MathUtils.random(0, 1);
        calculateFitness();
    }

    public void calculateFitness() {
        fitness = 0f;
        for (final float gene : genes)
            fitness += gene;
        fitness /= genes.length;
    }

    public void copyFrom(Individual source) {
        System.arraycopy(source.genes, 0, genes, 0, source.genes.length);
        fitness = source.fitness;
    }

    public void crossover(Individual other) {
        final var index = MathUtils.random(genes.length - 1);
        for (var i = 0; i < index; i++) {
            var temp = other.genes[i];
            other.genes[i] = genes[i];
            genes[i] = temp;
        }
    }

    public void mutate() {
        final var index = MathUtils.random(genes.length - 1);
        this.genes[index] = 1 - this.genes[index];
    }

    @Override
    public int compareTo(Individual o) {
        if (fitness > o.fitness)
            return -1;
        else if (fitness < o.fitness)
            return 1;
        return 0;
    }
}

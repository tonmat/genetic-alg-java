package tonmat.geneticalg;

import java.util.Arrays;

import com.badlogic.gdx.math.MathUtils;

public class Population {
    public final Individual[] individuals;
    public int generation;
    public Individual fittest;
    public Individual lessFit1;
    public Individual lessFit2;

    public Population(int count, int genes) {
        individuals = new Individual[count];
        generateIndividuals(genes);
    }

    private void generateIndividuals(int genes) {
        for (var i = 0; i < individuals.length; i++)
            individuals[i] = new Individual(genes);
        selection();
    }

    private void selection() {
        Arrays.sort(individuals);
        fittest = individuals[0];
        lessFit1 = individuals[individuals.length - 1];
        lessFit2 = individuals[individuals.length - 2];
    }

    public void nextGeneration() {
        generation++;

        lessFit1.copyFrom(individuals[0]);
        lessFit2.copyFrom(individuals[1]);

        lessFit1.crossover(lessFit2);

        if (MathUtils.randomBoolean(0.7f)) {
            lessFit1.mutate();
            lessFit2.mutate();
        }

        lessFit1.calculateFitness();
        lessFit2.calculateFitness();

        selection();
    }
}

package software.ulpgc.kata5.application;

import software.ulpgc.kata4.architecture.model.Movie;
import software.ulpgc.kata4.architecture.viewmodel.Histogram;
import software.ulpgc.kata4.architecture.viewmodel.HistogramBuilder;

import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    static void main() throws IOException {
        Desktop.creat().display(histogram()).setVisible(true);
    }

    private static Histogram histogram() throws IOException {
        return HistogramBuilder.with(movies()).title("Movies per decade").xAxis("Decade").yAxis("Count")
                .legend("Movies").use(Movie::years);
    }

    private static Stream<Movie> movies() throws IOException {
        return new RemoteStore(TsvMovieParser::from).movies().limit(1000);
    }
}

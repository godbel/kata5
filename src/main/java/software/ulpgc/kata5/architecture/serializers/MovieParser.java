package software.ulpgc.kata4.architecture.serializers;

import software.ulpgc.kata4.architecture.model.Movie;

public interface MovieParser {
    Movie from(String line);
}

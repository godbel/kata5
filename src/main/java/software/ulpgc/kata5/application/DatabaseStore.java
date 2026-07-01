package software.ulpgc.kata5.application;

import software.ulpgc.kata5.architecture.io.Store;
import software.ulpgc.kata5.architecture.model.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

public class DatabaseStore implements Store {

    private final Connection connection;

    public DatabaseStore(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stream<Movie> movies() throws IOException {
        try {
            return moviesIn(query());
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private Stream<Movie> moviesIn(ResultSet resultSet) {
        return Stream.generate(() -> moviesIn(resultSet).takeWhile(Objects::nonNull));
    }

    private ResultSet query() throws SQLException {
        return connection.createStatement().executeQuery("SELECT *" + "FROM MOVIES");

    }

    private Movie movieIn(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? readFrom(resultSet) : null;
    }

    private static Movie readFrom(ResultSet resultset) throws SQLException{
        return new Movie(resultset.getString(1), resultset.getInt(2), resultset.getInt(3));
    }
}
